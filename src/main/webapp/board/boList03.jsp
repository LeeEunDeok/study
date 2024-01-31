<%@page import="com.shopping.model.bean.Board"%>
<%@page import="java.util.List"%>
<%@page import="com.shopping.model.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>

<%
BoardDao dao = new BoardDao();
List<Board> dataList = dao.getDataList();
%>

<c:set var="dataList" value="<%=dataList%>" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시물 목록 페이지 02</title>
		<style type="text/css">
			.rounded-pill{
				opacity: 0.77;
			}
		</style>
	</head>
	<body>
		<div class="container mt-3">
			<h2>게시물 목록</h2>
			<p>사용자들이 작성한 게시물 목록을 보여주는 페이지입니다.</p>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>글번호</th>
						<th>작성자</th>
						<th>글제목</th>
						<th>글내용</th>
						<th>조회수</th>
						<th>작성일자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bean" items="${dataList }">
						<tr>
							<td align="center">${bean.no }</td>
							<td>${bean.id }</td>
							<td>
								${bean.subject }
								<c:if test="${bean.depth != 0 }">
									<span class="badge rounded-pill bg-success">
										${bean.depth }
									</span>
								</c:if>
							</td>
							<td>${bean.contents }</td>
							<td>
								<c:if test="${bean.readhit >= 20 }">
									<span class="badge rounded-pill bg-danger">
										${bean.readhit }
									</span>
								</c:if>
								
								<c:if test="${bean.readhit < 20 }">
									<span class="badge rounded-pill bg-primary">
										${bean.readhit }
									</span>
								</c:if>
							</td>
							<td>${bean.regdate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>