package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;

public class Jacket extends Clothing {
    public Jacket(String name, Price price, Quantity quantity, Weight weight) {
        super(name, price, quantity, weight);
    }

    private Jacket(Jacket other) {
        super(other);
    }

    @Override
    public Product clone() {
        return new Jacket(this);
    }
}
