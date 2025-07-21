package com.juyeoph.exam.coupon;

public class UseCouponResponseDto {
    /**
     * 정상가 (ex. 10000)
     * */
    private final long originalPrice;
    /**
     * 할인 금액 (ex. 1000)
     * */
    private final long discountAmount;
    /**
     * 할인 금액이 적용된 최종 가격 (ex. 9000)
     * */
    private final long finalPrice;
    /**
     * 적용된 쿠폰 타입
     * */
    private final CouponType couponType;

    public UseCouponResponseDto(
        final long originalPrice,
        final long discountAmount,
        final long finalPrice,
        final CouponType couponType
    ) {
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.discountAmount = discountAmount;
        this.couponType = couponType;
    }

    public long getOriginalPrice() {
        return originalPrice;
    }

    public long getDiscountAmount() {
        return discountAmount;
    }

    public long getFinalPrice() {
        return finalPrice;
    }

    public CouponType getCouponType() {
        return couponType;
    }
}
