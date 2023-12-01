package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan("com.example.demo")
public class SpringBootOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOperationsApplication.class, args);
	}

}
