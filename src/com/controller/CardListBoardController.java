package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.AddCardListCommand;
import com.service.CardListBoardCommand;
import com.service.Command;
import com.service.CommandForward;
import com.service.DeleteCardListCommand;
import com.service.InviteCommand;

// cardListTitle : 카드리스트 이름
// cardDTO

@WebServlet({"/cardListBoard.do", "/addCardListCommand.do", "/deleteCardListCommand.do", "/inviteCommand.do"})
public class CardListBoardController extends HttpServlet {
	private static final long serialVersionUID = -1455852485594261268L;
	
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
	    
	    // 카드리스트 게시판 화면 (작은거)
	    if(com.equals("/cardListBoard.do")) {
	    	command = new CardListBoardCommand();
	    	forward = command.execute(request, response);
	    }
	    // 카드 리스트 추가 처리
	    else if(com.equals("/addCardListCommand.do")) {
	    	command = new AddCardListCommand();
	    	forward = command.execute(request, response);
	    }
	    // 카드 리스트 삭제 처리
	    else if(com.equals("/deleteCardListCommand.do")) {
	    	command = new DeleteCardListCommand();
	    	forward = command.execute(request, response);
	    }
	    // 초대하기 처리
	    else if(com.equals("/inviteCommand.do")) {
	    	command = new InviteCommand();
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
