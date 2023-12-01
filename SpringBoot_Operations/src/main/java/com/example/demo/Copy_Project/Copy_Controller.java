package com.example.demo.Copy_Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Copy_Controller {
	@Autowired
	Copy_Projectname_service object;
	
	@PostMapping("update_projectName")
	public String empupdate(@RequestBody Copy_Data copy)	{

		String insert=object.projectName(copy);
		
		return insert;
	}


}
