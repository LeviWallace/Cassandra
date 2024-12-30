package tests;
public class InformationHidingExample {
    public int nonConstantInt = 0;
    public static final int CONSTANT_INT = 1;
    protected int shouldUseAGetterHere = 2;
    int packagePrivateNonConstantInt = 3;
    static final int PACKAGE_PRIVATE_CONSTANT_INT = 4;
    private int shouldntAppearSinceItsPrivateInt = 5;

    public InformationHidingExample(){};
}