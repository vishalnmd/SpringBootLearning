package com.springboot.oauth.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@GetMapping("/demo")
	public String getDemo() {
		return "Hello you are authenticated";
	}
	
	@GetMapping("/demo2")
	public String getDemo2() {
		return "Welcome to Demo API 2";
	}
}
