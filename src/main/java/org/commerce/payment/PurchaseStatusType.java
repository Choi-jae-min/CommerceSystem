package org.commerce.payment;

public enum PurchaseStatusType {
    PENDING("결재 요청"),
    COMPLETED("결재 완료"),
    CANCEL("결제 취소");

    final String status;
    PurchaseStatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
