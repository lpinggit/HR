package com.bluedot.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);

		if (uri.endsWith(".do")) {
			if (action.toLowerCase().indexOf("user") != -1) {
				UserController uc = new UserController();
				uc.userRequest(request, response, action);
			} else if (action.toLowerCase().indexOf("role") != -1) {
				RoleController rc = new RoleController();
				rc.userRequest(request, response, action);
			} else if (action.toLowerCase().indexOf("recruit") != -1) {
				RecruitController rec = new RecruitController();
				rec.userRequest(request, response, action);
			} else if (action.toLowerCase().indexOf("recommender") != -1) {
				RecoController rcl = new RecoController();
				rcl.userRequest(request, response, action);
			} else if (action.toLowerCase().indexOf("credit") != -1) {
				CreaditController cc = new CreaditController();
				cc.creaditRequest(request, response, action);
			}else if(action.toLowerCase().indexOf("interview")!=-1){
				CheckInterviewController checkInterview = new CheckInterviewController();
				checkInterview.CheckRequest(request, response, action);
			}

		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}