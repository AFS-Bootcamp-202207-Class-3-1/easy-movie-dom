package com.oocl.easymovie.controller;

import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    void should_return_user_when_perform_create_given_user_information() throws Exception {
        //given
        String newControllerJason = "{\n" +
                "    \"username\": \"M\",\n" +
                "    \"phoneNumber\": \"19089896767\",\n" +
                "    \"email\": \"110@oocl.com\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"birthday\": \"2002-08-09T07:57:29.000+00:00\",\n" +
                "    \"avatar\": \"https://p0.pipi.cn/mmdb/25bfd6d706d51b7a3567cbc55ff22a4b2686e.jpg?imageView2/1/w/464/h/644\" \n" +
                "}";

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newControllerJason))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("M"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("19089896767"));

        //then

    }

    @Test
    void should_return_user_when_perform_update_given_userId() throws Exception {
        //given
        String updateRequest = "{\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"birthday\": \"2000-08-09T07:57:29.000+00:00\" \n" +
                "}";

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/users/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("Female"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("2000-08-09T07:57:29.000+00:00"));

        //then

    }

    @Test
    void should_return_user_when_perform_get_given_userId() throws Exception {
        //given
        User user = userRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}",user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Carlos"));

        //then

    }
}
