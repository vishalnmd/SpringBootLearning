package com.springboot.security.demo.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.security.demo.model.Users;

@SuppressWarnings("serial")
public class MyUserDetails implements UserDetails{
	
	private Users user;
			
	public MyUserDetails(Users user) {		
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
	}

	@Override
	public String getPassword() {		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
