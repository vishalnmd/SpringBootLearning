package com.springboot.signUp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.signUp.dao.UserRepository;
import com.springboot.signUp.model.Users;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userRepo.findByEmail(username);
		
		System.out.println(user);
		
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("No Email entry found!");
		}
		
		return new MyUserDetails(user);
	}

}
