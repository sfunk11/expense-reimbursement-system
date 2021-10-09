package com.revature.project1.services;

import com.revature.project1.dao.ReimbursementDaoImpl;

public class ReimbursementService {

	private ReimbursementDaoImpl rdao;
	
	public ReimbursementService() {
		
	}

	public ReimbursementService(ReimbursementDaoImpl rdao) {
		super();
		this.rdao = rdao;
	}
	
	
	
	
}
