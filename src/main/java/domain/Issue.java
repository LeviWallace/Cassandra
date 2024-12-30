package domain;

import domain.source.SourceObject;

public class Issue {
    private final String description;
    private final SourceObject source;

    public Issue(String description, SourceObject source) {
        this.description = description;
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public SourceObject getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Issue: " + description;
    }
}
