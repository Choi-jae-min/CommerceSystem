package org.commerce;

public enum CategoryType {
    ELECTRONIC("전자 제품"),
    FOOD("음식"),
    BEVERAGE("음료"),
    KITCHEN("주방 용품"),
    ALL("전체");

    final String categoryType;
    CategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryType() {
        return categoryType;
    }
}
