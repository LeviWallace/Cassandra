package domain.source.asm;

import datasource.ClassFileLoader;
import datasource.InputStreamSupplier;
import domain.source.SourceClass;
import domain.source.SourceProgram;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASMSourceProgram implements SourceProgram {
    private final Map<String, ASMSourceClass> classMap;
    private final List<SourceClass> classes;

    public ASMSourceProgram(ClassFileLoader loader) throws IOException {
        classMap = new HashMap<>();
        classes = new ArrayList<>();

        for (InputStreamSupplier supplier : loader.getAllClassFiles()) {
            ClassReader reader = new ClassReader(supplier.get());
            ClassNode node = new ClassNode();
            reader.accept(node, ClassReader.EXPAND_FRAMES);

            ASMSourceClass sourceClass = new ASMSourceClass(node);
            classMap.put(sourceClass.getName(), sourceClass);
            classes.add(sourceClass);
        }

        for (ASMSourceClass sourceClass : classMap.values()) {
            sourceClass.link(this);
        }
    }

    @Override
    public Iterable<SourceClass> getClasses() {
        return classes;
    }

    ASMSourceClass getClass(String name) {
        return classMap.get(name);
    }
}
