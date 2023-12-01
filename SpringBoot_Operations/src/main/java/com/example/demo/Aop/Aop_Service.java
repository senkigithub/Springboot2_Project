//package com.example.demo.Aop;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@Aspect
//public class Aop_Service {
//	@Autowired
//	@Before("execution(* com.example.demo.Aop.Controller_Aop*(..))")
//	 public void doSomething() {
//		Logger log=(Logger) LoggerFactory.getLogger(Aop_Service.class);
//    	log.info("Doing something...");
//	       // System.out.println("Doing something...");
//	    }
//	
//	public void demo() {
//		Logger log=(Logger) LoggerFactory.getLogger(Aop_Service.class);
//		log.info("hifghgfgf");
//
//	}
//}
