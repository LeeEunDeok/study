<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
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
						<th>수정</th>
						<th>답글</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bean" items="${dataList}">
						<tr>
							<td align="center">${bean.no}</td>
							<td>
								<c:if test="${not empty bean.id}">
									${bean.id}
								</c:if>
								<c:if test="${empty bean.id}">
									탈퇴 회원
								</c:if>
							</td>
							<td>
								<%-- 로그인 아이디와 작성자의 아이디가 다르면 true 로 지정합니다. --%>
								<%-- readhitUpdate 파라미터는 조회수 업데이트를 할 것인지 결정해주는 boolean 변수입니다. --%>
								<c:set var="readhitUpdate" value="${not (sessionScope.logInfo.id == bean.id)}" />
								
								<%-- 글 제목을 클릭하면 상세보기 페이지로 이동합니다.(re도 포함됨) --%>
								<a href="<%=notWithFormTag%>boDetail&no=${bean.no}&readhitUpdate=${readhitUpdate}">
									<c:forEach var="i" begin="1" end="${bean.depth}" step="1">
										<span class="badge rounded-pill bg-secondary">re</span>
									</c:forEach>
									${bean.subject}
								</a>
							</td>
							
							<td>${bean.contents}</td>
							<td>
								<c:if test="${bean.readhit >= 25 }">
									<span class="badge rounded-pill bg-primary">
										${bean.readhit}
									</span>
								</c:if>
								
								<c:if test="${bean.readhit < 25 }">
									<span class="badge rounded-pill bg-danger">
										${bean.readhit}
									</span>
								</c:if>
							</td>
							<td>${bean.regdate}</td>
							<td>
								<c:if test="${sessionScope.logInfo.id == bean.id}">
									<a href="<%=notWithFormTag%>boUpdate&no=${bean.no}">수정</a>
								</c:if>
							</td>
							<td>답글</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			${requestScope.paging.pagingHtml}
		</div>
	</body>
</html>