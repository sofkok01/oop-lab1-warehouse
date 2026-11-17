package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;

public abstract class Electronics extends Product {
    public Electronics(String name, Price price, Quantity quantity, Weight weight) {
        super(name, price, quantity, weight, "electronics");
    }

    protected Electronics(Electronics other) {
        super(other);
    }
}
