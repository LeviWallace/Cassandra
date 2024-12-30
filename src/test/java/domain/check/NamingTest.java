package domain.check;

import java.nio.file.Path;
import java.util.List;

public class NamingTest extends LinterTest {
    @Override
    protected Path getPath() {
        return Path.of("sample/java/tests/Naming_Example.class");
    }

    @Override
    protected List<Check> getChecks() {
        return List.of(new CheckClassNaming(), new CheckMethodNaming());
    }

    @Override
    protected List<TestIssue> getIssues() {
        return List.of(
                new TestIssue(CheckClassNaming.MESSAGE, "tests/Naming_Example", "Naming_Example.java"),
                new TestIssue(CheckMethodNaming.MESSAGE, "BadName1", "Naming_Example.java", 9),
                new TestIssue(CheckMethodNaming.MESSAGE, "bad_name_2", "Naming_Example.java", 13)
        );
    }
}
