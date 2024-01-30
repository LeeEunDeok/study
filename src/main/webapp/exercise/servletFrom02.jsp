<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form action="<%=appName%>/world" method="post">
      상품 번호 : <input type="text" name="pnum" value="001"><br/>
      상품명 : <input type="text" name="name" value="메가리카노"><br/>
      제조 회사 : <input type="text" name="company" value="메가 커피"><br/>
      가격 : <input type="text" name="price" value="3500"><br>
      <input type="submit" value="전송">
   </form>
</body>
</html>