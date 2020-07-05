package com.sa4105.javaca2.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/email")
public class SimpleEmailController {

	@Autowired
	public JavaMailSender javaMailSender;
	
	
	@GetMapping(value="/sendEmail")
	public String sendEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("darellchua2@gmail.com");
		message.setSubject("Leave Applied");
		message.setText("You have applied your leave");
		
		javaMailSender.send(message);
		return "Successfully sent email";
	}
}
