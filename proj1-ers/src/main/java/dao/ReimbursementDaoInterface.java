package dao;

import models.Reimbursement;
import models.ReimbursementStatus;
import models.User;

public interface ReimbursementDaoInterface {

	public boolean createReimbursement(Reimbursement imburs);
	public boolean resolveReimbursement(int imbursId, ReimbursementStatus status, User resolver);

}