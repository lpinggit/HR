package com.bluedot.dao;

import java.util.List;

import com.bluedot.po.Credit;
import com.bluedot.util.SplitPage;

public interface CreditDao {
	Credit viewCredit(int empId);
	
	SplitPage getAllCredit(int curentPage);
}
