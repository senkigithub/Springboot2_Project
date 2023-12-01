package com.example.demo.Employe_tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Emp_Tasklist_controller {
@Autowired
Emp_Tasklist_service object;
@PostMapping("emp_tasklist")
public String emp_task_ins(@RequestBody Emp_TaskList_Pojo obj) {
	String ins=object.empinsert(obj);
	return ins;
}
}
