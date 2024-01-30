<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Get방식 Post방식</title>
	</head>
	<body>
		<form action="meTo01.jsp" method="get">
			아이디 : <input type="text" name="id" value="hong"><br>
			비밀번호 : <input type="password" name="password" value="abc1234"><br>
			취미 : 
			<input type="checkbox" name="hobby" value="축구">축구
			<input type="checkbox" name="hobby" value="야구">야구
			<input type="checkbox" name="hobby" value="농구">농구
			<br>
			숨김 파라미터 : 
			<input type="hidden" name="mpoint" value="1000">
			<br>
			<input type="submit" value="전송">
		</form>
		<hr>
		<form action="meTo01.jsp" method="post">
			아이디 : <input type="text" name="id" value="hong"><br>
			비밀번호 : <input type="password" name="password" value="abc1234"><br>
			취미 : 
			<input type="checkbox" name="hobby" value="축구" checked>축구
			<input type="checkbox" name="hobby" value="야구" checked>야구
			<input type="checkbox" name="hobby" value="농구">농구
			<br>
			숨김 파라미터 : 
			<input type="hidden" name="mpoint" value="1000">
			<br>
			<input type="submit" value="전송">
		</form>
		<hr>
		
		앵커 태그<br>
		<a href="meTo01.jsp?id=choi&mpoint=5000">하드 코딩</a>
		<br>
		<%
			String id = "park";
			int mpoint = 123;
		%>
		<a href="meTo01.jsp?id=<%=id %>&mpoint=<%=mpoint %>">변수 사용</a>
		<br>
	</body>
</html>