package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;

public class Bread extends Food {
    public Bread(String name, Price price, Quantity quantity, Weight weight) {
        super(name, price, quantity, weight);
    }

    private Bread(Bread other) {
        super(other);
    }

    @Override
    public Product clone() {
        return new Bread(this);
    }
}
