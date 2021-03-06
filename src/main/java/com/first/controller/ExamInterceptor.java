package com.first.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExamInterceptor extends HandlerInterceptorAdapter { 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
	
		System.out.println("傈贸府 : " + request.getContextPath());
		
		outpost();
		
		return true;
		
	}
	
	
	public void outpost() {
		
		System.out.println("post!");
		
	}
	
	@Override
	public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	
		System.out.println("饶贸府");
		super.postHandle(request, response, handler, modelAndView);
		
		
	}
	

}
