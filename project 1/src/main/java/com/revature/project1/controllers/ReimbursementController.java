package com.revature.project1.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	
	public static void getAllItems(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		List<ReimbursementItem> itemList = new ArrayList<>();
		
		itemList = rServ.getAllReimb();
		res.getWriter().write(new ObjectMapper().writeValueAsString(itemList));
		
	}
	
	
	public static void getAllForUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException  {
		
		User user = new User();
		HttpSession session = req.getSession();
		List<ReimbursementItem> itemList = new ArrayList<>();
		
		
		User currentUser = (User)session.getAttribute("currentUser");
		if (currentUser != null) {
			if (req.getParameter("employeeId")!=null) {
				user = uServ.getEmployeeById(Integer.parseInt(req.getParameter("employeeId")));
			}else {
				user = currentUser;
			}	
			
			itemList = rServ.getReimbursementsByUser(user.getUsername());
		}
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(itemList));
		
		
	}
	
	public static void submitNewItem(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException {
		User user = (User)req.getSession().getAttribute("currentUser");
		System.out.println(req.getParameter("amount"));
		ReimbursementItem newItem = new ReimbursementItem(Double.parseDouble(req.getParameter("amount")), req.getParameter("description"), user.getUserId(), Integer.parseInt(req.getParameter("reimbType")) );
		List<ReimbursementItem> itemList = rServ.submitItem(newItem, user);
		
	}
	
	
}
