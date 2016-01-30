package no.difi.data.skos.builder;

import no.difi.data.skos.model.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        Workspace workspace = new Workspace(Paths.get("los"));
        workspace.cleanTarget();

        Config config = workspace.getConfig();
        logger.info("Project \"{}\".", config.getName());

        Objects objects = workspace.getObjects();
        logger.info("Found {} objects.", objects.size());

        objects.populate();

        /*
        Node node = map.get("http://psi.norge.no/los/ord/adopsjon");

        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("skos", "http://www.w3.org/2004/02/skos/core#");

        Resource resource = model.createResource(node.getIdentifier(), SKOS.Concept);
        for (Language language : node.getTitle().keySet())
            resource.addProperty(SKOS.prefLabel, node.getTitle().get(language), language.name());
        if (node.getDescription() != null)
            resource.addProperty(SKOS.definition, node.getDescription());

        for (Association association : node.getAssociations()) {
            switch (association.getType()) {
                case "http://www.techquila.com/psi/thesaurus/#broader":
                    resource.addProperty(SKOS.broader, model.createResource(association.getReference()));
                    break;
                case "http://www.techquila.com/psi/thesaurus/#narrower":
                    resource.addProperty(SKOS.narrower, model.createResource(association.getReference()));
                    // resource.addProperty(SKOS.topConceptOf, model.createResource(association.getReference()));
                    break;

                case "http://psi.norge.no/los/ontologi/se-ogsaa":
                    resource.addProperty(SKOS.related, model.createResource(association.getReference()));
                    break;

                case "http://psi.norge.no/los/ontologi/ikke-foretrukket":
                    resource.addProperty(SKOS.narrowerTransitive, model.createResource(association.getReference()));
                    break;

                case "http://psi.norge.no/los/ontologi/ressurs":
                    // No action
                    break;

                default:
                    logger.info(association.getType());
            }
        }

        model.write(System.out);
        */
    }
}
