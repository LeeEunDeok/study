<%@page import="com.shopping.model.bean.Member"%>
<%@page import="java.util.List"%>
<%@page import="com.shopping.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>

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
		<style type="text/css">
			.rounded-pill{
				opacity: 0.9;
			}
		</style>
	</head>
	<body>
		<div class="container mt-3">
			<h2>회원 목록</h2>
			<p>회원들의 정보를 보여주는 페이지 입니다.</p>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>성별</th>
						<th>취미</th>
						<th>포인트</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bean" items="${dataList }">
						<tr>
							<td>${bean.id }</td>
							<td>${bean.name }</td>
							<td>${bean.gender }</td>
							<td>${bean.hobby }</td>
							<td>
								<c:if test="${bean.mpoint >= 7000 }">
									<span class="badge rounded-pill bg-danger">${bean.mpoint }</span>
								</c:if>
								<c:if test="${bean.mpoint < 7000 }">
									<span class="badge rounded-pill bg-secondary">${bean.mpoint }</span>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>			
			</table>
			
			<ul class="pagination justify-content-center">
				<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item active"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
		</div>
	</body>
</html>