package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    private List<String> build;
    private String root;
    private String name;

    private Map<String, Options> options;

    public void addBuild(String build) {
        if (this.build == null)
            this.build = new ArrayList<>();

        if (!this.build.contains(build))
            this.build.add(build);
    }

    public List<String> getBuild() {
        return build;
    }

    @SuppressWarnings("unused")
    public void setBuild(List<String> build) {
        this.build = build;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addOption(String group, String key, String value) {
        if (this.options == null)
            this.options = new HashMap<>();

        if (!this.options.containsKey(group))
            this.options.put(group, new Options());

        this.options.get(group).put(key, value);
    }

    public Map<String, Options> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Options> options) {
        this.options = options;
    }
}
