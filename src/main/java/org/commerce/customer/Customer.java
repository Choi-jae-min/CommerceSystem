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
        this.money = 10;
        this.cart = new Cart();
    }

    public int getMoney() {
        return money;
    }


    public String getName() {
        return this.name;
    }

    /// 고객이 돈을 지불합니다.
    public void payment(int productPrice) throws Exception {
        int tmepTotalPaymentPrice = this.totalPaymentPrice + productPrice;
        GradeType tempGrade = checkGrade(tmepTotalPaymentPrice);
        //이번 결제 금액까지 더해 할인률 적용.
        double discountRate = switch (tempGrade) {
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
        if(this.money < finalPrice) {
            throw new Exception("결제 실패 : 고객님이 소유하신 금액이 부족합니다.");
        }
        this.money -= finalPrice;
        //결제 완료 하면 실제 토탈 금액과 등급 변경
        this.totalPaymentPrice += finalPrice;
        setGrade();
    }
    private GradeType checkGrade(int tmepTotalPaymentPrice) {
        if(tmepTotalPaymentPrice < 500_000){
            return GradeType.BRONZE;
        }
        else if(tmepTotalPaymentPrice < 1_000_000){
            return GradeType.SLIVER;
        }
        else if(tmepTotalPaymentPrice <= 2_000_000){
            return GradeType.GOLD;
        }
        else {
            return GradeType.PLATINUM;
        }
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
