package service;

import java.util.List;

import models.*;

public interface UserServiceInterface {
	
	public User getUser(String username, String password);

	public List<Reimbursement> viewUserRequests(User user);
	public List<Reimbursement> viewUserRequests(User user, ReimbursementStatus rStatus);
	public List<Reimbursement> viewUserRequests(User user, ReimbursementType rType);
	public List<Reimbursement> viewUserRequests(User user, ReimbursementStatus rStatus, ReimbursementType rType);
	
	public List<Reimbursement> viewAllRequests();
	public List<Reimbursement> viewAllRequests(ReimbursementStatus rStatus);
	public List<Reimbursement> viewAllRequests(ReimbursementType rType);
	public List<Reimbursement> viewAllRequests(ReimbursementStatus rStatus, ReimbursementType rType);
}