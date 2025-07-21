package com.juyeoph.exam.coupon.exception;

public class CouponApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    public CouponApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
