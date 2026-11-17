package storage;

import model.attributes.Price;
import model.products.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Warehouse implements Iterable<Product>, Cloneable {
    private List<Product> products;
    private final int maxSize;

    public Warehouse(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Размер склада должен быть положительным");
        }
        this.maxSize = maxSize;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }
        if (products.size() >= maxSize) {
            throw new IllegalStateException("Превышен лимит вместимости склада");
        }
        products.add(product);
    }

    public boolean removeById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean removeByIndex(int index) {
        if (index < 0 || index >= products.size()) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }
        products.remove(index);
        return true;
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Product> findByCategory(String category) {
        List<Product> result = new ArrayList<>();
        if (category == null) {
            return result;
        }
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> findByPrice(Price price) {
        List<Product> result = new ArrayList<>();
        if (price == null) {
            return result;
        }
        for (Product p : products) {
            if (p.getPrice().getValue() == price.getValue()) {
                result.add(p);
            }
        }
        return result;
    }

    public void clear() {
        products.clear();
    }
    public int getMaxSize() {
        return maxSize;
    }
    public int getCurrentSize() {
        return products.size();
    }

    public void transferFrom(Warehouse other) {
        if (other == null) {
            throw new IllegalArgumentException("Исходный склад не может быть null");
        }
        if (other == this) {
            throw new IllegalArgumentException("Нельзя перенести товары со склада на этот же склад");
        }

        Iterator<Product> it = other.products.iterator();
        while (it.hasNext() && this.products.size() < this.maxSize) {
            Product p = it.next();
            this.addProduct(p);
            it.remove();
        }
    }

    @Override
    public Warehouse clone() {
        try {
            Warehouse cloned = (Warehouse) super.clone();
            cloned.products = new ArrayList<>(this.products.size());
            for (Product p : this.products) {
                cloned.products.add(p.clone());
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Клонирование должно поддерживаться", e);
        }
    }

    @Override
    public Iterator<Product> iterator() {
        return new WarehouseIterator();
    }

    private class WarehouseIterator implements Iterator<Product> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < products.size();
        }

        @Override
        public Product next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Больше нет товаров на складе");
            }
            return products.get(cursor++);
        }
    }
}
