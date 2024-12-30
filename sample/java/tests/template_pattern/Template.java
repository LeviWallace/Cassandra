package tests.template_pattern;

abstract class Template {
    public final void templateMethod() {
        abstractImp1();
        abstractImp2();
        concreteImp3();
    }

    protected abstract void abstractImp1();

    protected abstract void abstractImp2();

    final private void concreteImp3() {
    }

    protected void hook() {
    }
}