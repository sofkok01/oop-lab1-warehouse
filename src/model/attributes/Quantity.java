package model.attributes;

public class Quantity {
    private int value;

    public Quantity(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("колво не может быть отрицательным");
        }
        this.value = value;
    }

    public void change(int delta) {
        setValue(this.value + delta);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
