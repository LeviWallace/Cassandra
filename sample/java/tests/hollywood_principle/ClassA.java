package hollywood_priciple;

public class ClassA {

    private ClassB cb;

    public ClassA(ClassB cb)
    {
        this.cb = cb;
    }

    public ClassA()
    {
        this.cb = null;
    }

    public void setCB(ClassB cb)
    {
        this.cb = cb;
    }



    public void method1()
    {
        cb.method4();
    }

    public void method2()
    {
        cb.method3();
    }

}
