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
public class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_schedule_when_perform_get_given_theaterId_and_movieId() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/schedule")
                .param("theaterId","1")
                .param("movieId","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].theaterId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].movieId").value(1));

        //then

    }
}
