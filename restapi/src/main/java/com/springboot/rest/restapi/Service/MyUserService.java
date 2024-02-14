package com.springboot.rest.restapi.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springboot.rest.restapi.Pojo.User;

@Component
public class MyUserService {
    
    private static List<User> arr = new ArrayList<>();

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

    public void addUser(User user){
        arr.add(user);
    }

    public void deleteUser(String id){
        arr = arr.stream().filter(e->!e.getId().equals(id)).collect(Collectors.toList());
        System.out.println(id + "  " + arr);
    }

    public void editUser(User user, String id){
      arr = arr.stream().map(e->{
            if(e.getId().equals(id)){
                e.setName(user.getName());
                e.setGender(user.getGender());
            }
            return e;
        }).collect(Collectors.toList());

        System.out.println(arr);
    }
}
