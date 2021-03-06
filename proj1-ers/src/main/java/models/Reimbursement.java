package models;

import java.sql.Timestamp;
import java.util.Arrays;

public class Reimbursement {
	
	private int reimbId;
	private double reimbursAmount;
	private Timestamp submitted = new Timestamp(System.currentTimeMillis());
	private Timestamp resolved = null;
	private String description = null;
	private byte[] receipt = null;
	private ReimbursementType rType;
	private ReimbursementStatus rStatus = ReimbursementStatus.PENDING;
	private User author;
	private User resolver = null;
	
	public Reimbursement(int reimbId, double reimbursAmount, Timestamp submitted, Timestamp resolved, String description,
			byte[] receipt, ReimbursementType rType, ReimbursementStatus rStatus, User author, User resolver) {
		this.reimbId = reimbId;
		this.reimbursAmount = reimbursAmount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.rType = rType;
		this.rStatus = rStatus;
		this.author = author;
		this.resolver = resolver;
	}
	
	public Reimbursement(double reimbursAmount, Timestamp submitted, Timestamp resolved, String description,
			byte[] receipt, ReimbursementType rType, ReimbursementStatus rStatus, User author, User resolver) {
		this.reimbursAmount = reimbursAmount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.rType = rType;
		this.rStatus = rStatus;
		this.author = author;
		this.resolver = resolver;
	}

	public Reimbursement(double reimbursAmount, String description, byte[] receipt, ReimbursementType rType, User author) {
		this.reimbursAmount = reimbursAmount;
		this.submitted = new Timestamp(System.currentTimeMillis());
		this.description = description;
		this.receipt = receipt;
		this.rType = rType;
		this.author = author;
	}
	
	public Reimbursement(double reimbursAmount, String description, ReimbursementType rType, User author) {
		this.reimbursAmount = reimbursAmount;
		this.submitted = new Timestamp(System.currentTimeMillis());
		this.description = description;
		this.rType = rType;
		this.author = author;
	}
	
	public Reimbursement(double reimbursAmount, byte[] receipt, ReimbursementType rType, User author) {
		this.reimbursAmount = reimbursAmount;
		this.submitted = new Timestamp(System.currentTimeMillis());
		this.receipt = receipt;
		this.rType = rType;
		this.author = author;
	}
	
	public Reimbursement(double reimbursAmount, ReimbursementType rType, User author) {
		this.reimbursAmount = reimbursAmount;
		this.submitted = new Timestamp(System.currentTimeMillis());
		this.rType = rType;
		this.author = author;
	}
	
	public Reimbursement() {
		
	}

	public double getReimbursAmount() {
		return reimbursAmount;
	}

	public void setReimbursAmount(double reimbursAmount) {
		this.reimbursAmount = reimbursAmount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public ReimbursementType getrType() {
		return rType;
	}

	public void setrType(ReimbursementType rType) {
		this.rType = rType;
	}

	public ReimbursementStatus getrStatus() {
		return rStatus;
	}

	public void setrStatus(ReimbursementStatus rStatus) {
		this.rStatus = rStatus;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}
	
	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	@Override
	public String toString() {
		return "\nReimbursment [reimbursAmount=" + reimbursAmount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", rType=" + rType
				+ ", rStatus=" + rStatus + ", author=" + author + ", resolver=" + resolver + "]";
	}
	
}