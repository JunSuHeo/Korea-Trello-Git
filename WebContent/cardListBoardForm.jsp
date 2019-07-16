<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- taglib은 c:forEach를 쓰기 위해 추가한 것임. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>null</title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style type="text/css">

		#logoimg{
            width:130px;
            height:30px;
            margin-left:150%;
         }
	
		.card-list-board-contrainer{
			display: inline-block;
			white-space:nowrap;
			overflow-x:auto;
			overflow-y:hidden;
			height: 100%;
			margin-left: 4px;
			margin-right: 4px;
		}
		
		.card-list-wrapper{
			display: inline-block;
			box-sizing: border-box;
			height: 100%;
		}

		.card-list-container{
			background-color: #dfe1e6;
		    border-radius: 3px;
		    box-sizing: border-box;
		    display: flex;
		    flex-direction: column;
		    max-height: 100%;
		    width: 300px;
		    position: relative;
		    white-space: normal;
		    margin-top: 10px;
		    margin-bottom: 10px;
		    margin-left: 4px;
		    margin-right: 4px;
		    padding-top: 8px;
		    padding-left: 8px;
		    padding-right: 8px;
		}
		
		.card-list-header-container{
			margin-bottom: 5px;
			width: 100%;
			display: flex;
			flex-direction: row-reverse;
		}
		
		.card-list-header-name{
			display: inline-block;
			width: 100%;
			font-weight: bold;
		    font-size: 18px;
		    margin-top: 5px;
		    margin-left: 10px;
		}
		
		.card-list-header-extra{
			background: transparent;
			border-radius: 3px;
			border: none;
			height:30px;
   			width:30px;
   			text-align: center;
		}
		
		.card-list-header-extra:hover{
			background-color: rgba(9,30,66,.13);
		}
		
		.card-list-header-extra:hover .material-icons.delete-icon{
			color: #172b4d;
		}
		
		.material-icons.delete-icon
		{
		 	color: #6b778c;
		 	position: relative;
			top: 2px;
		    right: 2.6px;
		}
		
		.card-list-content-container{
			flex: 1 1 auto;
		    margin-bottom: 0;
		    overflow-y: auto;
		    overflow-x: hidden;
		    padding: 0 4px;
		}
		
		.card-content-container{
			padding: 6px 8px 2px;
			background-color: #fff;
			border-radius: 3px;
			box-shadow: 0 1px 0 rgba(9,30,66,.25);
			cursor: pointer;
			display: block;
			margin-bottom: 8px;
			max-width: 300px;
			min-height: 20px;
			position: relative;
			text-decoration: none;
		}
		
		.card-content-container:hover{
			background-color:rgb(244,245,247);
		}
		
		.card-title{
			clear: both;
		    display: block;
		    margin: 0 0 4px;
		    text-decoration: none;
		    word-wrap: break-word;
		    color: #172b4d;
		}
		
		.add-card-container{
			display: flex;
			flex-direction: row;
			box-sizing: border-box;
			flex: 0 0 auto;
			padding: 8px;
			color: #6b778c;
			cursor: pointer;
			font-size: 14px;
    		line-height: 15px;
    		font-weight: 200;
    		margin-left: -8px;
		    margin-right: -8px;
		}
		
		.add-card-container:hover{
			color: #172b4d;
			background-color: rgba(9,30,66,.13);
		}
		
		.add-card-container:hober .material-icons.add-icon{
			color: #6b778c;
		}
		
		.add-card-text{
			display: inline-block;
			margin-top: 5.7px;
			margin-left: 1ps;
		}
		
		.add-card-list-container{
			background-color: rgba(0,0,0,.12);
			display: flex;
			border-radius: 3px;
			flex-direction: row;
			box-sizing: border-box;
			flex: 0 0 auto;
			padding: 6px;
			cursor: pointer;
    		font-weight: 200;
    		width: 300px;
    		margin-left: 4px;
    		margin-right: 4px;
    		margin-top: 10px;
		}
		
		.add-card-list-container:hover{
			background-color: rgba(9,30,66,.25);
		}
		
		.material-icons.add-icon.add-card-list{
			color: hsla(0,0%,100%,.8);
			margin-left: 10px;
			margin-top: 5px;
		}
		
		.add-card-list-text{
			display: inline-block;
			font-weight: bold;
		    font-size: 18px;
		    margin-top: 4px;
		    margin-left: 5px;
		    color: hsla(0,0%,100%,.8);
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
        
         .modal-content.card {
            background-color: #fefefe;
            margin: 10% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 700px; /* Could be more or less, depending on screen size */  
            height: 550px;
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
    function reload()
    {
    	window.location.reload();
    }
    
    function deleteCardList(cardListNum)
    {
    	var form = document.submitForm;
    	var boardnum = '<c:out value="${param.boardnum}"/>';
    	
    	form.boardnum.value = boardnum;
    	form.things.value = cardListNum;
    	form.action = "deleteCardListCommand.do";
    	form.submit();
    }
    
    function checkCardListValue()
    {
    	var title = document.addCardListForm.newCardListTitle;
    	
    	if(!title.value)
    	{
    		alert("카드 리스트의 이름을 입력해주세요!");
    		title.focus();
    		return false;
    	}
    }
    
    function checkCardValue()
    {
    	// TODO: 카트 내용 수정할때 확인하는 코드
    }
    
    function addCardList()
    {
    	// Get the modal
    	var modal = document.getElementById('addCardListModal');
    	modal.style.display = "block";
    }
    
    function addCard(cardListNum)
    {
    	var modal = document.getElementById('cardModal');
    	modal.style.display = "block";
    	
    	var form = document.submitCardForm;
    	
    	form.cardlistnum.value = cardListNum;
    	form.action = "addCard.do";
    	form.target = "cardIframe";
    	form.submit();
    }
    
    function showCard(cardNum, cardListNum)
    {
    	var modal = document.getElementById('cardModal');
    	modal.style.display = "block";
    	
    	var form = document.submitCardForm;
    	
    	form.cardlistnum.value = cardListNum;
    	form.cardnum.value = cardNum;
    	form.action = "card.do";
    	form.target = "cardIframe";
    	form.submit();
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
    	var modal = document.getElementById('addCardListModal');
        if (event.target == modal) {
            modal.style.display = "none";
        }
        
        var modal = document.getElementById('cardModal');
        if (event.target == modal) {
            modal.style.display = "none";
            reload();
        }
    }

    </script>

</head>
<body style="background-color: rgb(0, 121, 191);">

	<!-- 뭔가를 submit하려고 만든 폼 -->
	<form name="submitForm" style="display:none" method="get">
		<input type="hidden" name="boardnum"/>
		<input type="hidden" name="things"/>
	</form>
	
	<!-- 카드 추가 및 보여주기 submit하기 위한 폼 -->
	<form name="submitCardForm" style="display:none" method="get">
		<input type="hidden" name="cardlistnum"/>
		<input type="hidden" name="cardnum"/>
	</form>

	<!-- The 카드 리스트 추가 Modal -->
	<div id ="addCardListModal" class="modal">
		
		<div class="modal-content">
			<form name="addCardListForm" action="addCardListCommand.do?boardnum=${param.boardnum}" method="post" onsubmit="return checkCardListValue()">
				<h2>카드 리스트 추가</h2>
				카드 리스트 이름<br>
				<input type="text" name="newCardListTitle"/>
				<input class="btnSubmit" type="submit" value="추가"/>
			</form>
		</div>
	</div>
	
	<!-- The 카드 관련 Modal -->
	<div id ="cardModal" class="modal">
		<div class="modal-content card">
			<iframe name="cardIframe" frameborder=0 framespacing=0 marginheight=0 marginwidth=0 vspace=0 style="display: block; height: 100%">
			</iframe>
		</div>
	</div>

	<div class="card-list-board-contrainer">
		<c:forEach items="${cardListArray}" var="cardlistDTO">
			<div class="card-list-wrapper">
				<div class="card-list-container">
				
					<!-- card list header -->
			  		<div class="card-list-header-container">
				  		<button class="card-list-header-extra" onclick="deleteCardList(${cardlistDTO.num})"> <i class="material-icons delete-icon">delete</i> </button>
			  			<h2 class="card-list-header-name">${cardlistDTO.title}</h2>
			 		</div>
			 		
			 		<!-- card list content(card) -->
			 		<c:forEach items="${cardlistDTO.cards}" var="cardDTO">
				 		<div class="card-list-content-container" onclick="showCard(${cardDTO.num}, ${cardDTO.listnum})">
				 			<div class="card-content-container">
				 				<span class="card-title">${cardDTO.title}</span>
				 			</div>
			 			</div>
		 			</c:forEach>
		 			
		 			<!-- add card -->
		 			<div class="add-card-container" onclick="addCard(${cardlistDTO.num})">
						<i class="material-icons add-icon">add</i>
						<span class="add-card-text">카드 추가하기</span>
					</div>
		 		</div>
		 	</div>
		</c:forEach>
		
		<div class="card-list-wrapper">
			<div class="add-card-list-container" id="addCardListBtn" onclick="addCardList()">
				<i class="material-icons add-icon add-card-list">add</i>
				<span class="add-card-list-text">카드 리스트 추가하기</span>
			</div>
		</div>
	</div>

</body>
</html>