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
            createForConcept(config, (Concept) object, model, resource);
        } else if (object instanceof Collection) {
            Resource resource = model.createResource(config.getRoot() + key, SKOS.Collection);
            createForCollection(config, (Collection) object, model, resource);
        } else if (object instanceof ConceptScheme) {
            Resource resource = model.createResource(config.getRoot() + key, SKOS.ConceptScheme);
            createForConceptScheme(config, (ConceptScheme) object, model, resource);
        }
    }

    protected void createForCollection(Config config, Collection collection, Model model, Resource resource) {
        createForLabel(collection.getLabel(), resource);
    }

    protected void createForConcept(Config config, Concept concept, Model model, Resource resource) {
        createForLabel(concept.getLabel(), resource);
        createForDocumentation(concept.getDocumentation(), resource);
        createForRelation(config, concept.getRelation(), model, resource);

        // inScheme
        if (concept.getInScheme() != null)
            for (String foreign : concept.getInScheme())
                resource.addProperty(SKOS.inScheme, model.createResource(config.getRoot() + foreign));

        // hasTopConcept
        if (concept.getHasTopConcept() != null)
            resource.addProperty(SKOS.hasTopConcept, model.createResource(config.getRoot() + concept.getHasTopConcept()));
    }

    protected void createForConceptScheme(Config config, ConceptScheme conceptScheme, Model model, Resource resource) {

    }

    protected void createForLabel(Label label, Resource resource) {
        // prefLabel
        for (SkosValue value : label.getPreferred())
            resource.addProperty(SKOS.prefLabel, value.getValue(), value.getLanguage());

        // altLabel
        for (SkosValue value : label.getAlternative())
            resource.addProperty(SKOS.altLabel, value.getValue(), value.getLanguage());

        // hiddenLabel
        for (SkosValue value : label.getHidden())
            resource.addProperty(SKOS.hiddenLabel, value.getValue(), value.getLanguage());
    }

    protected void createForDocumentation(Documentation documentation, Resource resource) {
        // note
        for (SkosValue value : documentation.getNote())
            resource.addProperty(SKOS.note, value.getValue(), value.getLanguage());

        // changeNote
        for (SkosValue value : documentation.getChangeNote())
            resource.addProperty(SKOS.changeNote, value.getValue(), value.getLanguage());

        // definition
        for (SkosValue value : documentation.getDefinition())
            resource.addProperty(SKOS.definition, value.getValue(), value.getLanguage());

        // editorialNote
        for (SkosValue value : documentation.getEditorialNote())
            resource.addProperty(SKOS.editorialNote, value.getValue(), value.getLanguage());

        // example
        for (SkosValue value : documentation.getExample())
            resource.addProperty(SKOS.example, value.getValue(), value.getLanguage());

        // historyNote
        for (SkosValue value : documentation.getHistoryNote())
            resource.addProperty(SKOS.historyNote, value.getValue(), value.getLanguage());

        // scopeNote
        for (SkosValue value : documentation.getScopeNote())
            resource.addProperty(SKOS.scopeNote, value.getValue(), value.getLanguage());
    }

    protected void createForRelation(Config config, Relation relation, Model model, Resource resource) {
        // semanticRelation
        for (String foreign : relation.getSemanticRelation())
            resource.addProperty(SKOS.semanticRelation, model.createResource(config.getRoot() + foreign));

        // broader
        for (String foreign : relation.getBroader())
            resource.addProperty(SKOS.broader, model.createResource(config.getRoot() + foreign));

        // narrower
        for (String foreign : relation.getNarrower())
            resource.addProperty(SKOS.narrower, model.createResource(config.getRoot() + foreign));

        // broaderTransitive
        for (String foreign : relation.getBroaderTransitive())
            resource.addProperty(SKOS.broaderTransitive, model.createResource(config.getRoot() + foreign));

        // narrowerTransitive
        for (String foreign : relation.getNarrowerTransitive())
            resource.addProperty(SKOS.narrowerTransitive, model.createResource(config.getRoot() + foreign));

        // related
        for (String foreign : relation.getRelated())
            resource.addProperty(SKOS.related, model.createResource(config.getRoot() + foreign));
    }

}
