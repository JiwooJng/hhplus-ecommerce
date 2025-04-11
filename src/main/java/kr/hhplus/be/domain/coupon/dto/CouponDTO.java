package kr.hhplus.be.domain.coupon.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponDTO {
    Long couponId;
    Integer remainAmount;
}
