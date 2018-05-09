package com.bluedot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bludeodt.dict.WordDict;
import com.bluedot.po.CheckReturnInfo;
import com.bluedot.po.Employee;
import com.bluedot.po.Recommender;
import com.bluedot.service.RecoService;
import com.bluedot.service.impl.RecoServiceImpl;
import com.bluedot.util.SplitPage;
import com.google.gson.Gson;

public class RecoController {

	public void userRequest(HttpServletRequest request,
			HttpServletResponse response, String action) {

		try {
			if (WordDict.ADD_RECOMMENDER.equals(action)) {
				// 在跳转页面之前，应该先获取到智能数据，以及专业数据
				RecoService recoService = new RecoServiceImpl();
				request.setAttribute("jobMap", recoService.getJobMap());
				request.setAttribute("majorMap", recoService.getMajorMap());
				request.getRequestDispatcher("/WEB-INF/jsp/reco/addReco.jsp")
						.forward(request, response);
			} else if (WordDict.SAVE_RECOMMENDER.equals(action)) {
				request.setCharacterEncoding("UTF-8");
				DiskFileItemFactory factory = new DiskFileItemFactory();
				String path = request.getRealPath("/pic");
				factory.setRepository(new File(path));
				factory.setSizeThreshold(1024 * 1024);
				ServletFileUpload sfu = new ServletFileUpload(factory);
				// 解析请求对象
				List<FileItem> items = sfu.parseRequest(request);

				String username = items.get(0).getString();
				String recoName = items.get(1).getString();
				System.out.println("RecoName"
						+ new String(recoName.getBytes("ISO-8859-1"), "UTF-8"));
				String jobId = items.get(2).getString();
				String majorId = items.get(3).getString();
				String sex = items.get(4).getString();

				File filePic = new File(path, items.get(5).getName());
				items.get(5).write(filePic);

				String degree = items.get(6).getString();
				String graduatedFrom = items.get(7).getString();
				String isGraguated = items.get(8).getString();
				String graduatedTime = items.get(9).getString();
				String phone = items.get(10).getString();
				String mailbox = items.get(11).getString();

				String skills = items.get(13).getString();

				path = request.getRealPath("/resume");
				File fileResume = new File(path, items.get(12).getName());
				items.get(12).write(fileResume);
				int empId = ((Employee) request.getSession().getAttribute(
						"user")).getEmpId();

				Recommender reco = new Recommender();
				// 把被推荐人的信息封装到reco对象中
				//reco.setRecoId(Integer.parseInt(recoId));
				reco.setRecoName(new String(recoName.getBytes("ISO-8859-1"),
						"UTF-8"));
				reco.setEmpId(empId);
				reco.setJobId(Integer.parseInt(jobId));
				reco.setMajorId(Integer.parseInt(majorId));
				reco.setSex(new String(sex.getBytes("ISO-8859-1"), "UTF-8"));
				reco.setPic(items.get(5).getName());
				reco.setRecoDegree(new String(degree.getBytes("ISO-8859-1"),
						"UTF-8"));
				reco.setGraduatedFrom(new String(graduatedFrom
						.getBytes("ISO-8859-1"), "UTF-8"));
				reco.setIsGraduated(new String(isGraguated
						.getBytes("ISO-8859-1"), "UTF-8"));
				Date date = Date.valueOf(graduatedTime);
				reco.setGraduatedTime(date);
				reco.setPhone(phone);
				reco.setMail(mailbox);
				reco.setSkills(new String(skills.getBytes("ISO-8859-1"),
						"UTF-8"));
				reco.setResume(items.get(12).getName());
				System.out.println("简历的名称" + items.get(12).getName());
				reco.setIsRecommended("F");
				reco.setCurrStatus("step");

				RecoService recoService = new RecoServiceImpl();
				if (recoService.saveReco(reco)) {
					System.out.println("添加被推荐成功");
					request.getRequestDispatcher("viewRecommenderInfo.do")
							.forward(request, response);
				}

			} else if ("viewRecommenderInfo.do".equals(action)) {
					//首先判断Session作用域中User是否存在，有的查看，没有则重新登录
				if (request.getSession().getAttribute("user") != null) {
					int empId = ((Employee) request.getSession().getAttribute(
							"user")).getEmpId();
					// 然后根据empId查询你所推荐的人
					System.out.println(empId);
					RecoService recoService = new RecoServiceImpl();
					
					String curentPage = "1";
					System.out.println("当前页" + curentPage);
					int curentPage01=Integer.parseInt(curentPage);
					if (curentPage != null) {
						SplitPage creditSp = new SplitPage();
						creditSp = recoService.getAllReco(curentPage01);
						HttpSession session = request.getSession();
						session.setAttribute("empSp", creditSp);
						session.setAttribute("curentPage", curentPage);
					} else {
						SplitPage creditSp = new SplitPage();
						creditSp = recoService.getAllReco(curentPage01);
						HttpSession session = request.getSession();
						session.setAttribute("empSp", creditSp);

					}
					
					
					List<Recommender> recoList = recoService
							.getRecommender(empId);
					for (Recommender reco : recoList) {

						System.out.println("被推荐人姓名" + reco.getRecoName());
						System.out.println("被推荐人Id" + reco.getRecoId());

					}
					request.setAttribute("recoList", recoList);
					request.getRequestDispatcher(
							"/WEB-INF/jsp/reco/showReco.jsp").forward(request,
							response);
				}else{
					
					request.getRequestDispatcher("login.jsp").forward(request, response);//得解決跳出框架
					
				}
				} else if ("getRecommenderCurrStatus.do".equals(action)) {
					System.out.println("被推荐的当前状态········");
					int empId = ((Employee) request.getSession().getAttribute(
							"user")).getEmpId();
					// 然后根据empId查询你所推荐的人
					System.out.println(empId);
					RecoService recoService = new RecoServiceImpl();
					
					String curentPage = "1";
					System.out.println("当前页" + curentPage);
					int curentPage01=Integer.parseInt(curentPage);
					if (curentPage != null) {
						SplitPage creditSp = new SplitPage();
						creditSp = recoService.getAllReco(curentPage01);
						HttpSession session = request.getSession();
						session.setAttribute("empSp", creditSp);
						session.setAttribute("curentPage", curentPage);
					} else {
						SplitPage creditSp = new SplitPage();
						creditSp = recoService.getAllReco(curentPage01);
						HttpSession session = request.getSession();
						session.setAttribute("empSp", creditSp);

					}
					
					
					List<Recommender> recoList = recoService
							.getRecommenderCurrStatus(empId);
					request.setAttribute("recolist", recoList);
					request.getRequestDispatcher("/WEB-INF/jsp/reco/getRecommenderCurrent.jsp").forward(request, response);
				}else if("deleteRecommender.do".equals(action)){
					
					/*	String recoId = request.getParameter("recoId");
						System.out.println("删除被推荐人的recoId:"+recoId);
						RecoService recoService = new RecoServiceImpl();
						if(recoService.deleteRecommender(Integer.parseInt(recoId))){
							System.out.println("删除被推荐人成功·········");
							request.getRequestDispatcher("viewRecommenderInfo.do").forward(request, response);
						}
					*/
					String selectReco =request.getParameter("selectReco");
					String []reco = selectReco.split(",");
					for(int i=0;i<reco.length;i++){
						System.out.println(reco[i]);
					}
					RecoService recoService = new RecoServiceImpl();
					if(recoService.deleteRecommender(reco)){
						System.out.println("删除被推荐人成功·········");
						request.getRequestDispatcher("viewRecommenderInfo.do").forward(request, response);
					}
				}
				//AJAX实现详情显示
				else if("showRecommenderDtls.do".equals(action)){
					response.setContentType("application/json;charset=UTF-8");
					String recoId = request.getParameter("recoId");
					RecoService recoService = new RecoServiceImpl();
					Recommender reco = recoService.getByRecoId(Integer.parseInt(recoId));
					if(reco!= null){
						Gson gson = new Gson();
						String gson_str = gson.toJson(reco);
						response.setCharacterEncoding("utf-8");
						response.getWriter().write(gson_str);
					}
				}
				//消息提示
				else if("recommenderMessage.do".equals(action)){
					int empId = ((Employee) request.getSession().getAttribute("user")).getEmpId();
					response.setContentType("text/html;charset=UTF-8");
					String recoName = request.getParameter("recoName");
					System.out.println("推荐人的姓名"+recoName);
					RecoService recoService = new RecoServiceImpl();
					if(recoService.Message(empId)==true){
						response.getWriter().write("true");
					}
					else{
						response.getWriter().write("false");
					}
				}
				//下载推荐人的简历表
				else if("loadRecommenderDoc.do".equals(action)){		
					request.setCharacterEncoding("UTF-8");
					//response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset=UTF-8");

					String fileName = request.getParameter("fileName");//得到jsp頁面傳過來的文件名字

					fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8").trim();//文件名
					System.out.println(fileName);
					String path = request.getRealPath("/");
					String target = path+"resume"+File.separator+fileName;//文件存储的位置,resume是对应你上传简历表时存的文件目录
					String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
					response.reset(); //清空response
					response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));
					OutputStream out = response.getOutputStream();
					FileInputStream in = new FileInputStream(target);
					try {
						fileName = URLEncoder.encode(fileName, "utf-8");
						fileType.toLowerCase();
						if("ceb".equals(fileType)){ 
							response.setContentType("application/x-ceb");
						}else if("doc".equals(fileType)){
							response.setContentType("application/msword");
						}else if("xls".equals(fileType)){
							response.setContentType("application/vnd.ms-excel");
						}else if("gif".equals(fileType)){
							response.setContentType("image/gif");
						}else if("bmp".equals(fileType)){
							response.setContentType("image/bmp");
						}else if("jpeg".equals(fileType)){
							response.setContentType("image/jpeg");
						}else if("txt".equals(fileType)){
							response.setContentType("text/plain");
						}else if("pdf".equals(fileType)){
							response.setContentType("application/pdf");
						}else if("jpeg".equals(fileType)){
							response.setContentType("image/jpeg");
						}else{
							response.setContentType("application/octet-stream");
						}

						int n = 0;
						byte b[] = new byte[1024];
						while ((n = in.read(b)) != -1)
						{
							out.write(b, 0, n);
						}
						out.flush();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					finally{
						if(in != null) {
							in.close();
						}
						if(out != null) {
							out.close();
						}
					}
					
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
