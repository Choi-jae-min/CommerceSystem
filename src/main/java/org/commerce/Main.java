package org.commerce;

import org.commerce.repository.ProductRepository;
import org.commerce.repository.ProductRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        // 의존성 주입을 위해  ProductRepositoryImpl 객체를 바깥에서 생성.
        ProductRepositoryImpl productRepository = new ProductRepository();
        // 이제 메인에서 상품은 전혀 관리할 필요없이 커머스 시스템을 단순히 start 해주면 됩니다.
        CommerceSystem commerceSystem = new CommerceSystem(productRepository);
        commerceSystem.start();
    }
}