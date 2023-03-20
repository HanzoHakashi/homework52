package com.example.homework_50.controller;

import com.example.homework_50.dto.UserDto;
import com.example.homework_50.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{email}")
    public UserDto getUserByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }
    @GetMapping("name/{name}")
    public UserDto getUserByName(@PathVariable String name){
        return userService.findByName(name);
    }
    @GetMapping("username/{username}")
    public UserDto getUserByUsername(@PathVariable String username){
        return userService.findByUserName(username);
    }
}
