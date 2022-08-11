package com.oocl.easymovie.controller;

import com.oocl.easymovie.entity.Theater;
import com.oocl.easymovie.repository.TheaterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TheaterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TheaterRepository theaterRepository;

    @Test
    void should_return_theater_when_perform_get_given_theaterId() throws Exception {
        //given
        Theater theater = theaterRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/theater/{theaterId}", theater.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.theater.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.theater.name").value("Coffee Studio"));

        //then

    }

    @Test
    void should_return_theater_when_perform_get_given_movieId() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/theater/movie/{movieId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("Coffee Studio"));

        //then

    }

    @Test
    void should_return_theater_list_when_perform_get_given_page_size() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/theater")
                .param("page","1")
                .param("pageSize","5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content",hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[0].name").value("Coffee Studio"));
        //then
    }
}
