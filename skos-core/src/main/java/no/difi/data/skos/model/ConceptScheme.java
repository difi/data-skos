package no.difi.data.skos.model;

public class ConceptScheme extends SkosObject {

    private Scheme scheme = new Scheme();

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }
}
