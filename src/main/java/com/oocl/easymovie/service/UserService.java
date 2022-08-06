package com.oocl.easymovie.service;

import com.oocl.easymovie.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author edward
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
