package org.commerce;

import java.util.Optional;
import java.util.Scanner;

public class CommerceSystem {
    private Category category;
    private final Scanner sc;
    Customer customer;

    CommerceSystem() {
        System.out.println("어서오세요 성함과 이메일을 입력해주세요.");
        sc = new Scanner(System.in);
        System.out.print("고객님의 성함 : ");
        String customerName = sc.nextLine();
        System.out.print("고객님의 이메일 : ");
        String customerEmail = sc.nextLine();
        customer = new Customer(customerName , customerEmail);

        System.out.println("환영합니다" + customerName + "님!");
        System.out.println("즐거운 시간보내세요! ^^7");
    }

    public void start(){
        showMenu();
    }

    private void showMenu() {
        char menu;
        for (;;){
            // 시작시 초기 메뉴를 장바구니 혹은 상품을 볼 수 있도록 변경.
            System.out.println("[ 실시간 커머스 플랫폼 ]");
            System.out.println("원하시는 제품 카테고리를 선택 해 주세요.");
            System.out.println("1. 상품보기");
            System.out.println("2. 장바구니");
            System.out.println("0. 종료    | 프로그램 종료");
            menu = sc.next().charAt(0);
            switch (menu){
                // 상품 보기를 하나의 프로세스로 묶어서 메서드 화.
                case '1' -> shoppingProcess();
                // 장바구니 컨트롤러 호출.
                case '2' -> customer.cart.cartController(sc);
            }
//            Product selectedProduct = selectProduct();
//            Buy(selectedProduct);

            if (menu == '0') {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
    }

    // 기존에 상품선택에서 결제 까지 진행했던 사항을 장바구니에 추가하는 로직으로 변경
    private void shoppingProcess() {
        showProductCategory();
        try {
            Optional<Product> selectedProduct = selectProduct();
            selectedProduct.ifPresent(product -> {
                System.out.println("선택하신 상품을 장바구니에 넣을까요? 0: 뒤로가기 | 그외 숫자 : 장바구니에 넣을 숫자.");
                int quantity = sc.nextInt();
                if (quantity == 0){
                    System.out.println("제품 추가 취소");
                    return;
                }
                //올바른 갯수를 입력했는지 확인
                product.checkIsValidQuantity(quantity);
                // 고객이 들고있는 카트에 아이템 추가
                customer.cart.addItemToCart(product.getId(),quantity,product.getName(),product.getPrice());
            });
        }catch (Exception e) {
            System.out.println("에러 발생. 다시 시도 해 주세요 = " + e);
        }
    }


    private void showProductCategory() {
        System.out.println("상품 카테고리를 선택 해 주세요.");
        System.out.println("1. 전체 상품");
        System.out.println("2. 전자 제품");
        System.out.println("3. 주방 용품");
        System.out.println("4. 음식");
        System.out.println("5. 음료");
        char menu = sc.next().charAt(0);
        switch (menu){
            case '1' -> category = new Category(CategoryType.ALL);
            case '2' -> category = new Category(CategoryType.ELECTRONIC);
            case '3' -> category = new Category(CategoryType.KITCHEN);
            case '4' -> category = new Category(CategoryType.FOOD);
            case '5' -> category = new Category(CategoryType.BEVERAGE);
        }
    }

    // 옵셔널로 변경. 상품이 있을 수도 없을 수도 있기때문에.
    private Optional<Product> selectProduct(){
        System.out.println("==================================");
        System.out.printf("[ %s 카테고리 ] 원하시는 상품 ID를 입력하세요." , category.getCategoryType().getCategoryType());
        System.out.println();

        String productString = category.toString();
        //상품 리스트 출력
        System.out.print(productString);
        System.out.println("0 -> 뒤로가기");
        System.out.println("==================================");

        sc.nextLine();
        String selectProductID = sc.nextLine();
        // "0" 입력시 메서드 종료
        if("0".equals(selectProductID)){
            return Optional.empty();
        }
        Optional<Product> selectProduct = category.getProduct()
                .stream()
                .filter(product -> selectProductID.equals(product.getId()))
                .findFirst();

        // id가 같은 값이 없으면 "상품을 잘못 선택 하셨습니다 에러 던기지.
        if(selectProduct.isEmpty()){
            throw new RuntimeException("상품을 잘못 선택 하셨습니다.");
        }
        return selectProduct;
    }

    public void Buy(Product product){
        System.out.println("선택하신 제품을 구매 하시겠습니까? 1. 구매  | 2. 취소");
        int buy = sc.nextInt();
        if(buy==2) return;

        int customerMoney = customer.getMoney();

        if(product.getPrice() > customerMoney){
            System.out.println("금액이 부족합니다.");
            System.out.println("현재 소지금액 = " + customerMoney);
            return;
        }
        product.setQuantity(product.getQuantity() - 1);
        customer.payment(product.getPrice());
        customer.setGrade();

        System.out.println("상품구매에 성공 하였습니다.");
        String customerInfo = customer.toString();
        System.out.println("구매 후 고객님의 정보" + customerInfo );
    }
}
