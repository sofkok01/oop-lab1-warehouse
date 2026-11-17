import factory.ProductFactories;
import factory.ProductFactory;
import model.attributes.Price;
import model.attributes.Quantity;
import model.attributes.Weight;
import model.products.Product;
import storage.Warehouse;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Сценарий 1: Создание склада, добавление через фабрики и итератор ---");
        Warehouse mainWarehouse = new Warehouse(5);

        ProductFactory phoneFactory = new ProductFactories.SmartphoneFactory();
        ProductFactory laptopFactory = new ProductFactories.LaptopFactory();
        ProductFactory tshirtFactory = new ProductFactories.TShirtFactory();
        ProductFactory milkFactory = new ProductFactories.MilkFactory();

        Product phone = phoneFactory.createProduct("iPhone", new Price(90000), new Quantity(15), new Weight(0.2));
        Product laptop = laptopFactory.createProduct("ThinkBook", new Price(200000), new Quantity(5), new Weight(1.6));
        Product tshirt = tshirtFactory.createProduct("Красная футболка", new Price(1500), new Quantity(50), new Weight(0.15));
        Product milk = milkFactory.createProduct("Молоко кокосовое", new Price(90), new Quantity(100), new Weight(1.0));

        mainWarehouse.addProduct(phone);
        mainWarehouse.addProduct(laptop);
        mainWarehouse.addProduct(tshirt);
        mainWarehouse.addProduct(milk);

        System.out.println("Товары на основном складе:");
        for (Product p : mainWarehouse) {
            System.out.println(p);
        }

        // Проверка двух независимых одновременных обходов
        Iterator<Product> it1 = mainWarehouse.iterator();
        Iterator<Product> it2 = mainWarehouse.iterator();
        System.out.println("Проверка независимых курсоров: it1 -> " + it1.next().getName() + ", it2 -> " + it2.next().getName());

        System.out.println("\n--- Сценарий 2: Копирование склада ---");
        Warehouse clonedWarehouse = mainWarehouse.clone();
        System.out.println("Склад успешно скопирован");

        System.out.println("Меняем цену и остаток айфона в копии склада");
        Product clonedPhone = clonedWarehouse.findById(phone.getId());
        clonedPhone.setPrice(new Price(95000));
        clonedPhone.changeQuantity(-5);

        System.out.println("Оригинальный склад (айфон должен сохранить цену 90000 и остаток 15):");
        System.out.println(mainWarehouse.findById(phone.getId()));
        System.out.println("Скопированный склад (айфон должен иметь цену 95000 и остаток 10):");
        System.out.println(clonedWarehouse.findById(phone.getId()));

        System.out.println("\n--- Сценарий 3: Присваивание второй переменной и владение объектами ---");
        Warehouse yandexWarehouse = mainWarehouse;
        System.out.println("Проверка ссылок (yandexWarehouse == mainWarehouse): " + (yandexWarehouse == mainWarehouse));
        System.out.println("Добавляем хлеб через aliasWarehouse...");
        ProductFactory breadFactory = new ProductFactories.BreadFactory();
        yandexWarehouse.addProduct(breadFactory.createProduct("Хлеб Бородинский", new Price(60), new Quantity(30), new Weight(0.5)));

        System.out.println("Размер через mainWarehouse (изменился, т.к. объект общий): " + mainWarehouse.getCurrentSize());

        yandexWarehouse = new Warehouse(2);
        System.out.println("После перепривязки yandexWarehouse на новый склад (yandexWarehouse == mainWarehouse): " + (yandexWarehouse == mainWarehouse));

        System.out.println("\n--- Сценарий 4: Поиск (по ID, категории, цене) и удаление (по ID и индексу) ---");
        System.out.println("1. Поиск по ID (" + phone.getId() + "):");
        System.out.println("Найдено: " + mainWarehouse.findById(phone.getId()));

        System.out.println("2. Поиск по категории (возьмем 'electronics'):");
        List<Product> electronics = mainWarehouse.findByCategory("electronics");
        for (Product p : electronics) {
            System.out.println("Найдено: " + p.getName());
        }

        System.out.println("3. Поиск по цене возьмем(1500 руб.):");
        List<Product> cheapItems = mainWarehouse.findByPrice(new Price(1500));
        for (Product p : cheapItems) {
            System.out.println("Найдено: " + p.getName());
        }

        System.out.println("4. Удаление товара по ID: " + laptop.getId() + " (THinkBook)");
        boolean removedById = mainWarehouse.removeById(laptop.getId());
        System.out.println("Удаление по ID успешно: " + removedById);

        System.out.println("5. Удаление товара по индексу 0:");
        boolean removedByIndex = mainWarehouse.removeByIndex(0);
        System.out.println("Удаление по индексу успешно: " + removedByIndex);

        System.out.println("Текущее состояние основного склада после удалений:");
        for (Product p : mainWarehouse) {
            System.out.println(p);
        }

        System.out.println("\n--- Сценарий 5: Перемещение товара, изменение остатка и цены ---");
        Warehouse reserveWarehouse = new Warehouse(3);
        System.out.println("Состояние футболки до перемещения: " + tshirt);

        tshirt.transferTo(reserveWarehouse);
        System.out.println("После пермещения:");
        System.out.println("На основном складе: " + mainWarehouse.findById(tshirt.getId()));
        System.out.println("На резервном складе: " + reserveWarehouse.findById(tshirt.getId()));

        tshirt.changeQuantity(10);
        tshirt.setPrice(new Price(1800));
        System.out.println("После изменения остатка (+10) и цены (1800 руб.) на основном складе:");
        System.out.println(mainWarehouse.findById(tshirt.getId()));

        reserveWarehouse.clear();
        System.out.println("Резервный склад после явной очистки clear(), товаров: " + reserveWarehouse.getCurrentSize());
    }
}
