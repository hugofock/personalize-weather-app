package com.pwa.service.impl;

import com.pwa.model.User;
import com.pwa.repository.UserRepository;
import com.pwa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findUserByUsername(String username) {

        return userRepository.findUserByUsername(username);
    }

}
