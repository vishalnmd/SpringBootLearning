package com.springboot.security.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.demo.dao.MyRepository;
import com.springboot.security.demo.jwt.JWTUtils;
import com.springboot.security.demo.model.Users;

@RestController
public class MyController {

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyRepository repo;

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody Users user) {

		Optional<Users> usr = repo.findUsersByUsername(user.getUsername());

		if (usr.isPresent())
			return ResponseEntity.ok("User already exists");
		else {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			repo.save(user);
		}
		return ResponseEntity.ok("User sigin success");
	}

	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers() {
		List<Users> userList = (List<Users>) repo.findAll();

		return ResponseEntity.ok(userList);
	}

	@PostMapping("/loginUser")
	public ResponseEntity<?> login(@RequestBody Users user) {

		Authentication authentication;		
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

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
