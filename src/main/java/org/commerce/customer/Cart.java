package org.commerce.customer;

import java.util.*;
import java.util.stream.Collectors;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();
    private int totalPrice = 0;

    /// productId 로 cart안에 담긴 상품 제품 제거.
    public void removeItem(String productId) {
        Optional<CartItem> isCartItem = findCartItemByProductId(productId);
        isCartItem.ifPresentOrElse(item -> {
            items.remove(item);
            manageTotalPrice(-(item.getProductQuantity() * item.getProductPrice()));
        } , () -> System.out.println("이미 장바구니에 없는 아이템입니다."));
    }

    ///  카트에 상품을 추가, 이미 상품이있다면 갯수만 증가합니다.
    public void addItemToCart(String productId, int quantity , String productName , int productPrice) {
        //이미 저장된 데이터가 있는지 확인
        Optional<CartItem> isCartItem = findCartItemByProductId(productId);
        isCartItem.ifPresentOrElse(item -> {
            // 있으면 기존 카트에 갯수만 추가.
            // 새로운 객체 생성 X
            item.manageProductQuantity(quantity);
        }, //없으면 새로운 카트 아이템 추가
                () -> items.add(new CartItem(productId,productName,productPrice,quantity)));
        manageTotalPrice(quantity * productPrice);
        System.out.println("장바구니 추가 완료!");
    }

    /// 카트에 담긴 물건의 수량을 조절 합니다.
    public void manageItemQuantity(String productId, int itemCount) {
        Optional<CartItem> isCartItem = findCartItemByProductId(productId);
        isCartItem.ifPresentOrElse(i -> {
                    i.manageProductQuantity(itemCount);
                    manageTotalPrice(itemCount * i.getProductPrice());
                },
                () -> System.out.println("장바구니에 존재하지 않은 제품입니다."));
    }

    /// 제품 Id로 카트 안에 담긴 제품을 조회. 다른 곳에서
    private Optional<CartItem> findCartItemByProductId(String productId) {
        return items.stream().filter(i -> productId.equals(i.getProductId())).findFirst();
    }

    /// 현재 카트의 총 결제 금액을 관리하는 메서드.
    /// 다른곳에서 관리 할 수 없도록 막기.
    private void manageTotalPrice(int totalPrice) {
        if(this.totalPrice + totalPrice <= 0){
            this.totalPrice = 0;
            return;
        }
        this.totalPrice += totalPrice;
    }

    public void cleanCart() {
        items.clear();
        totalPrice = 0;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    // primitive 이기때문에 어차피 복사본을 반환해서 복사해서 반환 할 필요 XX
    public int getTotalPrice() {
        return totalPrice;
    }
    @Override
    public String toString() {
        int totalAmount = items.stream()
                .mapToInt(item -> item.getProductPrice() * item.getProductQuantity())
                .sum();

        return items.stream()
                .map(CartItem::toString)
                .collect(Collectors.joining("\n"))
                + "\n================================================================================\n"
                + String.format("총 결제 금액: %,d원", totalAmount);
    }
}
