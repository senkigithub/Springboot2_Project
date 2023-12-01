package com.example.demo.tasklist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskList_controller {
@Autowired
Service_Tasklist object;
@PostMapping("tasklist")
public String tasklistins(@RequestBody PrjManager_Tasklist_pojo obj) {
	String ins=object.tasklist(obj);
	return ins;
}
@PostMapping("updatetask")
public String tasklistupate(@RequestBody PrjManager_Tasklist_pojo obj) {
	String update=object.updateTask(obj);
	return update;
}
@PostMapping("deletetask")
public String deleteTask1(@RequestBody PrjManager_Tasklist_pojo obj) {
	String update=object.deleteTask(obj);
	return update;
}
@PostMapping("selecttask")
public List select(@RequestBody PrjManager_Tasklist_pojo obj) {
	List select=object.selectTask(obj);
	return select;
}
}
