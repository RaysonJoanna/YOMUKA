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
<!-- 구글폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap"
	rel="stylesheet">


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
		<form method="post" action="/OrderGudok/set">
			<div class="container">
				<div class="row goods">
					<div class="col">
						<input type="hidden" name="productQuantity" id="quantity"	value="1"> 
						<input type="hidden" name="productPrice"	id="productPrice" value="${param.productPrice}"> 
						<input type="hidden" name="productImg" id="productImg"	value="${param.productImg}"> 
						<input type="hidden" name="productName" id="productName" value="${param.productName}">
						<input type="hidden" name="gudokBool${status.index}" value="false"> 
						<input	type="hidden" name="PageURL${status.index}" value="/OrderGudok/view">
						<div class="" id="" style="width: 500px;">
							<img src="${param.productImg}" name="productImg" alt="상품이미지" id="imgGoodsBigImage" width="300" height="">
						</div>
					</div>
					<div class="col">
						<ul class="goods_table">
							<li class="modelCode">
								<dl class="modelName">
									<dt>상품명</dt>
									<dd>
										<span class="foL" name="productName">${param.productName}</span>
									</dd>
								</dl>
							</li>
							<!-- 가격영역 -->
							<li class="priceArea">
								<dt>판매가</dt>
								<dd>
									<span class="price product-text" name="productPrice">${param.productPrice}</span><span
										class="won"></span>
								</dd>
							</li>
							<li>
								<dl>
									<dt>주문수량</dt>
									<dd>
										<div class="center">
											<a href="javascript:void(0);" class="a-order"
												id="minusButton"> <img
												src="http://static1.e-himart.co.kr/resources/layout/images/btn/qtyDownDisable.png"
												alt="수량감소">
											</a> <span id="result" style="padding: 10px;">1</span> <a
												href="javascript:void(0);" class="a-order" id="plusButton">
												<img
												src="http://static2.e-himart.co.kr/resources/layout/images/btn/qtyUp.gif"
												alt="수량증가">
											</a>
										</div>
									</dd>
								</dl>
							</li>

							<li class="priceArea">
								<dt>총 금액</dt>
								<dd>
									<span id="salePrice" class="product-text">${param.productPrice}</span>
								</dd>
							</li>
							<c:set var="doneLoop" value="false" />
							<c:set var="Exist" value="false" />
							<c:forEach items="${findGudok}" var="gudok">
								<c:if test="${not doneLoop}">
									<c:choose>
										<c:when test="${gudok.productName eq param.productName}">
											<c:set var="Exist" value="true" />
											<c:set var="doneLoop" value="true" />
										</c:when>
										<c:when test="${gudok.productName ne param.productName}">
											<c:set var="Exist" value="false" />
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${Exist}">
									<button type="submit" aria-haspopup="dialog"
										onclick="Input_PageURL(PageURL${status.index}, gudokBool${status.index}, ${Exist})"
										class="btn btn-lg button" style="margin-right: 10px">구독취소</button>
								</c:when>
								<c:when test="${not Exist}">
									<button type="submit" aria-haspopup="dialog"
										onclick="Input_PageURL(PageURL${status.index}, gudokBool${status.index}, ${Exist})"
										class="btn btn-lg button" style="margin-right: 10px">구독하기</button>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							</li>
						</ul>

					</div>
				</div>

			</div>
			<div class="container">
				<div class="goods">
					<p>
						<img style="width: 100%; height: auto;"
							src="${productDetail.productDetail}">
					</p>
					<div class="dvMoreBg" style="display: none;"></div>
				</div>
			</div>
			<div style="cursor: pointer;" onclick="window.scrollTo(0,0);">
				<img id="top"
					src="https://i.esdrop.com/d/f/bkx1JZ8DmV/oeakU2PxHU.png">
			</div>
		</form>
	</main>

	<%-- 푸터 --%>
	<%@ include file="../main/Footer.jsp"%>
	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
