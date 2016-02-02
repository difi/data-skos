package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Mapping implements SkosGroup {

    private List<String> mappingRelation;
    private List<String> closeMatch;
    private List<String> exactMatch;
    private List<String> broadMatch;
    private List<String> narrowMatch;
    private List<String> relatedMatch;

    @Override
    public boolean isEmpty() {
        return mappingRelation == null && closeMatch == null && exactMatch == null && broadMatch == null && narrowMatch == null && relatedMatch == null;
    }

    public void addMappingRelation(String mappingRelation) {
        this.mappingRelation = add(this.mappingRelation, mappingRelation);
    }

    public List<String> getMappingRelation() {
        return mappingRelation == null ? new ArrayList<String>() : mappingRelation;
    }

    @SuppressWarnings("unused")
    public void setMappingRelation(List<String> mappingRelation) {
        this.mappingRelation = mappingRelation;
    }

    public void addCloseMatch(String closeMatch) {
        this.closeMatch = add(this.closeMatch, closeMatch);
    }

    public List<String> getCloseMatch() {
        return closeMatch == null ? new ArrayList<String>() : closeMatch;
    }

    @SuppressWarnings("unused")
    public void setCloseMatch(List<String> closeMatch) {
        this.closeMatch = closeMatch;
    }

    public void addExactMatch(String exactMatch) {
        this.exactMatch = add(this.exactMatch, exactMatch);
    }

    public List<String> getExactMatch() {
        return exactMatch == null ? new ArrayList<String>() : exactMatch;
    }

    @SuppressWarnings("unused")
    public void setExactMatch(List<String> exactMatch) {
        this.exactMatch = exactMatch;
    }

    public void addBroadMatch(String broadMatch) {
        this.broadMatch = add(this.broadMatch, broadMatch);
    }

    public List<String> getBroadMatch() {
        return broadMatch == null ? new ArrayList<String>() : broadMatch;
    }

    @SuppressWarnings("unused")
    public void setBroadMatch(List<String> broadMatch) {
        this.broadMatch = broadMatch;
    }

    public void addNarrowMatch(String narrowMatch) {
        this.narrowMatch = add(this.narrowMatch, narrowMatch);
    }

    public List<String> getNarrowMatch() {
        return narrowMatch == null ? new ArrayList<String>() : narrowMatch;
    }

    @SuppressWarnings("unused")
    public void setNarrowMatch(List<String> narrowMatch) {
        this.narrowMatch = narrowMatch;
    }

    public void addRelatedMatch(String relatedMatch) {
        this.relatedMatch = add(this.relatedMatch, relatedMatch);
    }

    public List<String> getRelatedMatch() {
        return relatedMatch == null ? new ArrayList<String>() : relatedMatch;
    }

    @SuppressWarnings("unused")
    public void setRelatedMatch(List<String> relatedMatch) {
        this.relatedMatch = relatedMatch;
    }

    protected List<String> add(List<String> values, String value) {
        if (values == null)
            values = new ArrayList<>();

        if (!values.contains(value))
            values.add(value);

        return values;
    }
}
