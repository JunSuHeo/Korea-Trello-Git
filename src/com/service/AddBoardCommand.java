package com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.dao.CardDAO;
import com.dto.BoardDTO;
import com.dto.CardDTO;

public class AddBoardCommand implements Command {

   @Override
   public CommandForward execute(HttpServletRequest request, HttpServletResponse response) {
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html;charset=UTF-8");
         
         String boardtitle=request.getParameter("board_name");
         HttpSession session = request.getSession();
         String userid = (String)session.getAttribute("sessionId");         
         String[] users = new String[] {userid};

         BoardDAO.getInstance().insert(boardtitle,users,userid);
         
         CommandForward forward=new CommandForward();
         forward.setRedirect(true);
         forward.setNextPath("mainlist.do");
         return forward;
   }

}