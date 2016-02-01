package no.difi.data.skos.model;

import java.util.ArrayList;
import java.util.List;

public class Documentation implements SkosGroup {

    private List<SkosValue> note;
    private List<SkosValue> changeNote;
    private List<SkosValue> definition;
    private List<SkosValue> editorialNote;
    private List<SkosValue> example;
    private List<SkosValue> historyNote;
    private List<SkosValue> scopeNote;

    @Override
    public boolean isEmpty() {
        return note == null && changeNote == null && definition == null && editorialNote == null && example == null && historyNote == null && scopeNote == null;
    }

    public void addNote(SkosValue note) {
        this.note = SkosValue.genericAdd(this.note, note);
    }

    public List<SkosValue> getNote() {
        return note == null ? new ArrayList<SkosValue>() : note;
    }

    @SuppressWarnings("unused")
    public void setNote(List<SkosValue> note) {
        this.note = note;
    }

    public void addChangeNote(SkosValue changeNote) {
        this.changeNote = SkosValue.genericAdd(this.changeNote, changeNote);
    }

    public List<SkosValue> getChangeNote() {
        return changeNote == null ? new ArrayList<SkosValue>() : changeNote;
    }

    @SuppressWarnings("unused")
    public void setChangeNote(List<SkosValue> changeNote) {
        this.changeNote = changeNote;
    }

    public void addDefinition(SkosValue definition) {
        this.definition = SkosValue.genericAdd(this.definition, definition);
    }

    public List<SkosValue> getDefinition() {
        return definition == null ? new ArrayList<SkosValue>() : definition;
    }

    @SuppressWarnings("unused")
    public void setDefinition(List<SkosValue> definition) {
        this.definition = definition;
    }

    public void addEditorialNote(SkosValue editorialNote) {
        this.editorialNote = SkosValue.genericAdd(this.editorialNote, editorialNote);
    }

    public List<SkosValue> getEditorialNote() {
        return editorialNote == null ? new ArrayList<SkosValue>() : editorialNote;
    }

    @SuppressWarnings("unused")
    public void setEditorialNote(List<SkosValue> editorialNote) {
        this.editorialNote = editorialNote;
    }

    public void addExample(SkosValue example) {
        this.example = SkosValue.genericAdd(this.example, example);
    }

    public List<SkosValue> getExample() {
        return example == null ? new ArrayList<SkosValue>() : example;
    }

    @SuppressWarnings("unused")
    public void setExample(List<SkosValue> example) {
        this.example = example;
    }

    public void addHistoryNote(SkosValue historyNote) {
        this.historyNote = SkosValue.genericAdd(this.historyNote, historyNote);
    }

    public List<SkosValue> getHistoryNote() {
        return historyNote == null ? new ArrayList<SkosValue>() : historyNote;
    }

    @SuppressWarnings("unused")
    public void setHistoryNote(List<SkosValue> historyNote) {
        this.historyNote = historyNote;
    }

    public void addScopeNote(SkosValue scopeNote) {
        this.scopeNote = SkosValue.genericAdd(this.scopeNote, scopeNote);
    }

    public List<SkosValue> getScopeNote() {
        return scopeNote == null ? new ArrayList<SkosValue>() : scopeNote;
    }

    @SuppressWarnings("unused")
    public void setScopeNote(List<SkosValue> scopeNote) {
        this.scopeNote = scopeNote;
    }
}
