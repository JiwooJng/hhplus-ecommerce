package kr.hhplus.be.domain.coupon.repository;

import kr.hhplus.be.domain.coupon.entity.UserCoupon;

public interface UserCouponRepository {
    UserCoupon save(UserCoupon userCoupon);
    UserCoupon findUserCouponById(Long userId);
}
