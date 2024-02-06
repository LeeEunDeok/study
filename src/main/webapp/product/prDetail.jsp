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
		#qty{margin:-10px;border:0px;font-size:0.7rem;}	
		.plus, .minus{font-size:1.1rem;}	
	</style> 
<script type="text/javascript">
	$(document).ready(function(){
		 
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