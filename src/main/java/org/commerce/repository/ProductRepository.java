package org.commerce.repository;

import org.commerce.product.CategoryType;
import org.commerce.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    }

    /// id와 같은 상품을 찾고, 입력받은 quantity로 업데이트 친다.
    @Override
    public Boolean updateQuantityByProductId(String id, Integer quantity) {
        Optional<Product> isProduct = MockProductData.all().stream()
                .filter(p -> p.getId().equals(id)).findFirst();

        isProduct.ifPresentOrElse(p -> p.setQuantity(quantity) , () -> {throw new RuntimeException(
                "해당 id의 제품이 존재 하지 않습니다. id: " + id);});
        return true;
    }

    @Override
    public Boolean checkValidName(String productName) {
        Optional<Product> isProduct = MockProductData.all().stream().filter(product -> product.getName().equals(productName)).findFirst();
        return isProduct.isEmpty();
    }

    @Override
    public void updateQuantityByProductName(String name, Integer quantity) {
        Optional<Product> isProduct = MockProductData.all().stream()
                .filter(p -> p.getName().equals(name)).findFirst();

        isProduct.ifPresentOrElse(p -> p.setQuantity(quantity) , () -> {throw new RuntimeException(
                "해당 이름의 제품이 존재 하지 않습니다. name: " + name);});
    }

    @Override
    public void updatePriceByProductName(String name, Integer newPrice) {
        Optional<Product> isProduct = MockProductData.all().stream()
                .filter(p -> p.getName().equals(name)).findFirst();

        isProduct.ifPresentOrElse(p -> p.setPrice(newPrice) , () -> {throw new RuntimeException(
                "해당 이름의 제품이 존재 하지 않습니다. name: " + name);});

    }

    @Override
    public void updateDescriptionByProductName(String name, String description) {
        Optional<Product> isProduct = MockProductData.all().stream()
                .filter(p -> p.getName().equals(name)).findFirst();

        isProduct.ifPresentOrElse(p -> p.setDescription(description) , () -> {throw new RuntimeException(
                "해당 이름의 제품이 존재 하지 않습니다. name: " + name);});
    }

    @Override
    public String removeProductByName(String productName) {
        Optional<Product> target = MockProductData.all().stream()
                .filter(p -> p.getName().equals(productName)).findFirst();

        if (target.isEmpty()) {
            throw new RuntimeException("해당 이름의 상품이 존재하지 않습니다. name: " + productName);
        }
        // 있으면 상품을 제거하고
        Product product = target.get();
        MockProductData.all().remove(product);
        // 제거된 id 리턴.
        return product.getId();
    }

    @Override
    public List<Product> getProductsUnderPrice(int maxPrice) {
        return MockProductData.all().stream()
                .filter(p -> p.getPrice() < maxPrice)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Product> getProductsOverPrice(int minPrice) {
        return MockProductData.all().stream()
                .filter(p -> p.getPrice() > minPrice)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

