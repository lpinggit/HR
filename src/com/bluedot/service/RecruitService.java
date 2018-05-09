package com.bluedot.service;

import java.util.List;

import com.bluedot.po.Categories;
import com.bluedot.po.RecruitInfo;
import com.bluedot.util.SplitPage;

public interface RecruitService {
	List getRecruitInfo();
	List<RecruitInfo> viewRecruitInfo();
	List<RecruitInfo> viewRecruitInfoByRole(int empId);
	RecruitInfo viewRecruitById(int recruitId);
	int getRecruitIdFromSequence();
	boolean modifyMark();
	boolean saveRecruitInfo(RecruitInfo recruit);
	boolean deleteRecruitInfo(String []recruitId);
	boolean updateRecruitInfo(RecruitInfo recruit);
	SplitPage getAllRecruit(int curentPage);
}
