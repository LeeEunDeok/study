<%@page import="com.shopping.model.bean.Board"%>
<%@page import="com.shopping.model.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>

<%
	//스크립트릿 : 자바코딩하는곳!!
	BoardDao dao = new BoardDao();
	Board bean = new Board();
	
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String subject = request.getParameter("subject");
	String contents = request.getParameter("contents");
	
	int no = Integer.parseInt(request.getParameter("no"));
	bean.setId(id);
	bean.setPassword(password);
	bean.setSubject(subject);
	bean.setContents(contents);
	bean.setNo(no);
	
	int cnt = -1;
	cnt = dao.updateData(bean);
	String message = "";
	
	if(cnt == 1){
		message = "수정 성공";
	}else{
		message = "수정 실패";
	}
	
	out.print(bean + "<br>");
	out.print(message + "<br>");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript">
			$(document).ready(function(){
			
			});
		</script>
		<style type="text/css">
		
		</style>
	</head>
	<body>
		
	</body>
</html>