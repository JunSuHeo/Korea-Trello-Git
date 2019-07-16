package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CardDAO;
import com.dto.CardDTO;

public class CardAddCommand implements Command{
	
	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String cardtitle=request.getParameter("title"); 
		String carddescription=request.getParameter("description");
		int cardlistnum=Integer.parseInt(request.getParameter("cardlistnum"));
		
		CardDTO dto = CardDAO.getInstance().insert(cardtitle,carddescription,cardlistnum);
		int cardnum = dto.getNum();
		
		System.out.println(cardnum);
		
		CommandForward forward=new CommandForward();
		forward.setRedirect(true);
		forward.setNextPath("card.do?cardlistnum="+cardlistnum+"&cardnum="+cardnum);
		return forward;
	}
}