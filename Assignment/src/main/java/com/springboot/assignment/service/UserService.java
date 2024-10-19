package com.springboot.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.assignment.dao.MyRepository;
import com.springboot.assignment.model.Users;

@Component
public class UserService {
	
	@Autowired
	private MyRepository repo;
	
	public String insertUser(Users user) {
		
		List<Users> userList = (List<Users>) repo.findAll();
		
		Optional<Users> usr = userList.stream().filter(e->e.getEmail().equals(user.getEmail())).findFirst();				
		
		if(usr.isEmpty()) {
			repo.save(user);
			return "User successfully registerd";
		}else if(usr.get().getName().equals(user.getName()) && usr.get().getMob().equals(user.getMob())){
				return "User already exist";	
		}else {
//			Optional<Users> odUser = repo.findById(usr.get().getId());
			
			usr.get().setMob(user.getMob());
			usr.get().setName(user.getName());
			
			repo.save(usr.get());						
			
			return "User fields are updated successfully";
		}
		
		/*
		 * if(!userList.stream().filter((e)->e.getEmail().equals(user.getEmail())).
		 * findAny().isEmpty()) { return "User Already exists"; }else { repo.save(user);
		 * return "User successfully registed"; }
		 */
	}
	
	public List<Users> getAllUsers() {
		
		List<Users> userList = (List<Users>) repo.findAll();
		
		return userList;
	}
}
