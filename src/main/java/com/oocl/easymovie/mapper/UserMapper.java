package com.oocl.easymovie.mapper;

import com.oocl.easymovie.dto.UserRequest;
import com.oocl.easymovie.dto.UserResponse;
import com.oocl.easymovie.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author edward
 */
@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        final User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

    public UserResponse toResponse(User user) {
        final UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

}
