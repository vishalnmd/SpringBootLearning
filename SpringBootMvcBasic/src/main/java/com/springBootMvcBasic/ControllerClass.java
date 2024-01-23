package com.springBootMvcBasic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	
public class ControllerClass {
	
	@RequestMapping("/")
	public String getHome() {
		return "home";
	}
	
	@RequestMapping("/menu")
	public String getMenu() {
		return "menu";
	}
}
