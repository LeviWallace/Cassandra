package domain.check;

import domain.Issue;
import org.junit.jupiter.api.Assertions;

public class TestIssue {
    private final String description;
    private final String sourceName;
    private final String fileName;
    private final int lineNumber;

    public TestIssue(String description, String sourceName, String fileName) {
        this(description, sourceName, fileName, 0);
    }

    public TestIssue(String description, String sourceName, String fileName, int lineNumber) {
        this.description = description;
        this.sourceName = sourceName;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }

    public void verify(Issue issue) {
        Assertions.assertEquals(description, issue.getDescription());
        Assertions.assertEquals(sourceName, issue.getSource().getName());
        Assertions.assertEquals(fileName, issue.getSource().getFileReference().getFilename());
        Assertions.assertEquals(lineNumber, issue.getSource().getFileReference().getLineNumber());
    }
}
