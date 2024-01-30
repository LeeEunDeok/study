<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 한글 깨짐 방지를 위한 문자열 인코딩으로 반드시 최상단에 명시해야 합니다.
	request.setCharacterEncoding("UTF-8");

	// 자바 코딩하는 공간으로 스크립트릿(scriptlet)이라고 합니다.
	String name = "김철수";
	int age = 30;
	
	// request 내장 객체에 파라미터 정보가 담겨서 넘어옵니다.
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	int mpoint = Integer.parseInt(request.getParameter("mpoint"));
	
	// 체크 박스 양식은 여러개 선택이 가능하므로 반환 타입이 배열인 getParameterValues() 메소드를 사용합니다.
	String[] hobbies = request.getParameterValues("hobby");
	String hobby = "";
	
	if(hobbies == null){// 아무것도 체크 하지 않음
		hobby = "취미 선택하지 않음.";
	}else{
		for(int i=0; i<hobbies.length; i++){
			hobby += hobbies[i] + " ";
		}
	}
	
	
%>

<%!
	// 선언문 : 인스턴스 변수나 인스턴스 메소드를 작성하는 영역입니다.
	public String sayHello(String name){
		return name + "님~~안녕하세요.";
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<!-- HTML 주석은 '페이지 소스 보기'에서 보입니다. -->
		
		<%-- JSP 주석은 '페이지 소스 보기'에서 보이지 않습니다. --%>
		
		아이디 : <%=id %><br>
		비밀번호 : <%=password %><br>
		엠포인트 : <%=mpoint + 100%><br>
		이름 : <%=name %><br> 
		나이 : <%=age %><br>
		취미 : <%=hobby %><br>
		인사말 : <%=sayHello(name) %><br>
	</body>
</html>