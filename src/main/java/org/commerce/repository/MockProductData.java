package org.commerce.repository;

import org.commerce.product.CategoryType;
import org.commerce.product.Product;

import java.util.ArrayList;
import java.util.List;

public class MockProductData {
    private MockProductData() {}

    // Double Brace Initialization 방식으로 처음 구현 하였다가
    // 불필요한 익명 클래스 생성 등 문제가있고 static 블록 을 사용하는것을 권장한다는 글을 보고 수정.
    private static final ArrayList<Product> PRODUCTS = new ArrayList<>();
    static {
        PRODUCTS.add(new Product("1", "Galaxy S25", 1_200_000, "최신 안드로이드 스마트폰", 100, CategoryType.ELECTRONIC.getCategoryType()));
        PRODUCTS.add(new Product("2", "iPhone 16", 1_350_000, "Apple의 최신 스마트폰", 50, CategoryType.ELECTRONIC.getCategoryType()));
        PRODUCTS.add(new Product("3", "MacBook Pro", 2_400_000, "M3 칩셋이 탑재된 노트북", 100, CategoryType.ELECTRONIC.getCategoryType()));
        PRODUCTS.add(new Product("4", "AirPods Pro", 350_000, "노이즈 캔슬링 무선 이어폰", 100, CategoryType.ELECTRONIC.getCategoryType()));

        PRODUCTS.add(new Product("5", "아이스 아메리카노", 3_500, "고소한 풍미의 아메리카노", 999, CategoryType.BEVERAGE.getCategoryType()));
        PRODUCTS.add(new Product("6", "아이스 라떼", 4_000, "맛좋은 라떼", 800, CategoryType.BEVERAGE.getCategoryType()));
        PRODUCTS.add(new Product("7", "아로마노트 원두", 5_000, "과일향이 상큼하게나는 산미있는 커피", 200, CategoryType.BEVERAGE.getCategoryType()));
        PRODUCTS.add(new Product("8", "궁극의 아인슈페너", 5_500, "먹어는 봤나 궁극의 아인슈페너", 10, CategoryType.BEVERAGE.getCategoryType()));

        PRODUCTS.add(new Product("9", "페페로니 피자", 35_000, "짭짤한 맛있는 페페로니", 999, CategoryType.FOOD.getCategoryType()));
        PRODUCTS.add(new Product("10", "후라이드 치킨", 24_000, "바삭 고소 후라이드 치킨", 800, CategoryType.FOOD.getCategoryType()));
        PRODUCTS.add(new Product("11", "대패삼겹살", 25_000, "삼겹은 역시 대패!", 200, CategoryType.FOOD.getCategoryType()));
        PRODUCTS.add(new Product("12", "우리한돈", 155_000, "입살녹이란 무엇인가 우리한돈", 10, CategoryType.FOOD.getCategoryType()));

        PRODUCTS.add(new Product("13", "반찬통", 5_500, "보관이 편리한 반찬통", 1, CategoryType.KITCHEN.getCategoryType()));
        PRODUCTS.add(new Product("14", "트레이 세트", 10_000, "크기별로 나뉜 트레이세트", 2, CategoryType.KITCHEN.getCategoryType()));
        PRODUCTS.add(new Product("15", "바겐슈타이거 후라이펜", 25_000, "완전 편한 후라이펜", 5, CategoryType.KITCHEN.getCategoryType()));
        PRODUCTS.add(new Product("16", "바겐슈타이거 국자", 13_500, "귀여운 사이즈의 국자", 10, CategoryType.KITCHEN.getCategoryType()));
    }

    public static List<Product> all() {
        return PRODUCTS;
    }

    public static void addProduct(Product product) {
        PRODUCTS.add(product);
    }
}
