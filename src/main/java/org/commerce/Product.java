package org.commerce;

public class Product {
    private final int ID;
    private String name;
    private int price;
    private String description;
    private int quantity;

    Product(int id, String name, int price, String description, int quantity) {
        this.ID = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
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
