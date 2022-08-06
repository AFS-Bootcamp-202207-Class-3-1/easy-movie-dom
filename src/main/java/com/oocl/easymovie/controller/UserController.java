package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.UserRequest;
import com.oocl.easymovie.dto.UserResponse;
import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.mapper.UserMapper;
import com.oocl.easymovie.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author edward
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {
        final User user = userMapper.toEntity(request);
        userService.save(user);
        return userMapper.toResponse(user);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Integer id) {
        return userMapper.toResponse(userService.findById(id));
    }
}
