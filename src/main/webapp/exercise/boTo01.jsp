<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	int no = Integer.parseInt(request.getParameter("no"));
	String subject = request.getParameter("subject");
	String contents = request.getParameter("contents");
	int readhit = Integer.parseInt(request.getParameter("readhit"));
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		글번호 : <%=no %><br>
		글제목 : <%=subject %><br>
		글내용 : <%=contents %><br>
		조회수 : <%=readhit %><br>
	</body>
</html>