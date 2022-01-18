package com.jwt.blog.controller;

import com.jwt.blog.dto.UserDTO;
import com.jwt.blog.entity.AuthRequest;
import com.jwt.blog.entity.User;
import com.jwt.blog.util.Hawk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserDTO userDTO;
    @Autowired
    private Hawk hawk;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return this.userDTO.saveUser(user);
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }
        return hawk.generateToken(authRequest.getEmail());
    }
}
