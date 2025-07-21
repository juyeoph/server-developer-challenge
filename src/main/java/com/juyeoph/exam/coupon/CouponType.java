package com.juyeoph.exam.coupon;

import java.util.function.Function;

public enum CouponType {
    DISCOUNT_20_PERCENT(
            price -> price >= 30000,
            price -> Math.min((long) (price * 0.2), 10000)
    ),
    DISCOUNT_10_PERCENT(
            price -> true,
            price -> Math.min((long) (price * 0.1), 20000)
    ),
    DISCOUNT_1000_WON(
            price -> price >= 1000,
            price -> 1000L
    ),
    DISCOUNT_5000_WON(
            price -> price >= 50000,
            price -> 5000L
    );

    private final Function<Long, Boolean> isApplicable;
    private final Function<Long, Long> calculateDiscount;

    CouponType(Function<Long, Boolean> isApplicable, Function<Long, Long> calculateDiscount) {
        this.isApplicable = isApplicable;
        this.calculateDiscount = calculateDiscount;
    }

    /**
     * 쿠폰 적용 가능 여부 확인
     * @param price 원가
     * @return 적용 가능 여부
     */
    public boolean isApplicable(long price) {
        return isApplicable.apply(price);
    }

    /**
     * 할인 금액 계산
     * @param price 원가
     * @return 할인 금액
     */
    public long calculateDiscount(long price) {
        return calculateDiscount.apply(price);
    }
}
