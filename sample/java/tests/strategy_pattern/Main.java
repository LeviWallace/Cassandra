package tests.strategy_pattern;

public class Main {
    private Strategy strategy;

    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        strategy = new ConcreteStrategy1();
    }
}