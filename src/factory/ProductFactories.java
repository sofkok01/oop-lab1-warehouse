package factory;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;
import model.products.*;

public class ProductFactories {

    public static class SmartphoneFactory implements ProductFactory {
        @Override
        public Product createProduct(String name, Price price, Quantity quantity, Weight weight) {
            return new Smartphone(name, price, quantity, weight);
        }
    }

    public static class LaptopFactory implements ProductFactory {
        @Override
        public Product createProduct(String name, Price price, Quantity quantity, Weight weight) {
            return new Laptop(name, price, quantity, weight);
        }
    }

    public static class TShirtFactory implements ProductFactory {
        @Override
        public Product createProduct(String name, Price price, Quantity quantity, Weight weight) {
            return new Tshirt(name, price, quantity, weight);
        }
    }

    public static class JacketFactory implements ProductFactory {
        @Override
        public Product createProduct(String name, Price price, Quantity quantity, Weight weight) {
            return new Jacket(name, price, quantity, weight);
        }
    }

    public static class MilkFactory implements ProductFactory {
        @Override
        public Product createProduct(String name, Price price, Quantity quantity, Weight weight) {
            return new Milk(name, price, quantity, weight);
        }
    }

    public static class BreadFactory implements ProductFactory {
        @Override
        public Product createProduct(String name, Price price, Quantity quantity, Weight weight) {
            return new Bread(name, price, quantity, weight);
        }
    }
}
