package com.bluedot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bluedot.po.Credit;
import com.bluedot.po.Employee;
import com.bluedot.service.CreditService;
import com.bluedot.service.impl.CreditServiceImpl;
import com.bluedot.util.SplitPage;

public class CreaditController {

	public void creaditRequest(HttpServletRequest request,
			HttpServletResponse response, String action) {
		try {
			if ("viewCredit.do".equals(action)) {
				int empId = ((Employee) request.getSession().getAttribute(
						"user")).getEmpId();
				CreditService creditService = new CreditServiceImpl();
				Credit credit = creditService.viewCredit(empId);
				List<Credit> creditlist = new ArrayList<Credit>();
				creditlist.add(credit);
				System.out.println("积分等级:"+credit.getCreditGrade());
				request.setAttribute("creditlist", creditlist);
				request.getRequestDispatcher(
						"/WEB-INF/jsp/credit/viewCredit.jsp").forward(request,
						response);

			}if ("viewAllCredit.do".equals(action)) {
				String curentPage = request.getParameter("currentPage");
				System.out.println("当前页" + curentPage);
				CreditService creditService = new CreditServiceImpl();
				if (curentPage != null) {
					SplitPage creditSp = new SplitPage();
					creditSp = creditService.getAllCredit(Integer.parseInt(curentPage));
					HttpSession session = request.getSession();
					session.setAttribute("empSp", creditSp);
					session.setAttribute("curentPage", curentPage);
				} else {
					SplitPage creditSp = new SplitPage();
					creditSp = creditService.getAllCredit(1);
					System.out.println("credit有没有从dao层出来");
					HttpSession session = request.getSession();
					session.setAttribute("empSp", creditSp);
					session.setAttribute("curentPage","1");
				}
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsp/credit/viewAllCredit.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
		} catch (Exception e) {

		}
	}

}
