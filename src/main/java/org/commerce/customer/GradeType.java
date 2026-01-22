package org.commerce.customer;

public enum GradeType {
    BRONZE("브론즈"),
    SLIVER("실버"),
    GOLD("골드"),
    PLATINUM("플레티넘");

    String grade;
    GradeType(String grade) {
        this.grade = grade;
    }
    public String getGrade() {
        return grade;
    }
}
