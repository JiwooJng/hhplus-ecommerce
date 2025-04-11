package kr.hhplus.be.domain.order.service;


import kr.hhplus.be.domain.order.dto.OrderDTO;
import kr.hhplus.be.domain.order.entity.Order;
import kr.hhplus.be.domain.order.entity.OrderList;
import kr.hhplus.be.domain.order.repository.OrderListRepository;
import kr.hhplus.be.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;

    // 주문 생성
    /*
    * 사용자한테 받은 주문 정보들
    * 컨트롤러에서 DTO로 변환된 값을 받아서 처리
    * order랑 orderlist repository에 저장
    */
    public Long orderOrder(Order order, OrderDTO orderDTO) {
        Order ordered = orderRepository.save(order);

        for (OrderList orderList: orderDTO.getOrderLists()) {
            orderList.setOrderId(ordered.getOrderId());
            orderListRepository.save(orderList);
        }
        return order.getOrderId();
    }

    // 주문 정보 조회
    public OrderDTO getOrderInfo(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        List<OrderList> orderLists = orderListRepository.findByOrderId(orderId);

        return new OrderDTO(order.getOrderId(), order.getUserId(), orderLists,
                            order.getOrderPrice(), order.getDiscountAmount(), order.getTotalPrice());

    }

    public void completeOrder(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        order.complete();
        orderRepository.save(order);
    }



}
