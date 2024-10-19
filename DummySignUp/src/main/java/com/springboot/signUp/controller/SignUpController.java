package com.springboot.signUp.controller;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.signUp.dao.UserRepository;
import com.springboot.signUp.model.Users;
import com.springboot.signUp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SignUpController {
	
	@Autowired
	private UserRepository repo; 
	
	private UserService userService;		
	
	public SignUpController(UserService userService) {		
		this.userService = userService;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/signUp")
	public ResponseEntity<String> SignUpUser(@RequestBody Users user) {		
		String status = userService.insertUserToDb(user); 
		return new ResponseEntity<String>(status,HttpStatus.ACCEPTED);			
	}
		
	@GetMapping("/users")
//	@PreAuthorize("hasRole('employee')")
	public ResponseEntity<List<Users>> getAllUser(){
		
		List<Users> userLi = new ArrayList<Users>();
		repo.findAll().forEach(userLi::add);
		
		if(userLi.size()>0)
			return new ResponseEntity<List<Users>>(userLi,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/public/home")
	public ResponseEntity<String> getHome(){
		
		return new ResponseEntity<String>("home api called",HttpStatus.OK);
	}
	
	@GetMapping("/public/about")
	public ResponseEntity<String> getAbout(){
		
		return new ResponseEntity<String>("about api called",HttpStatus.OK);
	}
		
	@GetMapping("/csrfToken")
	public CsrfToken getToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	@PostMapping("/login")
	public String getLogin(@RequestBody Users user) {
		
		String res = userService.verifyLogin(user);
		return res;
	}
}
