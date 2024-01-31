<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.shopping.model.bean.Member"%>
<%@page import="com.shopping.model.dao.MemberDao"%>

<%@ include file="./../common/common.jsp"%>
<%
	MemberDao dao = new MemberDao();	
	List<Member> dataList = dao.getDataList02();
%>

<c:set var="dataList" value="<%=dataList%>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div class="container mt-3">
		<h2>회원 목록</h2>
		<p>회원 목록을 보여 주는 페이지입니다.</p>		
		<table class="table table-hover">
			<!-- table-hover, table-striped, table-condensed  -->
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>성별</th>
					<th>생일</th>
					<th>결혼 여부</th>
					<th>취미</th>
					<th>주소</th>
					<th>적립 포인트</th>
				</tr>
			</thead>
			<tbody>
  				<c:forEach var="bean" items="${dataList}">
				<tr>
					<td align="center">${bean.id}</td>
					<td>${bean.name}</td>
					<td>${bean.gender}</td>
					<td>${bean.birth}</td>
					<td>${bean.marriage}</td>
					<td>${bean.hobby}</td>
					<td>${bean.address}</td>
					<td>${bean.mpoint}</td>
				</tr>  				
				</c:forEach>
			</tbody>
		</table> 
	</div>
</body>
</html>