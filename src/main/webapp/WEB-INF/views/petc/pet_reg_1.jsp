<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <!-- 부트스트랩5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- 구글폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
    <!-- css -->
    <link href="/petc/css/pet_mgr.css" type="text/css" rel="stylesheet">
    <title>YOMUKA</title>
</head>
<body>
	<jsp:include page="../main/Header.jsp" />
    <div class="container" >
        <div class="col justify-content-center mt-5">
            <p class="display-6 text-center">반려하고 있는 아이는 어떤 동물인가요?</p> 
        </div> 
    </div>
    <!-- 반려동물 대분류 선택 -->
    <div class="container mb-5">
        <div class="row gap-5 mt-5">
            <div class="col justify-content-center">
                <a href="/yomuka/petc/register2?petKind=dog"><img class="img-fluid mb-2" src="/petc/img/selectDog.png" alt="Dog"></a>
                <p class="fs-4 text-center mt-2">강아지</p>
            </div>
            <div class="col justify-content-center">
                <a href="/yomuka/petc/register2?petKind=cat"><img class="img-fluid mb-2" src="/petc/img/selectCat.png" alt="Cat"></a>
            	<p class="fs-4 text-center mt-2">고양이</p>
            </div>
            <div class="col justify-content-center">
                <a href="/yomuka/petc/register2?petKind=other"><img class="img-fluid mb-2" src="/petc/img/selectOther.png" alt="Other"></a>
               	<p class="fs-4 text-center mt-2">다른 동물</p>
                <p class="text-center mt-2" id="otherAni_desc">※ 베타버전입니다. 제공되지 않는 서비스가 다소 있습니다.</p>
            </div>
        </div>  
    </div>
    <jsp:include page="../main/Footer.jsp" />
</body>
</html>