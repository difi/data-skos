package no.difi.data.skos.los;

import no.difi.data.skos.model.*;
import no.difi.data.skos.yaml.YamlInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(Main.class.getResourceAsStream("/los-alt.xml"));

        Map<String, Node> map = new HashMap<>();

        while (reader.hasNext()) {
            reader.next();

            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch (reader.getLocalName()) {
                    case "emne":
                        Node node = readEmne(reader);
                        map.put(node.getIdentifier(), node);
                        break;
                    case "assosiasjon":
                        loadAssosiasjon(reader, map);
                        break;
                }
            }
        }

        Map<String, SkosObject> objects = new HashMap<>();

        for (String uri : map.keySet()) {
            Node n = map.get(uri);
            uri = uri.replace("http://psi.norge.no/los/", "");

            if (uri.startsWith("tema") || uri.startsWith("ord")) {
                if (uri.equals("tema/temastruktur"))
                    uri = "struktur";

                if (uri.equals("struktur")) {
                    objects.put(uri, n.toConceptScheme());
                } else {
                    objects.put(uri, n.toConcept());
                }
            }
        }

        List<String> uris = new ArrayList<>();

        for (String uri : objects.keySet()) {
            SkosObject o = objects.get(uri);
            if (o instanceof Concept && ((Concept) o).getScheme().getIn().contains("ontologi/hjelpeord")) {
                for (String foreign : ((Concept) o).getRelation().getBroaderTransitive())
                    for (SkosValue value : o.getLabel().getPreferred())
                        objects.get(foreign).getLabel().addHidden(value);
            } else {
                uris.add(uri);
            }
        }

        for (String uri : uris) {
            Path path = Paths.get("los/src/" + uri + ".yaml");
            Files.createDirectories(path.getParent());
            YamlInstance.getInstance().dump(objects.get(uri), Files.newBufferedWriter(path, StandardCharsets.UTF_8));
        }

        Files.createDirectories(Paths.get("los/src/ontologi"));
        addConceptScheme("tema", "Tema", "Tema");
        addConceptScheme("ord", "Ord", "Ord");

        Config config = new Config();
        config.setBaseUri("http://psi.norge.no/los/");
        config.setName("Los");
        config.addBuild("no.difi.data.skos.builder.build.RdfSingleBuild");
        config.addBuild("no.difi.data.skos.builder.build.RdfMultipleBuild");
        config.addBuild("no.difi.data.skos.builder.build.FreemarkerBuild");
        config.addOption("github", "repository", "https://github.com/difi/los");
        config.addOption("github", "issues", "https://github.com/difi/los/issues");
        YamlInstance.getInstance().dump(config, Files.newBufferedWriter(Paths.get("los/config.yaml"), StandardCharsets.UTF_8));
    }

    public static void addConceptScheme(String file, String labelNb, String labelNn) throws IOException {
        ConceptScheme ordConceptScheme = new ConceptScheme();
        ordConceptScheme.getLabel().addPreferred(new SkosValue(labelNb, "nb"));
        ordConceptScheme.getLabel().addPreferred(new SkosValue(labelNn, "nn"));
        YamlInstance.getInstance().dump(ordConceptScheme, Files.newBufferedWriter(Paths.get("los/src/ontologi/" + file + ".yaml"), StandardCharsets.UTF_8));
    }

    public static Node readEmne(XMLStreamReader reader) throws Exception {
        Node node = new Node();
        node.setType(reader.getAttributeValue(null, "type"));

        while (reader.hasNext()) {
            reader.next();

            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch (reader.getLocalName()) {
                    case "identifikator":
                        reader.next();
                        node.setIdentifier(reader.getText());
                        break;

                    case "tidsstempel":
                        break;

                    case "namn":
                        Language language = null;
                        if (reader.getAttributeValue("", "spraak") != null)
                            language = Language.getByAlterantive(reader.getAttributeValue("", "spraak").split("#")[1]);

                        reader.next();
                        node.getTitle().put(language, reader.getText());
                        break;

                    case "definisjon":
                        reader.next();
                        node.setDescription(reader.getText());
                        break;
                }
            }
            else if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equalsIgnoreCase("emne"))
                break;
        }

        return node;
    }

    public static void loadAssosiasjon(XMLStreamReader reader, Map<String, Node> map) throws Exception {
        Association[] associations = new Association[2];
        int counter = 0;

        while (reader.hasNext()) {
            reader.next();

            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equalsIgnoreCase("medlem")) {
                associations[counter] = new Association();
                for (int i = 0; i < reader.getAttributeCount(); i++) {
                    switch (reader.getAttributeLocalName(i)) {
                        case "type":
                            associations[counter].setType(reader.getAttributeValue(i));
                            break;
                        case "referanse":
                            associations[counter].setReference(reader.getAttributeValue(i));
                            break;
                    }
                }
                ++counter;
            } else if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equalsIgnoreCase("assosiasjon"))
                break;
        }

        try {
            map.get(associations[0].getReference()).getAssociations().add(associations[1]);
            map.get(associations[1].getReference()).getAssociations().add(associations[0]);
        } catch (Exception e) {
            logger.info("{}, {}", associations[0], associations[1]);
        }
    }
}
