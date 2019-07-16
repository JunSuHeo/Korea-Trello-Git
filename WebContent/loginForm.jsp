<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>로그인 화면</title>
    
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
    <style type="text/css">
    	 #logoimg{
            width:130px;
            height:30px;
            margin-left:190%;
         }
         
		.login-container{
			height: 370px;
		    margin-top: 5%;
		    margin-bottom: 5%;
		    width: 500px;
		}
		.login-form-1{
		    padding: 5%;
		    box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0 rgba(0, 0, 0, 0.19);
		}
		.login-form-1 h3{
		    text-align: center;
		    color: #333;
		}
		
		.login-container form{
		    padding: 10%;
		}
		.btnSubmit
		{
		    width: 50%;
		    border-radius: 1rem;
		    padding: 1.5%;
		    border: none;
		    cursor: pointer;
		}
		.login-form-1 .btnSubmit{
		    font-weight: 600;
		    color: #fff;
		    background-color: #0062cc;
		}
		
		.login-form-1 .btnJoin{
		    color: #0062cc;
		    font-weight: 600;
		    text-decoration: none;
		}
    </style>
    
    <script type="text/javascript">
    
    	function checkValue()
    	{
    		inputForm = eval("document.loginInfo");
    		if(!inputForm.id.value)
    		{
    			alert("아이디를 입력해주세요");
    			inputForm.id.focus();
    			return false;
    		}
    		if(!inputForm.password.value)
    		{
    			alert("비밀번호를 입력해주세요");
    			inputForm.password.focus();
    			return false;
    		}
    	}
    
    </script>
    
</head>

<body>

	<header class="navbar navbar-expand-sm navbar-dark" style="background-color: #026aa7; height:50px; display:flex;">
         <div class="collapse navbar-collapse">
             <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link"></a></li>
                <li class="nav-item"><a class="nav-link"></a></li>
            </ul>
          </div>
          
         <div class="collapse navbar-collapse" style="margin:auto;">
            <a href="main.do"><img id="logoimg" src="trello1.png"></a>
         </div>
         
         <div class="collapse navbar-collapse">
           <ul style="margin-left:86%;" class="navbar-nav" >
              <li class="nav-item"><a class="nav-link" href="main.do">로그인</a></li>
           </ul>
        </div>
     </header>

    <div class="container login-container">
        <div class="login-form-1">
        	<h3 color="gray" size="6">로그인</h3>
        
	        <form name="loginInfo" action="loginCommand.do" method="post" onsubmit="return checkValue()">
	        	<div class="form-group">
	        		<input type="text" name="id" class="form-control" placeholder="아이디"/>
	        	</div>
	        	
	        	<div class="form-group">
	        		<input type="password" name="password" class="form-control" placeholder="비밀번호"/>
	        	</div>
	        	
	        	<div class="form-group">
	        		<input type="submit" class="btnSubmit" value="로그인"/>
	        	</div>
	        	
	            <div class="form-group">
	            	아이디가 없으신가요? <a class="btnLogin" href="join.do">회원가입</a>
	            </div>
	        </form>
        </div>
        
        <%
        	// 아이디나 비밀번호가 틀릴경우 Alert로 표시
			Object failed = request.getAttribute("login failed");
			if(failed != null && ((Boolean)failed))
			{
	     		out.println("<script type='text/javascript'>");
	     		out.println("alert('아이디나 비밀번호가 일치하지 않습니다!');");
	     		out.println("</script>");
	     		out.flush();
			}
		%>
        
    </div>
</body>
</html>