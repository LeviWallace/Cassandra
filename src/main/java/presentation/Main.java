package presentation;

import datasource.ClassFileLoader;
import datasource.ClassFileLoaderDirectory;
import domain.Checks;
import domain.Issue;
import domain.Linter;
import domain.source.SourceProgram;
import domain.source.asm.ASMSourceProgram;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static Path filePath;
    private static ArrayList<String> checkCases = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length == 0) {
            displayUses();
            return;
        } else {
            handleArguments(args);
        }
    }

    private static void handleArguments(String[] args) {
        SourceProgram program = null;

        for (int i = 0; i < args.length; i++) {
            switch(args[i]) {
                case "-h":
                case "--help":
                    displayUses();
                    return;
                case "-l":
                case "--list":
                    listChecks(false);
                    return;
                case "-p":
                case "--path":
                    filePath = Path.of(args[++i]);
                    program = handleFilePath(filePath);

                    break;
                case "-c":
                case "--checks":
                    String[] rawCheckCases = args[++i].split(",");

                    Collections.addAll(checkCases, rawCheckCases);

                    try {
                        validateChecks();
                    } catch (IllegalArgumentException e) {
                        System.err.printf("Error: %s\n", e.getMessage());

                        listChecks(true);

                        System.exit(1);
                    }
                    break;
                default:
                    System.out.printf("pcheck: '%s' is not a valid argument.\nSee 'pcheck --help'", args[i]);
                    return;
            }

        }

        if (checkCases.isEmpty()) {
            checkCases = new ArrayList<>(Checks.stringValues());
        }

        if (program == null) {
            System.err.println("Please provide a path to your Java .class files");
            System.exit(3);
        }

        Main main = new Main();
        main.runApp(program);
    }

    private static void displayUses()
    {
        System.out.println("usage: pcheck [-h | --help] [-p | --path <path>] [-c | --checks <checks>] [-l | --list]\n");
        System.out.println("-h | --help            : displays this help message");
        System.out.println("-p | --path <path>     : path to the directory containing java .class files to parse");
        System.out.println("-c | --checks <checks> : list of comma separated checks to do on class files, default is all checks");
        System.out.println("-l | --list            : lists all possible checks");
    }

    private static SourceProgram handleFilePath(Path path) {
        ClassFileLoader loader = new ClassFileLoaderDirectory(path);
        SourceProgram program = null;
        try {
            program = new ASMSourceProgram(loader);
        } catch (IOException e) {
            System.err.printf("Error: Path '%s' not found", path.toString());
            System.exit(1);
        }

        return program;
    }

    private void addChecks(Linter linter) {
        for (String c: checkCases) {
            try {
                linter.addCheck(Checks.valueOf(c.toUpperCase()).getCheck());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private static void listChecks(boolean isErr) {
        if (isErr) {
            System.err.println("List of checks:");
            for (Checks c : Checks.values()) {
                System.err.printf("\t%s\n", c.toString());
            }
        } else {
            System.out.println("List of checks:");
            for (Checks c : Checks.values()) {
                System.out.printf("\t%s\n", c.toString());
            }
        }
    }

    private static void validateChecks() throws IllegalArgumentException{
        for (String s: checkCases) {
            if (!Checks.stringValues().contains(s)) {
                String message = String.format("\"%s\" is not a valid check", s);
                throw new IllegalArgumentException(message);
            }
        }
    }

    private void runApp(SourceProgram program) {
        Linter linter = new Linter();

        addChecks(linter);

        List<Issue> issues = linter.runAll(program);
        for (Issue issue: issues) {
            System.out.println(new IssueFormatter(issue));
        }
    }

}
