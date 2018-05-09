package com.bluedot.po;

public class Department {
	private int deptId;
	private String deptName;
	private String deptMgr;
	public Department(){
		
	}
	public Department(String deptName){
		this.deptName = deptName;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptMgr() {
		return deptMgr;
	}
	public void setDeptMgr(String deptMgr) {
		this.deptMgr = deptMgr;
	}
	
}
