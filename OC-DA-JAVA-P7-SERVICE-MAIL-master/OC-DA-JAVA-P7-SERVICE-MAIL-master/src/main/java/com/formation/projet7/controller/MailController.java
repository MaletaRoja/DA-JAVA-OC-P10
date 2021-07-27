package com.formation.projet7.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.Constants.Constants;

@Service
public class MailController {

	@Autowired
	public JavaMailSender emailSender;

	
	public String sendSimpleEmail(String email, String sujet, String texte) {

		// Create a Simple MailMessage.
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(email);
		message.setSubject(sujet);
		message.setText(texte);

		// Send Message!
		this.emailSender.send(message);

		return "Email envoyé!";

	}
}