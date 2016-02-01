package no.difi.data.skos.model;

public abstract class SkosObject {

    private Label label = new Label();
    private Documentation documentation = new Documentation();

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
}
