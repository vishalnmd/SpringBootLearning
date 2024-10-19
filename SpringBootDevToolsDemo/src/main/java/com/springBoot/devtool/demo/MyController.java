package com.springBoot.devtool.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "This is sample Test from spring boot";
	}
}
