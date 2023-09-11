<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>요무까</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/main/css/MyPage.css">
<!-- 구글폰트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<script>
    window.onload = function() {
        const form = document.querySelector('form');
        
        form.addEventListener('submit', function(event) {
            if (document.getElementById('password').value === "") {
                document.getElementById('password').value = "${loggedUser.password}";
            }

            if (document.getElementById('name').value === "") {
                document.getElementById('name').value = "${loggedUser.name}";
            }

            if (document.getElementById('phone').value === "") {
                document.getElementById('phone').value = "${loggedUser.phone}";
            }

            if (document.getElementById('email').value === "") {
            	document.getElementById('email').value = "${loggedUser.phone}"
            }

            // address
            if (document.getElementById('address').value === "") {
                document.getElementById('address').value = "${loggedUser.address}";
            }
        });
    }
</script>
	
</head>

<body>
<jsp:include page="Header.jsp"></jsp:include>

<div class="container1">	
			<a class="navbar-brand d-flex align-items-center">
                <img src="/main/img/mypage_logo.png" alt="Logo" width="300">
            </a>
           
	<form action="/yomuka/UpdateProfile" method="post">
		<div class= pwd>
			<label class="form-label" for="memberid">아이디</label> 
			<input class="form-control" type="text" name="memberid" id="memberid" placeholder="현재 ID : ${loggedUser.memberid} / 아이디는 수정할수 없습니다." disabled/>
		</div>
		<p class="text-center"> 변경을 원하지 않으시면 입력을 건너뛰어 주시면됩니다.<br>
			회색으로 보이는 정보는 현재 고객님 정보입니다.</p>
		<div class= pwd>
			<label class="form-label" for="password">비밀번호</label> 
			<input class="form-control" type="password" name="password" id="password" placeholder="${loggedUser.password}"/>
		</div>
		<div class= pwd>
			<label class="form-label" for="name">이름</label> 
			<input class="form-control" type="text" name="name" id="name" placeholder="${loggedUser.name}"/>
		</div>
		<div class= pwd>
			<label class="form-label" for="phone">전화번호</label> 
			<input class="form-control" type="text" name="phone" id="phone" placeholder="${loggedUser.phone}"/>
		</div>
		<div class= pwd>
			<label class="form-label" for="email">이메일</label> 
			<input class="form-control" type="email" name="email" id="email" placeholder="${loggedUser.email}" />


		</div>
		<div class= pwd>
			<label class="form-label" for="address">주소</label> 
			<input class="form-control" type="text" name="address" id="address" placeholder="${loggedUser.address}"/>
		</div>
		<button class="btn btn btn-secondary" type="submit">수정하기</button>
		<button type="button" class="btn3 btn btn-secondary" onclick="location.href='/yomuka/main'" >뒤로가기</button>
		</form>
		
</div>
<jsp:include page="Footer.jsp"></jsp:include>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
