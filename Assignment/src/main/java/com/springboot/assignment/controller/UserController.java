package com.springboot.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.assignment.model.Users;
import com.springboot.assignment.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> insertUser(@RequestBody Users user){
		
		String response = userService.insertUser(user);
		
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<Users>> getAllUser(){
		
		List<Users> userList = userService.getAllUsers();
		
		return new ResponseEntity<List<Users>>(userList,HttpStatus.OK);
	}
}
