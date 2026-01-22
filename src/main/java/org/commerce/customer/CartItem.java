package org.commerce.customer;

public class CartItem {
    private final String productId;
    private final String productName;
    private final int productPrice;
    private int productQuantity;

    public CartItem(String productId,  String productName,int productPrice, int productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void manageProductQuantity(int productQuantity) {
        if(this.productQuantity + productQuantity < 0) {
            this.productQuantity = 0;
        }
        this.productQuantity += productQuantity;
    }

    @Override
    public String toString() {
        return """
                id = %s | 상품명 = %s | 가격 = %,d | 수량 = %d
                """.formatted(productId ,productName, productPrice, productQuantity);
    }
}
