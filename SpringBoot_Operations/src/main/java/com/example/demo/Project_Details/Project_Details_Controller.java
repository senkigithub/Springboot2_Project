package com.example.demo.Project_Details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Project_Details_Controller {
@Autowired
Project_details_service object;
@PostMapping("project_details")
public String prj(@RequestBody Projetc_Details obj)	{

	String insert=object.prjinsert(obj);
	
	return insert;

}
}
