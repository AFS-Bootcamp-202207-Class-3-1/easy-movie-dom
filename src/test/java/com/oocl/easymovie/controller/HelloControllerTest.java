package com.oocl.easymovie.controller;

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
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_pong_when_ping_given_ping() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ping"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("pong"));

        //then

    }

    @Test
    void should_return_devBranch_when_dev_given_dev() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/dev"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("devBranch"));

        //then

    }
}
