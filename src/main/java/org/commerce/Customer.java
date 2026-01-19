package org.commerce;

public class Customer {
    String name;
    String email;
    String grade;
    int totalPaymentPrice;

    Customer(String name, String email, String grade, int totalPaymentPrice) {
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.totalPaymentPrice = totalPaymentPrice;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getTotalPaymentPrice() {
        return totalPaymentPrice;
    }

    public void setTotalPaymentPrice(int totalPaymentPrice) {
        this.totalPaymentPrice = totalPaymentPrice;
    }
}
