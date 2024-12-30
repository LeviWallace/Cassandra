package domain.check;

import domain.Issue;
import domain.source.AccessModifier;
import domain.source.SourceClass;
import domain.source.SourceField;
import domain.source.SourceMethod;

import java.util.ArrayList;
import java.util.List;

public class CheckSingleton extends ClassChecker {
    @Override
    protected List<Issue> checkClass(SourceClass object) {
        if (!hasPrivateConstructor(object)) {
            return new ArrayList<Issue>();
        }

        if (!hasStaticGetter(object)) {
            return new ArrayList<Issue>();
        }

        if (!hasStaticInstance(object)) {
            return new ArrayList<Issue>();
        }

        ArrayList<Issue> issues = new ArrayList<>();

        issues.add(new Issue("Singleton spotted", object));

        return issues;
    }

    private boolean hasPrivateConstructor(SourceClass object) {
        for (SourceMethod m : object.getMethods()) {
            if (m.getName().equals("<init>")) {
                if (m.getAccess() == AccessModifier.PRIVATE) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasStaticGetter(SourceClass object) {
        for (SourceMethod m: object.getMethods()) {
            if (m.isStatic() && m.getAccess() != AccessModifier.PRIVATE) {
                if (m.getReturnType() != null) {
                    if (m.getReturnType().equals(object)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasStaticInstance(SourceClass object) {
        if (object.getName().equals("NotSingleton2")) {
            System.out.println("Here");
        }

        for (SourceField f: object.getFields()) {
            if (f.isStatic()) {
                if (f.getType() != null) {
                    if (f.getType().equals(object)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
