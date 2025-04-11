package kr.hhplus.be.application.order;


import kr.hhplus.be.domain.coupon.dto.CouponDTO;
import kr.hhplus.be.domain.coupon.dto.UserCouponDTO;
import kr.hhplus.be.domain.coupon.entity.UserCoupon;
import kr.hhplus.be.domain.coupon.service.CouponService;
import kr.hhplus.be.domain.order.dto.OrderDTO;
import kr.hhplus.be.domain.order.entity.Order;
import kr.hhplus.be.domain.order.entity.OrderProduct;
import kr.hhplus.be.domain.order.enumtype.OrderStatus;
import kr.hhplus.be.domain.order.service.OrderService;
import kr.hhplus.be.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final ProductService productService;
    private final CouponService couponService;
    private final OrderService orderService;

    /*
    * 주문 생성
    * 1. 주문한 상품 재고 확인
    * 2. 쿠폰 확인 및 적용
    * 3. 주문 생성 및 저장
    */

    @Transactional
    public Order orderOrder(Long userId, Long orderId) {
        // 주문 상품 재고 확인
        OrderDTO orderDTO = orderService.getOrderInfo(orderId);

        for (OrderProduct orderProduct: orderDTO.getOrderProducts()) {
            productService.checkStock(orderProduct.getProdId(), orderProduct.getAmount());
        }

        // 쿠폰 확인 및 적용
        UserCouponDTO userCouponDTO = couponService.getUserCoupon(userId);
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal totalPrice = orderDTO.getOrderPrice();

        boolean ret = couponService.useCoupon(userCouponDTO.getCouponId());
        // 쿠폰 사용 가능하면 쿠폰 할인 금액 적용 계산
        if (ret) {
            // 쿠폰 할인 금액
            discountAmount = couponService.getDiscountAmount(userCouponDTO.getCouponId());
            totalPrice = couponService.calcCouponDiscount(userCouponDTO.getCouponId(), orderDTO.getOrderPrice());
        }
        // 주문 정보 생성
        Order order = Order.of(userCouponDTO.getUserId(), userCouponDTO.getCouponId(), orderDTO.getOrderPrice(), discountAmount, totalPrice, OrderStatus.COMPLETE);
        return order;
    }


}
