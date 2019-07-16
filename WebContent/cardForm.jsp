<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- taglib은 c:forEach를 쓰기 위해 추가한 것임. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>card form</title>



<style type="text/css">
* {box-sizing: border-box}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* Full-width input fields */
input[type=text]{
  width: 50%;
  padding: 10px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit/register button */
.registerbtn1 {
  background-color: skyblue;
  color: black;
  padding: 8px 20px;
  margin: 5px 0;
  border: none;
  cursor: pointer;
  width: 130px;
  height:30px;
  opacity: 0.7;
  border-radius: 12px;
}

.registerbtn2 {
  background-color: skyblue;
  color: black;
  padding: 8px 20px;
  margin: 5px 10px;
  border: none;
  cursor: pointer;
  width: 130px;
  height:30px;
  opacity: 0.7;
  border-radius: 12px;
}


.registerbtn1:hover {
  opacity:1;
}

.registerbtn2:hover {
  opacity:1;
}

</style>
</head>

<body>
  <div class="container">
    <h1>카드 정보</h1>
    <p>선택한 카드의 세부 내용 입니다.</p>
    <hr>

    <label for="title"><b>제목</b></label><br/>
    <input type="text" value="${cardDTO.title}" name="title" disabled="disabled">
    <br/>
    <label for="description"><b>설명</b></label><br/>
    <input type="text" value="${cardDTO.content}" name="description" disabled='disabled'>
	<br/>
    <a href="modifyCard.do?cardlistnum=${param.cardlistnum}&cardnum=${param.cardnum}" class="registerbtn1">카드 수정</a>
    <a href="deleteCardCommand.do?cardlistnum=${param.cardlistnum}&cardnum=${param.cardnum}" class="registerbtn2">카드 삭제</a>
  </div>
</body>
</html>