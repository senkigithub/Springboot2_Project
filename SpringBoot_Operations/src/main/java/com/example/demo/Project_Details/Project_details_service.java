package com.example.demo.Project_Details;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class Project_details_service {
	@Autowired
	JdbcTemplate obj;

	public String prjinsert(Projetc_Details insert) {
		String result="";
		String usr=insert.getUsername();
		String pwd=insert.getPassword();
		String sql1="select * from emplogin2 where username=? and password=? and emproleid='a' ";
		List<Map<String,Object>> hm=new ArrayList<Map<String,Object>>();
		hm=obj.queryForList(sql1,usr,pwd);
		if(!hm.isEmpty()) {
			String nam=insert.getProject_name();
			String descrption=insert.getDescrption();
			String sdate=insert.getStart_date();
			String edate=insert.getEnd_date();
			String nemp=insert.getNo_of_emp();
			String prjmanager=insert.getProject_manager();
			String hr=insert.getHr();
			String id=nemp+nam;
			String assign=usr;
			String sql2="select *from emplogin2 where username=? and emproleid='p'";
			List<Map<String,Object>> objusr=new ArrayList<Map<String,Object>>();
			objusr=obj.queryForList(sql2,prjmanager);
			String sql3="select *from emplogin2 where username=? and emproleid='h'";
			List<Map<String,Object>> objhr=new ArrayList<Map<String,Object>>();
			objhr=obj.queryForList(sql3,hr);
			if(!objhr.isEmpty()) {
			if(!objusr.isEmpty()) {
			for(Map ur:objusr) {	
						if(ur.get("emproleid").equals("p")) {
					for(Map h:objhr) {
						if(h.get("emproleid").equals("h")) {
							try {
							
							if(sdate.length()==10) {
								if(sdate.charAt(2)=='-'&&sdate.charAt(5)=='-'||sdate.charAt(2)=='/'&&sdate.charAt(5)=='/') {
								if(edate.length()==10) {
									if(edate.charAt(2)=='-'&&edate.charAt(5)=='-'||edate.charAt(2)=='/'&&edate.charAt(5)=='/') {
							String s="insert into project_details values(?,?,?,?,?,?,?,?,?)";
							obj.update(s,id,nam,descrption,sdate,edate,nemp,prjmanager,hr,assign);
							String updt="update emplogin2 set status='a' where username=?";
							obj.update(updt,prjmanager);
							String upd="update emplogin2 set status='a' where username=?";
							obj.update(upd,hr);
							result="inserted";
									}else {
										result="end date third character and six shoud be should be '-' or '/' and both should be same ";
									}
							}
								else {
								result="end date length should be 10";
							}
								}else {
									result=" start date third character and six shoud be should be '-' or '/' and both should be same ";
								}
							}else {
								result="start date length shoud be 10";
							}
							}catch(Exception e) {
								result="duplicate entry"+e;
							}
						}
					}
				}
			}
			}else {
				result="enter project manager proper username";
			}
			}
			else {
				result="enter hr manager proper username";
			}		
			
		}else {
			result="admin username or password invalid";
		}
		
		return result;
	}
}
