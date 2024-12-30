package datasource;

import java.io.IOException;

public interface ClassFileLoader {
    Iterable<InputStreamSupplier> getAllClassFiles() throws IOException;
}
