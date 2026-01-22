package org.commerce.product;

public class Product {
    private final String id;
    private String name;
    private int price;
    private String description;
    private int quantity;
    private String category;

    public Product(String id, String name, int price, String description, int quantity , String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
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
    public void decrementQuantity(int quantity) {
        this.quantity -= quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    public void checkIsValidQuantity(int quantity) {
        if(this.quantity < quantity) {
            throw new IllegalArgumentException("현재 재고 수량보다 많은 수가 입력 되었습니다.");
        }
    }

    @Override
    public String toString() {
        return " name = " + name +
                ", price = " + String.format("%,d", price) +
                ", description = " + description +
                ", quantity= " + quantity;
    }
}
