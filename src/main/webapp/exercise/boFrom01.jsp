<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>연습 Post방식</title>
	</head>
	<body>
		<form action="boTo01.jsp" method="post">
			글번호 : <input type="text" name="no" value="1"><br>
			글제목 : <input type="text" name="subject" value="글제목입니다"><br>
			글내용 : <input type="text" name="contents" value="글내용입니다"><br>
			조회수 : <input type="text" name="readhit" value="3"><br>
			<input type="submit" value="전송">
		</form>
		<hr>
		앵커 태그<br>
		<a href="boTo01.jsp?no=1&readhit=3">하드 코딩</a>
		<br>
		<%
			int no = 1;
			int readhit = 1000;
		%>
		<a href="boTo01.jsp?no=<%=no %>&readhit=<%=readhit %>">변수 사용</a>
	</body>
</html>