package com.springBoot.restapi.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.restapi.crud.dao.MyRepository;
import com.springBoot.restapi.crud.entity.Movies;

@RestController
public class MoviesyRestController {
	
	@Autowired
	private MyRepository repo;
	
	@GetMapping("/test")
	public ResponseEntity<String> demo() {
		return new ResponseEntity<>("This is my demo api",HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/movies")
	public ResponseEntity<List<Movies>> getAllMovies(){
		List<Movies> movLst = new ArrayList<>();
		repo.findAll().forEach(movLst::add);
		
		if(movLst.size()>0)
			return new ResponseEntity<List<Movies>>(movLst,HttpStatus.OK);
		else
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
	
	@GetMapping("/movies/{id}")
	public ResponseEntity<Movies> getMovieById(@PathVariable("id") int id){
		Optional<Movies> mv = repo.findById(id);
		
		if(mv.isPresent())
			return new ResponseEntity<Movies>(mv.get(), HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/movies")
	public ResponseEntity<String> addMovie(@RequestBody Movies mv){
		Optional<Movies> mov = repo.findById(mv.getId());
		
		if(mov.isEmpty()) {
			repo.save(mv);		
			return new ResponseEntity<String>("New entry successfully added to the db", HttpStatus.ACCEPTED);
		}
		else
			return new ResponseEntity<String>("Element with same ID already Exists", HttpStatus.ALREADY_REPORTED);
			
	}
	
	@PutMapping("/movies/{id}")
	public ResponseEntity<String> editList(@RequestBody Movies mv , @PathVariable("id") int id){
		Optional<Movies> mov = repo.findById(id);
		
		if(mov.isPresent()) {
			mv.setId(mov.get().getId());
			repo.save(mv);
			return new ResponseEntity<>("Entry successfully updated into the db", HttpStatus.ACCEPTED);
		}
		else 
			return new ResponseEntity<>("No record from given id found !",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<Movies> deleteMovie(@PathVariable("id") int id){
		Optional<Movies> mov = repo.findById(id);
		
		if(mov.isPresent()) {
			repo.delete(mov.get());
			return new ResponseEntity<Movies>(mov.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<Movies>( HttpStatus.NOT_FOUND);
	}
	
 }