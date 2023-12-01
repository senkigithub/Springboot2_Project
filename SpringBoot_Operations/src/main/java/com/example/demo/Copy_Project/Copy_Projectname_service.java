package com.example.demo.Copy_Project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class Copy_Projectname_service {
	@Autowired
	JdbcTemplate obj;
	public String projectName(Copy_Data obj1) {
		String result="";
		String usr=obj1.getUsername();
		String pwd=obj1.getPassword();
		String sql1="select * from emplogin2 where username=?  and emproleid='a' ";
		List<Map<String,Object>> table=new ArrayList<Map<String,Object>>();
		table=obj.queryForList(sql1,usr);
		String sql5="select * from emplogin2 where  password=? and emproleid='a' ";
		List<Map<String,Object>> hm1=new ArrayList<Map<String,Object>>();
		hm1=obj.queryForList(sql5,pwd);
		String projectname=obj1.getProject_name();
		String newproject=obj1.getNew_project();
		if(!table.isEmpty()) {
			if(!hm1.isEmpty()) {
		String sql="select * from project_details where project_name=?";
		List<Map<String,Object>> copy=new ArrayList<Map<String,Object>>();
		copy=obj.queryForList(sql,projectname);
		DateTimeFormatter a=DateTimeFormatter.ofPattern("dd/MM/YYYY");
		LocalDateTime b=LocalDateTime.now();
		String date=a.format(b);
	     if(!copy.isEmpty()) {
			for(Map ins:copy) {
				try {
				String descrption=(String)ins.get("description");
				String edate=(String)ins.get("end_date");
				String stdate=(String)ins.get("start_date");
	            String nemp=(String)ins.get("No_of_emp");
	            String prjmanager=(String)ins.get("project_manager");
				String hr=(String)ins.get("hr");
				String id=nemp+newproject;
				String asn=usr;
				String sql2="insert into project_details values(?,?,?,?,?,?,?,?,?)";
				obj.update(sql2,id,newproject,descrption,stdate,edate,nemp,prjmanager,hr,asn);
				String sql3="select * from prj_emp_details where name=?";
				List<Map<String,Object>> add=new ArrayList<Map<String,Object>>();
				add=obj.queryForList(sql3,projectname);
				for(Map upt:add) {
					String sql4="insert into prj_emp_details values(?,?,?,?,?,?,?,?,?)";
	 obj.update(sql4,id,newproject,upt.get("emp_id"),upt.get("emp_usrname"),upt.get("phonenumber"),upt.get("email"),upt.get("join_date"),date,upt.get("assigned_by"));
				}
				
				result="inserted";
				}
				catch(Exception e) {
					result="YOU ARE ALREDAY USED "+e;
				   }
			}
	      }
	     else {
	    	 result="invalid old project name";
	         }
			}
			else {
				result="invalid admin password";
			 }
		}
		else {
			result="invalid admin username ";
		   }
		return result;
	}
	

}
