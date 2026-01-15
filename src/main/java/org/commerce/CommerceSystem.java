package org.commerce;

import org.commerce.repository.ProductRepository;
import org.commerce.repository.ProductRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    //상품 리스트 객체를 여기서 관리
    List<Product> productList;

    // 생성자를 통해서 ProductRepositoryImpl 를 매개변수로 받고( 의존선 주입?? ) 데이터 할당
    CommerceSystem(ProductRepositoryImpl productRepository){
        this.productList = productRepository.getProducts();
    }

    // start 함수를 통해 커머스 시스템을 시작
    public void start(){
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
