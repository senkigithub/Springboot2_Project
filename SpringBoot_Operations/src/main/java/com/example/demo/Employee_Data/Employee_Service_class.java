package com.example.demo.Employee_Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Employee_Service_class {
	@Autowired
	JdbcTemplate ob;
	public String employeeinsert(Employee_Pojo_class obj) {
	  String role2="";
	 	String result="";
		String usr=obj.getUsername();
		String pass=obj.getPassword();
		String sql1="select * from employee_login where username=? and password=?";
		List<Map<String,Object>> hm=new ArrayList();
		hm=ob.queryForList(sql1,usr,pass);
		if(hm.size()!=0) {
		for(Map rl:hm) {
			String rol=(String) rl.get("emproleid");
			if(rol.contentEquals("h")||rol.contentEquals("a")) {
			String id=obj.getId();
			String nam=obj.getName();
			String rolemp=obj.getEmprole();
			DateTimeFormatter object=DateTimeFormatter.ofPattern("HHmm");
			LocalDateTime ldt=LocalDateTime.now();
			String str=object.format(ldt);
			String usr1=Character.toUpperCase(nam.charAt(0))+id+rolemp;
			 String pass1=id+rolemp+str;
			 String s="select * from emplogin";
			 List<Map<String,Object>> mp=ob.queryForList(s);
			 		 for(Map b:mp) {
			 			if(b.get("role").equals(rolemp)) 
			 				role2=""+b.get("role");
			 		 }
			 			if(role2!="") {
			 				try {
			 					String sql="insert into employee_login values(?,?,?,?,?)";
						 int find =ob.update(sql,id,nam,role2,usr1,pass1);	 
						 if (find>0)
							 result="inserted";
						         }
			 			catch(Exception e) {
			 				result="Error id already present "+e;
			 			                   }
			                           }
			 			else
			 				result="invalid emp_name";
			 		 }
			          else
				     result="only admin or hr can insert";
		}
		              }else
			          result="invalid credentials(username or password)";
		return result;
		
	}

}
