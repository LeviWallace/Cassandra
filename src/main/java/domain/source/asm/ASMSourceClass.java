package domain.source.asm;

import domain.source.*;
import org.objectweb.asm.tree.ClassNode;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ASMSourceClass implements SourceClass {
    private final FileReference fileReference;
    private final List<ASMSourceField> fields;
    private final List<ASMSourceMethod> methods;
    private final Map<String, ASMSourceMethod> methodsMap;
    private final String name;
    private final AccessModifier access;
    private final String superName;
    private SourceClass superClass;
    private final List<SourceClass> subClasses;
    private final boolean isAbstract;
    private final boolean isInterface;

    public ASMSourceClass(ClassNode classNode) {
        name = classNode.name;
        access = ASMAccessParser.getAccessModifier(classNode.access);
        isAbstract = Modifier.isAbstract(classNode.access);
        isInterface = Modifier.isInterface(classNode.access);
        fileReference = new FileReference(classNode.sourceFile);

        fields = classNode.fields.stream().map(fieldNode -> new ASMSourceField(this, fieldNode, fileReference)).collect(Collectors.toList());
        methods = classNode.methods.stream().map(methodNode -> new ASMSourceMethod(this, methodNode, fileReference)).collect(Collectors.toList());
        methodsMap = methods.stream().collect(Collectors.toMap(ASMSourceMethod::getMethodMapKey, method -> method));

        superName = classNode.superName;
        subClasses = new ArrayList<>();
    }

    void link(ASMSourceProgram program) {
        ASMSourceClass superClass = program.getClass(superName);
        if (superClass != null) {
            this.superClass = superClass;
            superClass.subClasses.add(this);
        }

        for (ASMSourceField field : fields) {
            field.link(program);
        }

        for (ASMSourceMethod method : methods) {
            method.link(program);
        }
    }

    ASMSourceMethod getMethodMap(String methodMapKey) {
        return methodsMap.get(methodMapKey);
    }

    @Override
    public List<SourceField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    @Override
    public List<SourceMethod> getMethods() {
        return Collections.unmodifiableList(methods);
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public boolean isInterface() {
        return isInterface;
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

    @Override
    public SourceClass getSuperClass() {
        return superClass;
    }

    @Override
    public Iterable<SourceClass> getSubClasses() {
        return subClasses;
    }
}
