package domain.check;

import domain.Issue;
import domain.source.AccessModifier;
import domain.source.SourceClass;
import domain.source.SourceField;
import domain.source.SourceMethod;

import java.util.ArrayList;
import java.util.List;

public class CheckStrategy extends ClassChecker {

    static final String MESSAGE = "Method is a strategy method.";
    @Override
    protected List<Issue> checkClass(SourceClass object) {
        List<Issue> issues = new ArrayList<>();

        //
        List<SourceField> fields = new ArrayList<>();
        for (SourceField srcField: object.getFields())
        {
            if (srcField.getType() != null &&
                    (srcField.getType().isInterface() || srcField.getType().isAbstract()) &&
                    (srcField.getAccess() == AccessModifier.PRIVATE || srcField.getAccess() == AccessModifier.PROTECTED))
            {
                for (SourceMethod fieldMethod: srcField.getType().getMethods())
                {
                    if (fieldMethod.isAbstract())
                    {
                        return List.of(new Issue(MESSAGE, object));
                    }
                }
            }
        }
        return List.of();
    }
}
