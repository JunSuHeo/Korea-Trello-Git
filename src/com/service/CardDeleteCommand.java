package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CardDAO;

public class CardDeleteCommand implements Command{
	
	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int cardnum=Integer.parseInt(request.getParameter("cardnum"));

		CardDAO.getInstance().delete(cardnum);
		
		CommandForward forward=new CommandForward();
		forward.setRedirect(true);
		forward.setNextPath("cardOutForm.jsp");
		return forward;
	}
}