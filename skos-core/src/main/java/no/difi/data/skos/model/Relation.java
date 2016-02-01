package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Relation implements SkosGroup {

    private List<String> semanticRelation;
    private List<String> related;
    private List<String> broader;
    private List<String> narrower;
    private List<String> broaderTransitive;
    private List<String> narrowerTransitive;

    @Override
    public boolean isEmpty() {
        return semanticRelation == null && related == null && broader == null && narrower == null && broaderTransitive == null && narrowerTransitive == null;
    }

    public void addSemanticRelation(String semanticRelation) {
        this.semanticRelation = add(this.semanticRelation, semanticRelation);
    }

    public List<String> getSemanticRelation() {
        return semanticRelation == null ? new ArrayList<String>() : semanticRelation;
    }

    @SuppressWarnings("unused")
    public void setSemanticRelation(List<String> semanticRelation) {
        this.semanticRelation = semanticRelation;
    }

    public void addBroader(String broader) {
        this.broader = add(this.broader, broader);
    }

    public List<String> getBroader() {
        return broader == null ? new ArrayList<String>() : broader;
    }

    @SuppressWarnings("unused")
    public void setBroader(List<String> broader) {
        this.broader = broader;
    }

    public void addNarrower(String narrower) {
        this.narrower = add(this.narrower, narrower);
    }

    public List<String> getNarrower() {
        return narrower == null ? new ArrayList<String>() : narrower;
    }

    @SuppressWarnings("unused")
    public void setNarrower(List<String> narrower) {
        this.narrower = narrower;
    }

    public void addBroaderTransitive(String broaderTransitive) {
        this.broaderTransitive = add(this.broaderTransitive, broaderTransitive);
    }

    public List<String> getBroaderTransitive() {
        return broaderTransitive == null ? new ArrayList<String>() : broaderTransitive;
    }

    @SuppressWarnings("unused")
    public void setBroaderTransitive(List<String> broaderTransitive) {
        this.broaderTransitive = broaderTransitive;
    }

    public void addNarrowerTransitive(String narrowerTransitive) {
        this.narrowerTransitive = add(this.narrowerTransitive, narrowerTransitive);
    }

    public List<String> getNarrowerTransitive() {
        return narrowerTransitive == null ? new ArrayList<String>() : narrowerTransitive;
    }

    @SuppressWarnings("unused")
    public void setNarrowerTransitive(List<String> narrowerTransitive) {
        this.narrowerTransitive = narrowerTransitive;
    }

    public void addRelated(String related) {
        this.related = add(this.related, related);
    }

    public List<String> getRelated() {
        return related == null ? new ArrayList<String>() : related;
    }

    @SuppressWarnings("unused")
    public void setRelated(List<String> related) {
        this.related = related;
    }

    protected List<String> add(List<String> values, String value) {
        if (values == null)
            values = new ArrayList<>();

        if (!values.contains(value))
            values.add(value);

        return values;
    }
}
