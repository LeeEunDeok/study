<%@page import="com.shopping.model.dao.ProductDao"%>
<%@page import="com.shopping.model.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp"%>

<%
	ProductDao dao = new ProductDao();
	int beginRow = 1;
	int endRow = 10;
	List<Product> dataList = dao.getDataList(beginRow, endRow);
	
%>

<c:set var="dataList" value="<%=dataList%>"></c:set>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$(document).ready(function(){
		var popoverTriggerList = [].slice.call(document
				.querySelectorAll('[data-bs-toggle="popover"]'))
		var popoverList = popoverTriggerList.map(function(popoverTriggerEl) {
			return new bootstrap.Popover(popoverTriggerEl)
		})
	});
</script>
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
</style>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<h2>상품 목록</h2>
				<p>고객들이 구매하고자 하는 상품 목록을 보여 주는 페이지 입니다.</p>
				<table class="table table-borderless">
					<thead>
					</thead>
					<tbody>
						<c:set var="columnsu" value="3" />
						<c:forEach var="bean" items="${dataList}" varStatus="status">
							<%-- index가 3의 배수이면(나머지가0)<tr> 적고--%>
							<c:if test="${status.index mod columnsu == 0}">
								<tr>
							</c:if>
							<td>
								<div class="card" style="width: 300px">
									<a class="removeUnderLine"
										href="https://www.naver.com?pnum=${bean.pnum}"> <img
										class="card-img-top" src="./../image/${bean.image01}"
										alt="${bean.name}">
										<div class="card-body">
											<h4 class="card-title">${bean.name}</h4>
											<p class="card-text">
												<span data-bs-toggle="popover"
													title="${bean.name}"
													data-bs-trigger="hover"
													data-bs-content="${bean.contents}"> 
													<c:if test="${fn:length(bean.contents) >= 10}">
													${fn:substring(bean.contents, 0, 10)}...
													</c:if>
												</span>
												<c:if test="${fn:length(bean.contents) < 10}">
													${bean.contents}
												</c:if>
											</p>
											<a href="#" class="btn btn-primary">수정</a>
										</div>
									</a>
								</div>
							</td>
							<%--index가 3으로 나눴을때 나머지가 2인 경우 </tr> 닫는다.--%>
							<c:if test="${status.index mod columnsu == (columnsu-1)}">
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
</body>
</html>