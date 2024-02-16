package com.springboot.restapi.jpa.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MoviesController {
    
    @GetMapping("/demo")
    public String demo(){
        return "testing api response";
    }
}
