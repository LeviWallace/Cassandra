package domain.check;

import domain.Linter;

import java.nio.file.Path;
import java.util.List;

public class StrategyTest extends LinterTest {
    protected Path getPath() {
        return Path.of("sample/java/tests/strategy_pattern");
    }

    @Override
    protected List<Check> getChecks() {
        return List.of(new CheckStrategy());
    }

    @Override
    protected List<TestIssue> getIssues() {
        return List.of(
                new TestIssue(CheckStrategy.MESSAGE, "tests/strategy_pattern/Main", "Main.java")
        );
    }
}
