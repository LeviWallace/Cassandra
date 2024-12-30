package domain.check;

import domain.Issue;
import domain.NamingPolicyVerifier;
import domain.source.SourceMethod;

import java.util.ArrayList;
import java.util.List;

public class CheckMethodNaming extends MethodChecker {
    static final String MESSAGE = "Method name is not lowerCamelCase";

    @Override
    protected List<Issue> checkMethod(SourceMethod object) {
        ArrayList<Issue> issues = new ArrayList<>();

        if (!NamingPolicyVerifier.isLowerCamelCase(object.getName())) {
            issues.add(new Issue(MESSAGE, object));
        }

        return issues;
    }
}
