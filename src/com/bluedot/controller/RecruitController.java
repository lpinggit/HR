package com.bluedot.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bludeodt.dict.WordDict;
import com.bluedot.po.Categories;
import com.bluedot.po.Department;
import com.bluedot.po.Employee;
import com.bluedot.po.Recommender;
import com.bluedot.po.RecruitInfo;
import com.bluedot.service.RecoService;
import com.bluedot.service.RecruitService;
import com.bluedot.service.impl.RecoServiceImpl;
import com.bluedot.service.impl.RecruitServiceImpl;
import com.bluedot.util.SplitPage;

public class RecruitController {
	public void userRequest(HttpServletRequest request,
			HttpServletResponse response, String action) {
		try{
			if(WordDict.PUBLISH_RECRUIT_INFO.equals(action)){
				//���Ȼ�ȡְ����Ϣ��ְλ����
				List list=new ArrayList();
				RecruitService recruitService=new RecruitServiceImpl();
				list=recruitService.getRecruitInfo();
				List<Categories> cateList = new ArrayList<Categories>();
				List<Department> deptList = new ArrayList<Department>();
				for (Object obj : list) {
					if (obj instanceof Categories) {
						cateList.add((Categories) obj);
					} else if (obj instanceof Department) {
						deptList.add((Department) obj);
					}
				}
				request.setAttribute("cateList", cateList);
				request.setAttribute("deptList", deptList);
				request.getRequestDispatcher("/WEB-INF/jsp/recruit/publishRecruitInfo.jsp").forward(request, response);
			}else if("saveRecruitInfo.do".equals(action)){
				int empId = ((Employee)request.getSession().getAttribute("user")).getEmpId();
				String jobId=request.getParameter("job");
				String deptNo=request.getParameter("deptNo");
				//String hrId=request.getParameter("hrId");
				String workCateId=request.getParameter("work_cate_id");
				String number=request.getParameter("number");
				String workLoc=request.getParameter("workloc");
				String workshift=request.getParameter("workshift");
				String deadLine=request.getParameter("deadline");
				String experience=request.getParameter("experience");
				String jobDesc=request.getParameter("jobDesc");
				String jobRequest=request.getParameter("jobRequest");
				String specialRequest=request.getParameter("specialRequest");
				String isUrgent=request.getParameter("isUrgent");
			
				//����
				System.out.println("����id"+jobId);
				System.out.println("deptNo"+deptNo);
				System.out.println("����HRId"+empId);
				System.out.println("workCateId"+workCateId);
				System.out.println("number"+number);
				System.out.println("�����ص�"+workLoc);
				System.out.println("������ֹ����"+deadLine);
				System.out.println("��������"+workshift);
				System.out.println("��������"+experience);
				System.out.println("��������"+jobDesc);
				System.out.println("����Ҫ��"+jobRequest);
				System.out.println("�ر�Ҫ��"+specialRequest);
				System.out.println("������"+isUrgent);
				
				
				RecruitInfo recruit=new RecruitInfo();
				recruit.setJobId(Integer.parseInt(jobId));
				recruit.setDeptNo(Integer.parseInt(deptNo));
				recruit.setHrId(empId);
				recruit.setWorkCateId(Integer.parseInt(workCateId));
				recruit.setNumber(Integer.parseInt(number));
				recruit.setWorkLoc(new String(workLoc.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setDeadLine(Date.valueOf(deadLine));
				recruit.setWorkshift(new String(workshift.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setExperience(Integer.parseInt(experience));
				recruit.setJobDesc(new String(jobDesc.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setJobRequest(new String(jobRequest.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setSpecialRequest(new String(specialRequest.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setIsUrgent(new String(isUrgent.getBytes("ISO-8859-1"),"UTF-8"));
				
				//����ҵ���߼���
				RecruitService recruitService=new RecruitServiceImpl();
				if(recruitService.saveRecruitInfo(recruit)){
					System.out.println("������Ƹ��Ϣ�ɹ�");
					request.getRequestDispatcher("viewRecruitInfo.do").forward(request, response);
				}
				else{
					System.out.println("������Ƹ��Ϣʧ�ܡ�����������������");
					request.getRequestDispatcher("viewRecruitInfo.do").forward(request, response);
				}
				
			}else if("viewRecruitInfo.do".equals(action)){
				System.out.println("�鿴��Ƹ��Ϣ");
				RecruitService recruitService = new RecruitServiceImpl();
				String curentPage = "1";
				System.out.println("��ǰҳ" + curentPage);
				int curentPage01=Integer.parseInt(curentPage);
				if (curentPage != null) {
					SplitPage creditSp = new SplitPage();
					creditSp = recruitService.getAllRecruit(curentPage01);
					HttpSession session = request.getSession();
					session.setAttribute("empSp", creditSp);
					session.setAttribute("curentPage", curentPage);
				} else {
					SplitPage creditSp = new SplitPage();
					creditSp = recruitService.getAllRecruit(curentPage01);
					HttpSession session = request.getSession();
					session.setAttribute("empSp", creditSp);

				}
				List<RecruitInfo> list = recruitService.viewRecruitInfo();
				request.setAttribute("recruitlist", list);
				request.getRequestDispatcher("/WEB-INF/jsp/recruit/viewRecruitInfo.jsp").forward(request, response);
			}else if("recoRecruit.do".equals(action)){
				String recruitId =request.getParameter("recruitId");
				System.out.println("��ƸID"+recruitId);
				RecruitService recruitService = new RecruitServiceImpl();
				RecruitInfo recruitInfo= recruitService.viewRecruitById(Integer.parseInt(recruitId));
				System.out.println(recruitInfo.getHrId());
				request.setAttribute("recruitInfo", recruitInfo);
				request.getRequestDispatcher("/WEB-INF/jsp/recruit/recruitReco.jsp").forward(request, response);
			}else if("viewRecruitBar.do".equals(action)){
				System.out.println("hahahha");
				RecruitService recruitService = new RecruitServiceImpl();
				if(recruitService.modifyMark()){
					request.getRequestDispatcher("selectInterview.do").forward(request, response);
				}
				//С��ʾ��֮���ʶ�����
			}
			else if("manageRecruitInfo.do".equals(action)){
				int empId = ((Employee) request.getSession().getAttribute(
				"user")).getEmpId();
				RecruitService recruitService = new RecruitServiceImpl();
				
				String curentPage = "1";
				System.out.println("��ǰҳ" + curentPage);
				int curentPage01=Integer.parseInt(curentPage);
				if (curentPage != null) {
					SplitPage creditSp = new SplitPage();
					creditSp = recruitService.getAllRecruit(curentPage01);
					HttpSession session = request.getSession();
					session.setAttribute("empSp", creditSp);
					session.setAttribute("curentPage", curentPage);
				} else {
					SplitPage creditSp = new SplitPage();
					creditSp = recruitService.getAllRecruit(curentPage01);
					HttpSession session = request.getSession();
					session.setAttribute("empSp", creditSp);

				}
				
				List<RecruitInfo> list = recruitService.viewRecruitInfoByRole(empId);
				request.setAttribute("recruitlist", list);
				request.getRequestDispatcher("/WEB-INF/jsp/recruit/managerRcruitInfo.jsp").forward(request, response);
			}else if("deleteRecruitInfo.do".equals(action)){
				String selectRecruit = request.getParameter("selectRecruit");
				String []recruitId = selectRecruit.split(",");
				for(int i = 0;i<recruitId.length;i++){
					System.out.println("recruitID"+recruitId[i]);
				}
				RecruitService recruitService = new RecruitServiceImpl();
				if(recruitService.deleteRecruitInfo(recruitId)){
					System.out.println("�h����Ƹ��Ϣ�ɹ�");
					request.getRequestDispatcher("manageRecruitInfo.do").forward(request, response);
				}
			}else if("updateRecruitInfo.do".equals(action)){
				
				List list=new ArrayList();
				RecruitService recruitService=new RecruitServiceImpl();
				list=recruitService.getRecruitInfo();
				List<Categories> cateList = new ArrayList<Categories>();
				List<Department> deptList = new ArrayList<Department>();
				for (Object obj : list) {
					if (obj instanceof Categories) {
						cateList.add((Categories) obj);
					} else if (obj instanceof Department) {
						deptList.add((Department) obj);
					}
				}
				request.setAttribute("cateList", cateList);
				request.setAttribute("deptList", deptList);
				String recruitId = request.getParameter("recruitId");
				RecruitInfo recruitInfo= recruitService.viewRecruitById(Integer.parseInt(recruitId));
				request.setAttribute("recruitInfo", recruitInfo);
				request.getRequestDispatcher("/WEB-INF/jsp/recruit/managerRecruit.jsp").forward(request, response);
			}else if("udpateRecruit.do".equals(action)){
				int empId = ((Employee)request.getSession().getAttribute("user")).getEmpId();
				String recruitId = request.getParameter("recruitId");
				System.out.println("aaaaaaaaaaaaa"+recruitId);
				String jobId=request.getParameter("job");
				String deptNo=request.getParameter("deptNo");
				//String hrId=request.getParameter("hrId");
				String workCateId=request.getParameter("work_cate_id");
				String number=request.getParameter("number");
				String workLoc=request.getParameter("workloc");
				String workshift=request.getParameter("workshift");
				String deadLine=request.getParameter("deadline");
				String experience=request.getParameter("experience");
				String jobDesc=request.getParameter("jobDesc");
				String jobRequest=request.getParameter("jobRequest");
				String specialRequest=request.getParameter("specialRequest");
				String isUrgent=request.getParameter("isUrgent");
				
				
				RecruitInfo recruit=new RecruitInfo();
				recruit.setRecruitId(Integer.parseInt(recruitId));
				recruit.setJobId(Integer.parseInt(jobId));
				recruit.setDeptNo(Integer.parseInt(deptNo));
				recruit.setWorkCateId(Integer.parseInt(workCateId));
				recruit.setNumber(Integer.parseInt(number));
				recruit.setWorkLoc(new String(workLoc.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setDeadLine(Date.valueOf(deadLine));
				recruit.setWorkshift(new String(workshift.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setExperience(Integer.parseInt(experience));
				recruit.setJobDesc(new String(jobDesc.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setJobRequest(new String(jobRequest.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setSpecialRequest(new String(specialRequest.getBytes("ISO-8859-1"),"UTF-8"));
				recruit.setIsUrgent(new String(isUrgent.getBytes("ISO-8859-1"),"UTF-8"));
				
				//ҵ���
				RecruitService recruitService = new RecruitServiceImpl();
				if(recruitService.updateRecruitInfo(recruit)){
					request.getRequestDispatcher("updateRecruitInfo.do").forward(request, response);
				}
				request.getRequestDispatcher("updateRecruitInfo.do").forward(request, response);
			}else if("immendiateRecruitReco.do".equals(action)){
				//����תҳ��֮ǰ��Ӧ���Ȱ���Ҫ���Ƽ��˵���Ϣ��ѯ������
				System.out.println("aaaaaaaaaaaaaaaaa");
				int	empId = ((Employee)request.getSession().getAttribute("user")).getEmpId();
				String hrId = request.getParameter("hrId");
				System.out.println("hrId"+hrId);
				RecoService recoService = new RecoServiceImpl();
				List<Recommender> recoList = recoService.getReco(empId);
				request.setAttribute("recoList", recoList);
				request.setAttribute("hrId", hrId);
				request.getRequestDispatcher("/WEB-INF/jsp/recruit/reco.jsp").forward(request, response);
		
 			}else if("changeRecruitStatus.do".equals(action)){//����js�������ֵû�н��
 				String  selectReco = request.getParameter("selectReco");
 				String hrId = request.getParameter("hrId");
				System.out.println("hrId"+hrId);
 				String []recoId =null;
 				RecoService recoService = new RecoServiceImpl();
 				if(selectReco.contains(",")){
 					System.out.println("�ı�");
 					recoId= selectReco.split(",");
 					recoService.updateRecommender(recoId, Integer.parseInt(hrId));
 					request.getRequestDispatcher("immendiateRecruitReco.do").forward(request, response);
 				}
 				else {
 					String UselectReco =selectReco.concat(",");
 					recoId = UselectReco.split(",");
 					recoService.updateRecommender(recoId, Integer.parseInt(hrId));
 					request.getRequestDispatcher("immendiateRecruitReco.do").forward(request, response);
 					
 				}
 				
 			}
		}
		catch(Exception e ){
			
			e.printStackTrace();
		}
	}
}
