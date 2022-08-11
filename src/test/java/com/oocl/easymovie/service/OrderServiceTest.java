package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.repository.OrderRepository;
import com.oocl.easymovie.repository.SeatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    SeatingService seatingService;

    @Mock
    SeatingRepository seatingRepository;

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
    void should_return_null_when_pay_for_order_given_orderId() {
        //given
        Order order = new Order();
        doReturn(order).when(orderService).findOrderById(order.getId());
        doNothing().when(purchasePointService).deductBalance(order.getUserId(), (int) order.getTotalPrice());

        //when
        orderService.payForOrder(order.getId());

        //then
        verify(orderRepository, times(1)).save(order);

    }

    @Test
    void should_return_null_when_modify_seats_and_price_given_orderId_and_seating() {
        //given
        Order order = new Order();
        Schedule schedule = new Schedule();
        order.setScheduleId(schedule.getId());
        order.setSeats("000000100000000000000000000000000001");
        schedule.setPrice((double) 50);
        Seating seating = new Seating();
        seating.setSeats("000000100000000000000000000000000001");
        Seating seatingOrder = new Seating();
        seatingOrder.setSeats("000000100000000000000000000000000001");
        doReturn(Optional.of(order)).when(orderRepository).findById(order.getId());
        doReturn(schedule).when(scheduleService).findById(schedule.getId());
        doReturn(seatingOrder).when(seatingService).findSeatingById(seatingOrder.getId());

        //when
        orderService.modifySeatsAndPrice(order.getId(), seating);

        //then
        verify(orderRepository, times(1)).save(order);
        verify(seatingRepository, times(1)).save(seatingOrder);
    }
}
