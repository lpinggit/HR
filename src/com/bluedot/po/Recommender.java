package com.bluedot.po;

import java.sql.Date;

public class Recommender {
	
	private int recoId;
	private String recoName;
	private int empId;
	private String empFullName;
	private int currentHrId;
	private String currentHrName;
	private int jobId;
	private int majorId;
	private String sex;
	private String pic;
	private String recoDegree;
	private String graduatedFrom;
	private String isGraduated;
	private Date graduatedTime;
	private String phone;
	private String mail;
	private String skills;
	private String resume;
	private String isRecommended;
	private String currStatus;
	private Categories jobCate;
	private Categories majorCate;
	private Employee employee;
	
	
	
	public String getEmpFullName() {
		return empFullName;
	}
	public void setEmpFullname(String empFullName) {
		this.empFullName = empFullName;
	}
	public String getCurrentHrName() {
		return currentHrName;
	}
	public void setCurrentHrName(String currentHrName) {
		this.currentHrName = currentHrName;
	}
	public Categories getJobCate() {
		return jobCate;
	}
	public void setJobCate(Categories jobCate) {
		this.jobCate = jobCate;
	}
	public Categories getMajorCate() {
		return majorCate;
	}
	public void setMajorCate(Categories majorCate) {
		this.majorCate = majorCate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getRecoId() {
		return recoId;
	}
	public void setRecoId(int recoId) {
		this.recoId = recoId;
	}
	public String getRecoName() {
		return recoName;
	}
	public void setRecoName(String recoName) {
		this.recoName = recoName;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getCurrentHrId() {
		return currentHrId;
	}
	public void setCurrentHrId(int currentHrId) {
		this.currentHrId = currentHrId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getRecoDegree() {
		return recoDegree;
	}
	public void setRecoDegree(String recoDegree) {
		this.recoDegree = recoDegree;
	}
	public String getGraduatedFrom() {
		return graduatedFrom;
	}
	public void setGraduatedFrom(String graduatedFrom) {
		this.graduatedFrom = graduatedFrom;
	}
	public String getIsGraduated() {
		return isGraduated;
	}
	public void setIsGraduated(String isGraduated) {
		this.isGraduated = isGraduated;
	}
	public Date getGraduatedTime() {
		return graduatedTime;
	}
	public void setGraduatedTime(Date graduatedTime) {
		this.graduatedTime = graduatedTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(String isRecommended) {
		this.isRecommended = isRecommended;
	}
	public String getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}
}