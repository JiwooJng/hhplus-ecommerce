package kr.hhplus.be.domain.coupon.repository;

import kr.hhplus.be.domain.coupon.entity.Coupon;

public interface CouponRepository {
    Coupon save(Coupon coupon);
    Coupon findByCouponId(Long couponId);

    void decreaseCouponAmount(Long couponId);

}
