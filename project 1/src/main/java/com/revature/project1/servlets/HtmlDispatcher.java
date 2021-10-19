package com.revature.project1.servlets;

import javax.servlet.http.HttpServletRequest;

import com.revature.project1.controllers.UserController;

public class HtmlDispatcher {

public static String process(HttpServletRequest req) {
		
		switch(req.getRequestURI()) {
		
		case "/project1-ers/login.view":
			System.out.println("in login.view dispatcher");
			return UserController.login(req);
			
		case "/project1-ers/changeCreds.view":
			System.out.println("in change password dispatcher");
			return UserController.changePassword(req);
			
		case "/project1-ers/logout.view":
			System.out.println("in logout dispatcher");
			return UserController.logOut(req);
			
		case "/project1-ers/manager.view":
			System.out.println("in manager dispatcher");
			return "html/manager.html";
			
		 default:
			 	System.out.println("in  default html");
			 	return "html/unsuccessfullogin.html";
		
		}
		
	}
	
}
