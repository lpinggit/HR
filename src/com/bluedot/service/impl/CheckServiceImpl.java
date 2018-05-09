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
			String desc ="已经通过筛选进入初面之中！";
			//接下来recoId找到推荐人的empId然后查询出推荐人的邮箱，在发邮件。
			CheckReturnInfo cri= checkDao.getMailBox(recoId);
			System.out.println("推荐人姓名"+cri.getEmpFullName());
			System.out.println("service邮箱号码"+cri.getMailBox());
			SendMailBox.SendMailBox(cri.getMailBox(), cri.getRecoName(),cri.getEmpFullName(),desc);
			return checkDao.modifyInterviewStatus(recoId, currentStatus,empId);
			
		}else if("step1".equals(currentStatus)){
			currentStatus ="step2";
			String desc ="已经通过初面进入终面之中！";
			CheckReturnInfo cri= checkDao.getMailBox(recoId);
			System.out.println("推荐人姓名"+cri.getEmpFullName());
			System.out.println("service邮箱号码"+cri.getMailBox());
			SendMailBox.SendMailBox(cri.getMailBox(), cri.getRecoName(),cri.getEmpFullName(),desc);
			return checkDao.modifyInterviewStatus(recoId, currentStatus,empId);
		}else if("step2".equals(currentStatus)){
			currentStatus ="step3";
			String desc ="已经通过终面进入offer面试之中！";
			CheckReturnInfo cri= checkDao.getMailBox(recoId);
			System.out.println("推荐人姓名"+cri.getEmpFullName());
			System.out.println("service邮箱号码"+cri.getMailBox());
			SendMailBox.SendMailBox(cri.getMailBox(), cri.getRecoName(),cri.getEmpFullName(),desc);
			return checkDao.modifyInterviewStatus(recoId, currentStatus,empId);
		}else if("step3".equals(currentStatus)){
			System.out.println("你已经拿到Offer了，欢迎你入职公司！");
		}
		return false;
	}
	public boolean returnRecommender(int recoId) {
		String desc ="面试失败，抱歉！！请你继续为我们公司推荐人才。";
		CheckReturnInfo cri= checkDao.getMailBox(recoId);
		System.out.println("推荐人姓名"+cri.getEmpFullName());
		System.out.println("service邮箱号码"+cri.getMailBox());
		SendMailBox.SendMailBox(cri.getMailBox(), cri.getRecoName(),cri.getEmpFullName(),desc);
		return checkDao.returnRecommender(recoId);
	}
	public CheckReturnInfo getMailBox(int recoId) {
		return checkDao.getMailBox(recoId);
	}

}
