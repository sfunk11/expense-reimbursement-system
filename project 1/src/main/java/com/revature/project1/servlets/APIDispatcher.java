package com.revature.project1.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.controllers.ReimbursementController;
import com.revature.project1.controllers.UserController;
import com.revature.project1.models.ReimbursementItem;
import com.revature.project1.models.User;

public class APIDispatcher {

public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException {
		
		switch(req.getRequestURI()) {
		
		case "/project1-ers/getAllItems.api":
			System.out.println("in getAll.api dispatcher");
			User currentUser = (User)req.getSession().getAttribute("currentUser");
			if (currentUser == null) {
				res.getWriter().write(new ObjectMapper().writeValueAsString(new ArrayList<ReimbursementItem>()));
			}
			else {
			ReimbursementController.getAllForUser(req, res);
			}
			break;
		
		case "/project1-ers/getManagerItems.api":
			System.out.println("in get manager items dispatcher");
			ReimbursementController.getAllItems(req, res);
			break;
			
		case "/project1-ers/newItem.api":
			System.out.println("in newItem.api dispatcher");
			ReimbursementController.submitNewItem(req, res);
			break;
			
		case "/project1-ers/getEmployees.api":
			System.out.println("in get employees dispatcher");
			UserController.getEmployeeList(req, res);
			break;
			
		case "/project1-ers/newEmployee.api":
			System.out.println("in register employee dispatcher");
			UserController.insertEmployee(req, res);
			break;
			
		case "/project1-ers/changeItem.api":
			System.out.println("in change item dispatcher");
			ReimbursementController.changeItemStatus(req, res);
			break;
			
			
		 default:
			 	System.out.println("in  default api");
			 	res.getWriter().write(new ObjectMapper().writeValueAsString(new ArrayList<ReimbursementItem>()));
		
		}
		
	}
	
}
