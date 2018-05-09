package com.bluedot.service;

import java.util.List;

import com.bluedot.po.Recommender;

public interface CheckService {
	List<Recommender> RecoInterview(int empId,String currStatus);
	boolean modifyInterviewStatus(int recoId,String currentStatus,int empId);
	boolean returnRecommender(int recoId);
}
