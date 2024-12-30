package domain.check;

import domain.Issue;
import domain.source.SourceClass;
import domain.source.SourceField;
import domain.source.SourceProgram;

import java.util.List;
import java.util.ArrayList;

public abstract class FieldChecker implements Check {
    @Override
    public final List<Issue> check(SourceProgram program) {
        ArrayList<Issue> issues = new ArrayList<>();
        for (SourceClass srcClass : program.getClasses()) {
            for (SourceField srcField : srcClass.getFields()) {
                issues.addAll(checkField(srcField));
            }
        }
        return issues;
    }

    protected abstract List<Issue> checkField(SourceField object);
}
