package domain.check;

import domain.Issue;
import domain.source.SourceClass;
import domain.source.SourceMethod;

import java.util.ArrayList;
import java.util.List;

public class CheckEqualsHashCodeExist extends ClassChecker{

    static final String MESSAGE = "Method contains hashCode() but does not contain equals()";
    @Override
    protected List<Issue> checkClass(SourceClass object) {
        ArrayList<Issue> issues = new ArrayList<>();
        List<SourceMethod> methods = object.getMethods();

        boolean hasHashCodeMethod = false;
        boolean hasEqualsMethod = false;
        for (SourceMethod method : methods)
        {
            if (method.getName().equals("hashCode")) hasHashCodeMethod = true;
            else if (method.getName().equals("equals")) hasEqualsMethod = true;
        }
        if (hasHashCodeMethod && !hasEqualsMethod)
        {
            issues.add(new Issue(MESSAGE, object));
        }
        return issues;
    }
}
