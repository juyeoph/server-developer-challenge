package com.juyeoph.exam.coupon.db;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.juyeoph.utils.DelayUtils.randomDelay;

@Repository
public class CouponRepository {
    private final Map<Long, List<Coupon>> db = new ConcurrentHashMap<>();

    public List<Coupon> findByUserId(final long userId) {
        randomDelay();
        return db.getOrDefault(userId, List.of());
    }

    public Coupon insert(final Coupon coupon) {
        randomDelay();
        db.computeIfAbsent(coupon.getUserId(), k -> new ArrayList<>()).add(coupon);
        return coupon;
    }

    public void delete(final Coupon coupon) {
        randomDelay();
        final List<Coupon> list = db.get(coupon.getUserId());
        if (list == null || list.isEmpty()) {
            return;
        }
        list.remove(coupon);
    }

    public void clearDbForTest() {
        db.clear();
    }
}
