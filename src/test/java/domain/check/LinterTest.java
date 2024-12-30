package domain.check;

import datasource.ClassFileLoader;
import datasource.ClassFileLoaderDirectory;
import domain.Issue;
import domain.Linter;
import domain.source.SourceProgram;
import domain.source.asm.ASMSourceProgram;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public abstract class LinterTest {
    @Test
    public final void run() throws IOException {
        Linter linter = new Linter();
        for(Check check : getChecks()){
            linter.addCheck(check);
        }
        ClassFileLoader loader = new ClassFileLoaderDirectory(getPath());
        SourceProgram sourceProgram = new ASMSourceProgram(loader);

        List<TestIssue> expected = getIssues();
        List<Issue> actual = linter.runAll(sourceProgram);

        Assertions.assertEquals(expected.size(), actual.size(), "Wrong number of issues found");

        for (int i = 0; i < actual.size(); i++) {
            expected.get(i).verify(actual.get(i));
        }
    }
    protected abstract Path getPath();
    protected abstract List<Check> getChecks();
    protected abstract List<TestIssue> getIssues();
}
