package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Scheme implements SkosGroup {

    private List<String> in;
    private List<String> hasTop;
    private List<String> topOf;

    @Override
    public boolean isEmpty() {
        return in == null && hasTop == null && topOf == null;
    }

    public void addIn(String in) {
        this.in = add(this.in, in);
    }

    public List<String> getIn() {
        return in == null ? new ArrayList<String>() : in;
    }

    @SuppressWarnings("unused")
    public void setIn(List<String> in) {
        this.in = in;
    }

    public void addHasTop(String hasTop) {
        this.hasTop = add(this.hasTop, hasTop);
    }

    public List<String> getHasTop() {
        return hasTop == null ? new ArrayList<String>() : hasTop;
    }

    @SuppressWarnings("unused")
    public void setHasTop(List<String> hasTop) {
        this.hasTop = hasTop;
    }

    public void addTopOf(String topOf) {
        this.topOf = add(this.topOf, topOf);
    }

    public List<String> getTopOf() {
        return topOf == null ? new ArrayList<String>() : topOf;
    }

    @SuppressWarnings("unused")
    public void setTopOf(List<String> topOf) {
        this.topOf = topOf;
    }

    protected List<String> add(List<String> values, String value) {
        if (values == null)
            values = new ArrayList<>();

        if (!values.contains(value))
            values.add(value);

        return values;
    }
}
