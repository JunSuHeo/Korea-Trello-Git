<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="createcode.do?day=${day}&parent_num=${parent_num}&boardnum=${param.boardnum}" method="post">
		内靛力格 : <input type="text" name="code_name" /><br />
		内靛 : <br /><textarea rows=100 cols= 50 name="code">
		</textarea>
		<br />
		<input type="submit" value="积己" />
	</form>
</body>
</html>