package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.UserRequest;
import com.oocl.easymovie.dto.UserResponse;
import com.oocl.easymovie.mapper.UserMapper;
import com.oocl.easymovie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author edward
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {
        return userMapper.toResponse(userService.save(userMapper.toEntity(request)));
    }

    @PutMapping("/{id}")
    public UserResponse update(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        return userMapper.toResponse(userService.update(id, userMapper.toEntity(userRequest)));
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userMapper.toResponse(userService.findById(id));
    }
}
