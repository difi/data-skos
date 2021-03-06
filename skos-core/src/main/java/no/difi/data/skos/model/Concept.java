package no.difi.data.skos.model;

public class Concept extends SkosObject {

    private Relation relation = new Relation();
    private Scheme scheme = new Scheme();
    private Mapping mapping = new Mapping();

    public Relation getRelation() {
        return relation;
    }

    @SuppressWarnings("unused")
    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Scheme getScheme() {
        return scheme;
    }

    @SuppressWarnings("unused")
    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public Mapping getMapping() {
        return mapping;
    }

    @SuppressWarnings("unused")
    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }
}
