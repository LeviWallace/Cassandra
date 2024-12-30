package domain.check;

import java.nio.file.Path;
import java.util.List;

public class HashEqualsTest extends LinterTest{

    @Override
    protected Path getPath() {
        return Path.of("sample/java/tests/hashequals_style");
    }

    @Override
    protected List<Check> getChecks() {
        return List.of(new CheckEqualsHashCodeExist());
    }

    @Override
    protected List<TestIssue> getIssues() {
        return List.of(new TestIssue(CheckEqualsHashCodeExist.MESSAGE, "tests/HashCodeExample", "HashCodeExample.java"));
    }
}
