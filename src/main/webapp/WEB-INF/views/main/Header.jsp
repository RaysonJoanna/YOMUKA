<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<link rel="stylesheet" href="/main/css/Header.css">
<script type="text/javascript">
	function clickPetc(){
		let memberid = "<%= (String)session.getAttribute("memberid") %>";
		if(memberid == "null"){
			alert("로그인이 필요한 메뉴입니다");
			return false;
		} else {
			window.location.href = 'http://localhost:8085/yomuka/petc';
		}
	}
</script>
<header>
    <nav class="navbar navbar-expand-lg">
        <div class="container d-flex flex-column align-items-center"> 
            <a class="navbar-brand d-flex align-items-center" onclick="location.href='/yomuka/main'">
                <img src="/main/img/yomuka_cute.png" alt="Logo" width="300">
            </a>

            <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
		</nav>
		<nav class="navbar1 navbar-expand-lg">
        <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
            <ul class="nav nav-pills nav-fill w-100">
                <li class="nav-item"><a class="nav-link" onclick="clickPetc()" id="petc">반려동물정보</a></li>
                <li class="nav-item"><a class="nav-link" onclick="location.href='/yomuka/caremain'">반려동물케어</a></li>
                <li class="nav-item"><a class="nav-link" onclick="location.href='/yomuka/ord'">정기물품구매</a></li>
                 <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="dropdownMenu" role="button" data-bs-toggle="dropdown" aria-expanded="true">
                        진료예약관리
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">
                        <li><a class="dropdown-item" onclick="location.href='/yomuka/rsv/reserve'">진료예약하기</a></li>
                        <li><a class="dropdown-item" onclick="location.href='/yomuka/rsv/rsvListStart'">진료기록조회</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
