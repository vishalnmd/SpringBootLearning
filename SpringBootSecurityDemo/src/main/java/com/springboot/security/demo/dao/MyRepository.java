package com.springboot.security.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springboot.security.demo.model.Users;

public interface MyRepository extends CrudRepository<Users, Integer>{
	
	public Optional<Users> findUsersByUsername(String user);
}
