package com.oocl.easymovie.mapper;

import com.oocl.easymovie.dto.OrderRequest;
import com.oocl.easymovie.dto.OrderResponse;
import com.oocl.easymovie.entity.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toEntity(OrderRequest orderRequest){
        final Order order = new Order();
        BeanUtils.copyProperties(orderRequest, order);
        return order;
    }

    public OrderResponse toResponse(Order order){
        final OrderResponse response = new OrderResponse();
        BeanUtils.copyProperties(order, response);
        return response;
    }
}
