<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>메인 화면</title>
      <script src="http://code.jquery.com/jquery-latest.min.js"></script>
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
      <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <link rel="stylesheet" href="css/bootstrap.css">
      
      <style>
         #logoimg{
            width:130px;
            height:30px;
            margin-left:150%;
         }
         body{
            padding-top:0px;
            padding-bottom:30px;
         }
         header{
            background-color: #026aa7;
         }
         h3{
            margin:30px;
         }
         
         .boardbutton{
         	display:inline-block;
            width:200px;
            height:130px;
            margin:10px;
            border-radius: 3px;
            margin-bottom:50px;
            background-color: rgb(0, 121, 191);;
            border:0;
            outline:0;
            color:white;
            cursor: pointer;
            padding: 10px;
         }
         
         .boardbutton:hover {
         	background-color: rgb(0, 91, 143);
         }
         
         .boardtitle{
         	font-weight: bold;
         	display: inline-block;
         	font-size: 24px;
         	margin : 5px;
         	margin-right: 10px;
           	white-space:nowrap;
           	width:100%;
			overflow:hidden;
			text-overflow:ellipsis;
         }
         
         .boardtitle-extra{
			background: transparent;
			border-radius: 3px;
			border: none;
			height:30px;
   			width:30px;
    		margin-left: 83%;
    		margin-top: 20%;
    		overflow: hidden;
    		position: relative;
   			text-align: center;
		}
		
         
         .boardtitle-extra:hover .material-icons.delete-icon{
			 color: gray;
		 }
		
		 .material-icons.delete-icon
		 {
		  	 color: white;
		  	 position: relative;
			 top: 2px;
		     right: 2.6px;
		 }
      
         /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
    
        /* Modal Content/Box */
         .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 350px; /* Could be more or less, depending on screen size */                          
        }
           
  		.btnSubmit
		{
		    width: 50%;
		    border-radius: 3;
		    margin-top: 20px;
		   	margin-right: auto;
		   	margin-left: auto;
		    padding: 1.5%;
		    border: none;
		    cursor: pointer;
		}
         
      </style>
      
      <script>
      var isDeleteBtnHover = false;
      
      	function showBoard(num)
      	{
      		if(isDeleteBtnHover)
      			return;
      		
      		var form = document.getElementById('submitForm');
     		form.boardnum.value = num;
     		form.action = "board.do"; 
     		form.method = "get";
     		form.submit();
      	}
      
      	 function deleteBoard(num)
      	 {
      		 var form = document.getElementById('submitForm');
      		 form.boardnum.value = num;
      		 form.action = "deleteBoardCommand.do";
      		 form.submit();
      	 }
      
         function popupAddBoard()
         {
             var modal = document.getElementById('modal');
             modal.style.display = "block";
         }
      
      
         // When the user clicks anywhere outside of the modal, close it
          window.onclick = function(event) {
             var modal = document.getElementById('modal');
              if (event.target == modal) {
                  modal.style.display = "none";
              }
         }
                  
          $( document ).ready( function() {
	         $('.boardtitle-extra').hover(function() {
	       	  isDeleteBtnHover = true;
	       	}, function(){
	       	  isDeleteBtnHover = false;
	       	});
          });
      </script>
   </head>
   
   <%
		String sessionId = (String)session.getAttribute("sessionId");
   		pageContext.setAttribute("sessionId", sessionId);
   %>

   <body>
   	<form id="submitForm" action="" method="post">
   		<input name="boardnum" type="hidden"/>
   	</form>
   
      <div id ="modal" class="modal">
         <div class="modal-content">
            <form name="addBoardForm" action="addBoardCommand.do" method="post">
               <h2>게시판 추가</h2>
                  	게시판 이름<br>
               <input type="text" name="board_name"/>
               <input class="btnSubmit" type="submit" value="추가"/>
            </form>
         </div>
      </div>
   
      <header class="navbar navbar-expand-sm navbar-dark" style="height:50px; display:flex;">
          <div class="collapse navbar-collapse">
             <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="mainlist.do">홈</a></li>
                <li class="nav-item"><a class="nav-link" href="mainlist.do">게시판목록</a></li>
            </ul>
          </div>
          
          <div class="collapse navbar-collapse" style="margin:auto;">
             <a href="mainlist.do"><img id="logoimg" src="trello1.png"></a>
          </div>
          
          <div class="collapse navbar-collapse">
            <ul style="margin-left:70%;" class="navbar-nav" >
               <li class="nav-item"><a class="nav-link" onclick="popupAddBoard()" class="nav-link">게시판추가</a></li>
               <li class="nav-item"><a class="nav-link" href="logoutCommand.do">로그아웃</a></li>
            </ul>
         </div>
      </header>
      
   
      <div class="container">
         <h3 color="gray" style="font-weight:bold">게시판 목록</h3>
         
         <!-- 게시판 목록 -->
         <c:forEach items="${boardDTO}" var="dto">
         
         	<div onclick="showBoard(${dto.num})" class="boardbutton">
               	<span class="boardtitle">${dto.boardName}</span>
               	<c:if test="${dto.adminId eq sessionId}">
               		<button class="boardtitle-extra" type="button" onclick="deleteBoard(${dto.num})"> <i class="material-icons delete-icon">delete</i> </button>
	            </c:if>
	            <c:if test="${dto.adminId ne sessionId}">
               		<button class="boardtitle-extra" type="button" style="visibility:hidden"> <i class="material-icons delete-icon">delete</i> </button>
	            </c:if>
            </div>
            
         </c:forEach>
         
      </div>
      
      <script src="js/jquery-3.1.1.js"></script>
      <script src="js/bootsrtap.js"></script>
   </body>
</html>