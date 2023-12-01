package com.example.demo.Evolution;

//import java.util.logging.Logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.BasicConfigurator;
@Service
//@Aspect
@Component

public class service_class {
	
@Autowired

JdbcTemplate jos;
//@Before("execution(* com.example.demo.Evolution.controller.*(..))")
//public void before(){
//	System.out.println("request to");
//}


public String main(Pojo obj) {
	//Logger log=(Logger) LoggerFactory.getLogger(service_class.class);
	try {
	int id=obj.getId();
	String name=obj.getName();
	String result="";
	//log.error(name);
	String sql="insert into Evoloution_program values(?,?)";
	jos.update(sql,id,name);
	result="inserted";
	//log.info(result);
	//log.error("jhkhgjgfhfgh");
	
	return result;
	}catch(Exception e) {
		System.out.println("duplicate entry"+e.getMessage());
		return "duplicate entry";
	}
}

}
