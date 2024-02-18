package com.springBoot.restapi.crud.complex.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Company {
	
	@Id
	@Column(name = "company_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String origin;
	
	@OneToOne(mappedBy = "company")
	@JsonBackReference
	private Developer developer;
	
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
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}		
	
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", " + (name != null ? "name=" + name + ", " : "")
				+ (origin != null ? "origin=" + origin + ", " : "")
				+ (developer != null ? "developer=" + developer : "") + "]";
	}
}
