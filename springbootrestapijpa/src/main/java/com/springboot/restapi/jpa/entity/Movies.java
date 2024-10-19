package com.springboot.restapi.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name = "Movies_list")
public class Movies {
    
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String type;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Movies [id=" + id + ", name=" + name + ", type=" + type + "]";
    }        
}
