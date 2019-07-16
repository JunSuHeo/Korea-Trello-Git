package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GitDAO;

public class GitCommand implements Command {

	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CommandForward forward = new CommandForward();
		
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		
		List<String> list = GitDAO.getInstance().readDay(boardnum);
		
		request.setAttribute("list", list);
		forward.setNextPath("git.jsp?boardnum="+boardnum);
		forward.setRedirect(false);
		
		return forward;
	}

}
