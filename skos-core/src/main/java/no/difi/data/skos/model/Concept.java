package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Concept extends SkosObject {

    private List<String> broader;
    private List<String> narrower;

    private List<String> broaderTransitive;
    private List<String> narrowerTransitive;

    private List<String> related;

    private String hasTopConcept;
    private List<String> inScheme;

    public void addBroader(String broader) {
        if (this.broader == null)
            this.broader = new ArrayList<>();

        if (!this.broader.contains(broader))
            this.broader.add(broader);
    }

    public List<String> getBroader() {
        return broader;
    }

    @SuppressWarnings("unused")
    public void setBroader(List<String> broader) {
        this.broader = broader;
    }

    public void addNarrower(String narrower) {
        if (this.narrower == null)
            this.narrower = new ArrayList<>();

        if (!this.narrower.contains(narrower))
            this.narrower.add(narrower);
    }

    public List<String> getNarrower() {
        return narrower;
    }

    @SuppressWarnings("unused")
    public void setNarrower(List<String> narrower) {
        this.narrower = narrower;
    }

    public void addBroaderTransitive(String broaderTransitive) {
        if (this.broaderTransitive == null)
            this.broaderTransitive = new ArrayList<>();

        if (!this.broaderTransitive.contains(broaderTransitive))
            this.broaderTransitive.add(broaderTransitive);
    }

    public List<String> getBroaderTransitive() {
        return broaderTransitive;
    }

    @SuppressWarnings("unused")
    public void setBroaderTransitive(List<String> broaderTransitive) {
        this.broaderTransitive = broaderTransitive;
    }

    public void addNarrowerTransitive(String narrowerTransitive) {
        if (this.narrowerTransitive == null)
            this.narrowerTransitive = new ArrayList<>();

        if (!this.narrowerTransitive.contains(narrowerTransitive))
            this.narrowerTransitive.add(narrowerTransitive);
    }

    public List<String> getNarrowerTransitive() {
        return narrowerTransitive;
    }

    @SuppressWarnings("unused")
    public void setNarrowerTransitive(List<String> narrowerTransitive) {
        this.narrowerTransitive = narrowerTransitive;
    }

    public void addRelated(String related) {
        if (this.related == null)
            this.related = new ArrayList<>();

        if (!this.related.contains(related))
            this.related.add(related);
    }

    public List<String> getRelated() {
        return related;
    }

    @SuppressWarnings("unused")
    public void setRelated(List<String> related) {
        this.related = related;
    }

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
}
