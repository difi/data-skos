package no.difi.data.skos.los;

import no.difi.data.skos.model.Concept;
import no.difi.data.skos.model.ConceptScheme;
import no.difi.data.skos.model.SkosValue;
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

    public ConceptScheme toConceptScheme() {
        ConceptScheme conceptScheme = new ConceptScheme();

        for (Language language : title.keySet())
            conceptScheme.getLabel().addPreferred(new SkosValue(title.get(language), language.name()));

        return conceptScheme;
    }

    public Concept toConcept() {
        Concept concept = new Concept();

        for (Language language : title.keySet()) {
            concept.getLabel().addPreferred(new SkosValue(title.get(language), language.name()));
        }
        if (description != null) {
            concept.getDocumentation().addDefinition(new SkosValue(description, Language.nn.name()));
        }

        for (Association association : associations) {
            switch (association.getType()) {
                case "http://www.techquila.com/psi/thesaurus/#broader":
                    if (association.getReference().endsWith("tema/temastruktur"))
                        concept.getScheme().addTopOf("struktur");
                    else
                        concept.getRelation().addBroader(association.getReference().replace("http://psi.norge.no/los/", ""));
                    break;

                case "http://psi.norge.no/los/ontologi/se-ogsaa":
                    concept.getRelation().addRelated(association.getReference().replace("http://psi.norge.no/los/", ""));
                    break;

                case "http://psi.norge.no/los/ontologi/se":
                    concept.getRelation().addBroaderTransitive(association.getReference().replace("http://psi.norge.no/los/", ""));
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

        concept.getScheme().addIn(getType().replace("http://psi.norge.no/los/", ""));
        switch (concept.getScheme().getIn().get(0)) {
            case "ontologi/ord":
                if (concept.getRelation().getBroader().size() == 0)
                    concept.getScheme().addIn("ontologi/hjelpeord");
                else
                    concept.getScheme().addIn("ontologi/emneord");
                break;
        }

        return concept;
    }
}
