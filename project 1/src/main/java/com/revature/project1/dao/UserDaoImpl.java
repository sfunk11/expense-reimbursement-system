package com.revature.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project1.main.LogDriver;
import com.revature.project1.models.User;

public class UserDaoImpl implements UserDao {
	
	private ExpenseDBConnection dbCon;
	
	public UserDaoImpl() {
		
	}
	

	public UserDaoImpl(ExpenseDBConnection dbCon) {
		super();
		this.dbCon = dbCon;
	}



	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<>();
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "select * from users";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			
			
		}catch (SQLException e) {
			LogDriver.log.error(e);
		}
		
		
		return userList;
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getByRole(int roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByName(String username) {
		
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "select * from users where username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			User user = new User();
		
			
			while (rs.next()) {
				user = (new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			
			return user;
			
		}catch (SQLException e) {
			
			LogDriver.log.error(e);
		}
		
		return null;
	}

}
