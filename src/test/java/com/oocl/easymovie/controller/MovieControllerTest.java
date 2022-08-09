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
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_users_when_perform_get_given_find_by_id() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ping"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //then
    }


}