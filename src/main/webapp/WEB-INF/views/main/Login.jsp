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
                <img src="/main/img/login_logo.png" alt="Logo" width="300">
            </a>
      <c:if test="${not empty errorMessage}">
    <p class="alert alert-danger">${errorMessage}</p>
		</c:if>
	<form action="/yomuka/Login" method="post">
		<div class= id>
			<label class="form-label" for="memberid">아이디</label> 
			<input class="form-control" type="text" name="memberid" id="memberid" />
		</div>
		<div class= pwd>
			<label class="form-label" for="password">비밀번호</label> 
			<input class="form-control" type="password" name="password" id="password" />
		</div>
		<button class="btn btn btn-secondary" type="submit">로그인</button>
		<button type="button" class="btn3 btn btn-secondary" onclick="location.href='/yomuka/Signup'" >회원가입</button>
		<button type="button" class="btn3 btn btn-secondary" onclick="location.href='/yomuka/FindPage'" >비밀번호찾기</button>
		</form>
		
</div>
<jsp:include page="Footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
