package com.oocl.easymovie.controller;

import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    void should_return_order_when_find_order_contain_movie_theater_schedule_given_orderId() throws Exception {
        //given
        Order order = new Order();

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/{orderId}", order.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.theater.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.theater.name").value("Coffee Studio"));

        //then

    }
}
