<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.yomuka.yomuka.petc.DTO.Pet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
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
    <link href="/petc/css/pet_mgr.css" rel="stylesheet">
    <!-- jquery -->
    <script src="/petc/js/jquery.js"></script>
    <!-- 멀티셀렉트박스 -->
	<script src="/petc/js/jquery.sumoselect.min.js"></script>
    
   	<script type="text/javascript" src="/petc/js/validation.js"></script>
    
    <title>YOMUKA</title>
    <script type="text/javascript">
        function setThumbnail(event){
            var reader = new FileReader();

            reader.onload = function(event){
                var img = document.querySelector("#img");
                img.setAttribute("src", event.target.result);
            };
            reader.readAsDataURL(event.target.files[0]);
        }
    </script>
</head>
<body>
<jsp:include page="../main/Header.jsp" />
    <!-- 반려동물 상세 정보 입력 -->
    <div class="container col-8 mb-5">
        <form action="/yomuka/petc/add" name="pet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="kind" value="${kind}"/>
        <!-- 반려동물 프로필 사진 -->
        <div class="row justify-content-center mt-5">
            <img class="img-thumbnail img-fluid rounded-circle" src="/petc/img/reg_pet.png"
                 id="img" alt="addPetDetail" style="width: 20rem; height: 20rem; position:relative;" >
            <input class="upload" type="file" name="file" onChange="setThumbnail(event);">
        </div>
        <!-- yomuka/petc -->
        <div class="mt-5">
            <!-- 반려동물 이름/나이 입력칸 -->
            <div class="row justify-content-center gap-5">
                <div class="col-4">
                    <div class="input-group">
                        <span class="input-group-text">이름</span>
                        <input type="text" id="petName" name="petName" class="form-control" oninput="checkName()">
                    </div>
                </div>
                <div class="col-4">
                    <div class="input-group">
                        <span class="input-group-text">나이</span>
                        <input type="text" id="petAge" name="petAge" class="form-control">
                        <select class="form-select" id="petAgeUnit" name="petAgeUnit" aria-label="Default select example" style="text-align-last: end;">
                            <option value="살" selected>살</option>
                            <option value="개월">개월</option>
                        </select>
                    </div>
                </div>
            </div>
            <!-- 반려동물 몸무게/중성화여부 입력칸 -->
            <div class="row justify-content-center gap-5 mt-5">
                <div class="col-4">
                    <div class="input-group">
                        <span class="input-group-text">몸무게</span>
                        <input type="text" id="petWeight" class="form-control" name="petWeight" placeholder="소수점 두자리까지(kg)" >
                    </div>
                </div>
                <div class="col-4">
                    <div class="input-group">
                        <span class="input-group-text">중성화</span>
                        <div class="my-auto form-control">
                            <div class="form-check d-inline-block ms-3">
                                <input class="form-check-input " type="radio" name="petNeu" value="O" id="flexRadioDefault" checked>
                                <label class="form-check-label" for="flexRadioDefault">
                                했어요
                                </label>
                            </div>
                            <div class="form-check d-inline-block ms-3">
                                <input class="form-check-input" type="radio" name="petNeu" value="X" id="flexRadioDefault2">
                                <label class="form-check-label" for="flexRadioDefault2">
                                안했어요
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 반려동물 주요 질병여부/품종 입력칸 -->
            <div class="row justify-content-center gap-5 mt-5">
                <div class="col-4">
                    <div class="input-group">
                       <span class="input-group-text">주요질병</span>
                        <c:choose>
                            <c:when test="${kind != 'other'}">
                                <select class="form-select" id="diesease" name="diesease" aria-label="Default select example" style="text-align-last: center;">
                                    <option value="none" selected>없음</option>
                                    <c:forEach items="${dieseaseList}" var="diesease">
                                		<option value="${diesease}">${diesease}</option>
                           			</c:forEach>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <select class="form-select" name="diesease" aria-label="Default select example" style="text-align-last: center;">
                                    <option value="없음" selected>서비스 미제공</option>
                                </select>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="col-4">
                    <div class="input-group">
                        <span class="input-group-text">품종</span>
                        <select class="form-select" name="breed" aria-label="Default select example" style="text-align-last: center;">
                            <option value="모름" selected>모름</option>
                            <c:forEach items="${breedList}" var="breed">
                                <option value="${breed}">${breed}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center mt-5">
                <div class="mx-auto d-flex justify-content-center">
                    <button id="submitBtn" type="button" class="mx-auto col-3" onClick="validatePetInfo()">등록</button>
                </div>
            </div>
        </div>
     	</form>
    </div>
    <script type="text/javascript">
   		let inputName = $('#petName');
		let allPetName = '${allPetName}';
		let petNameList = allPetName.split(',');
    </script>
    <jsp:include page="../main/Footer.jsp"/>
</body>
</html>