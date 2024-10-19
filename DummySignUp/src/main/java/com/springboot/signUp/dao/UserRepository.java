package com.springboot.signUp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springboot.signUp.model.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {
	
	public Users findByEmail(String email);
}
