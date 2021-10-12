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
import com.revature.project1.models.ReimbursementItem;


public class ReimbursementDaoImpl implements ReimbursementDao {

	private ExpenseDBConnection dbCon;
	
	
	public ReimbursementDaoImpl() {
		
	}
	
	
	
	public ReimbursementDaoImpl(ExpenseDBConnection dbCon) {
		super();
		this.dbCon = dbCon;
	}



	@Override
	public List<ReimbursementItem> getAll() {
		List<ReimbursementItem> reimbList = new ArrayList<>();
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "select * from ers_reimbursement";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				reimbList.add(new ReimbursementItem(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),
						rs.getString(5), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			
			
		}catch (Exception e) {
			LogDriver.log.error(e);
		}
		
		return reimbList;
	}


	@Override
	public ReimbursementItem getById(int id) {
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "select * from ers_reimbursement where reimb_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ReimbursementItem item = new ReimbursementItem();
			
			while (rs.next()) {
				item = new ReimbursementItem(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			}
			
			return item;
			
		}catch (Exception e) {
			LogDriver.log.error(e);
		}
		return null;
	}
	

	@Override
	public void update(ReimbursementItem t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(ReimbursementItem t) {
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "{? = call insert_reimb(?,?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setDouble(2,t.getAmount());
			cs.setString(3, t.getDescription());
			cs.setBytes(4, null);
			cs.setInt(5,  t.getAuthorId());
			cs.setInt(6,  t.getReimbTypeId());
			cs.execute();
				
			
		}catch (Exception e) {
			LogDriver.log.error(e);
		}

	}

	@Override
	public void delete(ReimbursementItem t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ReimbursementItem> getbyStatusId(int status) {
		List<ReimbursementItem> reimbList = new ArrayList<>();
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "select * from ers_reimbursement where reimb_status_id =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,status);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				reimbList.add(new ReimbursementItem(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
			
			
		}catch (Exception e) {
			LogDriver.log.error(e);
		}
		
		return reimbList;
	}
	
	

	@Override
	public List<ReimbursementItem> getByUsername(String username) {
		List<ReimbursementItem> reimbList = new ArrayList<>();
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "select * from ers_reimbursement r inner join ers_users u on r.reimb_author=u.ers_users_id where u.ers_username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				reimbList.add(new ReimbursementItem(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
			
			
		}catch (Exception e) {
			LogDriver.log.error(e);
		}
		
		return reimbList;
	
	}



	@Override
	public void approveItem(ReimbursementItem item) {
		
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "{? = call approve_reimb(?,?}";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2,item.getReimbId());
			cs.setInt(3, item.getResolverId());
			cs.execute();

			
		} catch (Exception e) {
			LogDriver.log.error(e);
		}
		
		
	}



	@Override
	public void rejectItem(ReimbursementItem item) {
		try(Connection con = dbCon.getDBConnection()){
			
			String sql = "{? = call reject_reimb(?,?}";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2,item.getReimbId());
			cs.setInt(3, item.getResolverId());
			cs.execute();

			
		} catch (Exception e) {
			LogDriver.log.error(e);
		}
		
		
	}

}
