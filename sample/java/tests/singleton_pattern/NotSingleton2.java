public class NotSingleton2 {
    private static int CONSTANT = 4;
    private String info;

    public NotSingleton2(String info) {
        this.info = info;
    }

    private NotSingleton2() {
        // Does soemthing different that requires it to be private
    }

    public static NotSingleton2 staticMethod() {
        // Some custom math algorithm, I guess?
        return new NotSingleton2();
    }




}