package com.revature.project1.dao;

import java.util.List;

import com.revature.project1.models.ReimbursementItem;

public interface ReimbursementDao extends GenericDao<ReimbursementItem> {
	
	List<ReimbursementItem> getbyStatusId(int status);
	
	List<ReimbursementItem> getByUsername(String username);
	
	
}
