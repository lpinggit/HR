package com.bluedot.service;

import java.util.List;
import java.util.Map;

import com.bluedot.po.Recommender;
import com.bluedot.util.SplitPage;

public interface RecoService {
	Map<Integer,String> getJobMap();
	Map<Integer,String> getMajorMap();
	Recommender getByRecoId(int recoId);
	boolean Message(int empId);
   boolean saveReco(Recommender reco);
   boolean deleteRecommender(String	[] reco);
   boolean updateRecommender(String[]recoId,int hrId);
   List<Recommender> getRecommender(int empId);
   List<Recommender> getReco(int empId);//查询自己的推荐的人
   SplitPage getRecommenderCurrStatus(int curentPage,int empId);
   SplitPage getAllReco(int curentPage,int empId);
}
