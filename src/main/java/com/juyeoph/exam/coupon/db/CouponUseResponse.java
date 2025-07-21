package com.juyeoph.exam.coupon.db;

import com.juyeoph.exam.coupon.CouponType;

public class CouponUseResponse {
    private boolean success;
    private String message;
    private CouponType couponType;

    public CouponUseResponse(boolean success, String message, CouponType couponType) {
        this.success = success;
        this.message = message;
        this.couponType = couponType;
    }

    public CouponUseResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CouponUseResponse(boolean b) {
    }

//    public CouponIssueResponse(AtomicBoolean alreadyIssued, String string) {
//    }

    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public CouponType getCouponType() {
        return couponType;
    }
}
