package domain.source;

public interface SourceObject {
    FileReference getFileReference();
    String getName();

    AccessModifier getAccess();
}
