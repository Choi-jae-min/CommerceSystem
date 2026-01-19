package org.commerce;

import java.util.Scanner;

public class CommerceSystem {
    Category category;

    CommerceSystem(){
        this.category = new Category(CategoryType.ALL);
    }

    public void start(){
        System.out.println("[ 실시간 커머스 플랫폼 ]");
        String productString = category.toString();
        System.out.println(productString);

        System.out.println("0. 종료    | 프로그램 종료");
        Scanner sc = new Scanner(System.in);
        int menu = sc.nextInt();

        if (menu == 0) {
            System.out.println("프로그램을 종료합니다.");
        }
    }
}
