package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GitDAO;

public class ViewFolderCommand implements Command {

	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CommandForward forward = new CommandForward();
		
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		String name = request.getParameter("name");
		String parent_num = GitDAO.getInstance().getParentNum(name);
		
		List<String> list = GitDAO.getInstance().readParentNum(parent_num);
		request.setAttribute("list", list);
		
		request.setAttribute("name", name);
		
		forward.setNextPath("viewHistory.jsp?boardnum="+boardnum);
		
		return forward;
	}
}