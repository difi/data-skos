package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Collection extends SkosObject {

    private List<String> topConceptOf = new ArrayList<>();

    public List<String> getTopConceptOf() {
        return topConceptOf;
    }

    public void setTopConceptOf(List<String> topConceptOf) {
        this.topConceptOf = topConceptOf;
    }
}
