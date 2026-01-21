package org.commerce.repository;

import org.commerce.CategoryType;
import org.commerce.Product;

import java.util.List;
import java.util.Optional;

// 데이터를 여기서 불러와야 하기 때문에.
// category에있는 객체를 백날 수정해봐야 의미가 없다.
public class ProductRepository implements ProductRepositoryImpl {
    private static final List<Product> PRODUCTS = List.of(
            new Product("1", "Galaxy S25", 1_200_000, "최신 안드로이드 스마트폰", 100, CategoryType.ELECTRONIC.getCategoryType()),
            new Product("2", "iPhone 16", 1_350_000, "Apple의 최신 스마트폰", 50, CategoryType.ELECTRONIC.getCategoryType()),
            new Product("3", "MacBook Pro", 2_400_000, "M3 칩셋이 탑재된 노트북", 100, CategoryType.ELECTRONIC.getCategoryType()),
            new Product("4", "AirPods Pro", 350_000, "노이즈 캔슬링 무선 이어폰", 100, CategoryType.ELECTRONIC.getCategoryType()),

            new Product("5", "아이스 아메리카노", 3_500, "고소한 풍미의 아메리카노", 999, CategoryType.BEVERAGE.getCategoryType()),
            new Product("6", "아이스 라떼", 4_000, "맛좋은 라떼", 800, CategoryType.BEVERAGE.getCategoryType()),
            new Product("7", "아로마노트 원두", 5_000, "과일향이 상큼하게나는 산미있는 커피", 200, CategoryType.BEVERAGE.getCategoryType()),
            new Product("8", "궁극의 아인슈페너", 5_500, "먹어는 봤나 궁극의 아인슈페너", 10, CategoryType.BEVERAGE.getCategoryType()),

            new Product("9", "페페로니 피자", 35_000, "짭짤한 맛있는 페페로니", 999, CategoryType.FOOD.getCategoryType()),
            new Product("10", "후라이드 치킨", 24_000, "바삭 고소 후라이드 치킨", 800, CategoryType.FOOD.getCategoryType()),
            new Product("11", "대패삼겹살", 25_000, "삼겹은 역시 대패!", 200, CategoryType.FOOD.getCategoryType()),
            new Product("12", "우리한돈", 155_000, "입살녹이란 무엇인가 우리한돈", 10, CategoryType.FOOD.getCategoryType()),

            new Product("17", "반찬통", 5_500, "보관이 편리한 반찬통", 1, CategoryType.KITCHEN.getCategoryType()),
            new Product("18", "트레이 세트", 10_000, "크기별로 나뉜 트레이세트", 2, CategoryType.KITCHEN.getCategoryType()),
            new Product("19", "바겐슈타이거 후라이펜", 25_000, "완전 편한 후라이펜", 5, CategoryType.KITCHEN.getCategoryType()),
            new Product("20", "바겐슈타이거 국자", 13_500, "귀여운 사이즈의 국자", 10, CategoryType.KITCHEN.getCategoryType())
    );

    @Override
    public List<Product> getALLProducts() {
        return PRODUCTS;
    }

    public List<Product> getByCategory(CategoryType categoryType) {
        return PRODUCTS.stream()
                .filter(p -> p.getCategory().equals(categoryType.getCategoryType()))
                .toList();
    }

    public Optional<Product> findById(String id) {
        return PRODUCTS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}
