package com.springboot.login.controller;

import com.springboot.login.jwt.JwtUtils;
import com.springboot.login.model.Users;
import com.springboot.login.services.TasksServices;
import com.springboot.login.services.UsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

   private static final Logger log = LoggerFactory.getLogger(TaskController.class);

   @Autowired
   private TasksServices taskService;

   @Autowired
    private UsersServices usersServices;

   @Autowired
    private JwtUtils jwtUtils;

   @PostMapping("/addTask")
    public ResponseEntity<String> addTask(@RequestBody String task){

        Users user = (Users)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(user.getEmail());
   }
}
