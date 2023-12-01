package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	Logger log=LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut(value="execution(* com.example.demo.*.*.*(..))")
	public void aspectlog() {
		log.debug("hello");
		System.out.println("sdkhjhjhjfh");
	}
	
	@Before("aspectlog()")
	public void asp() {
		log.debug("Method is invoked");
		System.out.println("entered to method");
		log.error("hello");
	}
	
	@AfterReturning(value="execution(* com.example.demo.*.*.*(..))",returning = "result")
		public void display(JoinPoint joinPoint, Object result) {
		
		log.info("method name : "+joinPoint.getSignature().getName()+" class name : "+joinPoint.getTarget().getClass());
		log.info("Result :  "+" "+result);
	}
	
	
}
