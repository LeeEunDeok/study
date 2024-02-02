<%@page import="com.shopping.model.bean.Member"%>
<%@page import="com.shopping.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>

<%
	// 스크립트릿 : 자바코딩하는곳!!
	MemberDao dao = new MemberDao();
	Member bean = new Member();
	
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String gender = request.getParameter("gender");
	String birth = request.getParameter("birth");
	String marriage = request.getParameter("marriage");
	String address = request.getParameter("address");
	
	String hobby = "";
	String[] hobbies = request.getParameterValues("hobby");
	if(hobbies == null){
		hobby = null; // DB에 null값으로 채우기
	} else {
		for(int i=0; i<hobbies.length; i++){
			hobby += hobbies[i] + ", ";
		}
	}
	bean.setHobby(hobby);
	
	bean.setId(id);
	bean.setName(name);
	bean.setPassword(password);
	bean.setGender(gender);
	bean.setBirth(birth);
	bean.setMarriage(marriage);
	bean.setAddress(address);
	
	
	int cnt = -1;
	cnt = dao.insertData(bean);
	
	String message = "";
	if(cnt == 1){
		message = "인서트 성공";
	}else{
		message = "인서트 실패";
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