package com.springboot.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.login.jwt.JwtUtils;
import com.springboot.login.model.Users;
import com.springboot.login.services.UsersServices;


@RestController
public class LoginController {
	
	@Autowired
	UsersServices userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("/getusers")
	public ResponseEntity<List<Users>> getUsers(){
		
		return new ResponseEntity<List<Users>>(userService.getAllUser(),HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public String addUser(@RequestBody Users usr) {
				
		return userService.insertUser(usr);
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<?> login(@RequestBody Users usr){
		
		Authentication authentication;
		
		try {
			authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(usr.getEmail(), usr.getPassword()));
			
		} catch (AuthenticationException e) {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "bad request");
			map.put("status", false);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

		return ResponseEntity.ok(jwtToken);
	}
	
}
