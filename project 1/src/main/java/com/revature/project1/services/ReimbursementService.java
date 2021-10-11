package com.revature.project1.services;

import java.util.List;

import com.revature.project1.dao.ReimbursementDaoImpl;
import com.revature.project1.models.ReimbursementItem;
import com.revature.project1.models.User;

public class ReimbursementService {

	private ReimbursementDaoImpl rdao;
	
	
	public ReimbursementService() {
		
	}

	
	public ReimbursementService(ReimbursementDaoImpl rdao) {
		super();
		this.rdao = rdao;
	}
	
	
	public List<ReimbursementItem> getReimbursementsByUser(String username){
		
		List<ReimbursementItem> reimbList = rdao.getByUsername(username);
		
		
		return reimbList;
	}
	
	
	public ReimbursementItem resolveItem(ReimbursementItem item, boolean isApproved) {
		
		if (isApproved) {
			rdao.approveItem(item);
		} else {
			rdao.rejectItem(item);
		}
		
		ReimbursementItem newItem = rdao.getById(item.getReimbId());
		return newItem;
	}
	
	public List<ReimbursementItem> submitItem(ReimbursementItem item, User user){
		
		rdao.insert(item);
		List<ReimbursementItem> newList = rdao.getByUsername(user.getUsername());
		
		return newList;
	}
	
}
