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
			.container{
				margin: 10px;
			}
		</style>
	</head>
	<body>
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<div class="container mt-3">
				<h2>${sessionScope.logInfo.name}님의 정보</h2>
				<table class="table table-hover">
					<thead></thead>
					<tbody align="center">
						<tr>
							<td>아이디</td>
							<td>${requestScope.bean.id}</td>
						</tr>
						<tr>
							<td>이름</td>
							<td>${requestScope.bean.name}</td>
						</tr>
						<tr>
							<td>성별</td>
							<td>
								<c:choose>
									<c:when test="${bean.gender == 'male'}">남자</c:when>
									<c:when test="${bean.gender == 'female'}">여자</c:when>
									<c:otherwise>미확인</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>생일</td>
							<td>${requestScope.bean.birth}</td>
						</tr>
						<tr>
							<td>결혼 여부</td>
							<td>
								<c:choose>
									<c:when test="${bean.marriage == 'marriage'}">결혼</c:when>
									<c:when test="${bean.marriage == 'single'}">미혼</c:when>
									<c:when test="${bean.marriage == 'divorce'}">이혼</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>취미</td>
							<td> 
		                        <c:set var="newHobby" value="${requestScope.bean.hobby}"/>
		                        <c:forEach var="one" items="${hobbyList}" varStatus="status">
		                            <c:if test="${fn:contains(newHobby, one.engname)}">
		                                <c:set var="newHobby" value="${fn:replace(newHobby, one.engname, one.korname)}"/>
		                            </c:if>
		                        </c:forEach>
		                        ${newHobby}
		                    </td>
						</tr>
						<tr>
							<td>주소</td>
							<td>${requestScope.bean.address}</td>
						</tr>
						<tr>
							<td>적립 포인트</td>
							<td>${requestScope.bean.mpoint}</td>
						</tr>
					</tbody>
				</table>
				
				<div id="backButton" align="center">
					<button type="button" class="btn btn-primary" onclick="history.back();">
						돌아가기
					</button>
				</div>
				
			</div>
		</div>
		<div class="col"></div>
	</div>
</body>
</html>