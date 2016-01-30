package no.difi.data.skos.builder;

import no.difi.data.skos.model.Collection;
import no.difi.data.skos.model.Concept;
import no.difi.data.skos.model.SkosObject;

import java.util.HashMap;

public class Objects extends HashMap<String, SkosObject> {

    public void populate() {
        for (String key : keySet()) {
            SkosObject object = get(key);

            if (object instanceof Collection)
                populateCollection(key, (Collection) object);
            else if (object instanceof Concept)
                populateConcept(key, (Concept) object);
        }
    }

    private void populateCollection(String key, Collection collection) {
        // Populate 'hasTopConcept'
        if (collection.getTopConceptOf() != null)
            for (String foreign : collection.getTopConceptOf())
                getConcept(foreign).setHasTopConcept(key);
    }

    private void populateConcept(String key, Concept concept) {
        // Populate 'narrower'
        if (concept.getBroader() != null)
            for (String foreign : concept.getBroader())
                getConcept(foreign).addNarrower(key);

        // Populate 'broader'
        if (concept.getNarrower() != null)
            for (String foreign : concept.getNarrower())
                getConcept(foreign).addBroader(key);

        // Populate 'narrowerTransitive'
        if (concept.getBroaderTransitive() != null)
            for (String foreign : concept.getBroaderTransitive())
                getConcept(foreign).addNarrowerTransitive(key);

        // Populate 'broaderTransitive'
        if (concept.getNarrowerTransitive() != null)
            for (String foreign : concept.getNarrowerTransitive())
                getConcept(foreign).addBroaderTransitive(key);

        // Populate 'related'
        if (concept.getRelated() != null)
            for (String foreign : concept.getRelated())
                getConcept(foreign).addRelated(key);

        // Populate 'topConceptOf'
        if (concept.getHasTopConcept() != null)
            getCollection(concept.getHasTopConcept()).addTopConceptOf(key);
    }

    public Collection getCollection(String key) {
        return (Collection) get(key);
    }

    public Concept getConcept(String key) {
        return (Concept) get(key);
    }
}