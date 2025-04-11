package kr.hhplus.be.domain.order.entity;


import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

    @Setter
    private Long orderId;
    private Long prodId;
    private Integer amount;

    private BigDecimal price;
    private BigDecimal totalPrice;

}
