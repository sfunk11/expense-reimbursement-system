package com.revature.project1.models;

import java.io.InputStream;
import java.sql.Timestamp;

public class ReimbursementItem {

	
	private int reimbId;
	private double amount;
	private Timestamp submittedAt;
	private Timestamp resolvedAt;
	private String description;
	private int authorId;
	private int resolverId;
	private int reimbStatus;
	private int reimbTypeId;
	private String status;
	private String reimbCategory;
	private String authorUsername;
	private byte[] receipt;
	
	public ReimbursementItem() {
		
	}



	public ReimbursementItem(int reimbId, double amount, Timestamp submittedAt, Timestamp resolvedAt,
			String description, int authorId, int resolverId, int reimbStatus, int reimbTypeId, String status,
			String reimbCategory, String authorUsername) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submittedAt = submittedAt;
		this.resolvedAt = resolvedAt;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.reimbStatus = reimbStatus;
		this.reimbTypeId = reimbTypeId;
		this.status = status;
		this.reimbCategory = reimbCategory;
		this.authorUsername = authorUsername;
	}



	public ReimbursementItem(int reimbId, double amount, Timestamp submittedAt, Timestamp resolvedAt,
			String description, int authorId, int resolverId, int reimbStatus, int reimbTypeId, String status,
			String reimbCategory) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submittedAt = submittedAt;
		this.resolvedAt = resolvedAt;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.reimbStatus = reimbStatus;
		this.reimbTypeId = reimbTypeId;
		this.status = status;
		this.reimbCategory = reimbCategory;
	}



	public ReimbursementItem(int reimbId, double amount, Timestamp submittedAt, Timestamp resolvedAt, String description,
			int authorId, int resolverId, int reimbStatus, int reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submittedAt = submittedAt;
		this.resolvedAt = resolvedAt;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.reimbStatus = reimbStatus;
		this.reimbTypeId = reimbTypeId;
	}

	public ReimbursementItem(double amount, Timestamp resolvedAt, String description, int authorId, int resolverId,
			int reimbStatus, int reimbTypeId) {
		super();
		this.amount = amount;
		this.resolvedAt = resolvedAt;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.reimbStatus = reimbStatus;
		this.reimbTypeId = reimbTypeId;
	}
	
	

	public ReimbursementItem(double amount, String description, int authorId, int reimbTypeId) {
		super();
		this.amount = amount;
		this.description = description;
		this.authorId = authorId;
		this.reimbTypeId = reimbTypeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getResolvedAt() {
		return resolvedAt;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(int reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReimbCategory() {
		return reimbCategory;
	}
	
	public void setReimbCategory(String reimbCategory) {
		this.reimbCategory = reimbCategory;
	}

	public int getReimbId() {
		return reimbId;
	}
	public Timestamp getSubmittedAt() {
		return submittedAt;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}
	


	public byte[] getReceipt() {
		return receipt;
	}



	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}



	@Override
	public String toString() {
		return "ReimbursementItem [reimbId=" + reimbId + ", amount=" + amount + ", submittedAt=" + submittedAt
				+ ", resolvedAt=" + resolvedAt + ", description=" + description + ", authorId=" + authorId
				+ ", resolverId=" + resolverId + ", reimbStatus=" + reimbStatus + ", reimbTypeId=" + reimbTypeId
				+ ", status=" + status + ", reimbCategory=" + reimbCategory + ", authorUsername=" + authorUsername
				+ "]";
	}
	
	
	
	
}
