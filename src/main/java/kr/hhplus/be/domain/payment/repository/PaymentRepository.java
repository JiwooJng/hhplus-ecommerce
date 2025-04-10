package kr.hhplus.be.domain.payment.repository;

import kr.hhplus.be.domain.payment.entity.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);

    Payment findByOrderId(Long orderId);

}
