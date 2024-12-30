package domain.source.asm;

import domain.source.AccessModifier;

import java.lang.reflect.Modifier;

public class ASMAccessParser {
    public static AccessModifier getAccessModifier(int access) {
        if (Modifier.isPublic(access)) {
            return AccessModifier.PUBLIC;
        } else if (Modifier.isPrivate(access)) {
            return AccessModifier.PRIVATE;
        } else if (Modifier.isProtected(access)) {
            return AccessModifier.PROTECTED;
        } else {
            return AccessModifier.PACKAGE_PRIVATE;
        }
    }
}
