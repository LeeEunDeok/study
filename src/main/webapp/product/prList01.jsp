<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">			
			.card-img-top{
				height: 150px;
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
							<tr>
								<td>
									<div class="card" style="width: 200px">
										<img class="card-img-top" src="../image/americano01.png"
											alt="상품01">
										<div class="card-body">
											<h4 class="card-title">상품01</h4>
											<p class="card-text">이 상품은 매우 좋습니다.</p>
											<a href="#" class="btn btn-primary">수정</a>
										</div>
									</div>
								</td>
								<td>
									<div class="card" style="width: 200px">
										<img class="card-img-top" src="../image/americano02.png"
											alt="상품02">
										<div class="card-body">
											<h4 class="card-title">상품02</h4>
											<p class="card-text">이 상품은 매우 좋습니다.</p>
											<a href="#" class="btn btn-primary">수정</a>
										</div>
									</div>
								</td>
								<td>
									<div class="card" style="width: 200px">
										<img class="card-img-top" src="../image/americano03.png"
											alt="상품03">
										<div class="card-body">
											<h4 class="card-title">상품03</h4>
											<p class="card-text">이 상품은 매우 좋습니다.</p>
											<a href="#" class="btn btn-primary">수정</a>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="card" style="width: 200px">
										<img class="card-img-top" src="../image/brioche_01.png"
											alt="상품04">
										<div class="card-body">
											<h4 class="card-title">상품04</h4>
											<p class="card-text">이 상품은 매우 좋습니다.</p>
											<a href="#" class="btn btn-primary">수정</a>
										</div>
									</div>
								</td>
								<td>
									<div class="card" style="width: 200px">
										<img class="card-img-top" src="../image/brioche_02.png"
											alt="상품05">
										<div class="card-body">
											<h4 class="card-title">상품05</h4>
											<p class="card-text">이 상품은 매우 좋습니다.</p>
											<a href="#" class="btn btn-primary">수정</a>
										</div>
									</div>
								</td>
								<td>
									<div class="card" style="width: 200px">
										<img class="card-img-top" src="../image/brioche_03.png"
											alt="상품06">
										<div class="card-body">
											<h4 class="card-title">상품06</h4>
											<p class="card-text">이 상품은 매우 좋습니다.</p>
											<a href="#" class="btn btn-primary">수정</a>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</body>
</html>