package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Concept extends SkosObject {

    private String hasTopConcept;
    private List<String> inScheme;

    private Relation relation = new Relation();

    public String getHasTopConcept() {
        return hasTopConcept;
    }

    public void setHasTopConcept(String hasTopConcept) {
        this.hasTopConcept = hasTopConcept;
    }

    public void addInScheme(String inScheme) {
        if (this.inScheme == null)
            this.inScheme = new ArrayList<>();

        if (!this.inScheme.contains(inScheme))
            this.inScheme.add(inScheme);
    }

    public List<String> getInScheme() {
        return inScheme;
    }

    @SuppressWarnings("unused")
    public void setInScheme(List<String> inScheme) {
        this.inScheme = inScheme;
    }

    public Relation getRelation() {
        return relation;
    }

    @SuppressWarnings("unused")
    public void setRelation(Relation relation) {
        this.relation = relation;
    }
}
