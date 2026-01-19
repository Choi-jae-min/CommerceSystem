package org.commerce;

import org.commerce.repository.ProductRepository;
import org.commerce.repository.ProductRepositoryImpl;

import java.util.List;

public class Category {
    private CategoryType categoryType;
    List<Product> productList;

    Category(CategoryType categoryType) {
        this.categoryType = categoryType;
        ProductRepositoryImpl productRepository = new ProductRepository();
        if(categoryType == CategoryType.BEVERAGE){
            this.productList = productRepository.getBeverage();
        }else {
            this.productList = productRepository.getALLProducts();
        }
    }

    public CategoryType getName() {
        return categoryType;
    }
    public List<Product> getProduct() {
        return productList;
    }

    public void setName(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public String toString() {
        return "Category [name=" + categoryType + ", product=" + productList + "]";
    }
}
