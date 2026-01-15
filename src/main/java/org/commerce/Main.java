package org.commerce;

import org.commerce.repository.ProductRepository;
import org.commerce.repository.ProductRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductRepositoryImpl productRepository = new ProductRepository();
        // 저장소에서 데이터를 불러오기때문에 main은 product 객체를 알 필요가 없습니다.
        // 또한 데이터의 변경이있을 경우 main이나 product를 수정하는게 아니라 저장소의 데이터만 수정하면 됩니다.
        List<Product> productList = productRepository.getProducts();

        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        productList.forEach(product -> System.out.println(product.getId() + "." + product.getName() + " | " + product.getPrice() + " | " + product.getDescription() + " | " + product.getQuantity()));

        System.out.println("0. 종료    | 프로그램 종료");
        Scanner sc = new Scanner(System.in);
        int menu = sc.nextInt();

        if (menu == 0) {
            System.out.println("프로그램을 종료합니다.");
        }
    }
}