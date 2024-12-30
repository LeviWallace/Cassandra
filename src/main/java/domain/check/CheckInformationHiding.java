package domain.check;

import domain.Issue;
import domain.source.AccessModifier;
import domain.source.SourceClass;
import domain.source.SourceField;

import java.util.ArrayList;
import java.util.List;

public class CheckInformationHiding extends FieldChecker {


    @Override
    protected List<Issue> checkField(SourceField sourceField) {
        ArrayList<Issue> issues = new ArrayList<>();

        if (sourceField.getAccess() == AccessModifier.PUBLIC && !sourceField.isStatic() && !sourceField.isFinal()) {
            // Check if static final as well, otherwise add new Issue
            issues.add(new Issue("Public fields should only be used as a static final constant", sourceField));
        } else if (sourceField.getAccess() == AccessModifier.PROTECTED) {
            // Should only use protected on getters/setters between a class's child/parent
            issues.add(new Issue("Protected fields should be implemented using a protected getter in the superclass", sourceField));
        } else if (sourceField.getAccess() == AccessModifier.PACKAGE_PRIVATE && !sourceField.isStatic() && !sourceField.isFinal()) {
            // Check for static final, otherwise add new Issue
            issues.add(new Issue("Package private fields should only be used as a static final constant", sourceField));
        }

        return issues;
    }


}
