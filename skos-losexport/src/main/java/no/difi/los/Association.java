package no.difi.los;

public class Association {

    private String type;
    private String reference;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Association{" +
                "type='" + type + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
