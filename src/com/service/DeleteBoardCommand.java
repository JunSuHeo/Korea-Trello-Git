package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;

public class DeleteBoardCommand implements Command{
   
   @Override
   public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
      
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      
      int boardnum=Integer.parseInt(request.getParameter("boardnum"));

      BoardDAO.getInstance().delete(boardnum);
      
      CommandForward forward=new CommandForward();
      forward.setRedirect(true);
      forward.setNextPath("mainlist.do");
      return forward;
   }
}