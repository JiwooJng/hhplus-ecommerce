package kr.hhplus.be.domain.order.entity;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.hhplus.be.domain.order.enumtype.OrderStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class Order {
    private Long orderId;
    private Long userId;
    private Long userCouponId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal orderPrice;
    private BigDecimal discountAmount;
    private BigDecimal totalPrice;

    private LocalDateTime createDate;

    public static Order of(Long userId, Long userCouponId,  BigDecimal orderPrice, BigDecimal discountAmount, BigDecimal totalPrice, OrderStatus status) {
        Order order = new Order();

        order.userId = userId;
        order.userCouponId = userCouponId;
        order.createDate = LocalDateTime.now();

        order.orderPrice = orderPrice;
        order.discountAmount = discountAmount;
        order.totalPrice = totalPrice;

        order.status = status;

        return order;
    }

}
