package kr.hhplus.be.domain.coupon.service;


import kr.hhplus.be.domain.coupon.dto.CouponDTO;
import kr.hhplus.be.domain.coupon.dto.UserCouponDTO;
import kr.hhplus.be.domain.coupon.entity.Coupon;
import kr.hhplus.be.domain.coupon.entity.UserCoupon;
import kr.hhplus.be.domain.coupon.enumtype.CouponStatus;
import kr.hhplus.be.domain.coupon.repository.CouponRepository;
import kr.hhplus.be.domain.coupon.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    // 유저 쿠폰 조회
    public UserCouponDTO getUserCoupon(Long couponId) {
        UserCoupon userCoupon = userCouponRepository.findCouponById(couponId);
        return new UserCouponDTO(userCoupon.getUserId(), userCoupon.getCouponId());
    }

    // 쿠폰 할인 금액 조회
    public BigDecimal getDiscountAmount (Long couponId) {
        Coupon coupon = couponRepository.findByCouponId(couponId);
        return coupon.getDiscountAmount();
    }

    // 시스템상 쿠폰 등록
    @Transactional
    public Coupon publishCoupon(CouponDTO couponDTO) {
        Coupon coupon = couponRepository.findByCouponId(couponDTO.getCouponId());

        coupon.publish();
        couponRepository.save(coupon);

        return coupon;
    }

    // 유저에게 쿠폰 발급
    @Transactional
    public UserCoupon issueCoupon(UserCouponDTO userCouponDTO) {
        Coupon coupon = couponRepository.findByCouponId(userCouponDTO.getCouponId());

        if (coupon.getStatus().equals(CouponStatus.INACTIVE)) {
            throw new RuntimeException();
        }
        UserCoupon userCoupon = userCouponRepository.save(UserCoupon.issue(userCouponDTO));
        return userCoupon;

    }

    // 쿠폰 사용
    public boolean useCoupon(Long couponId) {
        UserCoupon userCoupon = userCouponRepository.findCouponById(couponId);
        boolean ret = userCoupon.use();

        if (!ret) {
            return false;
        }
        userCouponRepository.save(userCoupon);
        return true;
    }

    // 쿠폰 적용 결제 시 금액 계산
    public BigDecimal calcCouponDiscount(Long couponId, BigDecimal totalAmount) {
        UserCoupon userCoupon = userCouponRepository.findCouponById(couponId);
        Coupon coupon = couponRepository.findByCouponId(userCoupon.getCouponId());

        BigDecimal discountAmount = totalAmount.subtract(coupon.getDiscountAmount());

        return discountAmount;
    }

    // 쿠폰 잔여 수량 검사
    private void couponRemainAmount(Long couponId) {
        Coupon coupon = couponRepository.findByCouponId(couponId);
        Long remainAmount = coupon.getRemainAmount();

        // 잔여 수량 <= 0 -> 발급 불가
        if (remainAmount <= 0) {
            throw new RuntimeException();
        }
    }

    // 유저에게 쿠폰 발급 후 발급 가능 잔여 수량 차감
    private void decreaseCouponAmount(UserCouponDTO userCouponDTO) {
        couponRepository.decreaseCouponAmount(userCouponDTO.getCouponId());
    }

}
