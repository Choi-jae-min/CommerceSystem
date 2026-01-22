package org.commerce;

import org.commerce.repository.ProductRepository;
import org.commerce.repository.ProductRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Category {
    private final CategoryType categoryType;
    // 이건 그냥 사용자가 보기위한 제품리스트를 담는 복사본일 뿐,
    // 실제 데이터는  repo안에있다.
    // 그니까 이 데이터를 백날 수정해봤자. 실제 DB에는 영양이 가지 않기때문에,
    // 지금같은 repo를 사용하는 구조에서는 해당객체를 수정할게 아니라 Repo를 수정하는 방법을 찾아야 한다.
    private final List<Product> productList;
    public ProductRepositoryImpl productRepository;

    Category(CategoryType categoryType) {
        this.categoryType = categoryType;
        this.productRepository = new ProductRepository();

        if(CategoryType.ALL == categoryType){
            this.productList = productRepository.getALLProducts();
        }else{
            this.productList = productRepository.getByCategory(categoryType);
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
        return productList.stream()
                .map(product ->
                        "상품 ID = " + product.getId()
                                + " | 상품 이름 = " + product.getName()
                                + " | 상품 가격 = " + String.format("%,d", product.getPrice())
                                + " | 잔여 갯수 = " + product.getQuantity()
                )
                .collect(Collectors.joining("\n"));
    }
}
