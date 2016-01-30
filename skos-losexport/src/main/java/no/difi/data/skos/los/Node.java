package no.difi.data.skos.los;

import no.difi.data.skos.model.Collection;
import no.difi.data.skos.model.Concept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

    private static Logger logger = LoggerFactory.getLogger(Node.class);

    private String type;
    private String identifier;
    private Map<Language, String> title = new HashMap<>();
    private String description;
    private List<Association> associations = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Map<Language, String> getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Association> getAssociations() {
        return associations;
    }

    @Override
    public String toString() {
        return "Node{" +
                "type='" + type + '\'' +
                ", identifier='" + identifier + '\'' +
                ", title=" + title +
                ", description='" + description + '\'' +
                ", associations=" + associations +
                '}';
    }

    public Collection toCollection() {
        Collection collection = new Collection();

        for (Language language : title.keySet())
            collection.addLabel(language.name(), title.get(language));

        return collection;
    }

    public Concept toConcept() {
        Concept concept = new Concept();

        concept.setInScheme(getType().replace("http://psi.norge.no/los/", ""));
        for (Language language : title.keySet())
            concept.addLabel(language.name(), title.get(language));
        if (description != null)
            concept.addDefinition(Language.nn.name(), description);

        for (Association association : associations) {
            switch (association.getType()) {
                case "http://www.techquila.com/psi/thesaurus/#broader":
                    concept.addBroader(association.getReference().replace("http://psi.norge.no/los/", ""));
                    break;

                case "http://psi.norge.no/los/ontologi/se-ogsaa":
                    concept.addRelated(association.getReference().replace("http://psi.norge.no/los/", ""));
                    break;

                case "http://psi.norge.no/los/ontologi/se":
                    concept.addBroaderTransitive(association.getReference().replace("http://psi.norge.no/los/", ""));
                    break;

                case "http://www.techquila.com/psi/thesaurus/#narrower":
                case "http://psi.norge.no/los/ontologi/ikke-foretrukket":
                case "http://psi.norge.no/los/ontologi/ressurs":
                    // No action
                    break;

                default:
                    logger.info(association.getType());
            }

        }

        return concept;
    }
}
