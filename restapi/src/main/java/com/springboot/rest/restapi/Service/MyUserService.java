package com.springboot.rest.restapi.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.rest.restapi.Pojo.User;

@Component
public class MyUserService {
    
    private static ArrayList<User> arr = new ArrayList<>();

    static{
        arr.add(new User("1","Pomu","undefine"));
        arr.add(new User("2","Har-shit","Male"));
        arr.add(new User("3","Archit","gendu"));         
    }     

    public List<User> getAllUsers(){                
        return arr;
    }

    public User getUserById(String id){
        User ur = null;
        ur = arr.stream().filter(e->e.getId().equals(id)).findFirst().get();
        return ur;
    }
}
