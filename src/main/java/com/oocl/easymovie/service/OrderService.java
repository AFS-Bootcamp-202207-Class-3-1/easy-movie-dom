package com.oocl.easymovie.service;

import com.oocl.easymovie.dto.OrderContainMovieTheaterScheduleResponse;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.Seating;
import com.oocl.easymovie.exception.OrderNotFoundException;
import com.oocl.easymovie.mapper.OrderMapper;
import com.oocl.easymovie.repository.OrderRepository;
import com.oocl.easymovie.repository.SeatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OrderService {
    public static final Boolean IS_USED = true;
    public static final Boolean IS_REBOOK = true;
    public static final Boolean IS_PAID = true;
    public static final Boolean IS_REFUND = true;
    private final OrderRepository orderRepository;
    private final ScheduleService scheduleService;
    private final MovieService movieService;
    private final TheaterService theaterService;
    private final PurchasePointService purchasePointService;

    @Autowired
    private SeatingService seatingService;
    @Autowired
    private SeatingRepository seatingRepository;
    @Autowired
    OrderMapper orderMapper;


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

    public List<Order> findAllOrderByUserId(Long userId) {
        return orderRepository.findAllOrderByUserId(userId);
    }

    public List<Order> findUsedOrderByUserId(Long UserId) {
        return orderRepository.findUsedOrderByUserId(UserId, IS_USED);
    }

    public List<Order> findRebookOrderByUserId(Long UserId) {
        return orderRepository.findRebookOrderByUserId(UserId, IS_REBOOK);
    }

    public List<Order> findPaidOrderByUserId(Long UserId) {
        return orderRepository.findPaidOrderByUserId(UserId, IS_PAID);
    }

    public List<Order> findRefundOrderByUserId(Long UserId) {
        return orderRepository.findRefundOrderByUserId(UserId, IS_REFUND);
    }

    public List<OrderContainMovieTheaterScheduleResponse> getOrderLinkedDataListByTypes(List<Order> orderList){
        List<OrderContainMovieTheaterScheduleResponse> orderContainMovieTheaterScheduleResponseArrayList = new ArrayList<>();
        orderList.forEach(order -> orderContainMovieTheaterScheduleResponseArrayList.add(
                orderMapper.toOrderContainMovieTheaterSchedule(
                        order
                        ,movieService.findById(order.getMovieId())
                        ,theaterService.findTheaterById(order.getTheaterId())
                        , scheduleService.findById(order.getScheduleId())
                )));
        return orderContainMovieTheaterScheduleResponseArrayList;
    }

    public void payForOrder(Long orderId) {
        Order order = findOrderById(orderId);
        purchasePointService.deductBalance(order.getUserId(), (int) order.getTotalPrice());
        order.setIsPaid(true);
        String key = String.valueOf(System.currentTimeMillis());
        order.setQuickMarkKey(key);
        orderRepository.save(order);
    }

    public void modifySeatsAndPrice(Long orderId, Seating seating) {
        //这就要求插入数据的时候要插入每个场次的座位内容
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        Schedule schedule = scheduleService.findById(order.getScheduleId());
        Seating readySeating = seatingService.findSeatingById(schedule.getSeatingId());
        //更新订单的总额
        int count = statisticsSeatedNumber(seating.getSeats());//统计买了几个座位
        double totalPrice = schedule.getPrice() * count;
        order.setTotalPrice(totalPrice);
//        更新总座位的座位信息
        String seatingWithOrder = order.getSeats();
        String seatedStatus = mergeSeatStatus(seatingWithOrder, seating.getSeats());
        readySeating.setSeats(seatedStatus);
        seatingRepository.save(readySeating);
        //更新订单上的座位信息
        order.setSeats(seating.getSeats());
        orderRepository.save(order);

    }

    private int statisticsSeatedNumber(String seated) {
        int count1 = 0;
        for (int i = 0; i < seated.length(); i++) {
            if (seated.charAt(i) != '1' || seated.charAt(i) != '0') {
                if (seated.charAt(i) == '1') count1++;
            }
        }
        return count1;
    }

    private String mergeSeatStatus(String seatWithOrder, String toSeated) {
        String res = "";
        for (int i = 0; i < seatWithOrder.length(); i++) {
            if (seatWithOrder.charAt(i) == '1') {
                res += '1';
                continue;
            }
            if (toSeated.charAt(i) == '1') {
                res += '1';
                continue;
            }
            res += '0';
        }
        return res;

    }


}
