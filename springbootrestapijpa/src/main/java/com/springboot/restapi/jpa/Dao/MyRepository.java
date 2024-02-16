package com.springboot.restapi.jpa.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.springboot.restapi.jpa.entity.Movies;


@Component
public interface MyRepository extends CrudRepository<Movies,Integer> {
    
}
