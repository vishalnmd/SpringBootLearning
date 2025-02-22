package com.springboot.login.dao;

import com.springboot.login.model.Tasks;
import org.springframework.data.repository.CrudRepository;

public interface TasksRepository extends CrudRepository<Tasks,Integer> {

}
