package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.AddBoardCommand;
import com.service.Command;
import com.service.CommandForward;
import com.service.DeleteBoardCommand;
import com.service.MainListCommand;

@WebServlet({"/mainlist.do","/addBoardCommand.do","/deleteBoardCommand.do"})
public class MainListController extends HttpServlet {
   private static final long serialVersionUID = -1676226202101645752L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doProcess( request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doProcess( request, response);
   }

public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      request.setCharacterEncoding("UTF-8");
       
      String requestURI = request.getRequestURI();
       String contextPath = request.getContextPath();
       String com = requestURI.substring(contextPath.length());
       
       CommandForward forward = null; //다음으로 이동할 주소 정보
       Command command = null; 
       
       //게시판 목록 출력 화면
       if(com.equals("/mainlist.do")) {
          command = new MainListCommand();
          forward = command.execute(request, response);
       }
       //게시판 추가
       else if(com.equals("/addBoardCommand.do")) {
          command = new AddBoardCommand();
          forward = command.execute(request, response);
       }
       
       //게시판 삭제
       else if(com.equals("/deleteBoardCommand.do")) {
          command = new DeleteBoardCommand();
          forward = command.execute(request, response);
       }
       
            
       //redirect가 true면 화면 깜빡, false면 화면 남아있음!
       if(forward != null) {
          if(forward.isRedirect()) {
             response.sendRedirect(forward.getNextPath());
          } else {
             RequestDispatcher dis = request.getRequestDispatcher(forward.getNextPath());
             dis.forward(request, response);
          }
       }
   }
}