<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form action="<%=withFormTag%>" method="post">
		<input type="text" name="command" value="hello">
		<input type="submit" value="전송">
	</form>
	<hr>
	<a href="<%=notWithFormTag%>world">get 방식</a>
</body>
</html>