package com.springboot.multipart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.multipart.utility.ImgUtility;

import jakarta.websocket.server.PathParam;

@RestController
public class MyController {
	
	@Autowired
	ImgUtility img;
	
	@PostMapping("/img_post")
	public ResponseEntity<String> postImg(@PathParam("file") MultipartFile file){
		
		if(file.isEmpty()) {
			return new ResponseEntity<String>("no img found",HttpStatus.BAD_REQUEST);
		}
		
		else if(!file.getContentType().equals("image/jpeg")) {
			return new ResponseEntity<String>("please attach a img in the request",HttpStatus.BAD_REQUEST);
		}
		
		else
		{
			boolean check = img.postImg(file);
			
			if(check) {
				return new ResponseEntity<String>(ServletUriComponentsBuilder.fromCurrentContextPath().path("images").path(file.getOriginalFilename()).toUriString(),HttpStatus.CREATED);
			}
			else{
				return new ResponseEntity<String>("something went wrong",HttpStatus.BAD_REQUEST);
			}
		}
	}
}
