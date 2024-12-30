package hollywood_priciple;

public class Main {

    private static ClassA cla;
    private static ClassB clb;

    public static void main(String[] args) {
        cla = new ClassA(null);
        clb = new ClassB(cla);
        cla.setCB(clb);

        cla.method1();
        cla.method2();
        clb.method3();
        clb.method4();
    }

}
