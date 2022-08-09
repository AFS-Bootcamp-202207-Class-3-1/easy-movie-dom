package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.exception.UserNotFoundException;
import com.oocl.easymovie.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;


    @Test
    void should_save_user_when_save_given_user() {
        //given
        User user = new User();
        user.setUsername("OOCl");
        doReturn(user).when(userRepository).save(any());
        //when
        User save = userService.save(user);
        //then
        assertEquals(user,save);
    }

    @Test
    void should_return_user_when_find_by_id_given_id() {
        //given
        Long id=1L;
        User user = new User();
        user.setId(id);
        user.setUsername("Carlos");
        doReturn(Optional.of(user)).when(userRepository).findById(id);
        //when
        User findUser = userService.findById(id);
        //then
        assertEquals(user,findUser);
    }

    @Test
    void should_return_null_when_find_by_id_given_wrong_id() {
        //given
        //when
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.findById(999L));
        //then
        assertEquals("User not found",exception.getMessage());
    }

    @Test
    void should_update_user_when_update_given_gender() {
        //given
        User user = new User();
        user.setGender("male");
        user.setId(1L);
        doReturn(Optional.of(user)).when(userRepository).findById(1L);
        doReturn(user).when(userRepository).save(any());
        //when
        User update = userService.update(1L, user);
        //then
        assertEquals(user,update);
    }







}