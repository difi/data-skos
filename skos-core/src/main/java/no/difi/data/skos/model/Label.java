package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Label implements SkosGroup {

    private List<SkosValue> preferred;
    private List<SkosValue> alternative;
    private List<SkosValue> hidden;

    @Override
    public boolean isEmpty() {
        return preferred == null && alternative == null && hidden != null;
    }

    public void addPreferred(SkosValue preferred) {
        this.preferred = SkosValue.genericAdd(this.preferred, preferred);
    }

    public List<SkosValue> getPreferred() {
        return preferred == null ? new ArrayList<SkosValue>() : preferred;
    }

    @SuppressWarnings("unused")
    public void setPreferred(List<SkosValue> preferred) {
        this.preferred = preferred;
    }

    public void addAlternative(SkosValue alternative) {
        this.alternative = SkosValue.genericAdd(this.alternative, alternative);
    }

    public List<SkosValue> getAlternative() {
        return alternative == null ? new ArrayList<SkosValue>() : alternative;
    }

    @SuppressWarnings("unused")
    public void setAlternative(List<SkosValue> alternative) {
        this.alternative = alternative;
    }

    public void addHidden(SkosValue hidden) {
        this.hidden = SkosValue.genericAdd(this.hidden, hidden);
    }

    public List<SkosValue> getHidden() {
        return hidden == null ? new ArrayList<SkosValue>() : hidden;
    }

    @SuppressWarnings("unused")
    public void setHidden(List<SkosValue> hidden) {
        this.hidden = hidden;
    }
}
