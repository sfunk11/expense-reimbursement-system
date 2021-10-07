package com.revature.project1.dao;

import java.util.List;

import com.revature.project1.models.User;

public interface UserDao extends GenericDao<User> {

	List<User> getByRole(int roleId);
	User getByName(String username);
}
