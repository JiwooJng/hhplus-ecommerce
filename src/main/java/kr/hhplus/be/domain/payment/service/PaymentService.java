package kr.hhplus.be.domain.payment.service;


import kr.hhplus.be.domain.payment.entity.Payment;
import kr.hhplus.be.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment pay(Long orderId, BigDecimal payAmount) {
        Payment payment = Payment.create(orderId, payAmount);

        return paymentRepository.save(payment);
    }
}
