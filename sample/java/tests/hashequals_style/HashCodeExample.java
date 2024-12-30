package tests;

import java.util.Objects;
public class HashCodeExample {
    private int field;
    public HashCodeExample(int field)
    {
        this.field = field;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashCodeExample that = (HashCodeExample) o;
        return field == that.field;
    } */

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }
}
