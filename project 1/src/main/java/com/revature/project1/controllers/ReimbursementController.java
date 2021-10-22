package com.revature.project1.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	
	public static void getAllItems(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		System.out.println("in getALL items");
		HttpSession session = req.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		
		if (currentUser == null) {
			return;
		}
		List<ReimbursementItem> itemList = new ArrayList<>();
		
		if (Integer.parseInt(req.getParameter("employeeId")) == 0) {
		
		itemList = rServ.getAllReimb();
		System.out.println(itemList);
		String response = OBJECT_MAPPER.writeValueAsString(itemList);
		System.out.println(response);
		res.setContentType("application/json");
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		res.setHeader("Expires", "0"); // Proxies.
		
		res.getWriter().write(response); 
		
		}else {
			getAllForUser(req, res);
		}
	
	}
	
	
	public static void getAllForUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException  {
		System.out.println("in get all for user");
		User user = new User();
		HttpSession session = req.getSession();
		List<ReimbursementItem> itemList = new ArrayList<>();
		
		
		User currentUser = (User)session.getAttribute("currentUser");
		if (currentUser != null) {
			if (req.getParameter("employeeId") !=null) {
				user = uServ.getEmployeeById(Integer.parseInt(req.getParameter("employeeId")));
			}else {
				user = currentUser;
			}	
			
			itemList = rServ.getReimbursementsByUser(user.getUsername());
		}
		res.setContentType("application/json");
		res.getWriter().write(OBJECT_MAPPER.writeValueAsString(itemList));
		
		
	}
	
	public static void submitNewItem(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException {
		Part filePart = req.getPart("receipt");
		InputStream filecontent = filePart.getInputStream();
		File file = new File("receipt");
		FileOutputStream outFile = new FileOutputStream(file);
		byte[] bytes = new byte[16384];
		
		int read = 0;
		while((read = filecontent.read(bytes)) != -1) {
			outFile.write(bytes, 0 , read);
		}
		
		
		User user = (User)req.getSession().getAttribute("currentUser");
		if (user == null) {
			return;
		}
		ReimbursementItem newItem = new ReimbursementItem(Double.parseDouble(req.getParameter("amount")), req.getParameter("description"), user.getUserId(), Integer.parseInt(req.getParameter("reimbType")) );
		newItem.setReceipt(bytes);
		List<ReimbursementItem> itemList = rServ.submitItem(newItem, user);
		
	}
	
	public static void changeItemStatus(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException {
		
		
		HttpSession session = req.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		System.out.println(currentUser);
		ReimbursementItem item = rServ.getReimbById(Integer.parseInt(req.getParameter("reimbId")));
		System.out.println(item);
		item.setResolverId(currentUser.getUserId());
		Boolean isApproved = Boolean.parseBoolean(req.getParameter("isApproved"));
		System.out.println(isApproved);
		rServ.changeStatus(item, isApproved);
	}
	
	
}
