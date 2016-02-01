package no.difi.data.skos.builder;

import no.difi.data.skos.model.Collection;
import no.difi.data.skos.model.Concept;
import no.difi.data.skos.model.ConceptScheme;
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

    }

    private void populateConcept(String key, Concept concept) {
        // Populate 'narrower'
        for (String foreign : concept.getRelation().getBroader())
            getConcept(foreign).getRelation().addNarrower(key);

        // Populate 'broader'
        for (String foreign : concept.getRelation().getNarrower())
            getConcept(foreign).getRelation().addBroader(key);

        // Populate 'narrowerTransitive'
        for (String foreign : concept.getRelation().getBroaderTransitive())
            getConcept(foreign).getRelation().addNarrowerTransitive(key);

        // Populate 'broaderTransitive'
        for (String foreign : concept.getRelation().getNarrowerTransitive())
            getConcept(foreign).getRelation().addBroaderTransitive(key);

        // Populate 'related'
        for (String foreign : concept.getRelation().getRelated())
            getConcept(foreign).getRelation().addRelated(key);

        // Populate 'topConceptOf'
        if (concept.getHasTopConcept() != null)
            getConceptScheme(concept.getHasTopConcept()).addTopConceptOf(key);

        // Populate 'hasTopConcept'
        // if (concept.getInScheme() != null)
        //    getConcept(concept.getInScheme()).setHasTopConcept(key);
    }

    public Collection getCollection(String key) {
        return (Collection) get(key);
    }

    public Concept getConcept(String key) {
        return (Concept) get(key);
    }

    public ConceptScheme getConceptScheme(String key) {
        return (ConceptScheme) get(key);
    }
}