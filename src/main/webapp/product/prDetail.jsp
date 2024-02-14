<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.container{margin-top: 5px;}
		.card{margin-left:auto;margin-right:auto;}
		.leftside{margin-left:0px;}
		.card_borderless{border:0px;}
		
		.small_image{width:100px;height:100px;margin:2px;border-radius:5px;}
		#totalprice{color:red;font-size: 20px;font-weight:bolder;}
		.cart{background-color:black;border:1px solid black;} /* 속성 표기법 */
		.rightnow{background-color:red;border:1px solid red;}
		#qty{margin:-10px;border:0px;font-size:0.7rem;text-align:center;}	
		.plus, .minus{font-size:1.1rem;}	
	</style> 
	<script type="text/javascript">
		$(document).ready(function(){
			 $('#totalprice').text('0'); /* 최초 시작 시 금액을 0으로 설정합니다. */
			 
			 /* 마이너스 기호의 내부 높이와 수량 입력란의 높이를 동일하게 설정합니다. */
			 $('#qty').innerHeight($('.minus').innerHeight());
			 
			 const maxPurchaseSize = 5; /* 최대 구매 가능 개수 */
			 
			 var price = '${bean.price}'; /* 상품의 단가 */
			 
			 /* 사용자가 + 버튼을 클릭함 */
			 $('.plus').click(function(){
				 var qty = $('#qty').val();
				 
				 if(qty == ''){
					 $('#qty').val('1');
				 } else {
					 /* Number 객체는 자바의 Integer.parseInt() 와 동일한 효과*/
					 var newQty = Number(qty);
					 
					 if(newQty == maxPurchaseSize){
						 alert('최대 ' + maxPurchaseSize + '개 까지만 주문이 가능합니다.');
						 return; /* 더이상 진행하지 않습니다. */
					 }
					 
					 newQty = newQty + 1;
					 $('#qty').val(newQty);
				 }
				 var amount = newQty * price;
				 
				 
				 /* toLocaleString()는 해당 지역의 통화 유형으로 바꿔주는 함수*/
				 $('#totalprice').text(amount.toLocaleString());
				 
				 
				 
			 }); /* + 버튼 클릭 끝 */
			 
			 /* 사용자가 - 버튼을 클릭함 */
			 $('.minus').click(function(){
				 var qty = $('#qty').val();
				 
				 if(qty=='0'){
					 alert('최소 1개 이상 구매하셔야 합니다.');
					 return;
				 }
				 if(qty == ''){
					 $('#qty').val('0');
					 $('#totalprice').text('0');
				 }else{
					 var newQty = Number(qty) - 1;
					 $('#qty').val(newQty);
					 
					 var amount = newQty * price;
					 $('#totalprice').text(amount.toLocaleString());
				 }
			 });/* - 버튼 클릭 끝 */
			 
			 /* 수량 입력 란이 focus를 잃었을 때*/
			 $('#qty').blur(function(){
				 var qty = $('#qty').val();
				 
				 if(qty=='' || isNaN(qty) == true){
					 alert('0이상 ' + maxPurchaseSize + '이하의 숫자만 가능합니다.');
					 $('#qty').val('0');
					 $('#qty').focus();
					 return;
				 }
				 
				 if(isNaN(qty) == false){ /* 숫자 형식인 경우*/
				 	var newQty = Number(qty);
				 	if(newQty < 0 || newQty > maxPurchaseSize){
				 		alert('0이상 ' + maxPurchaseSize + '이하의 숫자만 가능합니다.');
						 $('#qty').val('0');
						 $('#qty').focus();
						 return;
				 	}
				 }
			});
			 
			 $('.cart').click(function(){ /* 장바구니 클릭 */
				 var qty = $('#qty').val();
			 	 if(qty < 1 || qty > 5){
			 		 alert('장바구니에는 1개 이상 5개 이하까지 담을 수 있습니다.')
			 		 return;
			 	 }
			 });
			 
			 $('.rightnow').click(function(){ /* 바로구매 클릭 */
				 var qty = $('#qty').val();
			 	 if(qty < 1 || qty > 5){
			 		 alert('바로구매는 1개 이상 5개 이하까지 가능합니다.')
			 		 return;
			 	 }
			 });
			 
			 /* 작은 이미지 클릭 시 큰 이미지 변경하기 */
			 /* attr() 함수는 속성(attribute)을 읽거나 쓰기 위한 함수 입니다. */
			 $('.small_image').click(function(){
				$('.active_image').attr('src', $(this).attr('src')); 
			 });
		});
	</script>
</head>
<body> 
	<div class="container">
		<h2>상품 상세 페이지</h2>
		<p>
			고객들이 구매하고자 하는 상품 목록을 보여 주는 페이지입니다.<br/>
			작은 이미지를 클릭하면 큰 이미지가 변경이 됩니다.
		</p>		
		<table class="table table-borderless">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td class="col-lg-5">						
						<div class="card picZoomer" style="width: 30rem;">
							<img alt="${bean.name}" src="image/${bean.image01}" 
								class="car-img-top active_image">
						</div>
					</td>
					
					<td class="col-lg-1">
						<img alt="${bean.name}" src="image/${bean.image01}" 
								class="car-img-top small_image rounded" >
						
						<c:if test="${not empty bean.image02}">
							<img alt="${bean.name}" src="image/${bean.image02}" 
								class="car-img-top small_image rounded" >
						</c:if>		
						
						<c:if test="${not empty bean.image03}">
							<img alt="${bean.name}" src="image/${bean.image03}" 
								class="car-img-top small_image rounded" >
						</c:if>
					</td>
					
					<td class="col-lg-6">
						<div class="card leftside card_borderless" style="width: 18rem;">
							<h5 class="card-title">${bean.name}</h5>
							<p class="card-text">${bean.contents}</p>
							<p class="card-text">
								합계 : <span id="totalprice">10,000</span>원
							</p>
							<form action="<%=withFormTag%>" method="post">							
							<ul class="pagination">
								<li class="page-item"><a class="page-link minus" href="#"> - </a></li>
								<li class="page-item">
									<a class="page-link" href="#">
										<input type="hidden" name="command" value="maInsert">
										<input type="hidden" name="pnum" value="${bean.pnum}">
										<input type="hidden" name="stock" value="${bean.stock}">
										
										<input type="text" name="qty" id="qty" value="0"
										data-bs-trigger="hover" data-bs-toggle="popover" 
										title="수량 누적 알림" 
										data-bs-content="기존 카트에 품목이 이미 존재하면 수량을 누적합니다.">
									</a>
								</li>
								<li class="page-item"><a class="page-link plus" href="#"> + </a></li>
							</ul>
							<div class="btn-group">
								<button type="submit" class="btn btn-primary cart">장바구니</button>
								<button type="button" class="btn btn-primary rightnow">바로 구매</button>								
							</div>
							</form>
						</div>
					</td>
				</tr>
			</tbody>
		</table>			
	</div>
</body>
</html>