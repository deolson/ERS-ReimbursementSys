package service;

import java.util.List;

import dao.ReimbursementDaoImpl;
import models.*;

public class ReimbursementServiceImpl implements ReimbursementServiceInterface{

	ReimbursementDaoImpl myDao = new ReimbursementDaoImpl();

	@Override
	public boolean createReimbursement(Reimbursement imburs) {
		myDao.createReimbursement(imburs);
		return false;
	}

	@Override
	public boolean resolveReimbursement(int imbursId, ReimbursementStatus status, User resolver) {
		myDao.resolveReimbursement(imbursId, status, resolver);
		return false;
	}
	
}
