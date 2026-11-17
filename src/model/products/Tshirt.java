package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;

public class Tshirt extends Clothing {
    public Tshirt(String name, Price price, Quantity quantity, Weight weight) {
        super(name, price, quantity, weight);
    }

    private Tshirt(Tshirt other) {
        super(other);
    }

    @Override
    public Product clone() {
        return new Tshirt(this);
    }
}
