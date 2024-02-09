package com.springBoot.functionalInterface;

public class MyInterface implements IMyInterface{
	
	@Override
	public void call() {
		System.out.println("My call method called");
	}
}
