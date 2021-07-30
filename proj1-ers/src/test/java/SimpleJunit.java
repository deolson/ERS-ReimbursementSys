import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import models.Reimbursement;
import models.ReimbursementStatus;

public class SimpleJunit {

	@Test
	public void testReimbursementStatus() {
		Reimbursement reim = new Reimbursement(); 
		assertEquals(ReimbursementStatus.PENDING,reim.getrStatus());
	}
	
	public void testTime() {
		Reimbursement reim = new Reimbursement();
		assertEquals(new Timestamp(System.currentTimeMillis()),reim.getSubmitted());
	}

}
