<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript">
			$(document).ready(function(){
			
			});
			
			function validCheck(){/* 폼 유효성 검사 */
	        	var password = $('#password').val();
	        	if(password.length < 3 || password.length > 20){
	            	alert('비밀번호는 3글자 이상 20글자 이하이어야 합니다.');
	            	$('#password').focus() ;
	            	return false ;
	           }
	        	
				var subject = $('#subject').val();
	        	if(subject.length < 3 || subject.length > 20){
	            	alert('글 제목은 3글자 이상 20글자 이하이어야 합니다.');
	            	$('#subject').focus() ;
	            	return false ;
	           }
	        	
	           var contents = $('#contents').val();
	           if(contents.length < 5 || contents.length > 30){
	           		alert('글 내용은 5글자 이상 30글자 이하이어야 합니다.');
	            	$('#contents').focus() ;
	            	return false ;
	           }
	           
	           //var regdate = $('#regdate').val();
	           
	           //var regex = /^\d{4}[\/-][01]\d{1}[\/-][0123]\d{1}$/ ;
	           //var result = regex.test(regdate) ;
	           
	           /*if(result == false){
	              alert('날짜 형식은 반드시 yyyy/mm/dd 형식 또는 yyyy-mm-dd으로 작성해 주세요.');
	              $('#regdate').focus() ;
	              return false ;
	           }*/
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
		</style>
	</head>
	<body>
		<div class="container mt-3">
			<h2>게시물 등록</h2>
			<p>사용자들이 게시물을 등록하는 페이지 입니다.</p>
			<form action="boInsertTo.jsp">
				<div id="boardNo" class="input-group mb-3">
					<span class="input-group-text">글번호</span>
					<input type="text" class="form-control" id="no" name="no">
				</div>
				
				<div class="input-group mb-3">
					<span class="input-group-text">작성자</span>
					<input type="text" class="form-control" id="fakeid" name="fakeid" value="hong" disabled>
					<input type="hidden" class="form-control" id="id" name="id" value="hong">
				</div>
				
				<div class="input-group mb-3">
					<span class="input-group-text">비밀번호</span>
					<input type="password" class="form-control" id="password" name="password">
				</div>
				
				<div class="input-group mb-3">
					<span class="input-group-text">글제목</span>
					<input type="text" class="form-control" id="subject" name="subject">
				</div>
				
				<div class="input-group mb-3">
					<span class="input-group-text">글내용</span>
					<input type="text" class="form-control" id="contents" name="contents">
				</div>
				
				<div id="buttonset" class="input-group mb-3">
					<button type="submit" class="btn btn-primary btn-lg" onclick="return validCheck();">
						등록
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="reset" class="btn btn-secondary btn-lg">
						초기화
					</button>
				</div>				
			</form>
		</div>
	</body>
</html>