package com.oocl.easymovie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.exception.OrderNotFoundException;
import com.oocl.easymovie.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
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
//        if (!Objects.isNull(newOrder.getQuickMarkKey())) {
//            oldOrder.setQuickMarkKey(newOrder.getQuickMarkKey());
//        }
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

    public List<Order> findOrderByUserId(Long UserId) {
        return orderRepository.findAllByUserId(UserId);
    }

    public void payForOrder(Long orderId) throws JsonProcessingException {
        Order order = findOrderById(orderId);
        purchasePointService.deductBalance(order.getUserId(), (int) order.getTotalPrice());
        order.setIsPaid(true);
        String key="key:"+order.getUserId()+order.getTheaterId()+order.getMovieId()+order.getExpirationTime();
        order.setQuickMarkKey(key);
        orderRepository.save(order);
    }

}
