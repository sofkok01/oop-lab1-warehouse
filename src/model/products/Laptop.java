package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;

public class Laptop extends Electronics {
    public Laptop(String name, Price price, Quantity quantity, Weight weight) {
        super(name, price, quantity, weight);
    }

    private Laptop(Laptop other) {
        super(other);
    }

    @Override
    public Product clone() {
        return new Laptop(this);
    }
}
