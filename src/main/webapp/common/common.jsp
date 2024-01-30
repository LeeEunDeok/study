<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>

<%-- JSTL tag library section --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	// appName : 애플리케이션 컨텍스트 이름(프로젝트 이름)
	String appName = request.getContextPath();
	String mappingName = "/Shopping"; // in FrontController.java
	
	// 폼 태그에서 사용할 전역 변수
	String withFormTag = appName + mappingName;
	// 폼 태그가 아닌 곳에서 사용할 전역 변수
	String notWithFormTag = withFormTag + "?command=";
	
	//out.print("컨텍스트 이름 : " + appName + "<br>");
	//out.print("mappingName : " + mappingName + "<br>");
	//out.print("withFormTag : " + withFormTag + "<br>");
	//out.print("notWithFormTag : " + notWithFormTag + "<br>");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<%-- JQuery section --%>
		<%-- Bootstrap 은 JQuery 를 기반으로 구동이 되므로 반드시 JQuery 선언이 먼저 되어야 합니다. --%>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
		
		
		<%-- Bootstrap section --%>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		
	</body>
</html>