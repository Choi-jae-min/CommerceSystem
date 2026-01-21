package org.commerce;

import java.util.*;
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

    public void manageItemQuantity(String productId, int itemCount) {
        Optional<CartItem> isCartItem = findItemByProductId(productId);
        isCartItem.ifPresentOrElse(i -> i.manageProductQuantity(itemCount),
                () -> System.out.println("장바구니에 존재하지 않은 제품입니다."));
    }

    // 커머스 시스템에서 처음에 이런 서비스를 만들려했는데 튜텨님과 대화하다보니 카트를 관리하는 서비스인데,
    // 왜 커머스 시스템에 만들지? 여기서 만들어서 커머스시스템은 그냥 활용만 하면 되는거 아닌가? 생각이 들었습니다.
    public void cartController(Scanner sc) {
        if (this.items.isEmpty()) {
            System.out.println("장바구니가 비어있습니다.");
            return;
        }
        System.out.println("=================================== 장바구니 =============================================");
        System.out.println("장바구니 리스트");
        System.out.println(this);

        System.out.println("1. 상품구매 | 2. 수량 조절 | 3. 상품 삭제 | 0. 뒤로");
        int cartMenu = sc.nextInt();
        if (cartMenu == 0) {
            return;
        }
        System.out.println("원하는 상품을 선택 해주세요.");
        String inputCardItemId = sc.next().trim();
        switch (cartMenu) {
            case 1 -> System.out.println("상품 구매 프로세스");
            case 2 -> {
                System.out.println("추가 혹은 뺴기 원하는 수를 입력 해 주세요 ex) 추가 : 4 빼기 : -4");
                int itemCount = sc.nextInt();
                manageItemQuantity(inputCardItemId , itemCount);
            }
            case 3 -> removeItem(inputCardItemId);
        }
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
