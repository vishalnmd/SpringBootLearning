package com.springBoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springBoot.Dao.UserRepository;
import com.springBoot.entity.User;

@SpringBootApplication
public class SpringBootCrudApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootCrudApplication.class, args);
		
		UserRepository repo = context.getBean(UserRepository.class);
		
		//Insert operation
		
//		User obj = new User();
//		obj.setName("Archit");
//		obj.setGender("lodu");
//		
//		User user1 = repo.save(obj);
//		
//		System.out.println(user1);
		
//		Optional<User> userOp =  repo.findById(1);
//		
		
		//update Operation
		
//		User user = userOp.get();
//		user.setGender("unknown");				
//		
//		System.out.println(repo.save(user));	
		
		//Selection Operation
		
//		Optional<User> userOp = repo.findById(1);
//		System.out.println(userOp.get());
//		
//		Iterable<User> users = repo.findAll();
//		
//		users.forEach((user)->System.out.println(user));
		
		//deletion Operation
		
//		Optional<User> userOp = repo.findById(1);
//		User user = userOp.get();
//		
//		repo.delete(user);
//		
//		Iterable<User> users = repo.findAll();
//		users.forEach((x)->System.out.println(x));
		
//		List<User> result = repo.findByNameLike("%mu");
//		List<User> result = repo.findByNameContaining("hit");
		
		List<User> result = repo.getAll("male");			    
		
		result.forEach(x->System.out.println(x));
	}
}
