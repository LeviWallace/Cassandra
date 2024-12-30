package domain.check;

import java.nio.file.Path;
import java.util.List;

public class HollywoodPrincipleTest extends LinterTest{
    @Override
    protected Path getPath() {
        return Path.of("sample/java/tests/hollywood-principle");
    }

    @Override
    protected List<Check> getChecks() {
        return List.of(new CheckHollywoodPrinciple());
    }

    @Override
    protected List<TestIssue> getIssues() {
        return List.of(
                new TestIssue(CheckHollywoodPrinciple.MESSAGE, "hollywood_principle/ClassA", "ClassA.java"),
                new TestIssue(CheckHollywoodPrinciple.MESSAGE, "hollywood_principle/ClassB", "ClassB.java"));
    }
}
