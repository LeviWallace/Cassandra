package domain.source;

import java.util.List;

public interface SourceMethod extends SourceObject {
    SourceClass getAssociatedClass();
    List<SourceMethod> getCalledMethods();
    List<SourceLocalVariable> getLocalVariables();
    SourceClass getReturnType();
    boolean isAbstract();
    boolean isStatic();
    boolean isFinal();

}
