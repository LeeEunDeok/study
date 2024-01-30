<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록 페이지 01</title>
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
				<tr>
					<td align="center">1</td>
					<td>김철수</td>
					<td>자바 공부</td>
					<td>재밌어요</td>
					<td><span class="badge bg-primary">11</span></td>
					<td>2024/01/25</td>
				</tr>
				<tr>
					<td align="center">2</td>
					<td>박영희</td>
					<td>파이썬</td>
					<td>판다스 입문</td>
					<td><span class="badge bg-primary">22</span></td>
					<td>2024/01/25</td>
				</tr>
			</tbody>
		</table>
		<ul class="pagination justify-content-center">
		
			<li class="page-item"><a class="page-link"
				href="javascript:void(0);">Previous</a></li>
				
			<li class="page-item"><a class="page-link"
				href="javascript:void(0);">1</a></li>
				
			<li class="page-item active"><a class="page-link"
				href="javascript:void(0);">2</a></li>
				
			<li class="page-item"><a class="page-link"
				href="javascript:void(0);">3</a></li>
				
			<li class="page-item disabled"><a class="page-link"
				href="javascript:void(0);">4</a></li>
				
			<li class="page-item"><a class="page-link"
				href="javascript:void(0);">5</a></li>
				
			<li class="page-item"><a class="page-link"
				href="javascript:void(0);">Next</a></li>
		</ul>
	</div>
</body>
</html>