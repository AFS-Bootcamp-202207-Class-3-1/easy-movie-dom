package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.OrderContainMovieTheaterScheduleResponse;
import com.oocl.easymovie.dto.OrderRequest;
import com.oocl.easymovie.dto.OrderResponse;
import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.dto.*;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.entity.Seating;
import com.oocl.easymovie.mapper.OrderMapper;
import com.oocl.easymovie.mapper.SeatingMapper;
import com.oocl.easymovie.service.MovieService;
import com.oocl.easymovie.service.OrderService;
import com.oocl.easymovie.service.ScheduleService;
import com.oocl.easymovie.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    SeatingMapper seatingMapper;
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

    @GetMapping("/used/{userId}")
    public ResultData<List<OrderResponse>> findUsedOrderByUserId(@PathVariable Long userId){
        return ResultData.success(orderService.findUsedOrderByUserId(userId).stream().map(order -> orderMapper.toResponse(order)).collect(Collectors.toList()));
    }

    @GetMapping("/rebook/{userId}")
    public ResultData<List<OrderResponse>> findRebookOrderByUserId(@PathVariable Long userId){
        return ResultData.success(orderService.findRebookOrderByUserId(userId).stream().map(order -> orderMapper.toResponse(order)).collect(Collectors.toList()));
    }

    @GetMapping("/paid/{userId}")
    public ResultData<List<OrderResponse>> findPaidOrderByUserId(@PathVariable Long userId){
        return ResultData.success(orderService.findPaidOrderByUserId(userId).stream().map(order -> orderMapper.toResponse(order)).collect(Collectors.toList()));
    }

    @GetMapping("/refund/{userId}")
    public ResultData<List<OrderResponse>> findRefundOrderByUserId(@PathVariable Long userId){
        return ResultData.success(orderService.findRefundOrderByUserId(userId).stream().map(order -> orderMapper.toResponse(order)).collect(Collectors.toList()));
    }

    @PostMapping("/payment/{orderId}")
    public void payForOrders(@PathVariable Long orderId) {
        orderService.payForOrder(orderId);
    }


    @PostMapping("/{orderId}/seats")
    public ResultData<Object> modifySeatsAndPrice(@PathVariable(value = "orderId") Long orderId, @RequestBody SeatingRequest seatingRequest){
        Seating seating= seatingMapper.toEntity(seatingRequest);
        orderService.modifySeatsAndPrice(orderId,seating);

        return ResultData.success();
    }
}
