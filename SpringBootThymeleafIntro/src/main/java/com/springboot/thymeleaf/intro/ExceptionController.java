package com.springboot.thymeleaf.intro;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	 @ExceptionHandler(Exception.class)
	    public String handleException() {
	        return "error"; // You can return a custom error page here
	    }
}
