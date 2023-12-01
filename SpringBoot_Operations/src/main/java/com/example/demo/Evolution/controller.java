package com.example.demo.Evolution;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.BasicConfigurator;

@RestController
@Aspect
@Component
public class controller {
@Autowired

service_class obj;

@PostMapping("evolution")

public String demo(@RequestBody Pojo object) {
	
	String insert= obj.main(object);
	return insert;
}
}
