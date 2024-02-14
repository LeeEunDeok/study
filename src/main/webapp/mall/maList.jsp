<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* 총금액과 적립 포인트를 위한 스타일 지정 */
.largeSpan {
	color: red;
	font-size: 1.3em;
	font-weight: bolder;
}

.bigFont {
	font-size: 1.3em;
}

/* 수정 버튼을 위한 스타일 지정 */
.row {
	margin: auto;
	vertical-align: middle;
}

.col {
	margin: -10px;
	vertical-align: middle;
}

.form-control-sm {
	width: 80px;
	height: 40px;
	border: 2px solid Gainsboro;
}
</style>
<script type="text/javascript">
		function checkAll(){
			if($('#checkall').prop('checked')){ /* 전체 선택 on */
				$('input[name=cartCheckBox]').prop('checked', true);
			}else{ /* 전체 선택 off */
				$('input[name=cartCheckBox]').prop('checked', false);
			}
			
			reCalculateAmount();
		}	
	
		/* 개별 체크 박스의 상태가 변경되거나, 전체 선택 체크의 상태 변경시 호출이 됩니다. */
		function reCalculateAmount(){
			var cartTotalPrice = 0 ;
			var cartTotalPoint = 0 ;
			
			/* 체크 상태가 on인 항목들을 반복합니다. */
			$('input[name=cartCheckBox]:checked').each(function(){ 
				var pnum = $(this).val() ;
				var price = $('.price_' + pnum).attr('data') ;
				var point = $('.point_' + pnum).attr('data') ;
				var qty = $('.qty_' + pnum).attr('data') ;
				console.log('price='+price+',point='+point+',qty='+qty);
				
				cartTotalPrice += price * qty ;
				cartTotalPoint += point * qty ;
			});			
			
			$('.cartTotalPrice').html(AddComma(cartTotalPrice)+ '원');
			$('.cartTotalPoint').html(AddComma(cartTotalPoint)+ '원');
		}
		
        function AddComma(num){ /* 3자리마다 콤마 유형 넣기 */
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return num.toString().replace(regexp, ',');
        }	
        
		$(document).ready(function(){
			$('#checkall').prop('checked', true); /* 전체 선택 체크 박스 on */
			$('input[name=cartCheckBox]').each(function(){ /* 개별 체크 박스 on */
				$(this).prop('checked', true);
			});
			
			$('input[name=cartCheckBox]').change(function(){ /* 개별 체크 박스의 상태 변경 */
				reCalculateAmount();	
			});
		});	
		
		/* 카트 목록에서 특정 상품(변수 : pnum) 개수를 변경해주는 함수입니다. */
		function editQty(pnum){
			/* 입력 양식을 'edit_상품번호' 형식으로 만들어 두었습니다. */
			var qty = $('#edit_' + pnum).val();
			
			if(!qty){
				alert('수정할 개수를 입력해 주세요.');
				$('#edit_' + pnum).focus();
				return false;
			}
			
			/* 수정될 상품의 번호와 수정량을 컨트롤러로 넘깁니다. */
			location.href='<%=notWithFormTag%>maUpdate&pnum='+pnum+'&qty='+qty;
		}
</script>
</head>
<body>
	<div class="container mt-3">
		<h2>카트 내역 보기</h2>
		<p>나의 관심/구매 예정인 목록에 대한 정보를 보여 줍니다.</p>
		<table class="table table-striped">
			<thead>
				<tr>
					<th width="9%">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="checkall"
								name="option1" onclick="checkAll();"> <label
								class="form-check-label">전체 선택</label>
						</div>
					</th>
					<th align="center">상품명</th>
					<th>수량</th>
					<th>단가</th>
					<th>포인트</th>
					<th>금액</th>
					<th>누적 포인트</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%-- 변수 총 금액 : totalAmount, 총 누적 포인트 : totalPoint --%>
				<c:set var="totalAmount" value="0" />
				<c:set var="totalPoint" value="0" />

				<c:forEach var="bean" items="${sessionScope.cartWishList}">
					<tr>
						<td align="center" valign="middle">
							<div class="form-check">
								<input type="checkbox" name="cartCheckBox" value="${bean.pnum}">
							</div>
						</td>
						<td align="center" valign="middle"><img alt="${bean.pname}"
							width="45" height="45" class="rounded"
							src="image/${bean.image01}"> <br /> ${bean.pname}</td>
						<td align="center" valign="middle" class="qty_${bean.pnum}"
							data="${bean.qty}">${bean.qty}개</td>
						<td align="center" valign="middle" class="price_${bean.pnum}"
							data="${bean.price}"><fmt:formatNumber value="${bean.price}"
								pattern="###,###" />원</td>
						<td align="center" valign="middle" class="point_${bean.pnum}"
							data="${bean.point}"><fmt:formatNumber value="${bean.point}"
								pattern="###,###" />원</td>
						<td align="center" valign="middle"><fmt:formatNumber
								value="${bean.qty * bean.price}" pattern="###,###" />원</td>
						<td align="center" valign="middle"><fmt:formatNumber
								value="${bean.qty * bean.point}" pattern="###,###" />원</td>
						<td align="center" valign="middle">
							<div class="row">
								<div class="col">
									<div class="col-sm-12">
										<input class="form-control-sm" type="text"
											id="edit_${bean.pnum}" name="edit_${bean.pnum}">
										<button class="btn btn-warning btn-sm form-control-sm"
											onclick="editQty('${bean.pnum}');">수정</button>
									</div>
								</div>
							</div>
						</td>
						<td align="center" valign="middle"><a
							href="<%=notWithFormTag%>maDelete&pnum=${bean.pnum}"> 삭제 </a></td>
					</tr>

					<%-- '총 금액'과 '총 누적 포인트'를 누적합니다. --%>
					<c:set var="totalAmount"
						value="${totalAmount + bean.qty * bean.price}" />
					<c:set var="totalPoint"
						value="${totalPoint + bean.qty * bean.point}" />

				</c:forEach>

				<%-- 결제하기, 추가 주문 기능, 총 금액, 총 누적 포인트 보여 주기 --%>
				<tr>
					<td align="center" colspan="3"><a class="bigFont"
						href="<%=notWithFormTag%>maCalculate">결제 하기</a> &nbsp;&nbsp;&nbsp;
						<a class="bigFont" href="<%=notWithFormTag%>prList">추가 주문</a></td>
					<td align="left" colspan="6"><span class="largeSpan"> 총
							금액 : <span class="cartTotalPrice"> <fmt:formatNumber
									value="${totalAmount}" pattern="###,###" />원
						</span>
					</span> &nbsp;&nbsp;&nbsp; <span class="largeSpan"> 총 적립 포인트 : <span
							class="cartTotalPoint"> <fmt:formatNumber
									value="${totalPoint}" pattern="###,###" />원
						</span>
					</span></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>