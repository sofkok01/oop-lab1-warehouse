package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;

public abstract class Food extends Product {
    public Food(String name, Price price, Quantity quantity, Weight weight) {
        super(name, price, quantity, weight, "food");
    }

    protected Food(Food other) {
        super(other);
    }
}
