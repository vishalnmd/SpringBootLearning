package com.springboot.assignment.dao;

import org.springframework.data.repository.CrudRepository;
import com.springboot.assignment.model.Users;

public interface MyRepository extends CrudRepository<Users,Integer>{

}
