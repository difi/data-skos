package no.difi.data.skos.los;

import no.difi.data.skos.model.Config;
import no.difi.data.skos.yaml.YamlInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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

        for (String uri : map.keySet()) {
            Node n = map.get(uri);
            uri = uri.replace("http://psi.norge.no/los/", "");

            if (uri.startsWith("tema") || uri.startsWith("ord")) {
                if (uri.equals("tema/temastruktur"))
                    uri = "struktur";

                Path path = Paths.get("los/src/" + uri + ".yaml");
                Files.createDirectories(path.getParent());

                if (uri.equals("struktur")) {
                    YamlInstance.getInstance().dump(n.toCollection(), Files.newBufferedWriter(path));
                } else {
                    YamlInstance.getInstance().dump(n.toConcept(), Files.newBufferedWriter(path));
                }
            }
        }

        Config config = new Config();
        config.setName("Los");
        config.setRoot("http://psi.norge.no/los/");
        YamlInstance.getInstance().dump(config, Files.newBufferedWriter(Paths.get("los/config.yaml")));
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
