package com.revature.project1.servlets;

import javax.servlet.http.HttpServletRequest;

import com.revature.project1.controllers.UserController;

public class HtmlDispatcher {

public static String process(HttpServletRequest req) {
		
		switch(req.getRequestURI()) {
		
		case "/project1-ers/login.view":
			System.out.println("in login.view dispatcher");
			return UserController.login(req);
			
		 default:
			 	System.out.println("in  default");
			 	return "html/unsuccessfullogin.html";
		
		}
		
	}
	
}
