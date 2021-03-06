package service;

import java.util.List;

import dao.UserDaoImpl;
import models.*;

public class UserServiceImpl implements UserServiceInterface{

	UserDaoImpl myDao = new UserDaoImpl();
	
	
	public User getUser(String username, String password) {
		return myDao.getUser(username, password);
	}

	@Override
	public List<Reimbursement> viewUserRequests(User user) {
//		System.out.println(myDao.viewUserRequests(user));
		return myDao.viewUserRequests(user);
	}
	
	public List<Reimbursement> viewAllRequests(ReimbursementType rType) {
		return myDao.viewAllRequests();
	}

	@Override
	public List<Reimbursement> viewUserRequests(User user, ReimbursementStatus rStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewUserRequests(User user, ReimbursementType rType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewUserRequests(User user, ReimbursementStatus rStatus, ReimbursementType rType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewAllRequests() {
		return myDao.viewAllRequests();
	}

	@Override
	public List<Reimbursement> viewAllRequests(ReimbursementStatus rStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewAllRequests(ReimbursementStatus rStatus, ReimbursementType rType) {
		// TODO Auto-generated method stub
		return null;
	}
}