package com.bluedot.po;

public class Credit {
	private int creditGrade;
	private int totalCredit;
	private String empFullName;
	private String empAccount;

	public Credit() {
		super();
		
	}
	public int getCreditGrade() {
		return creditGrade;
	}

	public void setCreditGrade(int creditGrade) {
		this.creditGrade = creditGrade;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public String getEmpFullName() {
		return empFullName;
	}

	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}

	public String getEmpAccount() {
		return empAccount;
	}

	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}

}
