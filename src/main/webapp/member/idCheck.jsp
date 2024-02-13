<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<!-- bootstrap section -->
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
			rel="stylesheet">
		<style type="text/css">
			.container{margin: 10px;}
		</style>
		<script>
			function meClose(isRegister){
				opener.myform.isRegister.value = isRegister;
				self.close();
			}
		</script>
	</head>
	<body>
		<div class="container">
	      <p align="center">${message}<br>${plus_message}</p>
	      <div class="row" align="center">
	         <button class="btn btn-primary" type="button" 
	            onclick="meClose('${isRegister}');">
	            닫&nbsp;&nbsp;기
	         </button>
	      </div>
	   </div>
	</body>
</html>