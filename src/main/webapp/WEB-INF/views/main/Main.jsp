<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요무까</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/main/css/Main.css">
<!-- 구글폰트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
	
</head>
<body>
<!-- 헤더 -->
<jsp:include page="Header.jsp"></jsp:include>
 <c:if test="${not empty successMessage}">
    <div class="alert alert-success">${successMessage}</div>
</c:if>
	<div class="d-flex">
  <div class="main1 ">
  		<img src="/main/img/banner1.png" alt="banner1" class="img-fluid">
  		<img src="/main/img/banner2.png" alt="banner2" class="img-fluid">
  		<img src="/main/img/banner3.png" alt="banner3" class="img-fluid">
  		<img src="/main/img/number1.png" alt="number1" class="img-fluid">
  		<img src="/main/img/number2.png" alt="number2" class="img-fluid">
  </div>
     <div class="main2 container" style="width: 1200px; padding-left: 0px; padding-right:0px" >
                    <div class="row1 d-flex justify-content-center align-items-center" data-bs-ride="carousel" data-bs-interval="2500">
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img src="/main/img/upper1.png" alt="upper1" class="img-fluid mx-auto">
							</div>
							<div class="carousel-item">
								<img src="/main/img/upper2.png" alt="upper2" class="img-fluid mx-auto">
							</div>
							<div class="carousel-item">
								<img src="/main/img/upper3.png" alt="upper3" class="img-fluid mx-auto">
							</div>
							<div class="carousel-item">
								<img src="/main/img/upper4.png" alt="upper4" class="img-fluid mx-auto">
						</div>
                    </div>
                   </div>
                    <hr>
                    <div class="row2 row" style="width:1188px">
   					    <div class="col-4 ps-0 pe-0">
					       <div class="card" style="width: 396px; height: 480px;">
							  <img src="https://img.catpre.com/mobile/catpre/product/39/38451_detailView_01410499.png" class="card-img-top" style="height: 250px;"alt="...">
							  <div class="card-body">
							    <h5 class="card-title">아카나 패시피카 캣 4.5kg</h5>
							    <a href="https://img.catpre.com/mobile/catpre/product/39/38451_desc_715147.jpg" class="card-link">설명보기</a>
							  </div>
							  <ul class="list-group list-group-flush">
							    <li class="list-group-item">가격 : 63900원 </li>
							  </ul>
							  <div class="card-body" style="padding-bottom: 0; height: 41px;">
							    <a href="#" class="card-link">구매하러가기</a>
							  </div>
							</div>
					    </div>
   					    <div class="col-4 ps-0 pe-0">
					       <div class="card" style="width: 396px; height: 480px;">
							  <img src="https://img.dogpre.com/mobile/dogpre/product/58/57547_detailView_01922506.png" class="card-img-top" style="height: 250px;"alt="...">
							  <div class="card-body">
							    <h5 class="card-title">로얄캐닌 미니 인도어 어덜트 8.7kg</h5>
							    <a href="https://img.dogpre.com/web/dogpre/product/58/57547_desc_923426.jpg" class="card-link">설명보기</a>
							  </div>
							  <ul class="list-group list-group-flush">
							    <li class="list-group-item">가격 : 79900원 </li>
							  </ul>
							  <div class="card-body" style="padding-bottom: 0; height: 41px;">
							    <a href="#" class="card-link">구매하러가기</a>
							  </div>
							</div>
					    </div>
   					    <div class="col-4 ps-0 pe-0">
					       <div class="card" style="width: 396px; height: 480px;">
							  <img src="https://dochiya.co.kr/web/product/big/dnjsifjqjd_1628.jpg" class="card-img-top" style="height: 250px;"alt="...">
							  <div class="card-body">
							    <h5 class="card-title">스위트 프리미엄500g</h5>
							    <a href="https://dochiya.co.kr/web/open_product/medium/super%20premium-de.jpg" class="card-link">설명보기</a>
							  </div>
							  <ul class="list-group list-group-flush">
							    <li class="list-group-item">가격 : 9000원 </li>
							  </ul>
							  <div class="card-body" style="padding-bottom: 0; height: 41px;">
							    <a href="#" class="card-link">구매하러가기</a>
							  </div>
							</div>
					    </div>
					</div>
                </div>	
  <div class="main3">
  <c:if test= "${loggedUser.name == null}"> <? 로그인전 ?> 
  		<div class="card">
		  <img src="/main/img/login1.png" class="card-img-top" alt="...">
		  <div class="card-body">
		  	<div>
		    	<h5 class="card-title text-center">요무까에 로그인하기</h5>
	    	</div>
		    <div>
			    <button type="button" class="btn1 btn btn-success" onclick="location.href='/yomuka/LoginPage'">로그인</button>
			    <button type="button" class="btn2 btn btn-success" onclick="location.href='/yomuka/Signup'">회원가입</button>
		    </div>
		    <div>
		    	<button type="button" class="btn3 btn btn-link text-center" onclick="location.href='/yomuka/FindPage'"> 집사! 비밀번호 뭔지 잊어먹었지! </button>
		    </div>
		  </div>
		</div>
	</c:if>
	<c:if test= "${loggedUser.name != null}"> <? 로그인후 ?>
  		<div class="card">
		  <img src="${loggedUser.profile}" class="card-img-top" alt="우리아기사진.">
		  <div class="card-body">
		  	<div>
		    	<h5 class="card-title text-center">${loggedUser.name} 님 환영합니다.</h5>
		    	<h6 class="text-center"> ${loggedUser.name} 님의 포인트는 ${loggedUser.point} 점 입니다.</h6>
	    	</div>
		    <div class="btn-group mb-3 mx-3" role="group" aria-label="Basic example">
			  <button type="button" class="btn btn-success" onclick="location.href='/yomuka/Logout'">로그아웃</button>
			  <button type="button" class="btn btn-success" onclick="location.href='/yomuka/MyPagepage'">마이페이지</button>
			  <button type="button" class="btn btn-success" onclick="location.href='/yomuka/ProfilePage'">사진등록</button>
			</div>
		    <c:if test= "${loggedUser.admin == 'Y'}">
		    	<button type="button" class="btn1 btn btn-success" onclick="location.href='/yomuka/AdminPage'">관리자용</button>
		    </c:if>
		  </div>
		</div>
	</c:if>
		<div>
			<a class="navbar-brand d-flex align-items-center"  onclick="location.href='/yomuka/board/notice'">
                <img src="/main/img/board.png" alt="board" width="300">
            </a>
		</div>
	</div>
</div>

 <jsp:include page="Footer.jsp"></jsp:include>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
