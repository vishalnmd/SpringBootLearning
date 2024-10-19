package com.springboot.rest.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> getUser(){
       List<User> li = service.getAllUsers();

       if(li.size()<=0){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();          
       }
       else{
        return new ResponseEntity<List<User>>(li, HttpStatus.OK);
       }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){
        Optional<User> user = service.getUserById(id);
       if(!user.isEmpty()){
        return new ResponseEntity<>(user.get(),HttpStatus.OK);
       }
       else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user){
        service.addUser(user);
        return new ResponseEntity<>("New User successfully added into the list",HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
       Boolean mode = service.deleteUser(id);

       if(mode)
        return new ResponseEntity<String>("User with id "+ id + "successfully deleted ", HttpStatus.ACCEPTED);
       else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       } 
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable("id") String id){
        Boolean val = service.editUser(user, id);
        if(val)
        return new ResponseEntity<User>(user, HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
