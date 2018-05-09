package com.bluedot.service.impl;

import java.util.List;

import com.bluedot.dao.CreditDao;
import com.bluedot.dao.impl.CreditDaoImpl;
import com.bluedot.po.Credit;
import com.bluedot.service.CreditService;
import com.bluedot.util.SplitPage;

public class CreditServiceImpl implements CreditService {
	CreditDao creditDao;
	public CreditServiceImpl(){
		creditDao = new CreditDaoImpl();
	}
	public Credit viewCredit(int empId) {
		return creditDao.viewCredit(empId);
	}
	
	public SplitPage getAllCredit(int curentPage) {
		return creditDao.getAllCredit(curentPage);
	}
	
}
