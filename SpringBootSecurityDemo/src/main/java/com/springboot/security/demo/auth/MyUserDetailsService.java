package com.springboot.security.demo.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springboot.security.demo.dao.MyRepository;
import com.springboot.security.demo.model.Users;
	
@Configuration
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MyRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> usr = repo.findUsersByUsername(username);
		
		if(usr.isEmpty()) {
			throw new UsernameNotFoundException("No username with inserted details found");
		}
		
		return new MyUserDetails(usr.get());
	}

}
