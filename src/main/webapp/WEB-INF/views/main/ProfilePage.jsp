<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/main/css/Login.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/main/css/Login.css">
<!-- 구글폰트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>

<div class="container1">	
			<a class="navbar-brand d-flex align-items-center">
            </a>
      <c:if test="${not empty errorMessage}">
    <p class="alert alert-danger">${errorMessage}</p>
		</c:if>
	<form action="/yomuka/ProfileChange" method="post" enctype="multipart/form-data">
		<div class= pwd>
			<p class="text-center"> 사진의 규격은 940 x 540 입니다. 더 큰 사진을 넣을경우 <br>
			사진의 크기가 작아져 이미지가 이상해 질 수 있습니다.
			</p> 
			<input class="form-control" type="file" name="imgFile"/>
		</div>
		<button class="btn btn btn-secondary" type="submit" value="Upload">등록하기</button>
		<button type="button" class="btn3 btn btn-secondary" onclick="location.href='/yomuka/main'" >메인화면</button>
		</form>
		
</div>
<jsp:include page="Footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
