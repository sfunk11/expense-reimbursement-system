package com.revature.project1.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.dao.ExpenseDBConnection;
import com.revature.project1.dao.ReimbursementDaoImpl;
import com.revature.project1.dao.UserDaoImpl;
import com.revature.project1.models.ReimbursementItem;
import com.revature.project1.models.User;
import com.revature.project1.services.ReimbursementService;
import com.revature.project1.services.UserService;

public class ReimbursementController {
	
	static ExpenseDBConnection dbCon = new ExpenseDBConnection();
	static ReimbursementDaoImpl rdao = new ReimbursementDaoImpl(dbCon);
	static UserDaoImpl udao = new UserDaoImpl(dbCon);
	static ReimbursementService rServ = new ReimbursementService(rdao);
	static UserService uServ = new UserService(udao);
	
	
	public static void getAllForUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException  {
		
		User user = uServ.getEmployeeById(Integer.parseInt(req.getParameter("employeeId")));
		
		
		List<ReimbursementItem> itemList = rServ.getReimbursementsByUser(user.getUsername());
	
		req.getSession().setAttribute("currentEmployee", user);	
		res.getWriter().write(new ObjectMapper().writeValueAsString(itemList));
		
		
	
	}
	
	
}
