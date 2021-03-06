package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import models.UserRole;

public class UserDaoImpl implements UserDaoInterface{
	
	final static Logger loggy = Logger.getLogger(UserDaoImpl.class);

	{
		loggy.setLevel(Level.ALL);
	}
	
	@Override
	public User getUser(String username, String password) {
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM ers_users e WHERE e.ers_username = ? AND e.ers_password = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				loggy.info("User Found!");
				return new User(rs.getString("ers_username"), rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), UserRole.valueOf(rs.getInt("user_role_id")) );
			}
		
		} catch (SQLException e) {
			loggy.info("Incorrect User Information Was Entered ");
			return null;
		}
		return null;
	}

	
	@Override
	public List<Reimbursement> viewUserRequests(User user) {
		List<Reimbursement> reimburList = new ArrayList<Reimbursement>();
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			
			String sql = "SELECT reimb_id,reimb_amount,reimb_submitted,reimb_resolved,reimb_description,reimb_receipt,reimb_type_id,reimb_status_id,eu2.ers_username,eu2.user_first_name,eu2.user_last_name,eu2.user_email,eu2.user_role_id FROM \r\n"
					+ "	(ers_reimbursement er INNER JOIN ers_users eu ON er.reimb_author = eu.ers_users_id) AS joined \r\n"
					+ "LEFT JOIN \r\n"
					+ "	ers_users eu2 ON eu2.ers_users_id = joined.reimb_resolver \r\n"
					+ "WHERE joined.ers_username = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				reimburList.add(new Reimbursement(rs.getInt("reimb_id"),rs.getDouble("reimb_amount"), rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"), rs.getBytes("reimb_receipt"), ReimbursementType.valueOf(rs.getInt("reimb_type_id")),ReimbursementStatus.valueOf(rs.getInt("reimb_status_id")), user,new User(rs.getString("ers_username"),rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), UserRole.valueOf(rs.getInt("user_role_id")))));		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reimburList;
	}
	
	@Override
	public List<Reimbursement> viewAllRequests() {
		List<Reimbursement> reimburList = new ArrayList<Reimbursement>();
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			
			String sql = "SELECT reimb_id,reimb_amount,reimb_submitted,reimb_resolved,reimb_description,reimb_receipt,reimb_type_id,reimb_status_id,\r\n"
					+ "joined.ers_username,joined.user_first_name,joined.user_last_name,joined.user_email,joined.user_role_id,\r\n"
					+ "eu2.ers_username AS res_username,eu2.user_first_name AS res_first_name,eu2.user_last_name AS res_last_name,eu2.user_email AS res_email,eu2.user_role_id AS res_role_id FROM \r\n"
					+ "	(ers_reimbursement er INNER JOIN ers_users eu ON er.reimb_author = eu.ers_users_id) AS joined \r\n"
					+ "LEFT JOIN \r\n"
					+ "	ers_users eu2 ON eu2.ers_users_id = joined.reimb_resolver;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				reimburList.add(new Reimbursement(rs.getInt("reimb_id"),rs.getDouble("reimb_amount"), rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"), rs.getBytes("reimb_receipt"), ReimbursementType.valueOf(rs.getInt("reimb_type_id")),ReimbursementStatus.valueOf(rs.getInt("reimb_status_id")),
						new User(rs.getString("ers_username"),rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), UserRole.valueOf(rs.getInt("user_role_id"))),
						new User(rs.getString("res_username"),rs.getString("res_first_name"), rs.getString("res_last_name"), rs.getString("res_email"), UserRole.valueOf(rs.getInt("res_role_id")))));		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reimburList;
	}

}