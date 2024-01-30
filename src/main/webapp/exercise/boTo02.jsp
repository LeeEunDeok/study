<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.shopping.model.bean.Board" %>
<%
	Board bean = new Board();
%>
<%
	request.setCharacterEncoding("UTF-8");
	
	int no = Integer.parseInt(request.getParameter("no"));
	String subject = request.getParameter("subject");
	String contents = request.getParameter("contents");
	int readhit = Integer.parseInt(request.getParameter("readhit"));
	
	bean.setNo(no);
	bean.setSubject(subject);
	bean.setContents(contents);
	bean.setReadhit(readhit);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		글번호 : <%=bean.getNo() %><br>
		글제목 : <%=bean.getSubject() %><br>
		글내용 : <%=bean.getContents() %><br>
		조회수 : <%=bean.getReadhit() %><br>
	</body>
</html>