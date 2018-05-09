package com.bluedot.service.impl;

import java.util.List;

import com.bluedot.dao.CheckDao;
import com.bluedot.dao.impl.CheckDaoImpl;
import com.bluedot.po.CheckReturnInfo;
import com.bluedot.po.Recommender;
import com.bluedot.service.CheckService;
import com.bluedot.util.SendMailBox;

public class CheckServiceImpl implements CheckDao, CheckService {
	CheckDao checkDao;
	public  CheckServiceImpl(){
		checkDao = new CheckDaoImpl();
	}
	public List<Recommender> RecoInterview(int empId,String currStatus) {
		return checkDao.RecoInterview(empId,currStatus);
	}
	public boolean modifyInterviewStatus(int recoId, String currentStatus,int empId) {
		if("step".equals(currentStatus)){
			currentStatus ="step1";
			String desc ="�Ѿ�ͨ��ɸѡ�������֮�У�";
			//������recoId�ҵ��Ƽ��˵�empIdȻ���ѯ���Ƽ��˵����䣬�ڷ��ʼ���
			CheckReturnInfo cri= checkDao.getMailBox(recoId);
			System.out.println("�Ƽ�������"+cri.getEmpFullName());
			System.out.println("service�������"+cri.getMailBox());
			SendMailBox.SendMailBox(cri.getMailBox(), cri.getRecoName(),cri.getEmpFullName(),desc);
			return checkDao.modifyInterviewStatus(recoId, currentStatus,empId);
			
		}else if("step1".equals(currentStatus)){
			currentStatus ="step2";
			String desc ="�Ѿ�ͨ�������������֮�У�";
			CheckReturnInfo cri= checkDao.getMailBox(recoId);
			System.out.println("�Ƽ�������"+cri.getEmpFullName());
			System.out.println("service�������"+cri.getMailBox());
			SendMailBox.SendMailBox(cri.getMailBox(), cri.getRecoName(),cri.getEmpFullName(),desc);
			return checkDao.modifyInterviewStatus(recoId, currentStatus,empId);
		}else if("step2".equals(currentStatus)){
			currentStatus ="step3";
			String desc ="�Ѿ�ͨ���������offer����֮�У�";
			CheckReturnInfo cri= checkDao.getMailBox(recoId);
			System.out.println("�Ƽ�������"+cri.getEmpFullName());
			System.out.println("service�������"+cri.getMailBox());
			SendMailBox.SendMailBox(cri.getMailBox(), cri.getRecoName(),cri.getEmpFullName(),desc);
			return checkDao.modifyInterviewStatus(recoId, currentStatus,empId);
		}else if("step3".equals(currentStatus)){
			System.out.println("���Ѿ��õ�Offer�ˣ���ӭ����ְ��˾��");
		}
		return false;
	}
	public boolean returnRecommender(int recoId) {
		String desc ="����ʧ�ܣ���Ǹ�����������Ϊ���ǹ�˾�Ƽ��˲š�";
		CheckReturnInfo cri= checkDao.getMailBox(recoId);
		System.out.println("�Ƽ�������"+cri.getEmpFullName());
		System.out.println("service�������"+cri.getMailBox());
		SendMailBox.SendMailBox(cri.getMailBox(), cri.getRecoName(),cri.getEmpFullName(),desc);
		return checkDao.returnRecommender(recoId);
	}
	public CheckReturnInfo getMailBox(int recoId) {
		return checkDao.getMailBox(recoId);
	}

}
