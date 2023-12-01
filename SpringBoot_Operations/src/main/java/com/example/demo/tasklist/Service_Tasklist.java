package com.example.demo.tasklist;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
//INSERT DATA
@Service
public class Service_Tasklist {
@Autowired
JdbcTemplate object;
public String tasklist(PrjManager_Tasklist_pojo obj) {
	String result="";
	String uname=obj.getUsername();
	String pwd=obj.getPassword();
	String table2sql="select * from emplogin2 where username=? and password=? and emproleid='p' ";
	List<Map<String,Object>> task1= object.queryForList(table2sql,uname,pwd);    
	String cretedby=uname;
	String id=obj.getProject_id();
//	System.out.println(task1+""+uname+""+pwd);
	if(!task1.isEmpty()) {
	String sql="select * from project_details where project_manager=? and id=?";
	List<Map<String,Object>> tasklist= object.queryForList(sql,uname,id);
	//	String id=(String)task.get("id");
		String taskid=obj.getTask_id();
		String taskname=obj.getTaskname();
		String descrption=obj.getDescrption();
		String duration =obj.getDescrption();
		String status="c";
		if(!tasklist.isEmpty()) {
		String inser="insert into Project_Task_List values(?,?,?,?,?,?,?)";
		object.update(inser,id,taskid,taskname,descrption,duration,status,cretedby);
		result="inserted";
		}
		else {
			result="mismatch";
		}
	}else {
		result ="invalid credentials";
	}
		return result;
}
//############################################################################################
//UPDATE DATA
public String updateTask(PrjManager_Tasklist_pojo updtobj) {
	String result="";
	String uname=updtobj.getUsername();
	String pwd=updtobj.getPassword();
	String table2sql="select * from emplogin2 where username=? and password=? and emproleid='p' ";
	List<Map<String,Object>> task1= object.queryForList(table2sql,uname,pwd);    
	String taskid=updtobj.getTask_id();
	if(!task1.isEmpty()) {
		String sqlupdate="select * from Project_Task_List where Task_Id=? and Created_By=?";
		List<Map<String,Object>> updatetask=object.queryForList(sqlupdate,taskid,uname);
		if(!updatetask.isEmpty()) {
		String derscrption=updtobj.getDescrption();
		String duration=updtobj.getDuration();
		String updt="update Project_Task_List set Description=? , Duration=? where Task_Id=? ";
		object.update(updt,derscrption,duration,taskid);
		result="update";
		}else {
			result="task id is mismatch";
		}
		
	}else {
		result="invalid credentials";
	}

	return result;
}
  public String deleteTask(PrjManager_Tasklist_pojo del) {
	  String result="";
	  String uname=del.getUsername();
		String pwd=del.getPassword();
		String table2sql="select * from emplogin2 where username=? and password=? and emproleid='p' ";
		List<Map<String,Object>> deltask= object.queryForList(table2sql,uname,pwd);    
		String taskid=del.getTask_id();
		if(!deltask.isEmpty()) {
			String sqlupdate="select * from Project_Task_List where Task_Id=?";
			List<Map<String,Object>> deletetask=object.queryForList(sqlupdate,taskid);
			if(!deletetask.isEmpty()) {
				String delete="delete from Project_Task_List where Task_Id=?";
				object.update(delete,taskid);
				result="delete";
			}else {
				result="mismatch task id";
			}
		}else {
			result="invalid credentials";
		}
	  return result;
  }
  //######################################################################################
  public List selectTask(PrjManager_Tasklist_pojo selobj) {
	  String result="";
	  String uname=selobj.getUsername();
		String pwd=selobj.getPassword();
		
		String table2sql="select * from emplogin2 where username=? and password=? and emproleid='p' ";
		List<Map<String,Object>> seltask= object.queryForList(table2sql,uname,pwd);  
					String sts=selobj.getStatus();
					String duaration=selobj.getDuration();
					String prj_id=selobj.getProject_id();
			String sql="select * from Project_Task_List where 1+1=2 ";
	       if(!sts.equals(null)) {
			sql+="and status='"+sts+"' ";
			
	       }
	       if(!duaration.equals(null)) {
	    	   sql+="and Duration='"+duaration+"' ";
	       }
	       if(!prj_id.equals(null)) {
	    	   sql+="and Project_Id='"+prj_id+"' ";
	       }
	      
	       seltask=object.queryForList(sql);
//	       seltask=object.queryForList(sql,duaration);
//	       seltask=object.queryForList(sql,prj_id);
	        
		return seltask;

	 
  }
  
}
