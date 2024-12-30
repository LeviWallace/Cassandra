package domain;


import domain.check.Check;
import domain.source.SourceProgram;

import java.util.ArrayList;
import java.util.List;

public class Linter {

    private final List<Check> checks;

    public Linter() {
        checks = new ArrayList<>();
    }


    public List<Issue> runAll(SourceProgram program) {
        List<Issue> issues = new ArrayList<>();
        for (Check check : checks) {
            issues.addAll(check.check(program));
        }
        return issues;
    }

    public void addCheck(Check check) {
        checks.add(check);
    }

    @Override
    public String toString() {
        String s = "Linter[";
        for (Check c: checks) {
            s = s.concat(c.getClass().getSimpleName());
            s = s.concat(",");
        }
        s = s.concat("]");

        return s;
    }
}
