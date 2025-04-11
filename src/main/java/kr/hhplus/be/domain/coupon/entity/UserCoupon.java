package kr.hhplus.be.domain.coupon.entity;

import kr.hhplus.be.domain.coupon.dto.UserCouponDTO;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class UserCoupon {

    private Long userCouponId;

    private Long userId;
    private Long couponId;

    private LocalDateTime issuedDate;

    private LocalDateTime usedDate;
    private boolean used;

    public static UserCoupon issue(UserCouponDTO userCouponDTO) {
        UserCoupon userCoupon = new UserCoupon();

        userCoupon.userId = userCouponDTO.getUserId();
        userCoupon.couponId = userCouponDTO.getCouponId();

        userCoupon.issuedDate = LocalDateTime.now();

        userCoupon.used = false;

        return userCoupon;
    }

    public boolean use() {
        if (this.used) {
            return false;
        }

        this.usedDate = LocalDateTime.now();
        this.used = false;

        return true;
    }

}
