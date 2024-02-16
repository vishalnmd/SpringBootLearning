package com.springboot.rest.restapi.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springboot.rest.restapi.Pojo.User;

@Component
public class MyUserService {
    
    private static List<User> arr = new ArrayList<>();
    private static Boolean val = false;
    static{
        arr.add(new User("1","Pomu","undefine"));
        arr.add(new User("2","Har-shit","Male"));
        arr.add(new User("3","Archit","gendu"));                 
    }     

    public List<User> getAllUsers(){                
        return arr;
    }

    public Optional<User> getUserById(String id){
        Optional<User>ur = Optional.empty();
        try{
            ur = Optional.of(arr.stream().filter(e->e.getId().equals(id)).findFirst().get());
            return ur;
        }
        catch(Exception e){
            System.out.print(e);
            return ur;
        }
    }

    public void addUser(User user){
        arr.add(user);
    }

    public Boolean deleteUser(String id){
        try{
            Optional<User> user = getUserById(id);
            if(user.isPresent()){
                arr.remove(user.get());
                System.out.println(id + "  " + arr);
                return true;
            }            
            else{
                return false;
            }
        }
        catch(Exception e){            
            e.getStackTrace();
            return false;
        }
    }

    public Boolean editUser(User user, String id){    
        val = false;  
      arr = arr.stream().map(e->{
            if(e.getId().equals(id)){
                e.setName(user.getName());
                e.setGender(user.getGender());
                val=true;
            }
            return e;
        }).collect(Collectors.toList());

        System.out.println(arr);

        return val;        
    }
}
