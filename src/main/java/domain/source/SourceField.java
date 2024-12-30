package domain.source;

public interface SourceField extends SourceObject {
    SourceClass getAssociatedClass();
    SourceClass getType();
    boolean isStatic();
    boolean isFinal();
}
