package com.oocl.easymovie.controller;

import com.oocl.easymovie.entity.PurchasePoint;
import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.repository.PurchasePointRepository;
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
public class PurchasePointControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PurchasePointRepository purchasePointRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void should_return_purchase_point_when_get_given_purchasePointId() throws Exception {
        //given
        PurchasePoint purchasePoint = purchasePointRepository.getReferenceById(1L);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/purchasePoint/{id}", purchasePoint.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then

    }

    @Test
    void should_recharge_when_put_given_userId() throws Exception {
        //given
        PurchasePoint purchasePoint = purchasePointRepository.getReferenceById(1L);
        String rechargeRequest = "{\n" +
                "            \"code\": \"coffee-1-studio\" \n" +
                "            }";

        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/purchasePoint/{id}", purchasePoint.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(rechargeRequest))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then

    }
}
