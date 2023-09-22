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
<link rel="stylesheet" href="/main/css/Signup.css">
<!-- 구글폰트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>
<script>
    function validateForm() {
        var inputs = document.getElementsByTagName('input');
        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].value.trim() === '') {
                alert('입력하지 않은 값이 있습니다. 전부 입력해주세요.');
                return false;
            }
        }
        return true;
    }
</script>
<body>
<jsp:include page="Header.jsp"></jsp:include>

<div class="container1">	
			<a class="navbar-brand d-flex align-items-center">
                <img src="/main/img/join_logo.png" alt="Logo" width="300">
            </a>
    <c:if test="${not empty errorMessage}">
      <div class="alert alert-danger" role="alert">
          ${errorMessage}
      </div>
    </c:if>
	<form action="/yomuka/register" method="post" onsubmit="return validateForm();">
		<div class= pwd>
			<label class="form-label" for="memberid">아이디</label> 
			<input class="form-control" type="text" name="memberid" id="memberid" placeholder="아이디는 꼭 기억해주세요!"/>
		</div>
		<div class= pwd>
			<label class="form-label" for="password">비밀번호</label> 
			<input class="form-control" type="password" name="password" id="password" placeholder="대소문자 구분"/>
		</div>
		<div class= pwd>
			<label class="form-label" for="name">이름</label> 
			<input class="form-control" type="text" name="name" id="name" />
		</div>
		<div class= pwd>
			<label class="form-label" for="phone">전화번호</label> 
			<input class="form-control" type="text" name="phone" id="phone" />
		</div>
		<div class= pwd>
			<label class="form-label" for="birth">생년월일</label> 
			<input class="form-control" type="date" name="birth" id="birth" />
		</div>
		<div class= pwd>
			<label class="form-label" for="gender">성별</label> 
			<select class="form-control" name="gender" id="gender">
				<option value="M">남성</option>
				<option value="F">여성</option>
	</select>
		</div>
		<div class= pwd>
			<label class="form-label" for="email">이메일</label> 
			<input class="form-control" type="email" name="email" id="email" placeholder="@를 포함한 도메인을 전부 입력해주세요" />


		</div>
		<div class= pwd>
			<label class="form-label" for="address">주소</label> 
			<input class="form-control" type="text" name="address" id="address" />
		</div>
		<button class="btn btn btn-secondary" type="submit">가입하기</button>
		<button type="button" class="btn3 btn btn-secondary" onclick="location.href='/yomuka/main'" >메인화면</button>
		</form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>