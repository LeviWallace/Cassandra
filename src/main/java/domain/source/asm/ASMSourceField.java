package domain.source.asm;

import domain.source.AccessModifier;
import domain.source.FileReference;
import domain.source.SourceClass;
import domain.source.SourceField;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.FieldNode;

public class ASMSourceField implements SourceField {
    private final ASMSourceClass associatedClass;
    private final FileReference fileReference;
    private final AccessModifier access;
    private final String name;
    private final boolean isStatic;
    private final boolean isFinal;
    private final Type asmType;
    private SourceClass type;

    public ASMSourceField(ASMSourceClass associatedClass, FieldNode fieldNode, FileReference fileReference) {
        this.associatedClass = associatedClass;
        this.fileReference = fileReference;
        this.name = fieldNode.name;
        this.access = ASMAccessParser.getAccessModifier(fieldNode.access);
        this.isStatic = ((fieldNode.access & Opcodes.ACC_STATIC) != 0);
        this.isFinal = ((fieldNode.access & Opcodes.ACC_FINAL) != 0);
        this.asmType = Type.getType(fieldNode.desc);
    }

    void link(ASMSourceProgram program){
        type = program.getClass(asmType.getClassName().replace('.', '/'));
    }

    @Override
    public ASMSourceClass getAssociatedClass() {
        return associatedClass;
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
        return access;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public SourceClass getType() {
        return type;
    }
}
