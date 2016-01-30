package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Concept extends SkosObject {

    private List<String> broader;
    private List<String> narrower;

    private List<String> broaderTransitive = new ArrayList<>();
    private List<String> narrowerTransitive = new ArrayList<>();

    private List<String> related;

    private Map<String, String> definition = new HashMap<>();

    private String hasTopConcept;

    public void addBroader(String broader) {
        if (broader.equals("tema/temastruktur")) {
            setHasTopConcept("struktur");
            return;
        }

        if (this.broader == null)
            this.broader = new ArrayList<>();

        if (!this.broader.contains(broader))
            this.broader.add(broader);
    }

    public List<String> getBroader() {
        return broader;
    }

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

    public void setRelated(List<String> related) {
        this.related = related;
    }

    public Map<String, String> getDefinition() {
        return definition;
    }

    public void setDefinition(Map<String, String> definition) {
        this.definition = definition;
    }

    public String getHasTopConcept() {
        return hasTopConcept;
    }

    public void setHasTopConcept(String hasTopConcept) {
        this.hasTopConcept = hasTopConcept;
    }
}
