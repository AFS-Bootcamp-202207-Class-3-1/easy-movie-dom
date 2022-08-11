package com.oocl.easymovie.controller;

import com.oocl.easymovie.advice.WebSocketServer;
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

import java.io.IOException;
import java.util.List;

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


    @GetMapping("/all/{userId}")
    public ResultData<List<OrderContainMovieTheaterScheduleResponse>> findAllOrderByUserId(@PathVariable Long userId){
        return ResultData.success(orderService.getOrderLinkedDataListByTypes(orderService.findAllOrderByUserId(userId)));
    }

    @GetMapping("/used/{userId}")
    public ResultData<List<OrderContainMovieTheaterScheduleResponse>> findUsedOrderByUserId(@PathVariable Long userId){
        return ResultData.success(orderService.getOrderLinkedDataListByTypes(orderService.findUsedOrderByUserId(userId)));
    }

    @GetMapping("/rebook/{userId}")
    public ResultData<List<OrderContainMovieTheaterScheduleResponse>> findRebookOrderByUserId(@PathVariable Long userId){
        return ResultData.success(orderService.getOrderLinkedDataListByTypes(orderService.findRebookOrderByUserId(userId)));
    }

    @GetMapping("/paid/{userId}")
    public ResultData<List<OrderContainMovieTheaterScheduleResponse>> findPaidOrderByUserId(@PathVariable Long userId){
        return ResultData.success(orderService.getOrderLinkedDataListByTypes(orderService.findPaidOrderByUserId(userId)));
    }

    @GetMapping("/refund/{userId}")
    public ResultData<List<OrderContainMovieTheaterScheduleResponse>> findRefundOrderByUserId(@PathVariable Long userId){
        return ResultData.success(orderService.getOrderLinkedDataListByTypes(orderService.findRefundOrderByUserId(userId)));
    }

    @PostMapping("/payment/{orderId}")
    public void payForOrders(@PathVariable Long orderId) {
        orderService.payForOrder(orderId);
    }


    @PostMapping("/{orderId}/seats")
    public ResultData<Object> modifySeatsAndPrice(@PathVariable(value = "orderId") Long orderId, @RequestBody SeatingRequest seatingRequest) {
        Seating seating = seatingMapper.toEntity(seatingRequest);
        orderService.modifySeatsAndPrice(orderId, seating);
        return ResultData.success();
    }

    @PostMapping("/refund/{orderId}")
    public ResultData<Object> refundForOrders(@PathVariable Long orderId) {
        orderService.refundOrdersById(orderId);
        return ResultData.success();
    }

    @GetMapping(value = "/ticket-redemption", params = "key")
    public ResultData<Object> redemptionTicket(@RequestParam(value = "key") String key) throws IOException {
        orderService.redemptionTicket(key);
        String message = "ticketUsed";
        WebSocketServer.sendInfo(message, key);//推送给前端
        return ResultData.success();
    }

}
