package com.bluedot.service;

import java.util.List;

import com.bluedot.po.Credit;
import com.bluedot.util.SplitPage;

public interface CreditService {
	Credit viewCredit(int empId);
	
	SplitPage getAllCredit(int curentPage);
}
