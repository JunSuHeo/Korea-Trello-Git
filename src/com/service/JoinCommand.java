package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;

public class JoinCommand implements Command
{
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CommandForward forward = new CommandForward();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String userName = request.getParameter("username");
		
		// DB에서 사용자 정보를 불러와 이 정보가 있는지 확인.
		int checkVal = UserDAO.getInstance().loginCheck(id, password);
		
		if(checkVal == -1) { // 회원가입 성공
			
			// 회원 정보 추가
			UserDAO.getInstance().insert(id, password, userName);
			
			// 로그인 화면으로 이동
			forward.setRedirect(true);
			forward.setNextPath("loginForm.jsp");
			
		}else { // 회원가입 실패
			request.setAttribute("login failed", true);
			
			// 다시 회원가입 페이지 띄워줌
			forward.setRedirect(false);
			forward.setNextPath("joinForm.jsp");
		}
		
		return forward;
		
	}
}
