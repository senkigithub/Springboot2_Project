package com.example.demo.meeting_details;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
@Service
public class meeting_details_service {
@Autowired
JdbcTemplate object;
@Autowired
JavaMailSender javaMailSender;
@Transactional
public String meeting(meeting_details_pojo obj) {
	
	int count=0;
	String result="";
	String usr=obj.getUsername();
	String pwd=obj.getPassword();
	LocalTime myObj = LocalTime.now();
	DateTimeFormatter a=DateTimeFormatter.ofPattern("dd/MM/YYYY");
	LocalDateTime b=LocalDateTime.now();
	String date=a.format(b);
	DateTimeFormatter lc=DateTimeFormatter.ofPattern("HH:mm:ss");
	String meeting_name=obj.getMeeting_name();
	String contact_details=obj.getContact_details();
	String project_id="";
	LocalTime lt=LocalTime.now();
    int duration=obj.getDuration();
	String  start_time=lc.format(lt);
	LocalTime end1=LocalTime.parse(start_time, lc);
	LocalTime end =end1.plusMinutes(duration);
    String end_time=end.format(lc);
	String meeting_id=start_time+end_time;
    String created_by=usr;
	String status="yes";
	String descrption=obj.getDescrption();
	String meetingsql="select * from emplogin2 where username=? and password=? and (emproleid='p' or emproleid='h' or emproleid='e')";
	List<Map<String,Object>> hm=new ArrayList<Map<String,Object>>();
	hm=object.queryForList(meetingsql,usr,pwd);
	project_id=obj.getProject_id();
if(!hm.isEmpty()) {
	String[] arry=contact_details.split(",");
	for(String itr:arry) {
		String sql2="select * from prj_emp_details where emp_id=? and id=?";
		List<Map<String,Object>> obj2=object.queryForList(sql2,itr,project_id);
		if(!obj2.isEmpty()) {
				String ins="insert into meeting_details values(?,?,?,?,?,?,?,?,?,?,?)";
				object.update(ins,meeting_id,meeting_name,itr,project_id,start_time,end_time,duration,date,created_by,status,descrption);
				count++;
							
				if(count==arry.length) {
					mail(itr);
					result="MEETING SUCCESSFULLY SCHEDULED";
				}
			}else {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			result="contact details mis match";
			
		}
}
}else {
	result="invalid credentials";
}
	
	return result;
}
public String mail(String itr) {
	//   Creating a simple mail message
	String email="";
	String sql="select * from emplogin2 where id=?";
	List<Map<String,Object>> data=object.queryForList(sql,itr);
	for(Map getting:data) {
		email=""+getting.get("email-id");
	}
    SimpleMailMessage mailMessage
        = new SimpleMailMessage();

    // Setting up necessary details
    mailMessage.setFrom("ramusenki2001@gmail.com");
    mailMessage.setTo(email);
    mailMessage.setCc("jahnavi.potnuru@gmail.com","sadhanavabbilisetty2001@gmail.com");
    mailMessage.setText("A man’s favorite donkey falls into a deep precipice. He can’t pull it out no matter how hard he tries. He therefore decides to bury it alive.\n"
    		+ "\n"
    		+ "Soil is poured onto the donkey from above. The donkey feels the load, shakes it off, and steps on it. More soil is poured.\n"
    		+ "\n"
    		+ "It shakes it off and steps up. The more the load was poured, the higher it rose. By noon, the donkey was grazing in green pastures.\n"
    		+ "\n"
    		+ "After much shaking off (of problems) And stepping up (learning from them), One will graze in GREEN PASTURES.");
    mailMessage.setSubject("Shake off Your Problems");
    

    // Sending the mail
    javaMailSender.send(mailMessage);
    return "Mail Sent Successfully...";
}
}
