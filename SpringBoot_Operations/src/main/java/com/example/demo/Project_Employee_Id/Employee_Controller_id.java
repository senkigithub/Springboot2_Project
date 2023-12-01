package com.example.demo.Project_Employee_Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Employee_Controller_id {
	@Autowired
	Employee_Service_Id object;
	@PostMapping("project_id")
	public String empinsert1(@RequestBody Employee_Details pj)	{

		String insert=object.empinsert(pj);
		
		return insert;


	}
}
