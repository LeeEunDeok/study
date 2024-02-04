<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
		<script type="text/javascript">
		   $(document).ready(function() {
			   $('#birth').datepicker({dateFormat:"yy/mm/dd"});
			      
		       /* value 속성의 값이 일치하는 항목에 대하여 체크 on 시킵니다. */
	           /* 성별 라디오 */
   	           $('input[value="${bean.gender}"]').attr('checked', true);
			      
			   /* 결혼 여부 */
	           $('input[value="${bean.marriage}"]').attr('checked', true);
			         
		       /* 취미 관련 문자열 예시 : "탁구,축구,"*/
	   	       var hobbyCheck = "${bean.hobby}";
			      

		       /* 취미 체크 박스 목록 */
	           var checkHobbies = $('input[name=hobby]') ;
			      
		       for(var i=0 ; i<checkHobbies.length ; i++){
		    	   var idx = hobbyCheck.indexOf(checkHobbies[i].value);         
			       if(idx >= 0){
			    	   checkHobbies[i].checked = true ;
			       	 }
			      }      
			   });
		   
			function validCheck(){/* form validation check */
		        var id = $('#id').val();           
		        
		        if(id.length < 4 || id.length > 10){
		           alert('아이디는 4자리 이상 10자리 이하로 입력해 주세요.');
		           $('#id').focus();
		           return false ; /* false이면 이벤트 전파 방지 */
		        }
		        
		        var name = $('#name').val();           
		        if(name.length < 3 || name.length > 15){              
		           $('#name').focus();
		           alert('이름은 3자리 이상 15자리 이하로 입력해 주세요.');
		           return false ;
		        }
		        
		        var password = $('#password').val();           
		        if(password.length < 5 || password.length > 12){
		           alert('비밀 번호는 5자리 이상 12자리 이하로 입력해 주세요.');
		           $('#password').focus();
		           return false ;
		        }    
		        
		        var regex = /^[a-z]\S{4,11}$/; /* 정규 표현식 */
		        var result = regex.test(password) ;

		        if(result == false){
		           alert('비밀 번호의 첫글자는 반드시 소문자이어야 합니다.');              
		           return false ;
		        }
		        
		        if(password.indexOf('@') <= 0 && password.indexOf('#') <= 0 && password.indexOf('$') <= 0){
		           alert('특수 문자 @#% 중에 최소 1개가 포함이 되어야 합니다.');              
		           return false ;
		        }
		        
		        var genderList = $('input[name="gender"]:checked') ;
		        if(genderList.length == 0){
		           alert('성별은 반드시 선택이 되어야 합니다.');
		           return false ; 
		        }
		        
		        var marriageList = $('input[name="marriage"]:checked') ;
		        if(marriageList.length == 0){
		           alert('결혼여부는 반드시 선택이 되어야 합니다.');
		           return false ; 
		        }
		       
		        /* jqueryUI 플러그인 date picker */
		        var birth = $('#birth').val();
		        var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/ ;
		        var result = regex.test(birth);
		        
		        if(result == false){
		           alert('생일은 반드시 yyyy/mm/dd 형식으로 입력해 주세요.');              
		           return false ;
		        }
		        
		        const hobbyLimit = 2;
		        var hobbyList = $('input[name="hobby"]:checked') ;
		        if(hobbyList.length < hobbyLimit){
		            alert('취미는 최소 2개 이상 선택해주셔야 합니다.');
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
			<h2>회원 정보 수정</h2>
			<p>회원 정보를 수정하는 페이지 입니다.</p>
	
			<form action="<%=withFormTag %>" method="post">
			<input type="hidden" name="command" value="meUpdate">
				<div class="input-group mb-3">
					<span class="input-group-text">아이디</span>
					<input type="text" class="form-control" id="fakeid" name="fakeid" value="${bean.id }" disabled>
					<input type="hidden" class="form-control" id="id" name="id" value="${bean.id }">
				</div>
	
				<div class="input-group mb-3">
					<span class="input-group-text">이름</span>
					<input type="text" class="form-control" id="name" name="name" value="${bean.name }">
				</div>
	
				<div class="input-group mb-3">
					<span class="input-group-text">비밀번호</span>
					<input type="password" class="form-control" id="password" name="password">
				</div>
	
				<div class="input-group mb-3">
					<span class="input-group-text">성별</span>
					<div class="form-control">
						<c:forEach var="fillItem" items="${genderList }" varStatus="status">
							<!-- 부트스트랩 label class 임 -->
							<label class="radio-inline radio_gender">
								&nbsp;<input type="radio" id="gender${status.count }" name="gender" value="${fillItem.engname}">&nbsp;${fillItem.korname}
							</label>
						</c:forEach>
					</div>
				</div>
	
				<div class="input-group mb-3">
					<span class="input-group-text">생일</span>
					<input type="text" class="form-control" id="birth" name="birth" value="${bean.birth }">
				</div>
	
				<div class="input-group mb-3">
					<span class="input-group-text">결혼여부</span>
					<div class="form-control">
						<c:forEach var="fillItem" items="${marriageList }" varStatus="status">
							<!-- 부트스트랩 label class 임 -->
							<label class="radio-inline radio_marriage">
								&nbsp;<input type="radio" id="marriage${status.count }" name="marriage" value="${fillItem.engname}">&nbsp;${fillItem.korname}
							</label>
						</c:forEach>
					</div>
				</div>
	
				<div class="input-group mb-3">
					<span class="input-group-text">취미</span>
					<div class="form-control">
						<c:forEach var="fillItem" items="${hobbyList }" varStatus="status">
							<!-- 부트스트랩 label class 임 -->
							<label class="checkbox-inline checkbox_hobby">
								&nbsp;<input type="checkbox" id="hobby${status.count }" name="hobby" value="${fillItem.engname}">&nbsp;${fillItem.korname}
							</label>
						</c:forEach>
					</div>
				</div>
	
				<div class="input-group mb-3">
					<span class="input-group-text">주소</span>
					<input type="text" class="form-control" id="address" name="address" value="${bean.address }">
				</div>
	
				<div id="buttonset" class="input-group mb-3">
					<button type="submit" class="btn btn-primary btn-sm" onclick="return validCheck();">수정</button>
					&nbsp;&nbsp;&nbsp;
					<button type="reset" class="btn btn-danger btn-sm">초기화</button>
				</div>
			</form>
		</div>
	</body>
</html>