package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CardDAO;

public class CardModifyCommand implements Command{
	
	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String cardtitle=request.getParameter("title");
		String carddescription=request.getParameter("description");
		int cardnum=Integer.parseInt(request.getParameter("cardnum"));
		int cardlistnum=Integer.parseInt(request.getParameter("cardlistnum"));
		
		CardDAO.getInstance().update(cardnum, cardtitle,carddescription);
		
		CommandForward forward=new CommandForward();
		forward.setRedirect(true);
		forward.setNextPath("card.do?cardlistnum="+cardlistnum+"&cardnum="+cardnum);
		return forward;
	}
}