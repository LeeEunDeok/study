<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>연습 Post방식 02</title>
	</head>
	<body>
		<form action="boTo02.jsp" method="post">
			글번호 : <input type="text" name="no" value="33"><br>
			글제목 : <input type="text" name="subject" value="글제목입니다."><br>
			글내용 : <input type="text" name="contents" value="글내용입니다."><br>
			조회수 : <input type="text" name="readhit" value="1111"><br>
			<input type="submit" value="전송">
		</form>
	</body>
</html>