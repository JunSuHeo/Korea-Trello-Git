package com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CardDAO;
import com.dao.CardListDAO;
import com.dto.CardListDTO;

public class CardListBoardCommand implements Command {
	
	@Override
	public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CommandForward forward = new CommandForward();
		
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		
		ArrayList<CardListDTO> list = CardListDAO.getInstance().list(boardnum);
		request.setAttribute("cardListArray", list);
		
    	forward.setRedirect(false);
    	forward.setNextPath("cardListBoardForm.jsp?boardnum="+boardnum);
    	
    	return forward;
	}

}
