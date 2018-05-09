package com.bluedot.service.impl;

import java.util.List;
import java.util.Map;

import com.bluedot.dao.RecoDao;
import com.bluedot.dao.impl.RecoDaoImpl;
import com.bluedot.po.Recommender;
import com.bluedot.service.RecoService;
import com.bluedot.util.SplitPage;

public class RecoServiceImpl implements RecoService {

	RecoDao recoDao;
	public RecoServiceImpl(){
		recoDao=new RecoDaoImpl();
	}
	public Map<Integer, String> getJobMap() {
		return recoDao.getJobMap();
	}

	public Map<Integer, String> getMajorMap() {
	
		return recoDao.getMajorMap();
	}
	public boolean saveReco(Recommender reco) {
		
		return recoDao.saveReco(reco);
	}
	public List<Recommender> getRecommender(int empId) {
		
		return recoDao.getRecommender(empId);
	}
	public List<Recommender> getRecommenderCurrStatus(int empId) {

		return recoDao.getRecommenderCurrStatus(empId);
	}
	public boolean deleteRecommender(String [] recoId) {
		
		return recoDao.deleteRecommender(recoId);
	}
	public Recommender getByRecoId(int recoId) {
		return recoDao.getByRecoId(recoId);
	}
	public List<Recommender> getReco(int empId) {
		return recoDao.getReco(empId);
	}
	public boolean updateRecommender(String[] recoId, int hrId) {
		return recoDao.updateRecommender(recoId, hrId);
	}
	public boolean Message(int empId) {
		return recoDao.Message(empId);
	}
	public SplitPage getAllReco(int curentPage) {
		return recoDao.getAllReco(curentPage);
	}

}
