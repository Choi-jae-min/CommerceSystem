package org.commerce;

public class CartItem {
    private final String productId;
    // 이름과 가격같은 최소한의 정보는 추가적으로 들고있는게 시스템 상 편해서 추가적으로 작성.
    // 없을경우 또 조회해서 이름이랑 가격을 찾아서 반환해줘야하는데 그것보단 이름과 가격을들고있는게 합리적이라고 생각했습니다.
    private final String productName;
    private final int productPrice;
    private int productQuantity;

    public CartItem(String productId,  String productName,int productPrice, int productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void manageProductQuantity(int productQuantity) {
        this.productQuantity += productQuantity;
    }

    @Override
    public String toString() {
        return """
                상품명 = %s | 가격 = %,d | 수량 = %d
                """.formatted(productName, productPrice, productQuantity);
    }
}
