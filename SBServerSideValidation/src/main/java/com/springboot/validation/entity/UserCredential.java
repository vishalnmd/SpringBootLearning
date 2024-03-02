package com.springboot.validation.entity;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCredential {
	
	@Pattern(regexp = "^(.+)@(\\S+)$", message = "Please enter correct Email")
	private String username;
	
	@Size(min = 8,max = 16)
	private String password;
	
	@AssertTrue
	private boolean isCheck;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}			
	
	public boolean getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	@Override
	public String toString() {
		return "UserCredential [" + (username != null ? "username=" + username + ", " : "")
				+ (password != null ? "password=" + password + ", " : "") + "isCheck=" + isCheck + "]";
	}
}
