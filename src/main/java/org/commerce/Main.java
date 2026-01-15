package org.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();

        //1. 제품을 하나하나 객체로 만들어서 전체 리스트 객체로 집어넣는다.
        Product product1 = new Product(1,"Galaxy S25" , 1200000 , "최신 안드로이드 스마트폰" ,100);
        Product product2 = new Product(2,"iPhone 16" , 1350000 , "Apple의 최신 스마트폰" ,50);
        Product product3 = new Product(3,"MacBook Pro" , 2400000 , "M3 칩셋이 탑재된 노트북" ,100);
        Product product4 = new Product(4,"AirPods Pro" , 350000 , "노이즈 캔슬링 무선 이어폰" ,100);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);

        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        productList.forEach(product -> {
            System.out.println(product.getID() + "." + product.getName() + " | " + product.getPrice() + " | " + product.getDescription() + " | " + product.getQuantity());
        });

        System.out.println("0. 종료    | 프로그램 종료");
        Scanner sc = new Scanner(System.in);
        int menu = sc.nextInt();

        if (menu == 0) {
            System.out.println("프로그램을 종료합니다.");
        }
    }
}