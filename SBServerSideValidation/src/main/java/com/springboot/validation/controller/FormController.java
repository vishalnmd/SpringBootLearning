package com.springboot.validation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.validation.entity.UserCredential;

import jakarta.validation.Valid;


@Controller
public class FormController {
	
	@GetMapping("/login")
	public String getlogin(Model model) {
		model.addAttribute("userCredentials",new UserCredential());
		return "login";
	}
	
	@PostMapping("/dashboard")
	public String getDashboard(@Valid @ModelAttribute("userCredentials") UserCredential user, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println(result);
			return "login";
		}		
		
		System.out.println(result);
		return "Dashboard";
	}
	
}
