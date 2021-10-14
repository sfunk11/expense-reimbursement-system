package com.revature.project1.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.dao.ExpenseDBConnection;
import com.revature.project1.dao.UserDaoImpl;
import com.revature.project1.models.User;
import com.revature.project1.services.UserService;

public class UserController {
	
	static ExpenseDBConnection dbCon = new ExpenseDBConnection();
	static UserDaoImpl uDao = new UserDaoImpl(dbCon);
	
	static UserService uServ = new UserService(uDao);
	
	public static String login(HttpServletRequest req) {
		User user = new User();
		
		if (req.getParameter("username") !=null) {
		user = uServ.verifyUserCreds(req.getParameter("username"), req.getParameter("password"));
		}
		else {
		user = (User)req.getSession().getAttribute("currentUser");
		}
		
		if (user == null) {
			return "wrongcreds.view";
		}else{
			req.getSession().setAttribute("currentUser", user);
			
			if(user.getRoleId() == 1) {
				return "html/home.html";
			} else {
			
			return "manager.view";
		}
		}
	}
	
	public static String changePassword(HttpServletRequest req) {
		
		User user = null;
		if (req.getParameter("newPass").equals(req.getParameter("newPass2"))) {
		
			user = uServ.changePassword(req.getParameter("username"), req.getParameter("fullName"), req.getParameter("newPass"));
		} else {
			//write logic to alert that passwords do not match, try again
		}
		if (req.getMethod().equals("POST")) {
			
			if (user == null) {
				return "wrongcreds.view";
			}else if (user.getRoleId() == 1){
				HttpSession session = req.getSession();
				session.setAttribute("currentUser", user);
				return "html/home.html";
			}else {
				return "html/manager.html";
			}
			
		}
		else {
			return "html/changePassword.html";
		}
	}	
		
	public static String logOut(HttpServletRequest req) {
			User user = null;
			req.getSession().setAttribute("currentUser", user);
			return "html/index.html";
		}
		
	public static void getEmployeeList(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		HttpSession session = req.getSession();
		User manager = (User)session.getAttribute("currentUser");
		List<User> employees = new ArrayList<>();
		
		if (manager.getRoleId() == 2) {
			employees = uServ.getAllEmployees();
		}	
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(employees));	
	}
		
		
}
	

