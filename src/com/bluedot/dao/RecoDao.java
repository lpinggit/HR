package com.bluedot.dao;

import java.util.List;
import java.util.Map;

import com.bluedot.po.Recommender;
import com.bluedot.util.SplitPage;

public interface RecoDao {
	boolean saveReco(Recommender reco);
	boolean deleteRecommender(String [] recoId);
	boolean updateRecommender(String[]recoId,int hrId);
	boolean Message(int empId);
	Recommender getByRecoId(int recoId);
	Map<Integer,String> getJobMap();
	Map<Integer,String> getMajorMap();
	List<Recommender> getRecommender(int empId);//������ݵõ��Ƽ���
	List<Recommender> getReco(int empId);//��ѯ�Լ����Ƽ�����
	List<Recommender> getRecommenderCurrStatus(int empId);
	SplitPage getAllReco(int curentPage);
}
