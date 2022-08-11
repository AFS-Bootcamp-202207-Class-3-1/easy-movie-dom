package com.oocl.easymovie.service;

import com.oocl.easymovie.dto.VIP;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.exception.OrderNotFoundException;
import com.oocl.easymovie.exception.UserNotFoundException;
import com.oocl.easymovie.repository.OrderRepository;
import com.oocl.easymovie.repository.SeatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class OrderServiceTest {
    @Spy
    @InjectMocks
    OrderService orderService;

    @Mock
    ScheduleService scheduleService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    PurchasePointService purchasePointService;

    @Mock
    UserService userService;

    @Test
    void should_return_order_when_get_by_id_given_orderId() {
        //given
        Order order = new Order();
        doReturn(Optional.of(order)).when(orderRepository).findById(order.getId());

        //when
        Order orderGotById = orderService.findOrderById(order.getId());

        //then
        assertEquals(order, orderGotById);
    }

    @Test
    void should_throw_exception_when_get_order_by_id_given_wrong_orderId() {
        //given

        //when
        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> orderService.findOrderById(999L));

        //then
        assertEquals("order not found", exception.getMessage());
    }

    @Test
    void should_return_order_when_create_order_given_order() {
        //given
        Schedule schedule = new Schedule();
        schedule.setPrice((double) 50);
        Order order = new Order();
        doReturn(order).when(orderRepository).save(order);
        doReturn(schedule).when(scheduleService).findById(order.getScheduleId());

        //when
        Order createdOrder = orderService.createOrder(order);

        //then
        assertEquals(order, createdOrder);
    }

//    @Test
//    void should_return_order_in_page_when_find_order_by_userId_and_page_given_userId_page() {
//        //given
//        User user = new User();
//        Order order1 = new Order();
//        Order order2 = new Order();
//        List<Order> orderList = new ArrayList<Order>();
//        orderList.add(order1);
//        orderList.add(order2);
//        Page<Order> orderPage = new PageImpl<Order>(orderList);
//        doReturn(orderPage).when(orderRepository).findByUserId(user.getId(), PageRequest.of(0, 2));
//
//        //when
//        Page<Order> orderInPage = orderService.findOrderByUserIdAndPage(user.getId(),1,2);
//
//        //then
//        assertEquals(orderPage, orderInPage);
//
//    }

    @Test
    void should_return_order_list_when_get_order_given_userId() {
        //given
        Order order1 = new Order();
        Order order2 = new Order();
        User user = new User();
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(order1);
        orderList.add(order2);
        doReturn(orderList).when(orderRepository).findAllOrderByUserId(user.getId());

        //when
        List<Order> gotOrder = orderService.findAllOrderByUserId(user.getId());

        //then
        assertEquals(orderList, gotOrder);

    }

    @Test
    void should_get_used_order_when_get_used_order_given_userId() {
        //given
        User user = new User();
        Order order = new Order();
        order.setIsTicketUsed(true);
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(order);
        doReturn(orderList).when(orderRepository).findUsedOrderByUserId(user.getId(), true);

        //when
        List<Order> gotOrders = orderService.findUsedOrderByUserId(user.getId());

        //then
        assertEquals(orderList, gotOrders);

    }

    @Test
    void should_return_rebooked_order_list_when_get_rebook_order_given_userId() {
        //given
        User user = new User();
        Order order = new Order();
        order.setIsRebook(true);
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(order);
        doReturn(orderList).when(orderRepository).findRebookOrderByUserId(user.getId(), true);

        //when
        List<Order> gotOrders = orderService.findRebookOrderByUserId(user.getId());

        //then
        assertEquals(orderList, gotOrders);

    }

    @Test
    void should_return_paid_order_when_get_paid_order_given_userId() {
        //given
        User user = new User();
        Order order = new Order();
        order.setIsPaid(true);
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(order);
        doReturn(orderList).when(orderRepository).findPaidOrderByUserId(user.getId(), true);

        //when
        List<Order> gotOrders = orderService.findPaidOrderByUserId(user.getId());

        //then
        assertEquals(orderList, gotOrders);

    }

    @Test
    void should_return_null_when_pay_for_order_given_orderId() {
        //given
        Order order = new Order();
        order.setUserId(1L);
        order.setTotalPrice(50);
        VIP vip = new VIP();
        vip.setDiscount(0.85);
        vip.setLevel(3);
        doReturn(order).when(orderService).findOrderById(order.getId());
        doNothing().when(purchasePointService).deductBalance(order.getUserId(), order.getTotalPrice());
        doReturn(vip).when(userService).findVIPLevelAndDiscountById(order.getUserId());

        //when
        orderService.payForOrder(order.getId());

        //then
        verify(orderRepository, times(1)).save(order);

    }
}
