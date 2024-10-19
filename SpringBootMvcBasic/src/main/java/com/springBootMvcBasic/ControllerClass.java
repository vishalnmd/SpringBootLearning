package com.springBootMvcBasic;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	
public class ControllerClass {
	private static final Logger log = LogManager.getLogger(SpringBootMvcBasicApplication.class);
	
	@RequestMapping("/")
	public String getHome() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		log.info("{} : Root API hits.",timestamp);
		return "home";
	}
	
	@RequestMapping("/menu")
	public String getMenu() {
		return "menu";
	}
}
