package com.juyeoph.exam.coupon;

import com.juyeoph.exam.coupon.exception.CouponApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CouponApplicationException.class)
    public ResponseEntity<Void> handleCouponApplicationException(CouponApplicationException e) {
        // 모든 커스텀 예외에 대해 500 상태 코드 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}