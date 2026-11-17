package model.attributes;

public class Price {
    private int value;

    public Price(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("цена не может быть отрицательной");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " руб.";
    }
}
