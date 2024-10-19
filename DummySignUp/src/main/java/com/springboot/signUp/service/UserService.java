package com.springboot.signUp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.springboot.signUp.dao.UserRepository;
import com.springboot.signUp.model.Users;

@Component
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
//	public UserService(AuthenticationManager authManager){
//		this.authManager = authManager;
//	}
	
	public String insertUserToDb(Users user) {
		List<Users> usr = (List<Users>) repo.findAll();
		   System.out.println(usr);
		   System.out.println(usr.stream().filter((e)->e.getEmail().equals(user.getEmail())).findAny().isEmpty());
		if(!usr.stream().filter((e)->e.getEmail().equals(user.getEmail())).findAny().isEmpty()) {
			return "Element Already exists";
		}else {
			user.setCreatedDate(new Date());
			user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
			repo.save(user);
			return "User Stored sucessfully";
		}		
	}
	
	public String verifyLogin(Users user) {
		Authentication authentication = authManager
										.authenticate(
										  new UsernamePasswordAuthenticationToken(
												user.getEmail(), user.getPassword()));
		
		if(authentication.isAuthenticated()) 
			return "Success";
			
		return "false";
	}
}
