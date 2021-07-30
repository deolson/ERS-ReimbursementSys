package models;

public enum UserRole {
	EMPLOYEE (1),
	FINANCE_MANAGER (2);
	
	public final int id;
    
	private UserRole(int id) {
		this.id = id;
	}

	public static UserRole valueOf(int userRole) {
		for (UserRole u : UserRole.values()) {
			if (u.id == userRole)
				return u;
		}
		return null;
	}
}