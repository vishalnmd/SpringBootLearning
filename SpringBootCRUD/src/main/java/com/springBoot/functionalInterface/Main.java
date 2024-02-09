package com.springBoot.functionalInterface;

public class Main {
	
	public static void main(String[] args) {
		
		IMyInterface obj = new MyInterface();
		
		obj.call();
		
		IMyInterface obj2 = new IMyInterface() {
			
			@Override
			public void call() {			
				System.out.println("This method is called using anonymous");
			}
		};
		
		obj2.call();
		
		IMyInterface obj3 = ()->{
			System.out.println("this is called using functional Interface");
		};
		
		obj3.call();
	}
}
