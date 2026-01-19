package org.commerce;

import java.util.Scanner;

public class CommerceSystem {
    Category category;

    CommerceSystem(){
        this.category = new Category(CategoryType.BEVERAGE);
    }

    // start 함수를 통해 커머스 시스템을 시작
    public void start(){
        System.out.println("[ 실시간 커머스 플랫폼 ]");
        category.productList.forEach(product -> System.out.println(product.getId() + "." + product.getName() + " | " + product.getPrice() + " | " + product.getDescription() + " | " + product.getQuantity()));

        System.out.println("0. 종료    | 프로그램 종료");
        Scanner sc = new Scanner(System.in);
        int menu = sc.nextInt();

        if (menu == 0) {
            System.out.println("프로그램을 종료합니다.");
        }
    }
}
