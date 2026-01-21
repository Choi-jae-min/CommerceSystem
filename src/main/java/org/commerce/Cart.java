package org.commerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cart {
    // 카트에서 꼭 제품 객채를 들고있어야 할까..?
    // 그냥 제품 아이디와 해당 제품의 갯수만 가지고 있어도 될거같은데...
    // 그렇다고 그냥 id 와 quantity만 가지고있다면..? 객체를 리스트로 만들어야하니 이는 절대 X
    //private List<Product> productList;
    // 그럼 id 와 quantity를 가지고있는 cartItem 객체를 만들자.
    private final List<CartItem> items = new ArrayList<>();

    public void removeItem(String productId) {
        //이미 저장된 데이터가 있는지 확인
        Optional<CartItem> isCartItem = findItemByProductId(productId);
        isCartItem.ifPresentOrElse(item -> items.remove(item) , () -> System.out.println("이미 장바구니에 없는 아이템입니다."));
    }

    public void addItemToCart(String productId, int quantity , String productName , int productPrice) {
        //이미 저장된 데이터가 있는지 확인
        Optional<CartItem> isCartItem = findItemByProductId(productId);
        isCartItem.ifPresentOrElse(item -> {
            // 있으면 기존 카트에 갯수만 추가.
            // 새로운 객체 생성 X
            item.manageProductQuantity(quantity);
        }, //없으면 새로운 카트 아이템 추가
                () -> items.add(new CartItem(productId,productName,productPrice,quantity)));
        System.out.println("장바구니 추가 완료!");
    }

    public void reduceItemQuantity(String productId, int reduceCount) {
        Optional<CartItem> isCartItem = findItemByProductId(productId);
        isCartItem.ifPresentOrElse(i -> i.manageProductQuantity(-reduceCount),
                () -> System.out.println("장바구니에 존재하지 않은 제품입니다."));
    }



//    이렇게 작성하면 객체에 직접적으로 영향을 줄 수 있기떄문에 다른 방식으로 캡슐화를 하는것이 좋다고 피드백을 받음.
//    public List<CartItem> getItems() {
//        return items;
//    }
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    // 중복된 코드가있어서 그냥 메서드로 분리함.
    private Optional<CartItem> findItemByProductId(String productId) {
        return items.stream().filter(i -> productId.equals(i.getProductId())).findFirst();
    }

    @Override
    public String toString() {
        return items.stream()
                .map(CartItem::toString)
                .collect(Collectors.joining("\n"));
    }

}
