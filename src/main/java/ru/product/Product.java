package ru.product;

public class Product {

    private String name;

    private String category;

    private String colour;

    private String brand;

    private double price;

    private double barcode;


    public Product(String name, String category, String colour, String brand, double price, double barcode) {
        this.name = name;
        this.category = category;
        this.colour = colour;
        this.brand = brand;
        this.price = price;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBarcode() {
        return barcode;
    }

    public void setBarcode(double barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", colour='" + colour + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", barcode=" + barcode +
                '}';
    }
}
