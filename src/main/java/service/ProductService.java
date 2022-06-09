package service;

import ru.product.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductService {
    private static Map<String, Product> products = new HashMap<>();
    static
    {
        products.put("Fanta",new Product("Fanta","Drinks","Orange","Cola Company",120,14));
        products.put("Kefir",new Product("Kefir","DiaryProduct","White","Prostokvashino",60,16));
        products.put("Milk",new Product("Milk","DiaryProduct","White","Prostokvashino",90,18));
        products.put("Yogurt",new Product("Yogurt","DiaryProduct","White","Prostokvashino",70,12));
        products.put("iPhone X",new Product("iPhone X","Phone","Red","Apple",76000,17));
        products.put("Galaxy S9",new Product("Galaxy S9","Phone","Blue","Samsung",45000,20));
        products.put("Cola", new Product("Cola ","Drinks","Green","Pepsi",100,46));


    }

    public static synchronized Map<String, Product> getAll() {
        return products;

    }

    public static Product get(String name) {

        return null;
    }


    public static Map<String, Product> getByNameAndCategory(String name, String category) {
        return null;
    }


    public static boolean delete(String name) {
        return false;
    }


    public static synchronized void add(Product product) {

        products.put(product.getName(), product);

    }

}
