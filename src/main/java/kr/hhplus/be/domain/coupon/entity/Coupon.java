package kr.hhplus.be.domain.coupon.entity;

/*
* 쿠폰 생성
  * 쿠폰 종류
  *   */

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.hhplus.be.domain.coupon.enumtype.CouponStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
public class Coupon {

    // 선착순 쿠폰 등 관리자가 발행할 수 있는 쿠폰 종류
    private String couponType;

    private Long couponId;

    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    private Long maxIssueAmount;
    private Long remainAmount;

    private LocalDateTime createDate;

    public static Coupon of(String couponType, BigDecimal discountAmount, Long maxIssueAmount) {
        Coupon coupon = new Coupon();

        coupon.couponType = couponType;
        coupon.discountAmount = discountAmount;

        coupon.status = CouponStatus.ACTIVE;

        coupon.maxIssueAmount = maxIssueAmount;
        coupon.remainAmount = coupon.maxIssueAmount;
        coupon.createDate = LocalDateTime.now();

        return coupon;
    }

    public void publish() {
        if (this.remainAmount <= 0) {
            throw new RuntimeException();
        }
    }
}
