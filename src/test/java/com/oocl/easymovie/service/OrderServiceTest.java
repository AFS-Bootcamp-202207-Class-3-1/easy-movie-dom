package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class OrderServiceTest {
    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

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
        Order order = new Order();
        doReturn(order).when(orderRepository).save(order);

        //when
        Order createdOrder = orderService.createOrder(order);

        //then
        assertEquals(order, createdOrder);
    }

    @Test
    void should_return_order_when_update_order_given_id_and_a_update_request() {
        //given
        Order order = new Order();
        order.setSnacksId((long) 1);
        Order orderRequest = new Order();
        orderRequest.setSnacksId((long) 2);
        doReturn(Optional.of(order)).when(orderRepository).findById(order.getId());
        doReturn(order).when(orderRepository).save(order);

        //when
        Order newOrder = orderService.updateOrder(order.getId(), orderRequest);

        //then
        assertEquals(2, newOrder.getSnacksId());
    }

    @Test
    void should_return_order_in_page_when_find_order_by_userId_and_page_given_userId_page() {
        //given
        User user = new User();
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(order1);
        orderList.add(order2);
        Page<Order> orderPage = new PageImpl<Order>(orderList);
        doReturn(orderPage).when(orderRepository).findByUserId(user.getId(), PageRequest.of(0, 2));

        //when
        Page<Order> orderInPage = orderService.findOrderByUserIdAndPage(user.getId(),1,2);

        //then
        assertEquals(orderPage, orderInPage);

    }
}
