package com.sarath.location.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtilImpl implements EmailUtil 
{
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendMail(String toAddress, String subject, String body) 
	{
		MimeMessage createMimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(createMimeMessage);
		try 
		{
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(body);
		} 
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
		javaMailSender.send(createMimeMessage);
	}

}
