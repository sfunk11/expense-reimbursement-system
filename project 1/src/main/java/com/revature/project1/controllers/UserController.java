package com.revature.project1.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.project1.dao.ExpenseDBConnection;
import com.revature.project1.dao.UserDaoImpl;
import com.revature.project1.models.User;
import com.revature.project1.services.UserService;

public class UserController {
	
	static ExpenseDBConnection dbCon = new ExpenseDBConnection();
	static UserDaoImpl uDao = new UserDaoImpl(dbCon);
	
	static UserService uServ = new UserService(uDao);
	
	public static String login(HttpServletRequest req) {
		
		
		User user = uServ.verifyUserCreds(req.getParameter("username"), req.getParameter("password"));
	
		if(!req.getMethod().equals("POST")) {
			return "html/unsuccessfullogin.html";
		}
		
		
		if (user == null) {
			return "wrongcreds.view";
		}else{
			req.getSession().setAttribute("currentUser", user);
			
			if(user.getRoleId() == 1) {
				return "html/home.html";
			} else {
			
			return "html/manager.html";
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
		
		
	}
	

