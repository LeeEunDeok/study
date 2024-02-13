<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>

<%-- JSTL tag library section --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- whologin 변수는 현재 로그인의 상태를 알려주는 변수 입니다. --%>
<%-- 미로그인(0), 일반 사용자(1), 관리자(2) --%>
<c:set var="whologin" value="0" />

<%-- 아이디가 'admin' 이면 '관리자' 입니다.--%>
<%-- logInfo 속성을 사용하여 현재 로그인 상태를 확인할 수 있습니다. --%>
<c:if test="${not empty sessionScope.logInfo}">
	<c:if test="${sessionScope.logInfo.id =='admin'}">
		<c:set var="whologin" value="2" />
	</c:if>
	<c:if test="${sessionScope.logInfo.id !='admin'}">
		<c:set var="whologin" value="1" />
	</c:if>
</c:if>

<%
// appName : 애플리케이션 컨텍스트 이름(프로젝트 이름)
String appName = request.getContextPath();
String mappingName = "/Shopping"; //in FrontController.java file

// 폼 태그에서 사용할 전역 변수
String withFormTag = appName + mappingName;

String notWithFormTag = withFormTag + "?command=";

//out.print("contetxt이름 : " + appName + "<br>");
//out.print("mappingName : " + mappingName + "<br>");
//out.print("withFormTag : " + withFormTag + "<br>");
//out.print("notWithFormTag : " + notWithFormTag + "<br>");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery section -->
<!-- Bootstrap 은 JQuery 를 기반으로 구동이 되므로 반드시 JQuery 선언이 먼저 되어야 합니다. -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<!-- bootstrap section -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
.alert-dismissible {
	margin: 10px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=notWithFormTag%>">쇼핑몰</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><c:if test="${whologin eq 0}">
							<a class="nav-link" href="#">미로그인</a>
						</c:if> <c:if test="${whologin ne 0}">
							<a class="nav-link" href="#">${sessionScope.logInfo.name}님</a>
						</c:if></li>

					<!-- member section  -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="<%=notWithFormTag%>"
						role="button" data-bs-toggle="dropdown">회원</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin eq 0 }">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meInsert">회원가입</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogin">로그인</a></li>
							</c:if>
							<c:if test="${whologin ne 0 }">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meUpdate&id=${sessionScope.logInfo.id}">정보 수정</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meDetail&id=${sessionScope.logInfo.id}">상세 보기</a></li>
							</c:if>
							<c:if test="${whologin eq 1 }">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meDelete&id=${sessionScope.logInfo.id}">탈퇴하기</a></li>
							</c:if>
							<c:if test="${whologin eq 2 }">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meList">목록 보기</a></li>
							</c:if>
						</ul>
					</li>
					<!-- board section  -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="<%=notWithFormTag%>"
						role="button" data-bs-toggle="dropdown">게시물</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin ne 0 }">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>boInsert">게시물 등록</a></li>
							</c:if>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>boList">목록보기</a></li>
						</ul>
					</li>
					<!-- product section  -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="<%=notWithFormTag%>" role="button" 
						data-bs-toggle="dropdown">상품</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin eq 2 }">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>prInsert">상품등록</a></li>
							</c:if>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>prList">목록보기</a></li>
						</ul>
					</li>
					<!-- mall section  -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button" 
						data-bs-toggle="dropdown">쇼핑몰</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>maList">카트내역 보기</a></li>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>maHistory">나의쇼핑 내역</a></li>
						</ul>
					</li>
					<!-- view section  -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="<%=notWithFormTag%>" role="button" 
						data-bs-toggle="dropdown">데이터 보기</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>bwList">목록 보기</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- 사용자에게 주의/경고/오류 등을 알려주기 위한 Alert Box  -->
	<c:if test="${not empty sessionScope.alertMessage }">
		<div class="alert alert-danger alert-dismissible">
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			<strong>경고 메시지 : </strong> ${sessionScope.alertMessage}
		</div>
	</c:if>

	<%-- 보여준 Alert Box 의 내용을 session 영역에서 제거합니다.  --%>
	<%-- session.removeAttribute("alertMessage");  자바방식--%>
	<c:remove var="alertMessage" scope="session" />

</body>
</html>