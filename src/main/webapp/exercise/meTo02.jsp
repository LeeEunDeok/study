<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.shopping.model.bean.Member" %>
<%
	Member bean = new Member();
%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	int mpoint = Integer.parseInt(request.getParameter("mpoint"));
	
	String[] hobbies = request.getParameterValues("hobby");
	String hobby = "";
	
	if(hobbies == null){
		hobby = "취미 선택하지 않음.";
	}else{
		for(int i=0; i<hobbies.length; i++){
			hobby += hobbies[i] + " ";
		}
	}
	
	bean.setId(id);
	bean.setPassword(password);
	bean.setHobby(hobby);
	bean.setMpoint(mpoint);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		아이디 : <%=bean.getId() %><br>
		비밀번호 : <%=bean.getPassword() %><br>
		취미 : <%=bean.getHobby() %><br>
		m포인트 : <%=bean.getMpoint() %><br>
		객체 : <%=bean %><br>
	</body>
</html>