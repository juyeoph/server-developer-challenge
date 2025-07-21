package com.juyeoph.exam.coupon.exception;

public enum ErrorCode {
    COUPON_ALREADY_EXISTS("이미 보유한 쿠폰입니다."),
    INVALID_PRICE("결제 금액이 유효하지 않습니다."),
    NO_APPLICABLE_COUPON("적용 가능한 쿠폰이 없습니다."),
    COUPON_NOT_FOUND("보유한 쿠폰이 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}