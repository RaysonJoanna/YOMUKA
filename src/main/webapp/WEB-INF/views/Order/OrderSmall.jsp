<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<div class="common container" style="text-align: left">
			<a href="/OrderDog"><button class="btn btn-lg button"
					style="margin-top: 5px;">강아지</button></a> <a href="/OrderCat"><button
					class="btn btn-lg button" style="margin-top: 5px;">고양이</button></a>
			<button class="btn btn-lg button" style="margin-top: 5px;"
				onclick="animal('small')">소동물</button>
			<br>
			<%-- 강아지 --%>
			<form id="productForm" action="/OrderSmall/product" method="get">
				<input type="hidden" id="productTypeInput" name="productType">
			</form>
			<%-- 소동물 --%>
			<button class="btn btn-lg button small button-button"
				onclick="setProductType('사료')">사료</button>
			<button class="btn btn-lg button small button-button"
				onclick="setProductType('베딩')">베딩</button>
			<button class="btn btn-lg button small button-button"
				onclick="setProductType('이갈이')">이갈이</button>
		</div>
		<div class="album container-fluid">
			<div class="common">
				<div class="row">
					<%-- JSTL로 데이터 출력 --%>
					<c:forEach items="${smallProduct}" var="product">
						<div class="col card-margin center">
							<form method="post" action="/OrderGudok/set">
								<input type="hidden" name="productQuantity" id="goodsRepImgUrl"
									value="1"> <input type="hidden" name="productName"
									value="${product.productName}"> <input type="hidden"
									name="productImg" value="${product.productImg}"> <input
									type="hidden" name="productPrice"
									value="${product.productPrice}"> <input type="hidden"
									name=gudokBool${status.index} value="false"> <input
									type="hidden" name=PageURL${status.index}
									value="/OrderGudok/view">
								<div class="card card-default">
									<a class="a-order"
										href="/OrderDetail?productNum=${product.productNum}&productImg=${product.productImg}&productName=${product.productName}&productPrice=${product.productPrice}">
										<img class="card-image" src="${product.productImg}">
									</a>
									<div class="card-body">
										<b><a class="a-order" href="/OrderDetail">${product.productName}</a></b>
										<p class="card-text product-text">${product.productPrice}원</p>
										<div class="d-flex justify-content-between align-items-center">
											<c:set var="doneLoop" value="false" />
											<c:set var="Exist" value="false" />
											<c:forEach items="${findGudok}" var="gudok">	
												<c:if test="${not doneLoop}">
													<c:choose>
														<c:when test="${gudok.productName eq product.productName}">
															<c:set var="Exist" value="true" />
															<c:set var="doneLoop" value="true" />
														</c:when>
														<c:when test="${gudok.productName ne product.productName}">
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
										</div>
									</div>
								</div>
							</form>
						</div>
					</c:forEach>
				</div>

			</div>
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
