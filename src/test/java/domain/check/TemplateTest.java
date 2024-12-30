package domain.check;

import java.nio.file.Path;
import java.util.List;

public class TemplateTest extends LinterTest {
    @Override
    protected Path getPath() {
        return Path.of("sample/java/tests/template_pattern");
    }

    @Override
    protected List<Check> getChecks() {
        return List.of(new CheckTemplate());
    }

    @Override
    protected List<TestIssue> getIssues() {
        return List.of(
                new TestIssue(CheckTemplate.MESSAGE, "templateMethod", "Template.java", 5)
        );
    }
}
