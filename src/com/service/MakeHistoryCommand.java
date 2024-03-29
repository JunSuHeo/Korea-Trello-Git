package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;

public class MakeHistoryCommand implements Command{
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		CommandForward forward = new CommandForward();
		
		forward.setNextPath("historyForm.jsp?boardnum="+boardnum);
		
		return forward;
	}
}
