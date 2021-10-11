package com.revature.project1.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.project1.controllers.ReimbursementController;

public class APIDispatcher {

public static String process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		switch(req.getRequestURI()) {
		
		case "/project1-ers/getAllItems.api":
			System.out.println("in getAll.api dispatcher");
			ReimbursementController.getAllForUser(req, res);
			
		
			
		 default:
			 	System.out.println("in  default");
			 	return null;
		
		}
		
	}
	
}
