package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;

public class Smartphone extends Electronics {
    public Smartphone(String name, Price price, Quantity quantity, Weight weight) {
        super(name, price, quantity, weight);
    }

    private Smartphone(Smartphone other) {
        super(other);
    }

    @Override
    public Product clone() {
        return new Smartphone(this);
    }
}
