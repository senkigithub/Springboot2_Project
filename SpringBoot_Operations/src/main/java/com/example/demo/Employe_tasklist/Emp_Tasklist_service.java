package com.example.demo.Employe_tasklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class Emp_Tasklist_service {
@Autowired
JdbcTemplate object;
public String empinsert(Emp_TaskList_Pojo obj) {
String result="";
String usr=obj.getUsername();
String pwd=obj.getPassword();
String empsql="select * from emplogin2 where username=? and password=? and emproleid='p' ";
List<Map<String,Object>> table1=object.queryForList(empsql,usr,pwd);
String prjid="";
if(!table1.isEmpty()) {
	String emp_id=obj.getEmp_id();
	for(Map pmanager:table1) {
		prjid=(String)pmanager.get("id");}
		if(!emp_id.equals(prjid)) {
	String id=obj.getProject_id();
	String empsql2="select * from project_details where id=? and project_manager=? ";
	List<Map<String,Object>> table12=object.queryForList(empsql2,id,usr);
	if(!table12.isEmpty()) {
		
		String empsql3="select * from prj_emp_details where  emp_id=? and assigned_by=?  ";
		List<Map<String,Object>> table3=object.queryForList(empsql3,emp_id,usr);
		if(!table3.isEmpty()) {
			String task_id=obj.getTask_id();
		     String	empsql4="select * from Project_Task_List where Task_Id=? and Created_By=? ";
		     List<Map<String,Object>> table4=object.queryForList(empsql4,task_id,usr);
		     if(!table4.isEmpty()) {
		    	 for(Map emp:table4) {
		    	 String prj_id=obj.getProject_id();
		    	 String prj_mngr_id=obj.getPrj_mangr_id();
		    	 String sdate=obj.getStart_date();
		    	 String edate=obj.getEnd_date();
		    	 String status="A";
		    	 String ins="insert into Employee_TaskList values(?,?,?,?,?,?,?)";
		    	 object.update(ins,prj_id,task_id,usr,emp_id,sdate,edate,status);
		    	 result="inserted";
		    	 }
		     }else {
		    	result="invalid  taskid  "; 
		     }
		}else {
			result="invalid epmloyee id";
		}

	}else {
		result="invalid project id  credentials";

	}
		}else {
			result="project manager can not insert";
		}
}else {
	result="invalid user  credentials";
}
return result;
}
}
