package com.springBoot.restapi.crud.complex.dao;

import org.springframework.data.repository.CrudRepository;

import com.springBoot.restapi.crud.complex.entity.Developer;

public interface MyRepository extends CrudRepository<Developer, Integer>{

}
