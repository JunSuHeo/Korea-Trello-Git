package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;

public class LogoutCommand implements Command {

	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CommandForward forward = new CommandForward();
		
		// 세션에 다이디를 저장
		HttpSession session = request.getSession();
		
		session.setAttribute("sessionId", null);
		
		forward.setRedirect(true);
		forward.setNextPath("main.do");
		
		return forward;
	}

}
