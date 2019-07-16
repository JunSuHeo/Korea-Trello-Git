package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;

public class IdCheckCommand implements Command {

	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		CommandForward forward = new CommandForward();

		String id = request.getParameter("id");
		int idCheck = UserDAO.getInstance().idCheck(id);
		
		if(idCheck == 1)
		{
			request.setAttribute("idChk", 0);
		}else
		{
			request.setAttribute("idChk", 1);
		}
		
		forward.setRedirect(false);
		forward.setNextPath("join.do");
		
		return forward;
	}

}
