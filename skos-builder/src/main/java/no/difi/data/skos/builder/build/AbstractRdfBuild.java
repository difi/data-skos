package no.difi.data.skos.builder.build;

import no.difi.data.skos.builder.api.Build;
import no.difi.data.skos.model.Collection;
import no.difi.data.skos.model.Concept;
import no.difi.data.skos.model.Config;
import no.difi.data.skos.model.SkosObject;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.SKOS;

abstract class AbstractRdfBuild implements Build {

    protected void createForObject(Config config, String key, SkosObject object, Model model) {
        Resource resource = model.createResource(config.getRoot() + key, SKOS.Concept);

        for (String language : object.getLabel().keySet())
            resource.addProperty(SKOS.prefLabel, object.getLabel().get(language), language);

        if (object instanceof Concept)
            createForConcept(config, (Concept) object, model, resource);
        else if (object instanceof Collection)
            createForCollection(config, (Collection) object, model, resource);
    }

    protected void createForConcept(Config config, Concept concept, Model model, Resource resource) {
        // definition
        for (String language : concept.getDefinition().keySet())
            resource.addProperty(SKOS.prefLabel, concept.getDefinition().get(language), language);

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

    protected void createForCollection(Config config, Collection collection, Model model, Resource resource) {
        // topConceptOf
        if (collection.getTopConceptOf() != null)
            for (String foreign : collection.getTopConceptOf())
                resource.addProperty(SKOS.topConceptOf, model.createResource(config.getRoot() + foreign));
    }

}
