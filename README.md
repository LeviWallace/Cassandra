# Project: Cassandra

## Dependencies
Specified in pom.xml

## Contributors
Levi Wallace, Elijah Watson, Evelyn Gordon

## Team Member's Engineering Notebooks (one per person)
- Levi Wallace: https://github.com/rhit-csse374/project-202410-team09-202410/wiki/Levi-Wallace's-Enginnering-Notebook
- Elijah Watson: https://github.com/rhit-csse374/project-202410-team09-202410/wiki/Elijah-Watson's-Engineering-Notebook
- Evelyn Gordon: https://github.com/rhit-csse374/project-202410-team09-202410/wiki/Evelyn-Gordon's-Engineering-Notebook

## Features


| Developer     | Style Check                                                                                                                      | Principle Check                                                                                                           | Pattern Check                                                     | A Feature (optional) |
|:--------------|:---------------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------|:---------------------|
| Levi Wallace  | hashcode() and equals()                                                                                                          | Hollywood Principal                                                                                                       | Strategy Pattern                                                  |                     |
| Elijah Watson | Unused Variables                                                                                                                 | Information Hiding                                                                                                        | Singleton Pattern                                                 |                      |
| Evelyn Gordon | [Method](src/main/java/domain/check/CheckMethodNaming.java) and [Class](src/main/java/domain/check/CheckClassNaming.java) Naming | Favor composition over inheritance ([CheckUnneededInheritance](src/main/java/domain/check/CheckUnneededInheritance.java)) | [Template Pattern](src/main/java/domain/check/CheckTemplate.java) | Line Numbers         |

## How to Use

Our program can be executed in the command line using 
```bash
$ java -jar pcheck.jar
```
Running the program with no arguments displays the help page

### Commands

The program has 4 different arguments that can be passed in to modify its execution

The commands are as follows:

| Flag        | Longer flag       | Description                                                                                                               |
|:------------|-------------------|---------------------------------------------------------------------------------------------------------------------------|
| -h          | --help            | Displays the help page and exits                                                                                          |
| -l          | --list            | Displays a list of possible checks and exits                                                                              |
| -p {path}   | --path {path}     | Tells the program the root directory containing the .class files you what to check                                        |
| -c {checks} | --checks {checks} | Tells the program what checks you want to use in a comma separated list.<br/>If left blank, it will default to all checks |

### Examples

Example 1: I want to check all the .class files in /home/user/directory with all checks enabled
```shell
$ java -jar pcheck --path /home/user/directory
```

Example 2: I want to use the information hiding and the method naming checks on the same files as above
```shell
$ java -jar pcheck --path /home/user/directory --checks information_hiding,method_naming
```

Example 3: I want to see all possible checks I can use
```shell
$ java -jar pcheck --list
```







