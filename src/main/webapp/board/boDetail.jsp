<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	.rounded-pill {
		opacity: 0.9;
	}
	
	.container {
		margin: 10px;
	}
	</style>
</head>
<body>
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<div class="container mt-3">
				<h2>[${requestScope.bean.no}]번 게시물 정보</h2>
				<table class="table table-hover">
					<thead></thead>
					<tbody align="center">
						<tr>
							<td>글번호</td>
							<td>${requestScope.bean.no}</td>
						</tr>
						
						<tr>
							<td>작성자</td>
							<td>
								<c:if test="${not empty requestScope.bean.id}">
									${requestScope.bean.id}
								</c:if>
								<c:if test="${empty requestScope.bean.id}">
									탈퇴 회원
								</c:if>
							</td>
						</tr>
						
						<tr>
							<td>글제목</td>
							<td>${requestScope.bean.subject}</td>
						</tr>
						
						<tr>
							<td>글내용</td>
							<td>${requestScope.bean.contents}</td>
						</tr>
						
						<tr>
							<td>조회수</td>
							<td>${requestScope.bean.readhit}</td>
						</tr>
						
						<tr>
							<td>작성일자</td>
							<td>${requestScope.bean.regdate}</td>
						</tr>
					</tbody>
				</table>

				<div id="backButton" align="center">
					<button type="button" class="btn btn-primary"
						onclick="location.href='<%=notWithFormTag%>boList${requestScope.paramList}'">돌아가기</button>
				</div>

			</div>
		</div>
		<div class="col"></div>
	</div>
</body>
</html>