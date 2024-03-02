package com.springboot.thymeleaf.intro.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class MyController {

	@GetMapping("/test")
	public String getDemo(Model model) {
		model.addAttribute("name", "itachi");
		System.out.println(model.getAttribute("name"));
		return "/demo/test";
	}
	
	@GetMapping("/iterable")
	public String getIterable(Model model) {
			List<String> names = List.of("archit","pomu","har-shit","pundi");
			
			model.addAttribute("names", names);
			
			return "iterable";
		}
	
	@GetMapping("/comparable")
	public String getComparable(Model model) {
		model.addAttribute("isTrue", true);			
		
		String gender = "F";
		
		model.addAttribute("gender",gender);
		
		return "comparable";
	}
	
	@GetMapping("/fragments")
	public String getImpFragments() {
		return "fragments";
	}
	
	@GetMapping("/dynaPass")
	public String getdynaPassFragment(Model model) {
		model.addAttribute("subtitle", "This is subtitle");
		return "dynaPass";
	}
	
	@GetMapping("/about")
	public String getAbout() {
		return "/inheritance/about";
	}
	
	@GetMapping("/service")
	public String getService() {
		return "inheritance/service";
	}
	
}
