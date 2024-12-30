package domain;

public class NamingPolicyVerifier {
    public static boolean isLowerCamelCase(String identifier) {
        return isCamelCase(identifier, false);
    }

    public static boolean isUpperCamelcase(String identifier) {
        return isCamelCase(identifier, true);
    }

    private static boolean isCamelCase(String identifier, boolean isUpper) {
        String[] parts = identifier.split("/");
        identifier = parts[parts.length - 1];

        if (isInternal(identifier)) {
            return true;
        }

        if (!identifier.matches("[A-Za-z0-9]*")) {
            return false;
        }

        if (isUpper) {
            return Character.isUpperCase(identifier.charAt(0));
        } else {
            return Character.isLowerCase(identifier.charAt(0));
        }
    }

    private static boolean isInternal(String identifier) {
        return identifier.matches("<.*>");
    }
}
