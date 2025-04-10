package kr.hhplus.be.domain.order.repository;

import kr.hhplus.be.domain.order.entity.OrderList;

import java.util.List;

public interface OrderListRepository {
    OrderList save(OrderList orderList);
    List<OrderList> findByOrderId(Long orderId);
}
