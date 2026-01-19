package org.commerce;

import org.commerce.repository.ProductRepository;
import org.commerce.repository.ProductRepositoryImpl;

import java.util.List;

public class Category {
    private final CategoryType categoryType;
    private final List<Product> productList;

    Category(CategoryType categoryType) {
        this.categoryType = categoryType;
        ProductRepositoryImpl productRepository = new ProductRepository();
        switch (categoryType) {
            case FOOD -> this.productList = productRepository.getFood();
            case BEVERAGE -> this.productList = productRepository.getBeverage();
            case ELECTRONIC -> this.productList = productRepository.getElectronic();
            case KITCHEN -> this.productList = productRepository.getKitchen();
            default -> this.productList = productRepository.getALLProducts();
        }
    }
    public CategoryType getCategoryType() {
        return categoryType;
    }

    public List<Product> getProduct() {
        return productList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : productList) {
            sb.append("상품 ID = ").append(product.getId())
                    .append(" | 상품 이름 = ").append(product.getName())
                    .append(" | 상품 가격 = ").append(String.format("%,d", product.getPrice()))
                    .append(" | 잔여 갯수 = ").append(product.getQuantity())
                    .append("\n");
        }

        return sb.toString();
    }

}
