<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		글번호 : ${param.no }<br>
		글제목 : ${param.subject }<br>
		글내용 : ${param.contents }<br>
		조회수 : ${param.readhit }
	</body>
</html>