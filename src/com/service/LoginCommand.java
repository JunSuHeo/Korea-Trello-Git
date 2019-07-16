package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;

public class LoginCommand implements Command {
	
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response)
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CommandForward forward = new CommandForward();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// DB에서 사용자 정보를 불러와 이 정보가 있는지 확인.
		int checkVal = UserDAO.getInstance().loginCheck(id, password);
		
		if(checkVal == 1) { // 로그인 성공
			
			// 세션에 다이디를 저장
			HttpSession session = request.getSession();
			
			session.setAttribute("sessionId", id);
			
			forward.setRedirect(true);
			forward.setNextPath("mainlist.do");
			
		}else { // 로그인 실패
			request.setAttribute("login failed", true);
			
			// 다시 로그인 페이지 띄워줌
			forward.setRedirect(false);
			forward.setNextPath("loginForm.jsp");
		}
		
		return forward;
	}
	
}
