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
			$(document).ready(function(){
				$('#birth').datepicker({dateFormat:"yy/mm/dd"});
				
				$('#id').keyup(function(){
					/* id를 변경하는 경우 다시 중복 체크를 유도하기 위해 false로 변경해주어야 합니다. */
					$('#isRegister').val('false');
				});
			});
			
			function idCheck(){/* 아이디 중복 체크를 수행합니다. */
				var isRegister = $('#isRegister').val();
				console.log('isRegister = ' + isRegister);
				// alert(isRegister);
				
				var id = $('#id').val();
				if(id == ''){
					alert('아이디를 입력해 주세요.');
					$('#id').focus();
					return;
				}
				
				var url = '<%=notWithFormTag%>meIdCheck&id=' + id;
				window.open(url, 'idCheck', width=400, height=300)
			}
			
			function validCheck(){/* form validation check */
				
				/* 회원 가입 체크용 변수가 true인지 체크합니다. */
				var isRegister = $('#isRegister').val();
				if(isRegister == 'false'){
					alert('아이디 중복 체크가 필요합니다.');
					$('#id').foucs(); /* $('#id').select; */
					return false;
				}
				
		        var id = $('#id').val();           
		        
		        if(id.length < 4 || id.length > 10){
		           alert('아이디는 4자리 이상 10자리 이하로 입력해 주세요.');
		           $('#id').select();
		           return false ; /* false이면 이벤트 전파 방지 */
		        }
		        
		        var name = $('#name').val();           
		        if(name.length < 3 || name.length > 15){              
		           $('#name').select();
		           alert('이름은 3자리 이상 15자리 이하로 입력해 주세요.');
		           return false ;
		        }
		        
		        var password = $('#password').val();           
		        if(password.length < 5 || password.length > 12){
		           alert('비밀 번호는 5자리 이상 12자리 이하로 입력해 주세요.');
		           $('#password').select();
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
		
		<%-- 다음 주소 검색을 위한 추가 코드 --%>
		<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
			function openZipSearch() {
			    new daum.Postcode({
			       oncomplete: function(data) {     
			      var addr = ''; 
			      if (data.userSelectedType === 'R') { 
			         addr = data.roadAddress;
			      } else {
			         addr = data.jibunAddress;
			      }
			
			      $("#zip_code").val(data.zonecode);
			      $("#address").val(addr); /* 메인 주소 입력 */
			      $("#address2").val(""); 
			      $("#address2").focus(); /* 상세 주소 입력란에 포커스 주기 */
			        }
			    }).open();
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
			<h2>회원 가입</h2>
			<p>신규 회원이 가입하는 페이지 입니다.</p>
	
			<form action="<%=withFormTag%>" method="post" name="myform">
				<input type="hidden" name="command" value="meInsert">
				
				<%-- 중복 체크를 했는지 판단하기 위한 boolean 타입의 히든 양식으로 true 일때만 회원 가입이 가능합니다. --%>
				<input type="hidden" id="isRegister" name="isRegister" value="false">
				
				<div class="input-group mb-3">
					<span class="input-group-text">아이디</span>
					<input type="text" class="form-control" id="id" name="id" value="asdf">
					<button type="button" class="btn btn-primary" onclick="idCheck()">중복 체크</button>
				</div>
	
				<div class="input-group mb-3">
					<span class="input-group-text">이름</span>
					<input type="text" class="form-control" id="name" name="name" value="강철수">
				</div>
	
				<div class="input-group mb-3">
					<span class="input-group-text">비밀번호</span>
					<input type="password" class="form-control" id="password" name="password" value="asdf@123">
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
					<input type="text" class="form-control" id="birth" name="birth" value="2021/01/30">
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
					<input type="text" class="form-control" id="address" name="address" value="" onclick="openZipSearch()">
					<input type="text" class="form-control" id="address2" name="address2" placeholder="세부 주소 입력">
				</div>
	
				<div id="buttonset" class="input-group mb-3">
					<button type="submit" class="btn btn-primary btn-sm" onclick="return validCheck();">가입</button>
					&nbsp;&nbsp;&nbsp;
					<button type="reset" class="btn btn-danger btn-sm">초기화</button>
				</div>
			</form>
		</div>
	</body>
</html>