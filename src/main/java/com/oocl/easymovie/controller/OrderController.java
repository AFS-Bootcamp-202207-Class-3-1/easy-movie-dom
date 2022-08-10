package com.oocl.easymovie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oocl.easymovie.dto.OrderContainMovieTheaterScheduleResponse;
import com.oocl.easymovie.dto.OrderRequest;
import com.oocl.easymovie.dto.OrderResponse;
import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.mapper.OrderMapper;
import com.oocl.easymovie.service.MovieService;
import com.oocl.easymovie.service.OrderService;
import com.oocl.easymovie.service.ScheduleService;
import com.oocl.easymovie.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderService orderService;
    @Autowired
    MovieService movieService;
    @Autowired
    TheaterService theaterService;
    @Autowired
    ScheduleService scheduleService;
    @GetMapping("/{orderId}")
    public ResultData<OrderContainMovieTheaterScheduleResponse> findOrderContainMovieTheaterScheduleById(@PathVariable Long orderId){
        Order order = orderService.findOrderById(orderId);
        return ResultData.success(
                orderMapper.toOrderContainMovieTheaterSchedule(
                        order
                        ,movieService.findById(order.getMovieId())
                        ,theaterService.findTheaterById(order.getTheaterId())
                        , scheduleService.findById(order.getScheduleId())));
    }

    @PostMapping
    public ResultData<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
        return ResultData.success(orderMapper.toResponse(orderService.createOrder(orderMapper.toEntity(orderRequest))));
    }

    @PutMapping("/{orderId}")
    public ResultData<OrderResponse> updateOrder(@PathVariable Long orderId,@RequestBody OrderRequest orderRequest){
        return ResultData.success(orderMapper.toResponse(orderService.updateOrder(orderId,orderMapper.toEntity(orderRequest))));
    }

    @GetMapping(params = {"userId"})
    public ResultData<List<OrderResponse>> findByUserId(@RequestParam(name = "userId") Long userId){
        return ResultData.success(orderService.findOrderByUserId(userId).stream().map(order -> orderMapper.toResponse(order)).collect(Collectors.toList()));
    }
    @PostMapping("/payment/{orderId}")
    public void payForOrders(@PathVariable Long orderId) throws JsonProcessingException {
        orderService.payForOrder(orderId);
    }


}
