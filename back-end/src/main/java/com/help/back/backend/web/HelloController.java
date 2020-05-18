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

    @GetMapping("/user")
    public List<User> getUser(){

        return userService.getUsers();
    }

    @PostMapping("/user")
    public ResponseEntity postUser(@RequestBody User user) throws Exception{
        try {
            System.out.println("유저 추가");
            System.out.println(user.toString());
            int ans = userService.postUser(user);
            System.out.println("추가 성공  : " + ans);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestBody User user) throws Exception{
        try {
            System.out.println("유저 수정");
            System.out.println(user.toString());
            int ans = userService.updateUser(user);
            System.out.println("수정 성공  : " + ans);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteUser(@RequestParam("user_id") String user_id) throws Exception{
        try {
            System.out.println("유저 삭제");
            System.out.println(user_id);
            int ans = userService.deleteUser(user_id);
            System.out.println("삭제 성공  : " + ans);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
