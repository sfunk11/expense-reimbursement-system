package com.revature.project1.services;

import java.util.List;

import com.revature.project1.dao.ReimbursementDaoImpl;
import com.revature.project1.main.LogDriver;
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
	
	public List<ReimbursementItem> getAllReimb(){
		System.out.println("in rServ getAll");
		
		List<ReimbursementItem> itemList = rdao.getAll();
		
		return itemList;
	}
	
	
	public List<ReimbursementItem> getReimbursementsByUser(String username){
		
		List<ReimbursementItem> reimbList = rdao.getByUsername(username);
		
		
		return reimbList;
	}
	
	
	public List<ReimbursementItem> submitItem(ReimbursementItem item, User user){
		
		rdao.insert(item);
		List<ReimbursementItem> newList = rdao.getByUsername(user.getUsername());
		LogDriver.log.info("New item was added to the database.");
		return newList;
	}
	
	public ReimbursementItem getReimbById(int id) {
		
		ReimbursementItem item = rdao.getById(id);

		return item;
	}
	
	public void changeStatus(ReimbursementItem item, boolean isApproved) {
	
		
		if(isApproved) {
			rdao.approveItem(item);
			LogDriver.log.info("Item "+ item.getReimbId() + " for "+item.getAuthorUsername() + " was approved.");
		}
		else {
			rdao.rejectItem(item);
			LogDriver.log.info("Item "+ item.getReimbId() + " for "+item.getAuthorUsername() + " was denied.");
		}
	}
	
}
