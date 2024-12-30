package domain.source;

import java.util.List;

public interface SourceClass extends SourceObject {
    List<SourceField> getFields();
    List<SourceMethod> getMethods();
    boolean isAbstract();
    boolean isInterface();
    SourceClass getSuperClass();
    Iterable<SourceClass> getSubClasses();
}
