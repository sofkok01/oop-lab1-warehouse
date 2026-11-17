package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;

public class Milk extends Food {
    public Milk(String name, Price price, Quantity quantity, Weight weight) {
        super(name, price, quantity, weight);
    }

    private Milk(Milk other) {
        super(other);
    }

    @Override
    public Product clone() {
        return new Milk(this);
    }
}
