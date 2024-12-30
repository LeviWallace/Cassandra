package domain.source.asm;

import domain.source.*;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ASMSourceMethod implements SourceMethod {
    private final ASMSourceClass associatedClass;
    private final FileReference fileReference;
    private final String name;
    private final String methodMapKey;
    private final AccessModifier access;
    private final boolean isAbstract;
    private final boolean isStatic;
    private final boolean isFinal;
    private final List<MethodInsnNode> methodCalls;
    private final List<ASMSourceLocalVariable> localVariables;
    private List<ASMSourceMethod> calledMethods;
    private final Type asmReturnType;
    private SourceClass returnType;

    public ASMSourceMethod(ASMSourceClass associatedClass, MethodNode methodNode, FileReference fileReference) {
        int lineNumber = findLineNumber(methodNode);
        FileReference fileReferenceWithLineNumber;
        if (lineNumber != -1) {
            fileReferenceWithLineNumber = new FileReference(fileReference.getFilename(), lineNumber);
        } else {
            fileReferenceWithLineNumber = fileReference;
        }
        this.methodCalls = findMethodCalls(methodNode);

        this.associatedClass = associatedClass;
        this.fileReference = fileReferenceWithLineNumber;
        this.name = methodNode.name;
        this.methodMapKey = formatMethodMapKey(methodNode.name, methodNode.desc);
        this.access = ASMAccessParser.getAccessModifier(methodNode.access);
        this.isAbstract = Modifier.isAbstract(methodNode.access);
        this.isStatic = Modifier.isStatic(methodNode.access);
        this.isFinal = Modifier.isFinal(methodNode.access);
        this.asmReturnType = Type.getReturnType(methodNode.desc);

        if (methodNode.localVariables == null) {
            this.localVariables = List.of();
        } else {
            this.localVariables = methodNode.localVariables
                    .stream()
                    .map(localVariableNode -> new ASMSourceLocalVariable(localVariableNode, fileReferenceWithLineNumber, methodNode.instructions))
                    .collect(Collectors.toList());
        }
    }

    private int findLineNumber(MethodNode methodNode) {
        for (AbstractInsnNode insnNode : methodNode.instructions) {
            if (insnNode instanceof LineNumberNode) {
                return ((LineNumberNode) insnNode).line;
            }
        }
        return -1;
    }

    private String formatMethodMapKey(String name, String desc) {
        return name + ":" + desc;
    }

    String getMethodMapKey() {
        return methodMapKey;
    }

    private List<MethodInsnNode> findMethodCalls(MethodNode methodNode) {
        List<MethodInsnNode> calls = new ArrayList<>();
        for (AbstractInsnNode insnNode : methodNode.instructions) {
            if (insnNode instanceof MethodInsnNode) {
                calls.add((MethodInsnNode) insnNode);
            }
        }
        return calls;
    }

    void link(ASMSourceProgram program) {
        fillCalledMethods(program);
        returnType = program.getClass(asmReturnType.getInternalName());
    }

    private void fillCalledMethods(ASMSourceProgram program) {
        calledMethods = new ArrayList<>();
        for (MethodInsnNode call : methodCalls) {
            ASMSourceClass sourceClass = program.getClass(call.owner);
            if (sourceClass != null) {
                ASMSourceMethod sourceMethod = sourceClass.getMethodMap(formatMethodMapKey(call.name, call.desc));
                if (sourceMethod != null) {
                    calledMethods.add(sourceMethod);
                }
            }
        }
    }

    @Override
    public ASMSourceClass getAssociatedClass() {
        return associatedClass;
    }

    @Override
    public List<SourceMethod> getCalledMethods() {
        return Collections.unmodifiableList(calledMethods);
    }

    @Override
    public List<SourceLocalVariable> getLocalVariables() {
        return Collections.unmodifiableList(localVariables);
    }

    @Override
    public SourceClass getReturnType() {
        return returnType;
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public boolean isStatic() {
        return isStatic;
    }

    @Override
    public boolean isFinal() {
        return isFinal;
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
}
