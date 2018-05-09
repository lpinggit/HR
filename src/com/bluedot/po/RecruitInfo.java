package com.bluedot.po;

import java.sql.Date;

public class RecruitInfo {
	private int recruitId;
	private int jobId;
	private int deptNo;
	private int hrId;
	private int workCateId;
	private int number;
	private String workLoc;
	private String workshift;
	private Date deadLine;
	private int experience;
	private String jobDesc;
	private String jobRequest;
	private String specialRequest;
	private String isUrgent;
	private Categories jobCate;
	private Department department;
	
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Categories getJobCate() {
		return jobCate;
	}

	public void setJobCate(Categories jobCate) {
		this.jobCate = jobCate;
	}

	public int getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(int recruitId) {
		this.recruitId = recruitId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public int getHrId() {
		return hrId;
	}

	public void setHrId(int hrId) {
		this.hrId = hrId;
	}

	public int getWorkCateId() {
		return workCateId;
	}

	public void setWorkCateId(int workCateId) {
		this.workCateId = workCateId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getWorkLoc() {
		return workLoc;
	}

	public void setWorkLoc(String workLoc) {
		this.workLoc = workLoc;
	}

	public String getWorkshift() {
		return workshift;
	}

	public void setWorkshift(String workshift) {
		this.workshift = workshift;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(String jobRequest) {
		this.jobRequest = jobRequest;
	}

	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}

	public String getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(String isUrgent) {
		this.isUrgent = isUrgent;
	}

}
