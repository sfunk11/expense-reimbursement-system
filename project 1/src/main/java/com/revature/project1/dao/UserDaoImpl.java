package com.revature.project1.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
			
			String sql = "select * from ers_users";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			
			
		}catch (Exception e) {
			LogDriver.log.error(e);
		}
		
		
		return userList;
	}
	

	@Override
	public User getById(int id) {
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "select * from ers_users where ers_users_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			User user = new User();
			while (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			return user;
			
		}catch (Exception e) {
			LogDriver.log.error(e);
		}
		
		
		return null;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(User t) {

		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "{? = call insert_user(?,?,?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			 cs.setString(2, t.getUsername());
			 cs.setString(3, t.getPassword());
			 cs.setString(4,  t.getFirstName());
			 cs.setString(5,  t.getLastName());
			 cs.setString(6, t.getEmail());
			 cs.setInt(7, t.getRoleId());
			 cs.execute();
					
			
		}catch (Exception e) {
			
			LogDriver.log.error(e);
		}
		

	}

	@Override
	public void delete(User t) {
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "delete from ers_users where ers_username = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, t.getUsername());
			ps.execute();		
			
		}catch (Exception e) {
			
			LogDriver.log.error(e);
		}

	}

	@Override
	public List<User> getByRole(int roleId) {
		List<User> userList = new ArrayList<>();
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "select * from ers_users where user_role_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, roleId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			
		}catch (Exception e) {
			LogDriver.log.error(e);
		}
		
		
		return userList;
	
	}

	@Override
	public User getByName(String username) {
		
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "select * from ers_users where ers_username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			User user = new User();
		
			
			while (rs.next()) {
				user = (new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			
			return user;
			
		}catch (Exception e) {
			
			LogDriver.log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}

}
