package no.difi.data.skos.model;

import java.util.HashMap;
import java.util.Map;

abstract class SkosObject {

    private Map<String, String> label = new HashMap<>();

    public Map<String, String> getLabel() {
        return label;
    }

    public void setLabel(Map<String, String> label) {
        this.label = label;
    }
}
