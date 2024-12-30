package domain.check;

import domain.Issue;
import domain.source.SourceClass;
import domain.source.SourceObject;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckUnneededInheritance extends ClassChecker {
    static final String MESSAGE = "Concrete class extends a base class, but does not override any methods. Consider using composition instead.";

    @Override
    protected List<Issue> checkClass(SourceClass object) {
        if(object.isInterface() || object.isAbstract()) {
            return List.of();
        }

        SourceClass superclass = object.getSuperClass();
        if (superclass == null) {
            return List.of();
        }

        if(superclass.isInterface()){
            return List.of();
        }

        Set<String> parentMethodNames = superclass
                .getMethods()
                .stream()
                .map(SourceObject::getName)
                .filter(name -> !name.equals("<init>")) // Ignore constructors
                .collect(Collectors.toSet());

        boolean overridesAnyMethods = object
                .getMethods()
                .stream()
                .anyMatch(method -> parentMethodNames.contains(method.getName()));

        if(!overridesAnyMethods) {
            return List.of(new Issue(MESSAGE, object));
        }

        return List.of();
    }
}
