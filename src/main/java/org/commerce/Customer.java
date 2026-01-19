package org.commerce;

public class Customer {
    private String name;
    private String email;
    private GradeType grade;
    private int totalPaymentPrice;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.grade = GradeType.BRONZE;
        this.totalPaymentPrice =0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GradeType getGrade() {
        return grade;
    }

    public void setGrade(GradeType grade) {
        this.grade = grade;
    }

    public int getTotalPaymentPrice() {
        return totalPaymentPrice;
    }

    public void setTotalPaymentPrice(int totalPaymentPrice) {
        this.totalPaymentPrice = totalPaymentPrice;
    }
}
