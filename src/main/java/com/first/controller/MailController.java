package com.first.controller;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailController {

	@Autowired
	  private JavaMailSender mailSender;
	
	@RequestMapping(value = "/mailboard.go")
	public String mailboard(ModelMap map, HttpServletRequest request) throws Exception {
		
		return "mailview";
		
	}
	
	@RequestMapping(value = "/mailsendboard.go")
	public String mailsendboard(ModelMap map, HttpServletRequest request) throws Exception {
		
		String setfrom = "wjdduf4439@eplatform.co.kr";         
	    String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
	    String title   = request.getParameter("title");      // 제목
	    String content = request.getParameter("content");    // 내용
	   
	    try {
	      MimeMessage message = mailSender.createMimeMessage();
	      //Message 클래스를 상속받은 메세지 형식을 지원해주는 객체
	      MimeMessageHelper messageHelper  = new MimeMessageHelper(message, true, "UTF-8");
	      //자바mail api을 활용할 수 있게 하는 객체 
	 
	      messageHelper.setFrom(setfrom);		// 보내는사람 생략하거나 하면 정상작동을 안함
	      messageHelper.setTo(tomail);     		// 받는사람 이메일
	      messageHelper.setSubject(title); 		// 메일제목은 생략이 가능하다
	      messageHelper.setText(content); 		// 메일 내용
	      
	      FileSystemResource file = new FileSystemResource(new File("C:\\Users\\wjddu\\Downloads\\icon.PNG"));
	      messageHelper.addAttachment("icon.png", file);
	     
	      mailSender.send(message);
	      
	    } catch(Exception e){ System.out.println(e); }
		
		return "redirect:/mainboard.go";
		
	}
	
	
}
