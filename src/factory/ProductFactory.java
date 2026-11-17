package factory;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;
import model.products.Product;

public interface ProductFactory {
    Product createProduct(String name, Price price, Quantity quantity, Weight weight);
}
