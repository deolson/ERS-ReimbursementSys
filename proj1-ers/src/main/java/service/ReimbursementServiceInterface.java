package service;

import models.Reimbursement;
import models.ReimbursementStatus;
import models.User;

public interface ReimbursementServiceInterface {

	public boolean createReimbursement(Reimbursement imburs);
	public boolean resolveReimbursement(int imbursId, ReimbursementStatus status, User resolver);

}