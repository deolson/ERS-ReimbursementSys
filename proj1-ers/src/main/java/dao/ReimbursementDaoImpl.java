package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import models.Reimbursement;
import models.ReimbursementStatus;
import models.User;

public class ReimbursementDaoImpl implements ReimbursementDaoInterface {

	final static Logger loggy = Logger.getLogger(UserDaoImpl.class);

	{
		loggy.setLevel(Level.ALL);
	}
	
	@Override
	public boolean createReimbursement(Reimbursement imburs) {
		
		try (Connection conn = CustomConnectionFactory.getConnection()) {	
			
			String sql = "SELECT ers_users_id FROM ers_users eu WHERE eu.ers_username = ?;";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,imburs.getAuthor().getUsername());
			ResultSet rs = ps.executeQuery();
			
			int pk;
			if(rs.next()) {
				pk = rs.getInt("ers_users_id");
			} else {
				return false;
			}
			
			sql = "INSERT INTO ers_reimbursement (reimb_amount,reimb_submitted,reimb_resolved,reimb_description,reimb_receipt,reimb_author,reimb_status_id,reimb_type_id) "
					+ "VALUES (?,?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, imburs.getReimbursAmount());
			ps.setTimestamp(2, imburs.getSubmitted());
			ps.setTimestamp(3, imburs.getResolved());
			ps.setString(4, imburs.getDescription());
			ps.setBytes(5, imburs.getReceipt());
			ps.setInt(6,pk);
			ps.setInt(7, imburs.getrStatus().id);
			ps.setInt(8, imburs.getrType().id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			loggy.info("Reimburesment Not Created! ");
			return false;
		}
		loggy.info("Reimburesment Created! ");
		return true;
	}

	@Override
	public boolean resolveReimbursement(int imbursID, ReimbursementStatus status, User resolver) {
		
		try (Connection conn = CustomConnectionFactory.getConnection()) {	
			
			String sql = "UPDATE ers_reimbursement r "
					+ "SET reimb_resolved = current_timestamp, reimb_resolver = (SELECT users.ers_users_id FROM ers_users users WHERE users.ers_username = ?), reimb_status_id = ? "
					+ "FROM ers_users u "
					+ "WHERE r.reimb_id = ? AND r.reimb_author = u.ers_users_id;";
			
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,resolver.getUsername());
			ps.setInt(2,status.id);
			ps.setInt(3, imbursID);
			int updateCount = ps.executeUpdate();
			
			if(updateCount == 1) {				
				loggy.info("Reimburesment Resolved!");
				return true;
			} else {
				loggy.info("Reimburesment Not Resolved!");
				return false;
			}
			
		} catch (SQLException e) {
			loggy.info("Reimburesment Not Resolved!");
			return false;
		}
	}

}