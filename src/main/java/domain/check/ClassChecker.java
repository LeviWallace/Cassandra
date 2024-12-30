package domain.check;

import domain.Issue;
import domain.source.SourceClass;
import domain.source.SourceProgram;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassChecker implements Check {
    @Override
    public final List<Issue> check(SourceProgram program)
    {
        ArrayList<Issue> issues = new ArrayList<>();
        for (SourceClass srcClass: program.getClasses())
        {
            issues.addAll(checkClass(srcClass));
        }
        return issues;
    }

    protected abstract List<Issue> checkClass(SourceClass object);
}

