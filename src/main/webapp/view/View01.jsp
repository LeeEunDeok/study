<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>	   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<h2>회원과 게시물</h2>
			</div>
			<div class="col-sm-2"></div>			
		</div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>이름</th>
							<th>글제목</th>
							<th>글내용</th>
							<th>작성 일자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bean" items="${requestScope.dataList}">
						<tr>
							<td align="center">${bean.name}</td>
							<td align="center">${bean.subject}</td>
							<td align="left">${bean.contents}</td>
							<td align="center">${bean.regdate}</td>					
						</tr>
						</c:forEach>				
					</tbody>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>	
	</div>
</body>
</html>