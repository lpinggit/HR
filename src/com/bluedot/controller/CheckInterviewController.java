package com.bluedot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluedot.po.Employee;
import com.bluedot.po.Recommender;
import com.bluedot.service.CheckService;
import com.bluedot.service.impl.CheckServiceImpl;

public class CheckInterviewController {

	public void CheckRequest(HttpServletRequest request,
			HttpServletResponse response, String action) {
		try{
			if("selectInterview.do".equals(action)){
				System.out.println("ͨ��ɸѡ������������");
				int empId = ((Employee) request.getSession().getAttribute(
				"user")).getEmpId();
				String currStatus ="step";
				CheckService checkService = new CheckServiceImpl();
				List<Recommender> list = checkService.RecoInterview(empId,currStatus);
				request.setAttribute("interlist", list);
				System.out.println("select :"+list.size());
				request.getRequestDispatcher("/WEB-INF/jsp/check/selectRecommender.jsp").forward(request, response);
			}else if("modifyInterviewStatus.do".equals(action)){
				String recoId = request.getParameter("recoId");
				String currentStatus = request.getParameter("currentstatus");
				int empId = ((Employee) request.getSession().getAttribute(
				"user")).getEmpId();
				System.out.println("recoId"+recoId);
				System.out.println("...."+currentStatus);
				CheckService checkService = new CheckServiceImpl();
				if(checkService.modifyInterviewStatus(Integer.parseInt(recoId), currentStatus,empId)){
					/*request.getRequestDispatcher("selectInterview.do").forward(request, response);*/
					List<Recommender> list = checkService.RecoInterview(empId,currentStatus);
					request.setAttribute("interlist", list);
					System.out.println("select :"+list.size());
					request.getRequestDispatcher("/WEB-INF/jsp/check/selectRecommender.jsp").forward(request, response);
				}
			}else if("InterviewFailed.do".equals(action)){
				System.out.println("����ʧ�ܡ���������������������");
				//����ʧ�ܺ�Ͱ��˲ŷ����˲ſ��С�����ֻҪ�ı�����״̬����
				String recoId =request.getParameter("recoId");
				CheckService checkService = new CheckServiceImpl();
				if(checkService.returnRecommender(Integer.parseInt(recoId))){
					request.getRequestDispatcher("selectInterview.do").forward(request, response);
				}	
			}else if("passedFirstInterview.do".equals(action)){
				//��ѯͨ����ɸѡ�ı��Ƽ���
				int empId = ((Employee) request.getSession().getAttribute(
				"user")).getEmpId();
				String currStatus ="step1";
				CheckService checkService = new CheckServiceImpl();
				List<Recommender> list = checkService.RecoInterview(empId,currStatus);
				request.setAttribute("interlist", list);
				System.out.println("select :"+list.size());
				request.getRequestDispatcher("/WEB-INF/jsp/check/selectRecommender.jsp").forward(request, response);
				
				
			}else if("passedFinalInterview.do".equals(action)){
				//�鿴ͨ���˳��Եı��Ƽ���
				int empId = ((Employee) request.getSession().getAttribute(
				"user")).getEmpId();
				String currStatus ="step2";
				CheckService checkService = new CheckServiceImpl();
				List<Recommender> list = checkService.RecoInterview(empId,currStatus);
				request.setAttribute("interlist", list);
				System.out.println("select :"+list.size());
				request.getRequestDispatcher("/WEB-INF/jsp/check/selectRecommender.jsp").forward(request, response);
			}else if("getOffterInterview.do".equals(action)){
				//�鿴ͨ���������Եı��Ƽ��ˣ��������ñ��Ƽ��Ƿ��Ϊ�ù�˾��Ա��
				int empId = ((Employee) request.getSession().getAttribute(
				"user")).getEmpId();
				String currStatus ="step3";
				CheckService checkService = new CheckServiceImpl();
				List<Recommender> list = checkService.RecoInterview(empId,currStatus);
				request.setAttribute("interlist", list);
				System.out.println("select :"+list.size());
				request.getRequestDispatcher("/WEB-INF/jsp/check/selectRecommender.jsp").forward(request, response);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
