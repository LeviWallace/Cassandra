package domain.check;

import domain.Issue;
import domain.source.SourceClass;
import domain.source.SourceMethod;
import domain.source.SourceProgram;

import java.util.ArrayList;
import java.util.List;

public abstract class MethodChecker implements Check {
    @Override
    public List<Issue> check(SourceProgram program) {
        ArrayList<Issue> issues = new ArrayList<>();
        for (SourceClass srcClass : program.getClasses()) {
            for (SourceMethod srcMethod : srcClass.getMethods()) {
                issues.addAll(checkMethod(srcMethod));
            }
        }
        return issues;
    }

    protected abstract List<Issue> checkMethod(SourceMethod object);
}
