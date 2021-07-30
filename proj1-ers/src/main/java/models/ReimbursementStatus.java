package models;

public enum ReimbursementStatus {
	PENDING (1),
	APPROVED (2),
	DENIED (3);
	
	public final int id;
    
	private ReimbursementStatus(int id) {
        this.id = id;
    }
	
	public static ReimbursementStatus valueOf(int reimbursementStatus) {
		for (ReimbursementStatus u : ReimbursementStatus.values()) {
			if (u.id == reimbursementStatus)
				return u;
		}
		return null;
	}
}
