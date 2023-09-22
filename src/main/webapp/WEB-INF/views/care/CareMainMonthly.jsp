<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang = "ko">
<head>
    <meta charset="UTF-8">    

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
	<!-- <script src="js/jquery.bxslider.min.js"></script> -->
	<script src="//cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
	
	<!-- 구글폰트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
    <!-- css -->
    <link rel="stylesheet" href="css/Header.css">
    <link rel="stylesheet" href="css/Footer.css">
    <link rel="stylesheet" href="/care/css/CareDetail.css">
    
    <!-- 화장실 청소는 고양이만! -->
    <script type="text/javascript">
    $(document).ready(function() {
    	  // 이미지를 클릭할 때
    	  $(".onlyCat").click(function(event) {
    	    // 클릭된 이미지의 data-animal-kind 속성 값을 가져옴
    	    var ToiletCat = $(this).find("img").data("animal-kind");
    	    
    	    // kind가 "cat"인 경우에만 CareDetail 페이지를 열도록 확인
    	    if (ToiletCat === "cat") {
    	      // CareDetail 페이지 열기
    	      window.location.href = "CareDetail?imageType=ToiletCat";
    	    } else {
    	      // 다른 종류의 동물일 경우에는 아무 작업도 하지 않음
    	      event.preventDefault(); // 기본 동작(링크 이동) 방지
    	    }
    	  });
    	});
    </script>
    
    <title>YOMUKA</title> 
    
</head>
<body>
<!-- header -->
<jsp:include page="../main/Header.jsp" />
 
<div class="container">
  <div class="row">
    <div class="col-lg-4 col-md-6 mb-4 custom-div"> 
      <a href="CareDetail?imageType=Ectoparasite">
        <img class="img-fluid" src="/care/img/CareDetail.png" alt="Ectoparasite">
      </a>
    </div>

    <div class="col-lg-4 col-md-6 mb-4 custom-div">
      <a href="CareDetail?imageType=HeartWorm">
        <img class="img-fluid" src="/care/img/CareDetail.png" alt="HeartWorm">
      </a>
    </div> 

    <div class="col-lg-4 col-md-6 mb-4 custom-div"> 
      <a href="CareDetail?imageType=ToiletCat" class="onlyCat">
        <img class="img-fluid" src="/care/img/CareDetail.png" alt="ToiletCat" data-animal-kind="cat">
      </a>
    </div>
    
  </div>
</div>



<!-- footer -->  
<jsp:include page="../main/Footer.jsp" />
</body>

</html>