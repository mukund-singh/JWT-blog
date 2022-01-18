package com.jwt.blog.dto;

import com.jwt.blog.entity.User;
import com.jwt.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDTO {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return this.userRepository.save(user);
    }
}
