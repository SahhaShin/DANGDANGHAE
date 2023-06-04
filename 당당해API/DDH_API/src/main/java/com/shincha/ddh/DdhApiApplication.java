package com.shincha.ddh;


import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class DdhApiApplication {
	
	public static void main(String[] args) throws IllegalStateException, IOException {
		ApplicationContext context = SpringApplication.run(DdhApiApplication.class, args);
		
		for(String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

}