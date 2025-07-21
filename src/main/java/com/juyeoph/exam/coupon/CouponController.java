package com.juyeoph.exam.coupon;

import com.juyeoph.exam.coupon.db.Coupon;
import com.juyeoph.exam.coupon.db.CouponRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CouponController {
    private final CouponRepository couponRepository;
    private final CouponService couponService;

    public CouponController(final CouponRepository couponRepository, final CouponService couponService) {
        this.couponRepository = couponRepository;
        this.couponService = couponService;
    }

    @PostMapping("/api/users/{userId}/coupons/issue")
    public void issueCoupon(
        @PathVariable final long userId,
        @RequestBody final IssueCouponRequestDto request
    ) {
        // CouponService의 발급 메서드 호출
        couponService.issueCoupon(userId, request.getType());
    }

    @PostMapping("/api/users/{userId}/coupons/use")
    public UseCouponResponseDto useCoupon(
        @PathVariable final long userId,
        @RequestBody final UseCouponRequestDto request
    ) {
        // CouponService의 사용 메서드 호출
        return couponService.useCoupon(userId, request.getPrice());
    }

    @GetMapping("/api/users/{userId}/coupons")
    public List<Coupon> getCoupon(@PathVariable final long userId) {
        return couponRepository.findByUserId(userId);
    }
}
