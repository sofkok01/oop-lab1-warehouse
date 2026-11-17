package model.products;

import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;
import storage.Warehouse;

public abstract class Product implements Cloneable {
    private static int nextId = 1;

    private final int id;
    private final String name;
    private Price price;
    private Quantity quantity;
    private final Weight weight;
    private final String category;

    public Product(String name, Price price, Quantity quantity, Weight weight, String category) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        if (price == null || quantity == null || weight == null) {
            throw new IllegalArgumentException("Атрибуты товара не могут быть пустыми");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Категория не может быть пустой");
        }

        this.id = nextId++;
        this.name = name;
        this.price = new Price(price.getValue());
        this.quantity = new Quantity(quantity.getValue());
        this.weight = new Weight(weight.getValue());
        this.category = category;
    }

    //конструткор копирования
    protected Product(Product other) {
        this.id = other.id;
        this.name = other.name;
        this.price = new Price(other.price.getValue());
        this.quantity = new Quantity(other.quantity.getValue());
        this.weight = new Weight(other.weight.getValue());
        this.category = other.category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Price getPrice() { return price; }
    public Quantity getQuantity() { return quantity; }
    public Weight getWeight() { return weight; }
    public String getCategory() { return category; }

    public void changeQuantity(int delta) {
        this.quantity.change(delta);
    }

    public void setPrice(Price newPrice) {
        if (newPrice == null) {
            throw new IllegalArgumentException("цена не может быть null");
        }
        this.price.setValue(newPrice.getValue());
    }

    public void transferTo(Warehouse other) {
        if (other == null) {
            throw new IllegalArgumentException("склад не может быть null");
        }
        if (this.quantity.getValue() <= 0) {
            throw new IllegalStateException("недостаточно товара для перемещения");
        }
        if (other.getCurrentSize() >= other.getMaxSize()) {
            throw new IllegalStateException("целевой склад полностью заполнен");
        }

        Product movedProduct = this.clone();
        movedProduct.quantity.setValue(1);

        other.addProduct(movedProduct);
        this.changeQuantity(-1);
    }

    @Override
    public abstract Product clone();

    @Override
    public String toString() {
        return String.format("[%s] ID: %d | %s | Цена: %s | Остаток: %s | Вес: %s",
                category.toUpperCase(), id, name, price, quantity, weight);
    }
}
