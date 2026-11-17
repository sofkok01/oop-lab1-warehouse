package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;

public abstract class Clothing extends Product {
    public Clothing(String name, Price price, Quantity quantity, Weight weight) {
        super(name, price, quantity, weight, "clothing");
    }

    protected Clothing(Clothing other) {
        super(other);
    }
}
