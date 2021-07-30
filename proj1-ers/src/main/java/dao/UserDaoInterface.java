package dao;

import java.util.List;

import models.*;

public interface UserDaoInterface {
	
	public User getUser(String username, String password);
	public List<Reimbursement> viewUserRequests(User user);
	public List<Reimbursement> viewAllRequests();

}