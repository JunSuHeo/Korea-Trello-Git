package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Command;
import com.service.CommandForward;
import com.service.IdCheckCommand;
import com.service.JoinCommand;
import com.service.LoginCommand;
import com.service.LogoutCommand;

@WebServlet({"/main.do","/loginCommand.do","/join.do","/joinCommand.do","/idChkCommand.do","/logoutCommand.do"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = -2150843596896643342L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request, response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	    
		String requestURI = request.getRequestURI();
	    String contextPath = request.getContextPath();
	    String com = requestURI.substring(contextPath.length());
	    
	    CommandForward forward = null;
	    Command command = null;
	    
	    // 메인화면
	    if(com.equals("/main.do")) {
	    	forward = new CommandForward();
	    	forward.setRedirect(false);
	    	forward.setNextPath("loginForm.jsp");
	    }
	    // 회원가입 화면
	    else if(com.equals("/join.do")) {
	    	forward = new CommandForward();
	    	forward.setRedirect(false);
	    	forward.setNextPath("joinForm.jsp");
	    }
	    // 로그인 처리
	    else if(com.equals("/loginCommand.do")) {
	    	command = new LoginCommand();
	    	forward = command.execute(request, response);
	    }
	    // 회원가입 처리
	    else if(com.equals("/joinCommand.do")) {
	    	command = new JoinCommand();
	    	forward = command.execute(request, response);
	    }
	    // 아이디 중복 확인 처리
	    else if(com.equals("/idChkCommand.do")) {
	    	command = new IdCheckCommand();
	    	forward = command.execute(request, response);
	    }
	    // 로그아웃 처리
	    else if(com.equals("/logoutCommand.do")) {
	    	command = new LogoutCommand();
	    	forward = command.execute(request, response);
	    }
	    
	    if(forward != null) {
	    	if(forward.isRedirect()) {
	    		response.sendRedirect(forward.getNextPath());
	    	} else {
	    		RequestDispatcher dis = request.getRequestDispatcher(forward.getNextPath());
			    dis.forward(request, response);
	    	}
	    }
	}
}