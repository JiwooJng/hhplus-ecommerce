package kr.hhplus.be.domain.payment.entity;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.hhplus.be.domain.payment.enumtype.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long payId;
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private BigDecimal payAmount;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static Payment create(Long orderId, BigDecimal payAmount) {
        Payment payment = new Payment();

        payment.orderId = orderId;
        payment.status = PaymentStatus.SUCCESS;

        payment.payAmount = payAmount;

        payment.createDate = LocalDateTime.now();

        return payment;
    }

    public void complete() {
        this.status = PaymentStatus.SUCCESS;
        this.modifyDate = LocalDateTime.now();

    }
}


