package domain.source.asm;

import domain.source.AccessModifier;
import domain.source.FileReference;
import domain.source.SourceLocalVariable;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LocalVariableNode;

public class ASMSourceLocalVariable implements SourceLocalVariable {
    private final String name;
    private final FileReference fileReference;

    public ASMSourceLocalVariable(LocalVariableNode node, FileReference fileReference, InsnList instructions) {
        this.name = node.name;
        this.fileReference = fileReference;
    }

    @Override
    public FileReference getFileReference() {
        return fileReference;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AccessModifier getAccess() {
        return AccessModifier.PRIVATE;
    }

    @Override
    public boolean isUsed() {
        return false;
    }
}
