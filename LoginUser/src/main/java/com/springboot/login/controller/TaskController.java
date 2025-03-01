package com.springboot.login.controller;

import com.springboot.login.jwt.JwtUtils;
import com.springboot.login.model.Tasks;
import com.springboot.login.model.Users;
import com.springboot.login.services.TasksServices;
import com.springboot.login.services.UsersServices;
import com.springboot.login.stub.FetchTaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TaskController {

   private static final Logger log = LoggerFactory.getLogger(TaskController.class);

   @Autowired
   private TasksServices taskService;

   @Autowired
    private UsersServices usersServices;

   @Autowired
    private JwtUtils jwtUtils;

   @PostMapping("/addTask")
    public ResponseEntity<String> addTask(@RequestBody Map<String,String> task){

        UserDetails user = (UserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       Optional<Users> usr = usersServices.getUserFromEmail(user.getUsername());

       if(usr.isPresent()){
           Tasks taskObj = new Tasks();
           taskObj.setTasks(task.get("task"));
           taskObj.setActive(true);
           taskObj.setUser(usr.get());
           taskObj.setCreatedDate(new Date());

           String response = taskService.addTask(taskObj);

           if(response.contains("successfully")) return ResponseEntity.ok("task successfully add!");
           else return ResponseEntity.ok("technical Error occurred, null task might passed ");
       }else{

           return ResponseEntity.ok("Invalid user");
       }
   }

   @GetMapping("/fetchTasks")
    public ResponseEntity<List<FetchTaskResponse>> fetchTasks(){

       UserDetails user = (UserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       List<FetchTaskResponse> taskLst = taskService.getTaskByUsername(user.getUsername());

       log.debug(taskLst.toString());

       return ResponseEntity.ok(taskLst);
   }

   @PostMapping("/taskCompleted")
    public ResponseEntity<String> taskCompletedByEmail(@RequestBody Map<String,Integer>body){
       UserDetails user = (UserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       String respo = taskService.tasksCompletedByUsername(user.getUsername(),body.get("id"));

       return ResponseEntity.ok(respo);
   }

}
