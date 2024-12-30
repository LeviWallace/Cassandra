class Singleton1 {
    private static Singleton1 INSTANCE;

    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (INSTANCE == null) {
            return new Singleton1();
        }

        return INSTANCE;
    }
}