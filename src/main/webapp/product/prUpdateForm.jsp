<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<style type="text/css">
	/* box model에 대한 공부가 필요합니다. */
	.container {
		margin-top: 10px;
	}
	
	.input-group {
		margin: 7px;
	}
	
	.input-group-text {
		display: block;
		margin-left: auto;
		margin-right: auto;
	}
	
	#buttonset {
		margin-top: 15px;
	}
	
	.radio_gender, .checkbox_hobby {
		font-size: 1.1rem;
	} /* 주위 글꼴의 1.1배 */
	#productPnum {
		display: none;
		visibility: hidden;
	} /* 상품 번호는 안보이게 지정*/
	</style>
	<script type="text/javascript">
	  		$(document).ready(function(){ 
	  	  		$('#inputdate').datepicker({dateFormat: "yy/mm/dd"});
	  	  		
				/* $("#category").prepend('<option value="aaa">bbb</option>'); */			
				/* $('option').eq(2).attr('selected', true) ; */
				
	  	  		/* 이전에 선택했던 카테고리 정보가 자동으로 선택되어 있도록 합니다. */
	  	        var category = '${requestScope.bean.category}';
	  	        /* alert(category); */
	  	         
	  	        var optionList = $('#category option');
	  	        optionList.each(function(){
	  	            console.log($(this).val());
	  	            if($(this).val() == category){
	  	               $('option[value="' + category + '"').attr('selected', true);
	  	            }
  	         	});
	  		});
	  		
	  		function validCheck(){
	  			var name = $('#name').val();
	  			if(name.length < 4 || name.length > 10){
	  				alert('상품명은 4글자 이상 10글자 이하이어야 합니다.');
	  				$('#name').focus() ;
	  				return false ;
	  			}
	  			
	  			var company = $('#company').val();
	  			if(company.length < 4 || company.length > 10){
	  				alert('제조 회사는 4글자 이상 10글자 이하이어야 합니다.');
	  				$('#company').focus() ;
	  				return false ;
	  			}  		
	  			
	  			var image01 = $('#image01').val();
	  			if(image01 == ''){
	  				alert('이미지는 필수 사항입니다.');
	  				return false ;
	  			}
	  			
	  			var isCheck = false ; /* 확장자 체크에 충족하면 true가 됩니다. */
	  			const imgCheck = ['.png', '.jpg'] ; /* 확장자 체크용 배열 */
	  			for(var i=0 ; i < imgCheck.length ; i++){
	  				if(image01.endsWith(imgCheck[i])){
	  					isCheck = true ;
	  					break ;
	  				}	
	  			}
	  			
	  			if(isCheck == false){
	  				alert('이미지의 확장자는 png 또는 jpg 형식이어야 합니다.');
	  				return false ;
	  			}
	  			
	  			const NumberMsg = '는(은) 숫자 형식이어야 합니다.' ;
	  			
	  			var stock = $('#stock').val();  			
	  			if(stock == '' || isNaN(stock) == true){
	  				alert('재고' + NumberMsg);
	  				$('#stock').focus();
	  				return false ;
	  			}else{
	  				var numStock = Number(stock);
	  				if(numStock < -1 || numStock > 5){
	  					alert('재고는 최대 5개까지입니다.');
	  	  				$('#stock').focus();
	  	  				return false ;
	  				}
	  			}
	  			
	  			var price = $('#price').val();  			
	  			if(price == '' || isNaN(price) == true){
	  				alert('단가' + NumberMsg);
	  				$('#price').focus();
	  				return false ;
	  			}else{
	  				var numPrice = Number(price);
	  				if(numPrice < 100 || numPrice > 10000){
	  					alert('단가은 100이상 10000이하의 값이어야 합니다.');
	  	  				$('#price').focus();
	  	  				return false ;
	  				}
	  			}  			
	  			
	
	  			var contents = $('#contents').val();
	  			if(contents.length < 10 || contents.length > 30){
	  				alert('상품에 대한 코멘트는 10글자 이상 30글자 이하이어야 합니다.');
	  				$('#contents').focus() ;
	  				return false ;
	  			}  		
	
	  			var category = $('#category').val();
	  			if(category == '-'){ /* 코딩할 때 option의 value 속성을 하이폰으로 설정했습니다. */
	  				alert('카테고리를 선택해 주세요.');
		  			$('#category').focus();
		  			return false ;
	  			}  	
	  			
	  			var point = $('#point').val();  			
	  			if(point == '' || isNaN(point) == true){
	  				alert('포인트' + NumberMsg);
	  				$('#point').focus();
	  				return false ;
	  			}else{
	  				var numPoint = Number(point);
	  				if(numPoint < 3 || numPoint > 10){
	  					alert('포인트는 3이상 10이하의 값이어야 합니다.');
	  	  				$('#point').focus();
	  	  				return false ;
	  				}
	  			}  		
	  			
	  			var inputdate = $('#inputdate').val();
	  			
	  			var regex = /^\d{4}[\/-][01]\d{1}[\/-][0123]\d{1}$/ ;
	  			var result = regex.test(inputdate) ;
	  			
	  			if(result == false){
	  				alert('날짜 형식은 반드시 yyyy/mm/dd 형식 또는 yyyy-mm-dd으로 작성해 주세요.');
	  				$('#inputdate').focus() ;
	  				return false ;
	  			} 
	  		}
  	</script>
</head>
<body>
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<div class="container">
				<h2>상품 수정</h2>
				<p>관리자가 등록한 상품 정보를 수정하는 페이지입니다.</p>
				<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
					<input type="hidden" name="command" value="prUpdate">
					<div id="productPnum" class="input-group">
						<span class="input-group-text">상품 번호</span> <input
							class="form-control" type="number" id="pnum" name="pnum" value="${bean.pnum}">
					</div>
					<div class="input-group">
						<span class="input-group-text">상품 이름</span> <input
							class="form-control" type="text" id="name" name="name" value="${bean.name}">
					</div>
					<div class="input-group">
						<span class="input-group-text">제조 회사</span> <input
							class="form-control" type="text" id="company" name="company" value="${bean.company}">
					</div>
					<div class="input-group">
						<span class="input-group-text">이미지01</span> <input
							class="form-control" type="file" id="image01" name="image01" value="image01">
							
						<%-- 과거에 업로드 했던 이미지는 삭제 되어야 합니다. --%> 
						<input type="hidden" name="deleteImage01" value="${bean.image01}">
					</div>
					<div class="input-group">
						<span class="input-group-text">이미지02</span> <input
							class="form-control" type="file" id="image02" name="image02">
							
						<input type="hidden" name="deleteImage02" value="${bean.image02}">
					</div>
					<div class="input-group">
						<span class="input-group-text">이미지03</span> <input
							class="form-control" type="file" id="image03" name="image03">
							
						<input type="hidden" name="deleteImage03" value="${bean.image03}">
					</div>
					<div class="input-group">
						<span class="input-group-text">재고 수량</span> <input
							class="form-control" type="number" id="stock" name="stock" value="${bean.stock}">
					</div>
					<div class="input-group">
						<span class="input-group-text">단가</span> <input
							class="form-control" type="number" id="price" name="price" value="${bean.price}">
					</div>
					<div class="input-group">
						<span class="input-group-text">카테고리</span> <select id="category"
							name="category" class="form-select">
							<c:forEach var="category" items="${requestScope.categories}">
								<option value="${category.engname}">${category.korname}</option>
							</c:forEach>
						</select>
					</div>
					<div class="input-group">
						<span class="input-group-text">상품 상세 설명</span> <input
							class="form-control" type="text" id="contents" name="contents" value="${bean.contents}">
					</div>
					<div class="input-group">
						<span class="input-group-text">적립 포인트</span> <input
							class="form-control" type="number" id="point" name="point" value="${bean.point}">
					</div>
					<div class="input-group">
						<span class="input-group-text">입고 일자</span> <input
							class="form-control" type="datetime" id="inputdate"
							name="inputdate" value="${bean.inputDate}">
					</div>
					<div id="buttonset" class="input-group">
						<button type="submit" class="btn btn-primary btn-lg"
							onclick="return validCheck();">수정</button>
						&nbsp;&nbsp;&nbsp;
						<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
					</div>
				</form>
			</div>
		</div>
		<div class="col-sm-2"></div>
	</div>
</body>
</html>