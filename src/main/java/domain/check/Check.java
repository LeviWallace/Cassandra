package domain.check;

import domain.Issue;
import domain.source.SourceProgram;

import java.util.List;

public interface Check {
    List<Issue> check(SourceProgram program);
}
