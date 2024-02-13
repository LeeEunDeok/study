<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
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
					<td>
						<c:choose>
							<c:when test="${bean.gender == 'male'}">
								남자
							</c:when>
							<c:when test="${bean.gender == 'female'}">
								여자
							</c:when>
							<c:otherwise>
								
							</c:otherwise>							
						</c:choose>						
					</td>
					<td>${bean.birth}</td>
					<td>
						<c:choose>
							<c:when test="${bean.marriage == 'single'}">
								미혼
							</c:when>
							<c:when test="${bean.marriage == 'marriage'}">
								결혼
							</c:when>
							<c:when test="${bean.marriage == 'divorce'}">
								이혼
							</c:when>							
						</c:choose>							
					</td>
					<td> 
						<c:set var="newHobby" value="${bean.hobby}"/>
						<c:forEach var="one" items="${hobbyList}" varStatus="status">
							<c:if test="${fn:contains(newHobby, one.engname)}">
								<c:set var="newHobby" value="${fn:replace(newHobby, one.engname, one.korname)}"/>
							</c:if>
						</c:forEach>						
						${newHobby}						
					</td>					
					<td>${bean.address}</td>
					<td>${bean.mpoint}</td>
				</tr>  				
				</c:forEach>
			</tbody>
		</table>
		${requestScope.paging.pagingHtml}
	</div>
</body>
</html>