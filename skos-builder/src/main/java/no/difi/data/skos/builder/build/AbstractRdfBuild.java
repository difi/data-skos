package no.difi.data.skos.builder.build;

import no.difi.data.skos.builder.api.Build;
import no.difi.data.skos.model.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.SKOS;

abstract class AbstractRdfBuild implements Build {

    protected void createForObject(Config config, String key, SkosObject object, Model model) {
        if (object instanceof Concept) {
            Resource resource = model.createResource(config.getRoot() + key, SKOS.Concept);
            createForGeneric(object, resource);
            createForConcept(config, (Concept) object, model, resource);
        } else if (object instanceof Collection) {
            Resource resource = model.createResource(config.getRoot() + key, SKOS.Collection);
            createForGeneric(object, resource);
            createForCollection(config, (Collection) object, model, resource);
        } else if (object instanceof ConceptScheme) {
            Resource resource = model.createResource(config.getRoot() + key, SKOS.ConceptScheme);
            createForGeneric(object, resource);
            createForConceptScheme(config, (ConceptScheme) object, model, resource);
        }
    }

    protected void createForGeneric(SkosObject object, Resource resource) {
        // prefLabel
        for (String language : object.getLabel().keySet())
            resource.addProperty(SKOS.prefLabel, object.getLabel().get(language), language);
    }

    protected void createForCollection(Config config, Collection collection, Model model, Resource resource) {
        // topConceptOf
        if (collection.getTopConceptOf() != null)
            for (String foreign : collection.getTopConceptOf())
                resource.addProperty(SKOS.topConceptOf, model.createResource(config.getRoot() + foreign));
    }

    protected void createForConcept(Config config, Concept concept, Model model, Resource resource) {
        // inScheme
        if (concept.getInScheme() != null)
            for (String foreign : concept.getInScheme())
                resource.addProperty(SKOS.inScheme, model.createResource(config.getRoot() + foreign));

        // definition
        if (concept.getDefinition() != null)
            for (String language : concept.getDefinition().keySet())
                resource.addProperty(SKOS.definition, concept.getDefinition().get(language), language);

        // broader
        if (concept.getBroader() != null)
            for (String foreign : concept.getBroader())
                resource.addProperty(SKOS.broader, model.createResource(config.getRoot() + foreign));

        // narrower
        if (concept.getNarrower() != null)
            for (String foreign : concept.getNarrower())
                resource.addProperty(SKOS.narrower, model.createResource(config.getRoot() + foreign));

        // broaderTransitive
        if (concept.getBroaderTransitive() != null)
            for (String foreign : concept.getBroaderTransitive())
                resource.addProperty(SKOS.broaderTransitive, model.createResource(config.getRoot() + foreign));

        // broaderTransitive
        if (concept.getNarrowerTransitive() != null)
            for (String foreign : concept.getNarrowerTransitive())
                resource.addProperty(SKOS.narrowerTransitive, model.createResource(config.getRoot() + foreign));

        // related
        if (concept.getRelated() != null)
            for (String foreign : concept.getRelated())
                resource.addProperty(SKOS.related, model.createResource(config.getRoot() + foreign));

        // hasTopConcept
        if (concept.getHasTopConcept() != null)
            resource.addProperty(SKOS.hasTopConcept, model.createResource(config.getRoot() + concept.getHasTopConcept()));
    }

    protected void createForConceptScheme(Config config, ConceptScheme conceptScheme, Model model, Resource resource) {

    }
}
