package hollywood_priciple;

public class ClassB {

    private ClassA ca;

    public ClassB(ClassA ca)
    {
        this.ca = ca;
    }

    public ClassB()
    {
        this.ca = null;
    }

    public void setCA(ClassA ca)
    {
        this.ca = ca;
    }

    public void method3()
    {
        ca.method1();
    }

    public void method4()
    {
        return;
    }





}
