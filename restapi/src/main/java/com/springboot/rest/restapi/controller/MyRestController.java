package com.springboot.rest.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/user")
    public List<User> getUser(){
       return service.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") String id){
       return service.getUserById(id);
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user){
        service.addUser(user);
        return "New User successfully added into the list";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") String id){
        service.deleteUser(id);
        return "User with id "+ id + "successfully deleted ";
    }

    @PutMapping("/user/{id}")
    public User editUser(@RequestBody User user, @PathVariable("id") String id){
        service.editUser(user, id);
        return user;
    }
}
