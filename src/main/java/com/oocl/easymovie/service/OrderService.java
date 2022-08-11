package com.oocl.easymovie.service;

import com.oocl.easymovie.dto.OrderContainMovieTheaterScheduleResponse;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.entity.PurchasePoint;
import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.Seating;
import com.oocl.easymovie.exception.BalanceNotEnough;
import com.oocl.easymovie.exception.OrderNotFoundException;
import com.oocl.easymovie.exception.ScheduleNotFoundException;
import com.oocl.easymovie.exception.SeatingNotFoundException;
import com.oocl.easymovie.mapper.OrderMapper;
import com.oocl.easymovie.repository.OrderRepository;
import com.oocl.easymovie.repository.PurchasePointRepository;
import com.oocl.easymovie.repository.ScheduleRepository;
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

    private final PurchasePointRepository purchasePointRepository;

    @Autowired
    private SeatingService seatingService;
    @Autowired
    private SeatingRepository seatingRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    OrderMapper orderMapper;


    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order createOrder(Order order) {

        //TODO 这里创建订单的时候就他设置总额有问题，如果加入选座环节的话
        order.setTotalPrice(scheduleService.findById(order.getScheduleId()).getPrice());
        order.setIsPaid(false);
        order.setIsRebook(false);
        order.setIsRefund(false);
        order.setIsTicketUsed(false);
        order.setSeats("000000000000000000000000000000000000");//设置当前订单订购座位情况
        return orderRepository.save(order);
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

    public List<OrderContainMovieTheaterScheduleResponse> getOrderLinkedDataListByTypes(List<Order> orderList) {
        List<OrderContainMovieTheaterScheduleResponse> orderContainMovieTheaterScheduleResponseArrayList = new ArrayList<>();
        orderList.forEach(order -> orderContainMovieTheaterScheduleResponseArrayList.add(
                orderMapper.toOrderContainMovieTheaterSchedule(
                        order
                        , movieService.findById(order.getMovieId())
                        , theaterService.findTheaterById(order.getTheaterId())
                        , scheduleService.findById(order.getScheduleId())
                )));
        return orderContainMovieTheaterScheduleResponseArrayList;
    }

    public void payForOrder(Long orderId) {
        Order order = findOrderById(orderId);
        purchasePointService.deductBalance(order.getUserId(), order.getTotalPrice());
        order.setIsPaid(true);
        order.setIsRefund(false);
        order.setIsTicketUsed(false);
        String key = String.valueOf(System.currentTimeMillis());
        order.setQuickMarkKey(key);
        orderRepository.save(order);
    }

    public void modifySeatsAndPrice(Long orderId, Seating seating) {
        //这就要求插入数据的时候要插入每个场次的座位内容
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        Schedule schedule = scheduleService.findById(order.getScheduleId());
        Seating seatingWithSchedule = seatingService.findSeatingById(schedule.getSeatingId());
        //更新订单的总额
        int count = statisticsSeatedNumber(seating.getSeats());//统计买了几个座位
        double totalPrice = schedule.getPrice() * count;
        order.setTotalPrice(totalPrice);
//        更新schedule的seating信息
        String seatedStatus = mergeSeatStatus(seatingWithSchedule.getSeats(), seating.getSeats());
        seatingWithSchedule.setSeats(seatedStatus);
        seatingRepository.save(seatingWithSchedule);
        //更新订单上的座位信息
        order.setSeats(seating.getSeats());
        orderRepository.save(order);

    }

    private int statisticsSeatedNumber(String seated) {
        int count = 0;
        for (int i = 0; i < seated.length(); i++) {
            if (seated.charAt(i) == '1') count++;
        }
        return count;
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


    public void refundOrdersById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
//        if(order.getIsRefund())throw new RuntimeException("订单退款失败"); 待完善
        Long userId = order.getUserId();
        Schedule schedule = scheduleRepository.findById(order.getScheduleId()).orElseThrow(ScheduleNotFoundException::new);
        Seating seating = seatingRepository.findById(schedule.getSeatingId()).orElseThrow(SeatingNotFoundException::new);
        //退款，更新用户余额
        refundBalance(userId, order.getTotalPrice());
        //解锁Schedule中Seating的seats
        String unlockSeating = unlockSeating(seating.getSeats(), order.getSeats());
        seating.setSeats(unlockSeating);
        seatingRepository.save(seating);

        //更新订单状态
        order.setIsPaid(false);
        order.setIsRefund(true);
        order.setIsTicketUsed(false);
        order.setSeats("000000000000000000000000000000000000");
        orderRepository.save(order);

    }

    private void refundBalance(Long userId, Double price) {
        PurchasePoint purchasePoint = purchasePointRepository.findByUserId(userId);
        if (Objects.isNull(purchasePoint)) throw new BalanceNotEnough();
        if (Objects.nonNull(price) && price > 0) {
            purchasePoint.setBalance(purchasePoint.getBalance() + price);
        }
        purchasePointRepository.save(purchasePoint);
    }

    private String unlockSeating(String seatingWithSchedule, String seatingOrder) {
        String res = "";
        for (int i = 0; i < seatingOrder.length(); i++) {
            if (seatingOrder.charAt(i) == '1') {
                res += '0';
                continue;
            }
            res += seatingWithSchedule.charAt(i);
        }
        return res;
    }
}
