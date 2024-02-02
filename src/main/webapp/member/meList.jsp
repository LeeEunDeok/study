<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
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
						<th>생일</th>
						<th>결혼 여부</th>
						<th>취미</th>
						<th>주소</th>
						<th>포인트</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bean" items="${dataList }">
						<tr>
							<td>${bean.id }</td>
							<td>${bean.name }</td>
							<td>
								<c:choose>
									<c:when test="${bean.gender == 'male'}">남자</c:when>
									<c:when test="${bean.gender == 'female'}">여자</c:when>
									<c:otherwise>미확인</c:otherwise>
								</c:choose>
							</td>
							
							<td>
								<c:set var="condition" value="${bean.birth == 'null'}" />
								${condition ? "미확인" : bean.birth}
							</td>
							
							<td>
								<c:choose>
									<c:when test="${bean.marriage == 'marriage'}">결혼</c:when>
									<c:when test="${bean.marriage == 'single'}">미혼</c:when>
									<c:when test="${bean.marriage == 'divorce'}">이혼</c:when>
								</c:choose>
							</td>

							<td> 
		                        <c:set var="newHobby" value="${requestScope.bean.hobby}"/>
		                        <c:forEach var="one" items="${hobbyList}" varStatus="status">
		                            <c:if test="${fn:contains(newHobby, one.engname)}">
		                                <c:set var="newHobby" value="${fn:replace(newHobby, one.engname, one.korname)}"/>
		                            </c:if>
		                        </c:forEach>
		                        ${newHobby}
		                    </td>
													
							<td>${bean.address }</td>
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