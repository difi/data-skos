package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public abstract class SkosObject {

    private Label label = new Label();
    private Documentation documentation = new Documentation();

    private List<String> notation;

    public Label getLabel() {
        return label;
    }

    @SuppressWarnings("unused")
    public void setLabel(Label label) {
        this.label = label;
    }


    public Documentation getDocumentation() {
        return documentation;
    }

    @SuppressWarnings("unused")
    public void setDocumentation(Documentation documentation) {
        this.documentation = documentation;
    }

    public void addNotation(String notation) {
        if (this.notation == null)
            this.notation = new ArrayList<>();

        if (!this.notation.contains(notation))
            this.notation.add(notation);
    }

    public List<String> getNotation() {
        return notation;
    }

    @SuppressWarnings("unused")
    public void setNotation(List<String> notation) {
        this.notation = notation;
    }
}
