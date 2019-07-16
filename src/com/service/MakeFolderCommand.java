package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GitDAO;

public class MakeFolderCommand implements Command{
public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CommandForward forward = new CommandForward();
		
		String day = request.getParameter("day");
		String parent_num = request.getParameter("parent_num");
		String num = "0";
		String folder_name = request.getParameter("folder_name");
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		
		List<String> list = GitDAO.getInstance().readDay(day, parent_num,boardnum);
		
		request.setAttribute("list", list);
		request.setAttribute("day", day);
		request.setAttribute("parent_num", parent_num);
		
		GitDAO.getInstance().make_folder(day, parent_num, folder_name, boardnum);
		
		forward.setNextPath("viewhistory.do?boardnum="+boardnum);
		
		return forward;
	}
}