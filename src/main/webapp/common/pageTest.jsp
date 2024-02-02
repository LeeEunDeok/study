<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>
<%@page import="com.shopping.utility.Paging"%> 
<%
	String _pageNumber = "20" ;
	String _pageSize = "10" ;
	int totalCount = 283 ;
	String url = "boList" ;
	
	String mode = "subject" ;
	String keyword = "jsp" ;
	
	boolean isGrid = false ;
	
	Paging pageInfo = new Paging(_pageNumber, _pageSize, totalCount, url, mode, keyword, isGrid);	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=pageInfo.toString()%>
</body>
</html>