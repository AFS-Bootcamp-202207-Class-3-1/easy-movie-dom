package com.oocl.easymovie.controller;

import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    void should_return_user_when_perform_update_given_userId() throws Exception {
        //given
        String updateRequest = "{\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"birthday\": \"2000-08-09T07:57:29.000+00:00\" \n" +
                "}";

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("Female"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("2000-08-09T07:57:29.000+00:00"));

        //then

    }

    @Test
    void should_return_user_when_perform_get_given_userId() throws Exception {
        //given
        User user = userRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Carlos"));

        //then

    }

    @Test
    void should_return_vipLevel_and_discount_when_find_given_userId() throws Exception {
        //given
        User user = userRepository.getReferenceById((long) 1);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/level/{id}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.level").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.discount").isNumber());

        //then

    }

//    @Test
//    void should_return_user_response_when_login_given_user_request() throws Exception {
//        //given
//        User user = userRepository.getReferenceById((long) 1);
//        String loginRequest = String.format("{\n" +
//                "    \"username\": \"%s\",\n" +
//                "    \"password\": \"%s\" \n" +
//                "}", user.getUsername(), user.getPassword());
//
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/user/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(loginRequest))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value(user.getUsername()));
//
//        //then
//
//    }

//    @Test
//    void should_return_null_when_create_user_given_user_request() throws Exception {
//        //given
//        String createRequest = "{\n" +
//                "    \"username\": \"Melanie\",\n" +
//                "    \"password\": \"ThisIsMelanie\" \n" +
//                "    \"phoneNumber\": \"11223344556\" \n" +
//                "}";
//
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/user/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(createRequest))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        //then
//
//    }
}
