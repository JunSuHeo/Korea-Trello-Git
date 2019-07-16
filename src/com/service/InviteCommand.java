package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.CardListDAO;
import com.dao.UserDAO;

public class InviteCommand implements Command {

	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		String memberId = request.getParameter("memberName");
		
		int flag = UserDAO.getInstance().idCheck(memberId);
		
		if(flag != 1) {
			request.setAttribute("invite failed", true);
		}else {
			BoardDAO.getInstance().inviteUser(boardnum, memberId);
		}
		
		
		CommandForward forward = new CommandForward();
		forward.setRedirect(true);
		forward.setNextPath("board.do?boardnum="+boardnum);
		return forward;
	}

}
