package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.SeatingRequest;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.repository.OrderRepository;
import com.oocl.easymovie.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void should_return_order_when_find_order_contain_movie_theater_schedule_given_orderId() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/{orderId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.theater.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.theater.name").value("Coffee Studio"));

        //then

    }

    @Test
    void should_return_order_when_create_order_given_order_request() throws Exception {
        //given
        String createRequest = "{\n" +
                "            \"userId\": 1,\n" +
                "            \"movieId\": 1,\n" +
                "            \"theaterId\": 1,\n" +
                "            \"scheduleId\": 1\n" +
                "            }";

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userId").value(1));

        //then

    }

    @Test
    void should_return_all_orders_when_find_all_orders_given_userId() throws Exception {
        //given
        User user = userRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/all/{userId}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].order.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].order.userId").value(user.getId()));


        //then

    }

    @Test
    void should_return_used_order_when_get_used_order_given_userId() throws Exception {
        //given
        User user = userRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/used/{userId}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());


        //then
    }

    @Test
    void should_return_rebook_order_when_get_rebook_order_given_userId() throws Exception {
        //given
        User user = userRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/rebook/{userId}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());


        //then
    }

    @Test
    void should_return_paid_order_when_get_paid_order_given_userId() throws Exception {
        //given
        User user = userRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/paid/{userId}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());


        //then
    }

    @Test
    void should_return_refund_order_when_get_refund_order_given_userId() throws Exception {
        //given
        User user = userRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/refund/{userId}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());


        //then
    }

    @Test
    void should_return_nothing_when_pay_for_orders_given_pay_for_orders() throws Exception {
        //given
        Order order = orderRepository.getReferenceById(1L);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/payment/{orderId}", order.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then

    }

    @Test
    void should_modify_seats_and_price_when_post_given_orderId() throws Exception {
        //given
        Order order = orderRepository.getReferenceById(1L);
        String createRequest = "{\n" +
                "            \"seats\": \"000000000100000000010000000001000000\" \n" +
                "            }";

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/{orderId}/seats", order.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequest))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then

    }

    @Test
    void should_refund_when_post_given_orderId() throws Exception {
        //given
        Order order = orderRepository.getReferenceById(1L);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/refund/{orderId}", order.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then

    }
}
