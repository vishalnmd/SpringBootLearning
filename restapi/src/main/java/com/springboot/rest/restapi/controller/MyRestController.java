package com.springboot.rest.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.restapi.Pojo.User;
import com.springboot.rest.restapi.Service.MyUserService;

@RestController
public class MyRestController {
    
    @Autowired
    private MyUserService service;

    @GetMapping("/test")
    public String get(){
        return "This is my first rest API ";
    }

    @GetMapping("/users")
    public List<User> getUser(){
       return service.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") String id){
       return service.getUserById(id);
    }
}
