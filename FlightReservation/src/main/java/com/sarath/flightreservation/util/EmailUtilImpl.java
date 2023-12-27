package com.sarath.flightreservation.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtilImpl implements EmailUtil 
{
	// Getting these values from the application properties
	@Value("${com.sarath.flightreservation.subject}")
	private String MAIL_SUBJECT;
	
	@Value("${com.sarath.flightreservation.body}")
	private String MAIL_BODY;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendMail(String toAddress, String filePath) 
	{
		MimeMessage createMimeMessage = javaMailSender.createMimeMessage();
		
		try 
		{
			MimeMessageHelper helper = new MimeMessageHelper(createMimeMessage,true);
			helper.setTo(toAddress);
			helper.setSubject(MAIL_SUBJECT);
			helper.setText(MAIL_BODY);
			helper.addAttachment("Itenary", new File(filePath));
		} 
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
		javaMailSender.send(createMimeMessage);
	}

}
