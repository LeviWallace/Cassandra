package domain.source;

public class FileReference {
    private final String filename;
    private final int lineNumber;

    public FileReference(String filename) {
        this(filename, 0);
    }

    public FileReference(String filename, int lineNumber) {
        this.filename = filename;
        this.lineNumber = lineNumber;
    }

    public String getFilename() {
        return filename;
    }

    public boolean hasLineNumber() {
        return lineNumber != 0;
    }
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return this.filename + ":" + lineNumber;
    }
}
