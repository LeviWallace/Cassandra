package presentation;

import domain.Issue;
import domain.source.SourceObject;

public class IssueFormatter {

    private final StringBuilder sb;
    private final Issue issue;

    public IssueFormatter(Issue issue) {
        this.sb = new StringBuilder();
        this.issue = issue;
        format();
    }

    private void format() {
        sb.append("ISSUE\n");
        SourceObject source = issue.getSource();
        sb.append("Name: ");
        sb.append(source.getName());
        sb.append("\n");

        sb.append("File Name: ");
        sb.append(source.getFileReference().getFilename());
        sb.append("\n");

        if (source.getFileReference().hasLineNumber()) {
            sb.append("Line Number: ");
            sb.append(source.getFileReference().getLineNumber());
            sb.append("\n");
        }

        sb.append("Issue: ");
        sb.append(issue.getDescription());
        sb.append("\n");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
