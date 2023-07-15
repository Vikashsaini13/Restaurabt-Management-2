package com.example.RestaurantManagement2.controller;

import com.example.RestaurantManagement2.model.User;
import com.example.RestaurantManagement2.model.dto.SignInInput;
import com.example.RestaurantManagement2.model.dto.SignUpOutput;
import com.example.RestaurantManagement2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //add patient
    @PostMapping("user/signUp")
    public SignUpOutput signUpUser(@RequestBody User user){
        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput){
        return userService.signInUser(signInInput);
    }
}
