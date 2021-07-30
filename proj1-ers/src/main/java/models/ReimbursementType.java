package models;

public enum ReimbursementType {
	LODGING (1),
	TRAVEL (2),
	FOOD (3),
	OTHER (4);
	
	public final int id;
    
	private ReimbursementType(int id) {
        this.id = id;
    }
	
	public static ReimbursementType valueOf(int reimbursementType) {
		for (ReimbursementType u : ReimbursementType.values()) {
			if (u.id == reimbursementType)
				return u;
		}
		return null;
	}
	
}