package com.help.back.backend.web;

import com.help.back.backend.domain.User;
import com.help.back.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/list")
    public List<User> getUser(){

        return userService.getUsers();
    }
}
