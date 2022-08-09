package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.OrderRequest;
import com.oocl.easymovie.dto.OrderResponse;
import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.mapper.OrderMapper;
import com.oocl.easymovie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderService orderService;

    @GetMapping("/{orderId}")
    public ResultData<OrderResponse> findOrderById(@PathVariable Long orderId){
        return ResultData.success(orderMapper.toResponse(orderService.findOrderById(orderId)));
    }

    @PostMapping
    public ResultData<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
        return ResultData.success(orderMapper.toResponse(orderService.createOrder(orderMapper.toEntity(orderRequest))));
    }

    @PutMapping("/{orderId}")
    public ResultData<OrderResponse> updateOrder(@PathVariable Long orderId,@RequestBody OrderRequest orderRequest){
        return ResultData.success(orderMapper.toResponse(orderService.updateOrder(orderId,orderMapper.toEntity(orderRequest))));
    }

    @GetMapping(params = {"userId","page","pageSize"})
    public ResultData<Page<Order>> findByUserIdAndPage(@RequestParam(name = "userId") Long userId, @RequestParam(value = "page") Integer page, @RequestParam(value = "pageSize") Integer pageSize){
        return ResultData.success(orderService.findOrderByUserIdAndPage(userId,page,pageSize));

    }
}
