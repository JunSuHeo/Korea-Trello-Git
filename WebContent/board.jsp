<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<style type="text/css">

		#logoimg{
            width:130px;
            height:30px;
            margin-left:150%;
         }
         
        .splitedFrame{
			display: inline-block;
			width:50%;
			height:100%;
			box-sizing: border-box;
        }
        
        .left{
        	float: left;
        }
        
        .right{
        	float: right;
        }
        
        .table-content{
        	display:flex;
        	flex-direction: column;
        }
        
        html, body { height:100%; overflow:hidden }
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
function checkInviteValue()
{
	var memberName = document.inviteForm.memberName;
	
	if(!memberName.value)
		{
		alert("�ʴ��� ���� ���̵� �Է����ּ���!");
		memberName.focus();
		return false;
		}
	}
	
function popupAddMember()
{
	var modal = document.getElementById('inviteModal');
	modal.style.display = "block";
}

window.onclick = function(event) {
var modal = document.getElementById('inviteModal');
if (event.target == modal) {
    modal.style.display = "none";
    reload();
}}
</script>



<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ���</title>
</head>
<body class="table-content">

<!-- �ʴ���� Modal -->
	<div id = "inviteModal" class="modal">
		<div class="modal-content">
			<form name="inviteForm" action="inviteCommand.do?boardnum=${param.boardnum}" method="post" onsubmit="return checkInviteValue()">
				<h2>���� �ʴ��ϱ�</h2>
				�ʴ��� ���� ���̵�<br>
				<input type="text" name="memberName"/>
				<input class="btnSubmit" type="submit" value="�ʴ��ϱ�"/>
			</form>
		</div>
	</div>
		<header class="navbar navbar-expand-sm navbar-dark" style="background-color: #026aa7; height:50px; display:flex;">
          <div class="collapse navbar-collapse">
             <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="mainlist.do">Ȩ</a></li>
                <li class="nav-item"><a class="nav-link" href="mainlist.do">�Խ��Ǹ��</a></li>
            </ul>
          </div>
          
          <div class="collapse navbar-collapse" style="margin:auto;">
             <a href="mainlist.do"><img id="logoimg" src="trello1.png"></a>
          </div>
          
          <div class="collapse navbar-collapse">
            <ul style="margin-left:70%;" class="navbar-nav" >
               <li class="nav-item"><a class="nav-link" onclick="popupAddMember()" class="nav-link">�ʴ��ϱ�</a></li>
               <li class="nav-item"><a class="nav-link" href="logoutCommand.do">�α׾ƿ�</a></li>
            </ul>
         </div>
      </header>

	<div style="width:100%;height:calc(100vh - 50px);">
		<iframe class="splitedFrame left" src="cardListBoard.do?boardnum=${param.boardnum}"
			scrolling=auto framespacing=0 frameborder=0 marginheight=0 marginwidth=0 vspace=0></iframe>
		<iframe class="splitedFrame right" src="gitCommand.do?boardnum=${param.boardnum}"
			scrolling=auto framespacing=0 frameborder=0 marginheight=0 marginwidth=0 vspace=0></iframe>
	</div>
	
		<%
       	// ���� ������ ���  Alert�� ǥ��
		Object failed = request.getAttribute("invite failed");
		if(failed != null && ((Boolean)failed))
		{
     		out.println("<script type='text/javascript'>");
     		out.println("alert('�ش��ϴ� ������ �����ϴ�!');");
     		out.println("</script>");
     		out.flush();
		}
	%>
</body>
</html>