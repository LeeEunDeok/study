<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.container {
	margin-top: 10px;
}

/* table 셀의 수평 정렬을 가운데로 지정합니다. */
.card {
	margin-left: auto;
	margin-right: auto;
}

.card-img-top {
	width: 300px;
	height: 300px;
}

.removeUnderLine {
	text-decoration: none;
}

.rounded-pill {
	opacity: 0.7;
}

.mode, .keyword, .col {
	margin: auto;
}

.form-control-sm {
	border: 1px solid Gainsboro;
}
#buttonList{
	margin-top: 10px;
	opacity: 0.8;
}

</style>
<script type="text/javascript">
	$(document).ready(function(){
		/* 필드 검색시 체크한 콤보 박스 상태 보존 */
		var optionList = $('#mode option'); /* 옵션 목록 */
		for(var i=0; i<optionList.length; i++){
			if(optionList[i].value == '${requestScope.paging.mode}'){
				optionList[i].selected = true;
			}
		}
		
		/* 필드 검색시 입력한 keyword 내용 보존 */
		$('#keyword').val('${requestScope.paging.keyword}');
		
		/* 상품 삭제 버튼 클릭 */
		/* id 속성이 deleteAnhor로 시작하는 항목을 찾아서 클릭 function */
		$('[id^=deleteAnchor]').click(function(){
			var response = confirm('해당 상품을 삭제하시겠습니까?');
			if(response){
				var pnum = $(this).attr('data'); /* 상품 번호 */
				
				var url = '<%=notWithFormTag%>prDelete${requestScope.paging.flowParameter}&pnum=' + pnum;
				
				location.href = url;
			}else{
				alert('상품 삭제가 취소되었습니다.');
			}
		});
	});

	function searchAll(){
		location.href='<%=notWithFormTag%>prList';
	}
	function writeForm(){
		location.href='<%=notWithFormTag%>prInsert';
	}
</script>

</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<h2>상품 목록</h2>
				<p>고객들이 구매하고자 하는 상품 목록을 보여 주는 페이지입니다.</p>
				<table class="table table-borderless">
					<thead>
					</thead>
					<tbody>
						<c:set var="columnSu" value="3" />
						<c:forEach var="bean" items="${requestScope.dataList}" varStatus="status">
							<c:if test="${status.index mod columnSu == 0}">
								<tr>
							</c:if>
							<td>
								<div class="card" style="width: 300px">
									<a class="removeUnderLine"
										href="https://www.naver.com?pnum=${bean.pnum}"> <img
										class="card-img-top" src="image/${bean.image01}"
										alt="${bean.name}">
										<div class="card-body">
											<h4 class="card-title">${bean.name}</h4>
											<p class="card-text">
												<span data-bs-toggle="popover" title="${bean.name}"
													data-bs-trigger="hover" data-bs-content="${bean.contents}">
													<c:if test="${fn:length(bean.contents) >= 10}">
														${fn:substring(bean.contents, 0, 10)}...
													</c:if>
												</span>

												<c:if test="${fn:length(bean.contents) < 10}">
													${bean.contents}
												</c:if>
											</p>
											<c:if test="${whologin == 2}">
												<div id="buttonList">
													<a id="updateAnchor" class="btn btn-info"
													href="<%=notWithFormTag%>prUdate&pnum=${bean.pnum}${requestScope.paging.flowParameter}">
														수정
													</a>
													<a id="deleteAnchor_${bean.pnum}" class="btn btn-danger"
													 data="${bean.pnum}">
														삭제
													</a>
												</div>
											</c:if>
										</div>
									</a>
								</div>
							</td>
							<c:if test="${status.index mod columnSu == (columnSu-1)}">
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="8" align="center">
								<div class="row">
									<div class="col-sm-1"></div>
									<div class="col-sm-10">
										<form action="<%=withFormTag%>" method="get">
											<input type="hidden" name="command" value="prList">
											<div class="row">
												<div class="col-sm-12">
													<select class="form-control-sm" id="mode" name="mode">
														<option value="all">-- 선택해주세요.
														<option value="name">상품명
														<option value="company">제조회사
														<option value="category">카테고리
														<option value="contents">상품설명
													</select> <input class="form-control-sm" type="text" id="keyword"
														name="keyword">
													<button class="form-control-sm btn btn-warning"
														type="submit">검색</button>

													<button class="form-control-sm btn btn-warning"
														type="button" onclick="searchAll();">전체 검색</button>
													<c:if test="${whologin == 2}">
														<button class="form-control-sm btn btn-info" type="button"
															onclick="writeForm();">상품 등록</button>
													</c:if>	
													&nbsp;&nbsp; <span class="label laber-default">
														${requestScope.paging.pagingStatus} </span>
												</div>
											</div>
										</form>
									</div>
									<div class="col-sm-1"></div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				${requestScope.paging.pagingHtml}
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
</body>
<script>
	$(document).ready(
			function() {
				var popoverTriggerList = [].slice.call(document
						.querySelectorAll('[data-bs-toggle="popover"]'))
				var popoverList = popoverTriggerList.map(function(
						popoverTriggerEl) {
					return new bootstrap.Popover(popoverTriggerEl)
				})
			});
</script>
</html>