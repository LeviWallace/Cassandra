package domain.check;

import domain.Issue;
import domain.source.SourceClass;
import domain.source.SourceMethod;

import java.util.ArrayList;
import java.util.List;

public class CheckUnusedVariables extends ClassChecker{
    // If a variable's value is never read, its unused
    public CheckUnusedVariables() {};

    @Override
    protected List<Issue> checkClass(SourceClass object) {
        for (SourceMethod m: object.getMethods()) {

        }

        return new ArrayList<Issue>();
    }
}
