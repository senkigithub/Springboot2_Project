//package com.example.demo.Aop;
//
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
////@RequestMapping("/api")
//public class Controller_Aop {
//@Autowired
//    @GetMapping("/hello")
//@Before("execution(* com.example.demo.Aop.*(..))")
//    public String sayHello() {
//    	Logger log=(Logger) LoggerFactory.getLogger(Controller_Aop.class);
//    	log.info("hi");
//        return "Hello from the controller!";
//    }
//}
//
