<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Get방식 Post방식 02</title>
	</head>
	<body>
		<form action="meTo02.jsp" method="post">
			아이디 : <input type="text" name="id" value="hong"><br>
			비밀번호 : <input type="password" name="password" value="abc1234"><br>
			취미 : 
			<input type="checkbox" name="hobby" value="축구" checked>축구
			<input type="checkbox" name="hobby" value="야구">야구
			<input type="checkbox" name="hobby" value="농구" checked>농구
			<br>
			숨김 파라미터 : 
			<input type="hidden" name="mpoint" value="1000"><br>
			<input type="submit" value="전송">
		</form>
	</body>
</html>