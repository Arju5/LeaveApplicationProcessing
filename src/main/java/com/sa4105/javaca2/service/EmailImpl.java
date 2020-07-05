package com.sa4105.javaca2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.User;

@Service
public class EmailImpl {
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	
	public void sendApprovedEmail(Leave leave) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(leave.getUser().getEmailAddress());
		
		String messageTitle = "Leave Application Approved [LeaveID]: " + leave.getId();
		String messageContent = "Applicant Name: " + leave.getUser().getFirstName() + " " + leave.getUser().getLastName() + "\n"
				+ "Your Leave has been approved";
 		
		message.setSubject(messageTitle);
		message.setText(messageContent);
		
		javaMailSender.send(message);
	}

}
