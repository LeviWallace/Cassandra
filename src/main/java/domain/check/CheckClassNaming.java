package domain.check;

import domain.Issue;
import domain.NamingPolicyVerifier;
import domain.source.SourceClass;

import java.util.ArrayList;
import java.util.List;

public class CheckClassNaming extends ClassChecker {
    static final String MESSAGE = "Class name is not UpperCamelCase";
    
    @Override
    protected List<Issue> checkClass(SourceClass object) {

        ArrayList<Issue> issues = new ArrayList<>();

        if (!NamingPolicyVerifier.isUpperCamelcase(object.getName())) {
            issues.add(new Issue(MESSAGE, object));
        }

        return issues;
    }
}
