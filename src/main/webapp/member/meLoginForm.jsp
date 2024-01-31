<%@page import="com.shopping.model.bean.FillItem"%>
<%@page import="com.shopping.model.dao.FillItemDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>

<%
	// 라디오버튼과 체크박스는 데이터베이스에서 읽어 동적으로 채웁니다.
	FillItemDao fdao = new FillItemDao();
	List<FillItem> genderList = fdao.getDataList("members", "gender");
	List<FillItem> marriageList = fdao.getDataList("members", "marriage");
	List<FillItem> hobbyList = fdao.getDataList("members", "hobby");
%>
<c:set var="genderList" value="<%=genderList %>" />
<c:set var="marriageList" value="<%=marriageList %>" />
<c:set var="hobbyList" value="<%=hobbyList %>" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<script type="text/javascript">
		$(document).ready(function(){
			
		});
		
		function validCheck(){/* form validation check */
	        var id = $('#id').val();           
	        
	        if(id.length < 4 || id.length > 10){
	           alert('아이디는 4자리 이상 10자리 이하로 입력해 주세요.');
	           $('#id').focus();
	           return false ; /* false이면 이벤트 전파 방지 */
	        }
	        
	        var password = $('#password').val();           
	        if(password.length < 5 || password.length > 12){
	           alert('비밀 번호는 5자리 이상 12자리 이하로 입력해 주세요.');
	           $('#password').focus();
	           return false ;
	        }    
	     }
	</script>
	<style type="text/css">
		/* box model에 대한 공부가 필요합니다. */
        .container{margin-top: 10px;}
        .input-group{margin: 7px;}
        .input-group-text{
           display: block;
           margin-left: auto;
           margin-right: auto;
        }
        #buttonset{margin-top: 15px;}
        /* 게시물 번호는 시퀀스를 사용하여 자동으로 처리할 예정이므로 숨기도록 합니다.*/
        #boardNo{display: none; visibility: hidden;}
        /* 주위 글꼴의 1.1배 */
        .radio_gender, .radio_marriage, .checkbox_hobby{font-size: 1.1rem;}
	</style>
</head>
<body>
	<div class="container mt-3">
		<h2>로그인 페이지</h2>
		<p>로그인하는 페이지 입니다.</p>

		<form action="<%=withFormTag%>" method="post">
			<input type="text" name="command" value="meLogin">
			<div class="input-group mb-3">
				<span class="input-group-text">아이디</span>
				<input type="text" class="form-control" id="id" name="id" value="">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">비밀번호</span>
				<input type="password" class="form-control" id="password" name="password" value="">
			</div>
			
			<div id="buttonset" class="input-group mb-3">
				<button type="submit" class="btn btn-primary btn-sm" onclick="return validCheck();">로그인</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-danger btn-sm">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>