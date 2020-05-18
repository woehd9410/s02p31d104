package com.help.back.backend.web;

import com.help.back.backend.domain.User;
import com.help.back.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/user")
    public ResponseEntity postUser(@RequestBody User user) throws Exception{
        try {
            User test = new User();
            System.out.println("유저 추가");
            System.out.println(user.toString());
            int ans = userService.postUser(user);
            System.out.println("추가 성공  : " + ans);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
