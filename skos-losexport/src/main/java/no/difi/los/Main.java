package no.difi.los;

import no.difi.data.skos.model.Collection;
import no.difi.data.skos.model.Concept;
import no.difi.data.skos.model.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                        readAssosiasjon(reader, map);
                        break;
                }
            }
        }

        Representer representer = new MyRepresenter();
        representer.addClassTag(Collection.class, new Tag("!collection"));
        representer.addClassTag(Concept.class, new Tag("!concept"));
        representer.addClassTag(Config.class, new Tag("!config"));

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setCanonical(false);

        Yaml yaml = new Yaml(representer, options);
        // yaml.dump(node, new OutputStreamWriter(System.out));
        // yaml.dump(node.toConcept(), new OutputStreamWriter(System.out));

        for (String uri : map.keySet()) {
            Node n = map.get(uri);
            uri = uri.replace("http://psi.norge.no/los/", "");

            if (uri.startsWith("tema") || uri.startsWith("ord")) {
                if (uri.equals("tema/temastruktur"))
                    uri = "struktur";

                Path path = Paths.get("los/src/" + uri + ".yaml");
                Files.createDirectories(path.getParent());

                if (uri.equals("struktur")) {
                    yaml.dump(n.toCollection(), Files.newBufferedWriter(path));
                } else {
                    yaml.dump(n.toConcept(), Files.newBufferedWriter(path));
                }
            }
        }

        Config config = new Config();
        config.setRoot("http://psi.norge.no/los/");
        config.setName("Los");
        yaml.dump(config, Files.newBufferedWriter(Paths.get("los/config.yaml")));
    }

    static class MyRepresenter extends Representer {
        @Override
        protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
            if (propertyValue == null)
                return null;
            if (propertyValue instanceof List && ((List) propertyValue).isEmpty())
                return null;
            if (propertyValue instanceof Map && ((Map) propertyValue).isEmpty())
                return null;

            return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
        }
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

                    /*
                    default:
                        logger.info(reader.getLocalName());
                        break;
                        */
                }
            }
            else if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equalsIgnoreCase("emne"))
                break;
        }

        return node;
    }

    public static void readAssosiasjon(XMLStreamReader reader, Map<String, Node> map) throws Exception {
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
