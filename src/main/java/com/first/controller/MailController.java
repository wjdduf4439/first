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
	    String tomail  = request.getParameter("tomail");     // �޴� ��� �̸���
	    String title   = request.getParameter("title");      // ����
	    String content = request.getParameter("content");    // ����
	   
	    try {
	      MimeMessage message = mailSender.createMimeMessage();
	      //Message Ŭ������ ��ӹ��� �޼��� ������ �������ִ� ��ü
	      MimeMessageHelper messageHelper  = new MimeMessageHelper(message, true, "UTF-8");
	      //�ڹ�mail api�� Ȱ���� �� �ְ� �ϴ� ��ü 
	 
	      messageHelper.setFrom(setfrom);		// �����»�� �����ϰų� �ϸ� �����۵��� ����
	      messageHelper.setTo(tomail);     		// �޴»�� �̸���
	      messageHelper.setSubject(title); 		// ���������� ������ �����ϴ�
	      messageHelper.setText(content); 		// ���� ����
	      
	      FileSystemResource file = new FileSystemResource(new File("C:\\Users\\wjddu\\Downloads\\icon.PNG"));
	      messageHelper.addAttachment("icon.png", file);
	     
	      mailSender.send(message);
	      
	    } catch(Exception e){ System.out.println(e); }
		
		return "redirect:/mainboard.go";
		
	}
	
	
}
