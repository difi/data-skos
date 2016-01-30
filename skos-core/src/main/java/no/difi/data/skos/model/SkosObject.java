package no.difi.data.skos.model;

import java.util.HashMap;
import java.util.Map;

public abstract class SkosObject {

    private Map<String, String> label;

    public void addLabel(String language, String label) {
        if (this.label == null)
            this.label = new HashMap<>();

        this.label.put(language, label);
    }

    public Map<String, String> getLabel() {
        return label;
    }

    @SuppressWarnings("unused")
    public void setLabel(Map<String, String> label) {
        this.label = label;
    }
}
