package org.commerce;

import java.util.Optional;
import java.util.Scanner;

public class CommerceSystem {
    private Category category;
    private final Scanner sc;

    CommerceSystem() {
        sc = new Scanner(System.in);
    }
    public void start(){
        // 메뉴 출력을 하나의 메서드로 분리.
        // -> 해당 start가 정확히 처음에 무엇을 하는지 명확하지 않다고 생각이 들어서 분리하였습니다.
        showMenu();
    }

    private void showMenu() {
        char menu;
        for (;;){
            System.out.println("[ 실시간 커머스 플랫폼 ]");
            System.out.println("1. 전체 상품");
            System.out.println("2. 전자 제품");
            System.out.println("3. 주방 용품");
            System.out.println("4. 음식");
            System.out.println("5. 음료");
            System.out.println("0. 종료    | 프로그램 종료");
            menu = sc.next().charAt(0);
            switch (menu){
                case '1' -> category = new Category(CategoryType.ALL);
                case '2' -> category = new Category(CategoryType.ELECTRONIC);
                case '3' -> category = new Category(CategoryType.KITCHEN);
                case '4' -> category = new Category(CategoryType.FOOD);
                case '5' -> category = new Category(CategoryType.BEVERAGE);
            }
            // 제품 선택 메서드
            selectProduct();

            if (menu == '0') {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
    }

    // 상품 구매 프로세수
    public void selectProduct(){
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
            return;
        }
        Optional<Product> selectProduct = category.getProduct()
                .stream()
                .filter(product -> selectProductID.equals(product.getId()))
                .findFirst();

        // id가 같은 값이 없으면 "상품을 잘못 선택 하셨습니다 출력
        if(selectProduct.isEmpty()){
            System.out.println("상품을 잘못 선택 하셨습니다.");
        }else{
            System.out.println("선택하신 상품 = " + selectProduct.get());
        }
    }
}
