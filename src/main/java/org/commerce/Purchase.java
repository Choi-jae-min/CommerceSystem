package org.commerce;


import java.util.HashMap;
import java.util.UUID;

public class Purchase {
    private final String id;
    private final HashMap<String, Integer> productInfo = new HashMap<>();
    private int totalPrice = 0;

    Purchase() {
        // 랜덤한 ID 생성.
        this.id = UUID.randomUUID().toString();
    }

    ///  구매 상품 리스트를 추가한다.
    public void addProductId(String productId ,int count, int price) {
        this.productInfo.put(productId , count);
        addTotalPrice(price * count);
    }

    /// 상품의 총 가격을 관리한다.
    private void addTotalPrice(int totalPrice) {
        this.totalPrice += totalPrice;
    }

    public HashMap<String, Integer> getProductId() {
        return productInfo;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
