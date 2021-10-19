package com.revature.project1.services;

import java.util.List;

import com.revature.project1.dao.UserDaoImpl;
import com.revature.project1.main.LogDriver;
import com.revature.project1.models.User;
import com.revature.project1.util.PasswordUtil;
import com.revature.project1.util.SendEmail;

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
			
			if (user.getPassword().equals(new PasswordUtil().encryptPassword(password))) {
				LogDriver.log.info(username + " has logged in.");
				
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
				String newPass = new PasswordUtil().encryptPassword(password);
				System.out.println(newPass);
				user.setPassword(newPass);
				uDao.update(user);
				LogDriver.log.info("Username: "+ user.getUsername() + "has changed their password.");
				return user;
			}
		}
		return null;
	}
	
	
	public void registerUser(User user) {
		uDao.insert(user);
		SendEmail.main(user);
		LogDriver.log.info("Username: "+ user.getUsername() + "was added to the database.");
		
	}

	public User getEmployeeById(int id) {
		User user = uDao.getById(id);
	return user;
}
	
	public List<User> getAllEmployees() {
		
		List<User> employeeList = uDao.getAll();
		
		return employeeList;
	
	}
	
	
}
	
