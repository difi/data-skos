package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Collection extends SkosObject {

    private List<String> topConceptOf;

    public void addTopConceptOf(String topConceptOf) {
        if (this.topConceptOf == null)
            this.topConceptOf = new ArrayList<>();

        if (!this.topConceptOf.contains(topConceptOf))
            this.topConceptOf.add(topConceptOf);
    }

    public List<String> getTopConceptOf() {
        return topConceptOf;
    }

    @SuppressWarnings("unused")
    public void setTopConceptOf(List<String> topConceptOf) {
        this.topConceptOf = topConceptOf;
    }
}
