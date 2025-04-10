package kr.hhplus.be.domain.order.entity;


import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderList {

    @Setter
    private Long orderId;
    private Long prodId;
    private Integer quantity;

    private BigDecimal price;
    private BigDecimal totalPrice;

}
