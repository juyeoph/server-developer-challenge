package com.juyeoph.exam.coupon;

import com.juyeoph.exam.coupon.db.Coupon;
import com.juyeoph.exam.coupon.db.CouponRepository;
import com.juyeoph.exam.coupon.exception.CouponApplicationException;
import com.juyeoph.exam.coupon.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    // 사용자별 Lock 객체를 관리하여 동시성 문제 해결
    private final Map<Long, Lock> userLocks = new ConcurrentHashMap<>();

    public CouponService(final CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    /**
     * 쿠폰 발급 로직
     */
    public void issueCoupon(long userId, CouponType type) {
        // 사용자별 Lock을 획득, 없으면 새로 생성
        Lock userLock = userLocks.computeIfAbsent(userId, k -> new ReentrantLock());
        userLock.lock();
        try {
            // 이미 해당 타입의 쿠폰을 보유하고 있는지 확인
            boolean alreadyExists = couponRepository.findByUserId(userId).stream()
                    .anyMatch(coupon -> coupon.getType() == type);

            if (alreadyExists) {
                throw new CouponApplicationException(ErrorCode.COUPON_ALREADY_EXISTS);
            }
            // 쿠폰을 생성하고 DB에 저장
            couponRepository.insert(new Coupon(userId, type));
        } finally {
            userLock.unlock();
        }
    }

    /**
     * 쿠폰 사용 로직
     */
    public UseCouponResponseDto useCoupon(long userId, long price) {
        // 결제 금액 유효성 검증
        if (price < 10 || price % 10 != 0) {
            throw new CouponApplicationException(ErrorCode.INVALID_PRICE);
        }

        Lock userLock = userLocks.computeIfAbsent(userId, k -> new ReentrantLock());
        userLock.lock();
        try {
            List<Coupon> userCoupons = couponRepository.findByUserId(userId);
            if (userCoupons.isEmpty()) {
                throw new CouponApplicationException(ErrorCode.COUPON_NOT_FOUND);
            }

            // 적용 가능한 쿠폰 필터링
            List<Coupon> applicableCoupons = userCoupons.stream()
                    .filter(coupon -> coupon.getType().isApplicable(price))
                    .collect(Collectors.toList());

            if (applicableCoupons.isEmpty()) {
                throw new CouponApplicationException(ErrorCode.NO_APPLICABLE_COUPON);
            }

            // 최적의 쿠폰 선택 (할인액이 가장 크고, 같으면 먼저 발급된 순)
            Optional<Coupon> bestCouponOpt = applicableCoupons.stream()
                    .max(Comparator.comparing((Coupon coupon) -> coupon.getType().calculateDiscount(price))
                            .thenComparing(Coupon::getIssuedAt, Comparator.reverseOrder()));

            Coupon bestCoupon = bestCouponOpt.get();
            CouponType bestCouponType = bestCoupon.getType();
            long discountAmount = bestCouponType.calculateDiscount(price);
            long finalPrice = price - discountAmount;

            // 사용한 쿠폰 삭제
            couponRepository.delete(bestCoupon);

            return new UseCouponResponseDto(price, discountAmount, finalPrice, bestCouponType);
        } finally {
            userLock.unlock();
        }
    }

}