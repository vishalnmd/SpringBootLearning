package com.springBoot.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springBoot.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	public List<User> findByNameContaining(String name);
	
	public List<User> findByNameLike(String name);
	
	@Query("select u from User u where gender = :n")
	public List<User> getAll(@Param("n") String gender);
		
}
