package com.bluedot.po;

public class Categories {
	//categories是种类和派别的意思
	private int cateId;
	private String cateName;
	private String isWhatCate;
	


	public Categories()
	{
		
	}
	public Categories(String cateName,String isWhatCate)
	{
		this.cateName=cateName;
		this.isWhatCate=isWhatCate;
	}
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getIsWhatCate() {
		return isWhatCate;
	}
	public void setIsWhatCate(String isWhatCate) {
		this.isWhatCate = isWhatCate;
	}
	

}
