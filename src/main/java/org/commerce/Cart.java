package org.commerce;

import java.util.List;
import java.util.Optional;

public class Cart {
    // 카트에서 꼭 제품 객채를 들고있어야 할까..?
    // 그냥 제품 아이디와 해당 제품의 갯수만 가지고 있어도 될거같은데...
    // 그렇다고 그냥 id 와 quantity만 가지고있다면..? 객체를 리스트로 만들어야하니 이는 절대 X
    //private List<Product> productList;
    // 그럼 id 와 quantity를 가지고있는 cartItem 객체를 만들자.
    private List<CartItem> items;

    public void addItem(int productId , int quantity ) {
        items.add(new CartItem(productId , quantity));
    }

    public void removeItem(int productId) {
        //이미 저장된 데이터가 있는지 확인
        Optional<CartItem> isCartItem = findItemByProductId(productId);
        isCartItem.ifPresentOrElse(item -> items.remove(item) , () -> System.out.println("이미 장바구니에 없는 아이템입니다."));
    }

    public void addItemToCart(int productId, int quantity) {
        //이미 저장된 데이터가 있는지 확인
        Optional<CartItem> isCartItem = findItemByProductId(productId);
        isCartItem.ifPresentOrElse(item -> {
            // 있으면 기존 카트에 갯수만 추가.
            // 새로운 객체 생성 X
            item.manageProductQuantity(quantity);
        }, //없으면 새로운 카트 아이템 추가
                () -> items.add(new CartItem(productId , quantity)));

        System.out.println("장바구니 추가 완료!");
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }

    // 중복된 코드가있어서 그냥 메서드로 분리함.
    private Optional<CartItem> findItemByProductId(int productId) {
        return items.stream().filter(i -> i.getProductId() == productId).findFirst();
    }
}
