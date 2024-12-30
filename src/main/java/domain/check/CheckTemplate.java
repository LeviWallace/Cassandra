package domain.check;

import domain.Issue;
import domain.source.SourceMethod;

import java.util.List;

public class CheckTemplate extends MethodChecker {
    static final String MESSAGE = "Method is a template method";

    @Override
    protected List<Issue> checkMethod(SourceMethod object) {
        if (object.isAbstract() || !object.isFinal() || object.isStatic()) {
            return List.of();
        }

        int abstractCalls = 0;
        for (SourceMethod calledMethod : object.getCalledMethods()) {
            if (calledMethod.getAssociatedClass() == object.getAssociatedClass() && calledMethod.isAbstract()) {
                abstractCalls++;
            }
        }

        if (abstractCalls > 0) {
            return List.of(new Issue(MESSAGE, object));
        } else {
            return List.of();
        }
    }
}
