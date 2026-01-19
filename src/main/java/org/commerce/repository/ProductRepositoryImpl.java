package org.commerce.repository;

import org.commerce.Product;

import java.util.List;

public interface ProductRepositoryImpl {
    List<Product> getALLProducts();
    List<Product> getBeverage();
}
