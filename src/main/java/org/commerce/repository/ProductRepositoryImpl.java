package org.commerce.repository;

import org.commerce.product.CategoryType;
import org.commerce.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryImpl {
    List<Product> getALLProducts();

    List<Product> getByCategory(CategoryType categoryType);

    Optional<Product> findById(String id);

    void addProduct(Product product);

    Boolean updateQuantityByProductId(String id , Integer quantity);

    void incrementQuantityByProductId(String id , Integer quantity);

    void updateQuantityByProductName(String name , Integer quantity);

    void updatePriceByProductName(String name , Integer newPrice);

    void updateDescriptionByProductName(String name , String description);

    Boolean checkValidName(String productName);

    String removeProductByName(String productName);

    List<Product> getProductsUnderPrice(int maxPrice);

    List<Product> getProductsOverPrice(int minPrice);
}
