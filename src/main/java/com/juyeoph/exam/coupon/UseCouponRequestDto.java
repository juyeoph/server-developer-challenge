package com.juyeoph.exam.coupon;

public class UseCouponRequestDto {
    private final long price;

    public UseCouponRequestDto(final long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }
}
