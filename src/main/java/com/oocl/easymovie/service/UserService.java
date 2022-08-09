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

    public User save(User user) {
        if (user == null) {
            return null;
        }
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User update(Long id, User userRequest) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new UserNotFoundException();
        }
        userRequest.setId(id);
        return userRepository.save(userRequest);
    }
}
