package com.revature.project1.servlets;

import javax.servlet.http.HttpServletRequest;

import com.revature.project1.controllers.ReimbursementController;

public class APIDispatcher {

public static String process(HttpServletRequest req) {
		
		switch(req.getRequestURI()) {
		
		case "/project1-ers/getAllItems.api":
			System.out.println("in getAll.api dispatcher");
			return ReimbursementController.getAllItems();
			
		
			
		 default:
			 	System.out.println("in  default");
			 	return "html/unsuccessfullogin.html";
		
		}
		
	}
	
}
