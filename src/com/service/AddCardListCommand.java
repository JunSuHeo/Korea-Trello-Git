package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CardListDAO;

public class AddCardListCommand implements Command {

	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		String cardListTitle = request.getParameter("newCardListTitle");
		
		CardListDAO.getInstance().insert(cardListTitle, boardnum);
		
		CommandForward forward = new CommandForward();
		forward.setRedirect(true);
		forward.setNextPath("cardListBoard.do?boardnum="+boardnum);
		return forward;
	}

}
