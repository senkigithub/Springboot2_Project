package com.example.demo.meeting_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("meetings")
public class meeting_details_controller {
@Autowired
meeting_details_service object;
@PostMapping("meeting_details")
public String meetingnsert(@RequestBody meeting_details_pojo ins) {
	String i=object.meeting(ins);
	return i;
}
}
