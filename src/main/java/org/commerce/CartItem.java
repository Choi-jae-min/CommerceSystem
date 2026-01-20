package org.commerce;

public class CartItem {
    private final int productId;
    private int productQuantity;

    public CartItem(int productId, int productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void manageProductQuantity(int productQuantity) {
        this.productQuantity += productQuantity;
    }

}
