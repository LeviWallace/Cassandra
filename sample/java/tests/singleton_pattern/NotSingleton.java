public class NotSingleton {
    private static NotSingleton RANDOM_CONSTANT;
    public NotSingleton() {}

    public static NotSingleton getRandomConstant() {
        if (RANDOM_CONSTANT == null) {
            return new NotSingleton();
        }

        return RANDOM_CONSTANT;
    }
}