package domain;

import domain.check.*;

import java.util.*;

public enum Checks {
    CLASS_NAMING,
    EQUALS_HASH_CODE,
    INFORMATION_HIDING,
    METHOD_NAMING,
    SINGLETON,
    TEMPLATE,
    STRATEGY,
    HOLLYWOOD,

    UNUSED_VARIABLES,
    UNNEEDED_INHERITANCE
    ;

    // TODO: Add more checks as needed

    @Override
    public String toString() {
        // Convert to lower_snake_case

        return this.name().toLowerCase();
    }


    public static List<String> stringValues() {
        List<String> values = new ArrayList<>();

        for (Checks c: values()) {
            values.add(c.toString());
        }

        return values;
    }

    public Check getCheck() {
        switch (this) {
            case CLASS_NAMING:
                return new CheckClassNaming();
            case EQUALS_HASH_CODE:
                return new CheckEqualsHashCodeExist();
            case INFORMATION_HIDING:
                return new CheckInformationHiding();
            case METHOD_NAMING:
                return new CheckMethodNaming();
            case SINGLETON:
                return new CheckSingleton();
            case TEMPLATE:
                return new CheckTemplate();
            case STRATEGY:
                return new CheckStrategy();
            case HOLLYWOOD:
                return new CheckHollywoodPrinciple();
            case UNUSED_VARIABLES:
                return new CheckUnusedVariables();
            case UNNEEDED_INHERITANCE:
                return new CheckUnneededInheritance();
            default:
                return null;
        }
    }
}
