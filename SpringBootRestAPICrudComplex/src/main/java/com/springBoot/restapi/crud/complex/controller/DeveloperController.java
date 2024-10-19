package com.springBoot.restapi.crud.complex.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.restapi.crud.complex.dao.MyRepository;
import com.springBoot.restapi.crud.complex.entity.Developer;


@RestController
public class DeveloperController {

	@Autowired
	private MyRepository repo;
	
	@GetMapping("/developers")
	public ResponseEntity<List<Developer>> getAllDevs(){
		List<Developer> li = new ArrayList<>();
		repo.findAll().forEach(li::add);
		
		if(li.size()>0) 
			return new ResponseEntity<List<Developer>>(li, HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	@GetMapping("/developers/{id}")
	public ResponseEntity<Developer> getDevById(@PathVariable("id") int id){
		Optional<Developer> dev = repo.findById(id);
		
		if(dev.isPresent())
			return new ResponseEntity<Developer>(dev.get(),HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/developers")
	public ResponseEntity<String> addDev(@RequestBody Developer dev){
		Optional<Developer> dv = repo.findById(dev.getId());
		
		if(dv.isEmpty()) {
			repo.save(dev);
			return new ResponseEntity<String>("Data successfully saved into the db", HttpStatus.ACCEPTED);
		}
		else
			return new ResponseEntity<String>("Daa with same ID already exists", HttpStatus.ALREADY_REPORTED);
	}
}
