package com.sp;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component
public class SmtpMailSender {
	
	@Autowired
	private JavaMailSender javaMailSender;	
	
	@Autowired 
	 private TemplateEngine templateEngine;
	
	public void send(String from,String to, String subject, final Context ctx,String emailTemplate) throws MessagingException {
	
		try{
		    
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true); 
		
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		
		//helper.setText(body, true);  
	final String htmlContent = this.templateEngine.process(emailTemplate, ctx);
        helper.setText(htmlContent, true );
		
        
		javaMailSender.send(message);
		}
		catch(Exception ex)
		{
			System.out.print(ex.getMessage());
		}
		
	}
	
	
	 

}
