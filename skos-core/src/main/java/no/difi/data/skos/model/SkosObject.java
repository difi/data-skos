package no.difi.data.skos.model;

import java.util.HashMap;
import java.util.Map;

public abstract class SkosObject {

    private Map<String, String> label;

    private Map<String, String> definition;

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

    public void addDefinition(String language, String definition) {
        if (this.definition == null)
            this.definition = new HashMap<>();

        this.definition.put(language, definition);
    }

    public Map<String, String> getDefinition() {
        return definition;
    }

    @SuppressWarnings("unused")
    public void setDefinition(Map<String, String> definition) {
        this.definition = definition;
    }
}
