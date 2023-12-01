package com.example.demo.Project_Employee_Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;



@Service
public class Employee_Service_Id {
	@Autowired
	JdbcTemplate object;
	public String empinsert(Employee_Details obj) {
		String result="";
		String usr=obj.getUsername();
		String pwd=obj.getPassword();
		String empid=obj.getEmpid();
		
		String sql1="select * from emplogin2 where username=? and password=? and emproleid='p' ";
		List<Map<String,Object>> hm=new ArrayList<Map<String,Object>>();
		hm=object.queryForList(sql1,usr,pwd);
		String empname="";
   	 String joindate="";
         String phn="";
   	  String mail="";
  	String prjid="";
	String prname="";
	DateTimeFormatter a=DateTimeFormatter.ofPattern("dd/MM/YYYY");
	LocalDateTime b=LocalDateTime.now();
	String date=a.format(b);
     if(!hm.isEmpty()) {
    	 String sql="select * from emplogin2 where id=? and emproleid='e' or emproleid='p'";
    	 List<Map<String,Object>> h=new ArrayList<Map<String,Object>>();
 		h=object.queryForList(sql,empid);
 		if(!h.isEmpty()) {
       for(Map i:h) {
    	 empname=(String)i.get("username");
    	 joindate=(String)i.get("join_date");
         phn=(String)i.get("phonenumber");
    	 mail=(String)i.get("email-id"); 
       }
       
       String sql2="select * from project_details where project_manager=?";
  	 List<Map<String,Object>> objk=new ArrayList<Map<String,Object>>();
		objk=object.queryForList(sql2,usr);
		if(!objk.isEmpty()) {
		for(Map j:objk) {
			prjid=(String)j.get("id");
			 prname=(String)j.get("project_name");
		}
		try {
    	String ins="insert into prj_emp_details values(?,?,?,?,?,?,?,?,?) ";
    	object.update(ins,prjid,prname,empid,empname,phn,mail,joindate,date,usr);
    	String upd="update emplogin2 set status='a' where id=?";
		object.update(upd,empid);
    	result="inserted";
		}catch(Exception e) {
			result="duplicate entry:YOU ALREADY USED THIS ID"+e;
		}
		}
		else
			result="no project for project manager";
		}else
 			result="invalid employee ID";
     }else {
    	 result="invalid project manager username or password";
     }
	return result;

	}
}
