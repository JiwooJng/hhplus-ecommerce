package kr.hhplus.be.application.payment;

import kr.hhplus.be.domain.order.dto.OrderDTO;
import kr.hhplus.be.domain.order.entity.OrderProduct;
import kr.hhplus.be.domain.order.service.OrderService;
import kr.hhplus.be.domain.payment.entity.Payment;
import kr.hhplus.be.domain.payment.service.PaymentService;
import kr.hhplus.be.domain.point.service.PointService;
import kr.hhplus.be.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentFacade {
    private final PointService pointService;
    private final ProductService productService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    /*
    * 이전 단계: 재고 확인 및 쿠폰 적용 후 결제 요청
    * 결제 요청 시
    * 1. 결제 정보 생성 : 결제 대기 상태
    * 2. 잔액 확인 및 차감
    * 3. 결제 성공 -> 저장
    * 4. 재고 차감
    * 5. 주문 성공 -> 저장
    */

    @Transactional
    public Payment payment (Long orderId, BigDecimal payAmount) {
        // 결제 정보 생성
        Payment payment = paymentService.pay(orderId, payAmount);
        // 잔액 차감
        pointService.use(orderId, payAmount);
        // 결제 성공
        paymentService.completePay(payment.getPayId());

        // 재고 차감
        OrderDTO orderDTO = orderService.getOrderInfo(orderId);
        for (OrderProduct orderProduct : orderDTO.getOrderProducts()) {
            productService.deductStock(orderProduct.getProdId(), orderProduct.getAmount());
        }

        return payment;

    }

}
