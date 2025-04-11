package kr.hhplus.be.domain.order.repository;

import kr.hhplus.be.domain.order.entity.OrderProduct;

import java.util.List;

public interface OrderListRepository {
    OrderProduct save(OrderProduct orderProduct);
    List<OrderProduct> findByOrderId(Long orderId);
}
