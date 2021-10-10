package com.revature.project1.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.project1.dao.ExpenseDBConnection;
import com.revature.project1.dao.UserDaoImpl;
import com.revature.project1.models.User;
import com.revature.project1.services.UserService;

public class UserController {
	
	static ExpenseDBConnection dbCon = new ExpenseDBConnection();
	static UserDaoImpl uDao = new UserDaoImpl(dbCon);
	
	static UserService uServ = new UserService(uDao);
	
	public static String login(HttpServletRequest req) {
		
		System.out.println("in user controller");
		User user = uServ.logIn(req.getParameter("username"), req.getParameter("password"));
		System.out.println(user);
		if(!req.getMethod().equals("POST")) {
			return "html/unsuccessfullogin.html";
		}
		
		
		if (user == null) {
			return "wrongcreds.view";
		}else {
			req.getSession().setAttribute("currentUser", user);
			return "html/home.html";
		}
		
	}
	
}
