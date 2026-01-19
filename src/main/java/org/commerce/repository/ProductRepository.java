package org.commerce.repository;

import org.commerce.Product;

import java.util.List;

// Repository 생성, main 에서 product 객체를 알고 하나하나 넣어 주는게 아닌, 어딘가에 저장되어있는 데이터들을 List<product> 객체로 변경하여 반환.
// 해당 클래스는 ProductRepositoryImpl 라는 인터페이스를 상속받아 getProducts 라는 메서드를 필수로 만들도록 설정해줍니다.
public class ProductRepository implements ProductRepositoryImpl {
    public List<Product> getALLProducts() {
        return List.of(
                new Product("1", "Galaxy S25", 1_200_000, "최신 안드로이드 스마트폰", 100),
                new Product("2", "iPhone 16", 1_350_000, "Apple의 최신 스마트폰", 50),
                new Product("3", "MacBook Pro", 2_400_000, "M3 칩셋이 탑재된 노트북", 100),
                new Product("4", "AirPods Pro", 350_000, "노이즈 캔슬링 무선 이어폰", 100)
        );
    }

    public List<Product> getBeverage() {
        return List.of(
                new Product("5", "아이스 아메리카노", 3_500, "고소한 풍미의 아메리카노", 999),
                new Product("6", "아이스 라떼", 4_000, "맛좋은 라떼", 800),
                new Product("7", "아로마노트 원두", 5_000, "과일향이 상큼하게나는 산미있는 커피", 200),
                new Product("8", "궁극의 아인슈페너", 5_500, "먹어는 봤나 궁극의 아인슈페너", 10)
        );
    }
}
