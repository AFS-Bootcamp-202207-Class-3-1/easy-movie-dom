package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.exception.OrderNotFoundException;
import com.oocl.easymovie.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order newOrder) {
        Order oldOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        if (!Objects.isNull(newOrder.getQuickMarkKey())) {
            oldOrder.setQuickMarkKey(newOrder.getQuickMarkKey());
        }
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

    public Page<Order> findOrderByUserIdAndPage(Long UserId, int page, int pageSize) {
        return orderRepository.findByUserId(UserId, PageRequest.of(page - 1, pageSize));
    }

}
