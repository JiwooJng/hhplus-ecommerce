package kr.hhplus.be.domain.order.dto;


import kr.hhplus.be.domain.order.entity.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDTO {
    Long orderId;
    Long userId;
    List<OrderProduct> orderProducts;
    BigDecimal orderPrice;
    BigDecimal discountAmount;
    BigDecimal totalPrice;
}
