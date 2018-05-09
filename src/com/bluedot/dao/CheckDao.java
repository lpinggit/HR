package com.bluedot.dao;

import java.util.List;

import com.bluedot.po.CheckReturnInfo;
import com.bluedot.po.Recommender;

public interface CheckDao {
	List<Recommender> RecoInterview(int emId,String currStatus);
	CheckReturnInfo getMailBox(int recoId);
	boolean modifyInterviewStatus(int recoId, String currentStatus,int empId);
	boolean returnRecommender(int recoId);
	
}
