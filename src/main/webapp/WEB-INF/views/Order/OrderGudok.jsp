<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en" data-bs-theme="auto">
<head>
<script src="../assets/js/color-modes.js"></script>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.115.4">
<title>요무까</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.3/examples/album/">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="/css/Order.css">
<link rel="stylesheet" href="/css/detail.css">
<script type="text/javascript" src="/js/order.js"></script>

<style>
.button {
	margin-bottom: 5px;
	border: 2px solid #FCC8C8;
	background-color: #FFEFEF;
	color: #000000;
}

.button:hover {
	background-color: #FFEFEF margin:20px;
	border: 2px solid #FCC8C8;
	color: #848484
}

.button-mind {
	margin: 10px 0px 10px 0px;
	border: 2px solid #FFFFFF;
	background-color: #FFFFFF;
	color: #000000;
}
</style>

</head>

<body>
	<%-- 헤더 --%>
	<%@ include file="../main/Header.jsp"%>

	<main>
		<div class="container">
			<form action='/OrderGudok/delete' method="post">
				<div class="row common goods">
					<h3 class="card-margin center">구독 현황</h3>

					<table class="center">
						<tr>
							<th><input type="checkbox" id="allcheck"
								onclick="allcanCheck()"></th>
							<th>상품 이미지</th>
							<th>상품명</th>
							<th>가격</th>
							<th>수량</th>
							<th>총 금액</th>
							<th>구독 시작 일자</th>
						</tr>

						<c:forEach items="${findGudok}" var="gudok">
							<tr>
								<td><input type="checkbox" name="productName" value="${gudok.productName}"></td>
								<td><img class="gudok_img" src="${gudok.productImg}"
									alt="상품 이미지"></td>
								<td>${gudok.productName}</td>
								<td>${gudok.productPrice}</td>
								<td>${gudok.productQuantity}</td>
								<td>${gudok.totalPrice}</td>
								<td>${gudok.gudokstartdate}</td>
							</tr>
						</c:forEach>
					</table>

					<div class="col" style="margin: 20px;">
						<button type="submit" aria-haspopup="dialog"
							onclick="deletecheck()" class="btn btn-lg button"
							style="margin-right: 10px; float: left;">구독 취소</button>
					</div>
				</div>
			</form>


		</div>
		<div style="cursor: pointer;" onclick="window.scrollTo(0,0);">
			<img id="top"
				src="https://i.esdrop.com/d/f/bkx1JZ8DmV/oeakU2PxHU.png">
		</div>
	</main>

	<%-- 푸터 --%>
	<%@ include file="../main/Footer.jsp"%>
	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
