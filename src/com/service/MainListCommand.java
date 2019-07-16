package com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.dto.BoardDTO;

public class MainListCommand implements Command {

   @Override
   public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      
      CommandForward forward = new CommandForward();
      
      HttpSession session = request.getSession();
      
      String userId = (String)session.getAttribute("sessionId");
      
      ArrayList<BoardDTO> list = BoardDAO.getInstance().list(userId);
      
      request.setAttribute("boardDTO", list);
      
      forward.setRedirect(false);
      forward.setNextPath("mainlist.jsp");
      
      return forward;
   }

}