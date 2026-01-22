package org.commerce.customer;

public class Customer {
    private String name;
    private String email;
    private GradeType grade;
    private int totalPaymentPrice;
    private int money;
    public Cart cart;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.grade = GradeType.BRONZE;
        this.totalPaymentPrice = 0;
        this.money = 1_000_000_000;
        this.cart = new Cart();
    }

    public int getMoney() {
        return money;
    }


    public String getName() {
        return this.name;
    }

    /// 고객이 돈을 지불합니다.
    public void payment(int productPrice) {
        this.totalPaymentPrice += productPrice;
        setGrade();
        //이번 결제 금액까지 더해 할인률 적용.
        double discountRate = switch (grade) {
            case BRONZE -> 0;
            case SLIVER -> 0.05;
            case GOLD -> 0.1;
            case PLATINUM -> 0.15;
        };
        //혹시 할인 금액 소수점이 나오면 올림 처리
        int discountAmount = (int) Math.ceil(productPrice * discountRate);
        System.out.println("총 할인 금액 = " + discountAmount);
        int finalPrice = productPrice - discountAmount;

        System.out.println("결제 금액 = " + finalPrice);
        this.money -= finalPrice;
    }

    /// 고객의 총 결제 금액에 따라 (원가) 등급을 설정.
    private void setGrade() {
        if(this.totalPaymentPrice < 500_000){
            this.grade = GradeType.BRONZE;
        }
        else if(this.totalPaymentPrice < 1_000_000){
            this.grade = GradeType.SLIVER;
        }
        else if(this.totalPaymentPrice <= 2_000_000){
            this.grade = GradeType.GOLD;
        }
        else {
            this.grade = GradeType.PLATINUM;
        }
    }

    @Override
    public String toString() {
        return "고객님의 등급 = " + grade +
                " | 총 결제금액 = " + totalPaymentPrice +
                " | 보유 금액 = " + money;
    }
}
