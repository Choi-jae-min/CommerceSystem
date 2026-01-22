package org.commerce.repository;

import org.commerce.CategoryType;
import org.commerce.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryImpl {
    List<Product> getALLProducts();

    List<Product> getByCategory(CategoryType categoryType);

    Optional<Product> findById(String id);

    void addProduct(Product product);

    Boolean updateQuantityByProductId(String id , Integer quantity);

    Boolean checkValidName(String productName);
}
