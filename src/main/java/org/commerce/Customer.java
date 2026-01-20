package org.commerce;

public class Customer {
    private String name;
    private String email;
    private GradeType grade;
    private int totalPaymentPrice;
    private int money;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.grade = GradeType.BRONZE;
        this.totalPaymentPrice = 0;
        this.money = 1_000_000_000;
    }

    public void setGrade() {
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

    public int getTotalPaymentPrice() {
        return totalPaymentPrice;
    }

    public void setTotalPaymentPrice(int totalPaymentPrice) {
        this.totalPaymentPrice = totalPaymentPrice;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void payment(int productPrice) {
        this.totalPaymentPrice += productPrice;
        this.money -= productPrice;
    }


    @Override
    public String toString() {
        return "고객님의 등급 = " + grade +
                " | 총 결제금액 = " + totalPaymentPrice +
                " | 보유 금액 = " + money;
    }
}
