package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.exception.OrderNotFoundException;
import com.oocl.easymovie.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    public static final Boolean IS_USED = true;
    public static final Boolean IS_REBOOK = true;
    public static final Boolean IS_PAID = true;
    public static final Boolean IS_REFUND = true;
    private final OrderRepository orderRepository;
    private final ScheduleService scheduleService;
    private final PurchasePointService purchasePointService;
    public OrderService(OrderRepository orderRepository, ScheduleService scheduleService, PurchasePointService purchasePointService) {
        this.orderRepository = orderRepository;
        this.scheduleService = scheduleService;
        this.purchasePointService = purchasePointService;
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order createOrder(Order order) {
        order.setTotalPrice(scheduleService.findById(order.getScheduleId()).getPrice());
        order.setIsPaid(false);
        order.setIsRebook(false);
        order.setIsRefund(false);
        order.setIsTicketUsed(false);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order newOrder) {
        Order oldOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        if (!Objects.isNull(newOrder.getScheduleId())) {
            oldOrder.setScheduleId(newOrder.getScheduleId());
        }
        if (!Objects.isNull(newOrder.getSnacksId())) {
            oldOrder.setSnacksId(newOrder.getSnacksId());
        }
        if (!Objects.isNull(newOrder.getExpirationTime())) {
            oldOrder.setExpirationTime(newOrder.getExpirationTime());
        }
        oldOrder.setSnacksTotalPrice(newOrder.getSnacksTotalPrice());
        oldOrder.setTotalPrice(newOrder.getTotalPrice());
        return orderRepository.save(oldOrder);
    }

    public List<Order> findUsedOrderByUserId(Long UserId) {
        return orderRepository.findUsedOrderByUserId(UserId,IS_USED);
    }

    public List<Order> findRebookOrderByUserId(Long UserId) {
        return orderRepository.findRebookOrderByUserId(UserId,IS_REBOOK);
    }

    public List<Order> findPaidOrderByUserId(Long UserId) {
        return orderRepository.findPaidOrderByUserId(UserId,IS_PAID);
    }

    public List<Order> findRefundOrderByUserId(Long UserId) {
        return orderRepository.findRefundOrderByUserId(UserId,IS_REFUND);
    }

    public void payForOrder(Long orderId) {
        Order order = findOrderById(orderId);
        purchasePointService.deductBalance(order.getUserId(), (int) order.getTotalPrice());
        order.setIsPaid(true);
        String key=String.valueOf(System.currentTimeMillis()) ;
        order.setQuickMarkKey(key);
        orderRepository.save(order);
    }

}
