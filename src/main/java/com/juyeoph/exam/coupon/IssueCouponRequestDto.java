package com.juyeoph.exam.coupon;

public class IssueCouponRequestDto {
    private final CouponType type;

    public IssueCouponRequestDto(final CouponType type) {
        this.type = type;
    }

    public CouponType getType() {
        return type;
    }
}
