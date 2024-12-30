package datasource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ClassFileLoaderDirectory implements ClassFileLoader {
    private final Path path;

    public ClassFileLoaderDirectory(Path path) {
        this.path = path;
    }

    public List<InputStreamSupplier> getAllClassFiles() throws IOException {
        List<InputStreamSupplier> files = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(path)) {
            for (Iterator<Path> it = paths.iterator(); it.hasNext(); ) {
                Path path = it.next();

                if(!path.toString().endsWith(".class")){
                    continue;
                }

                if(!Files.isRegularFile(path)) {
                    continue;
                }

                files.add(() -> Files.newInputStream(path));
            }
        }

        return files;
    }
}
