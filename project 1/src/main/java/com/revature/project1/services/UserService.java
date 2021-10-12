package com.revature.project1.services;

import com.revature.project1.dao.UserDaoImpl;
import com.revature.project1.main.LogDriver;
import com.revature.project1.models.User;

public class UserService {
	
	private UserDaoImpl uDao;
	
	
	public UserService() {
		
	}

	
	public UserService(UserDaoImpl uDao) {
		super();
		this.uDao = uDao;
	}
	
	
	public User verifyUserCreds( String username, String password) {
		
		try {
			
			User user = uDao.getByName(username);
			
			if (user.getPassword().equals(password.trim())) {
				LogDriver.log.info(username + "has logged in.");
				
				return user;
			} else {
				throw new IllegalArgumentException("That username and password do not match any users.");
			}
		}catch (Exception e) {
			LogDriver.log.error(e);
		}
			
		return null;
	}
	
	
	public User changePassword(String username, String fullName, String password) {
		
		User user = uDao.getByName(username);
		String[] name = fullName.split(" ");
		if (name[0].trim().equals(user.getFirstName())) {
			if(name[1].trim().equals(user.getLastName())) {
				user.setPassword(password);
				uDao.update(user);
				return user;
			}
		}else {
		// logic to alert that the name and user name do not match and go back to login
		}
		return null;
	}
	
	
	public User registerUser() {
		//ToDo: Implement method
		return null;
	}

	public User getEmployeeById(int id) {
		
	return null;
}
	
	
	
}
	
