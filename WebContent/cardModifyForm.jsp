<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- taglib은 c:forEach를 쓰기 위해 추가한 것임. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>card modify</title>

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

input[type=text]:focus{
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit/register button */
.registerbtn {
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

.registerbtn:hover {
  opacity:1;
}

</style>
</head>

<body>

<form action="modifyCardCommand.do" method="post">
  <div class="container">
    <h1>카드 수정</h1>
    <p>카드를 수정하려면 양식을 고치십시오.</p>
    <hr>

    <label for="title"><b>제목</b></label><br/>
    <input type="text" value="${cardDTO.title}" name="title" required>
    <br/>
    <label for="description"><b>설명</b></label><br/>
    <input type="text" value="${cardDTO.content}" name="description" required>
	<br/>
	<input type="hidden" name="cardlistnum" value="${param.cardlistnum}"/>
	<input type="hidden" name="cardnum" value="${param.cardnum}"/>
	
    <button type="submit" class="registerbtn">수정 완료</button>
  </div>

</form>

</body>
</html>