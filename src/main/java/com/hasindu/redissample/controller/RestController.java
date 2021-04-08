package com.hasindu.redissample.controller;

import com.hasindu.redissample.model.User;
import com.hasindu.redissample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hasindu_d
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/user-api")
public class RestController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User addSensor(@RequestBody User user) {
        try {
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return user;

    }
}
