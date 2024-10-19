package com.springBoot.restapi.crud.dao;

import org.springframework.data.repository.CrudRepository;

import com.springBoot.restapi.crud.entity.Movies;

public interface MyRepository extends CrudRepository<Movies, Integer>{

}
