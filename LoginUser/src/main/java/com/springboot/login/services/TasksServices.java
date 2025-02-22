package com.springboot.login.services;

import com.springboot.login.dao.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TasksServices {

    @Autowired
    private TasksRepository repo;

    public String addTask(String task,Integer userId){
        return "";
    }
}
