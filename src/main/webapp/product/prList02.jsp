<%@page import="com.shopping.model.bean.Product"%>
<%@page import="com.shopping.model.dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>

<%
ProductDao dao = new ProductDao();
List<Product> dataList = dao.getDataList02();
%>

<c:set var="dataList" value="<%=dataList%>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container {
	margin-top: 10px;
}

.card {
	margin-left: auto;
	margin-right: auto;
}

.card-img-top {
	height: 150px;
}

.removeUnderLine {
	text-decoration: none;
	color: black;
}

.card-title {
	font-size: 30px;
}
</style>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<h2>상품 목록</h2>
				<p>고객들이 구매하고자 하는 상품 목록을 보여주는 페이지입니다.</p>
				<table class="table table-borderless">
					<thead>
					</thead>
					<tbody>
						<c:set var="columSu" value="3" />
						<c:forEach var="bean" items="${dataList }" varStatus="status">
							<c:if test="${status.index % columSu == 0}">
								<tr>
							</c:if>
							<td>
								<div class="card" style="width: 200px">
									<a class="removeUnderLine"
										href="https://www.naver.com?pnum=${bean.name}"> <img
										class="card-img-top" src="${bean.image01 }"
										alt="${bean.name }">
										<div class="card-body">
									 <span class="card-title">${bean.name }</span><br>
									 </a>
									<span class="card-text"> <a class="removeUnderLine" href="#"
										data-bs-toggle="popover" title="${bean.name }"
										data-bs-trigger="focus" data-bs-content="${bean.contents }">
											<c:if test="${fn:length(bean.contents) >= 10 }">
													${fn:substring(bean.contents, 0, 10)}...
												</c:if>
									</a> <c:if test="${fn:length(bean.contents) < 10 }">
												${bean.contents }
											</c:if>
									</span><br> <a href="#" class="btn btn-primary">수정</a>
								</div>
								</div>
							</td>
							<c:if test="${status.index mod columSu == (columSu-1)}">
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-sm-2"></div>
	</div>
	<script>
		var popoverTriggerList = [].slice.call(document
				.querySelectorAll('[data-bs-toggle="popover"]'))
		var popoverList = popoverTriggerList.map(function(popoverTriggerEl) {
			return new bootstrap.Popover(popoverTriggerEl)
		})
	</script>
</body>
</html>