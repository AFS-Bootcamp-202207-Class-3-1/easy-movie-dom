package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.exception.UserNotFoundException;
import com.oocl.easymovie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author edward
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
