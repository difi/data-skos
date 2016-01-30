package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private List<String> build;
    private String root;
    private String name;

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
}
