package org.commerce.repository;

import org.commerce.CategoryType;
import org.commerce.Product;

import java.util.List;
import java.util.Optional;

// 데이터를 여기서 불러와야 하기 때문에.
// category에있는 객체를 백날 수정해봐야 의미가 없다.
public class ProductRepository implements ProductRepositoryImpl {

    @Override
    public List<Product> getALLProducts() {
        return MockProductData.all();
    }

    @Override
    public List<Product> getByCategory(CategoryType categoryType) {
        return MockProductData.all().stream()
                .filter(p -> p.getCategory().equals(categoryType.getCategoryType()))
                .toList();
    }

    @Override
    public Optional<Product> findById(String id) {
        return MockProductData.all().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public void addProduct(Product product) {
        MockProductData.addProduct(product);
    };

    /// id와 같은 상품을 찾고, 입력받은 quantity로 업데이트 친다.
    @Override
    public Boolean updateQuantityByProductId(String id, Integer quantity) {
        MockProductData.updateQuantityByProductId(id, quantity);
        return true;
    }

    @Override
    public Boolean checkValidName(String productName) {
        Optional<Product> isProduct = MockProductData.all().stream().filter(product -> product.getName().equals(productName)).findFirst();
        return isProduct.isEmpty();
    }
}

