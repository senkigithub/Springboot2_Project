package com.example.demo.Employee_Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Employee_Controller_class {
@Autowired
Employee_Service_class object;

@PostMapping("meeting_details")
public String empinsert(@RequestBody Employee_Pojo_class ins) {
	String i=object.employeeinsert(ins);
	return i;
}
}
