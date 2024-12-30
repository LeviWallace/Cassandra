package domain.check;

import java.nio.file.Path;
import java.util.List;

public class UnneededInheritanceTest extends LinterTest {
    @Override
    protected Path getPath() {
        return Path.of("sample/java/tests/favor_composition");
    }

    @Override
    protected List<Check> getChecks() {
        return List.of(new CheckUnneededInheritance());
    }

    @Override
    protected List<TestIssue> getIssues() {
        return List.of(new TestIssue(CheckUnneededInheritance.MESSAGE, "tests/favor_composition/ChildNoOverride", "ChildNoOverride.java"));
    }
}
