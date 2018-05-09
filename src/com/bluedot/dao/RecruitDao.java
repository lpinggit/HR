package com.bluedot.dao;

import java.util.List;
import com.bluedot.po.RecruitInfo;
import com.bluedot.util.SplitPage;

public interface RecruitDao {
	List getRecruitInfo();
	List<RecruitInfo> viewRecruitInfo();
	List<RecruitInfo> viewRecruitInfoByRole(int empId);
	List<String> getAllmailBox();
	RecruitInfo viewRecruitById(int recruitId);
	int getRecruitIdFromSequence();
	boolean saveRecruitInfo(RecruitInfo recruit);
	boolean deleteRecruitInfo(String []recruitId);
	boolean updateRecruitInfo(RecruitInfo recruit);
	boolean modifyMark();//修改员工表的message标识
	SplitPage getAllRecruit(int curentPage);
	
}
