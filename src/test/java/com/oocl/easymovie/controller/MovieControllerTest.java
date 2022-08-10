package com.oocl.easymovie.controller;

import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.repository.MovieRepository;
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
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void should_return_hot_movie_list_when_perform_get_given_find_hot_movies() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/hot"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value("明日战记"));
        //then
    }

    @Test
    void should_return_next_movie_list_when_perform_get_given_find_next_movies() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/next"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("断·桥"));
        //then
    }

    @Test
    void should_return_movie_list_when_perform_get_given_page_size_and_keyword() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies")
                .param("keyword","狼")
                .param("page","1")
                .param("pageSize","5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content",hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[0].name").value("战狼2"));
        //then
    }

    @Test
    void should_return_movie_when_perform_get_given_id() throws Exception {
        //given
        Movie movie = movieRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/{movieId}",movie.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.movie.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.movie.name").value("战狼2"));
        //then
    }


}