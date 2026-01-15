package org.commerce;

public class Product {
    private final String id;
    private String name;
    private int price;
    private String description;
    private int quantity;

    public Product(String id, String name, int price, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public int getQuantity() {
        return quantity;
    }

}
