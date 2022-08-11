package com.oocl.easymovie.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.dto.UserRequest;
import com.oocl.easymovie.dto.UserResponse;
import com.oocl.easymovie.entity.VIP;
import com.oocl.easymovie.entity.User;
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

//    @PostMapping
//    public UserResponse create(@RequestBody UserRequest request) {
//        return userMapper.toResponse(userService.save(userMapper.toEntity(request)));
//    }

    @PutMapping("/{id}")
    public UserResponse update(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        return userMapper.toResponse(userService.update(id, userMapper.toEntity(userRequest)));
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userMapper.toResponse(userService.findById(id));
    }

    @GetMapping("/level/{id}")
    public ResultData<VIP> findVIPLevelAndDiscountById(@PathVariable Long id) {
        VIP vip = userService.findVIPLevelAndDiscountById(id);
        return ResultData.success(vip);
    }

    @PostMapping("/user/login")
    public UserResponse login(@RequestBody UserRequest userRequest) {
        long id = userService.login(userMapper.toEntity(userRequest));
        StpUtil.login(id);
        return userMapper.toResponse(userService.findById(id));
    }

    @PostMapping("/user/register")
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(userMapper.toEntity(userRequest));
    }

}
