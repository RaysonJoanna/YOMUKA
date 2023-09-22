<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String imageType = request.getParameter("imageType");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang = "ko">
<head>
    <meta charset="UTF-8">    

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
		
	<!-- 구글폰트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
    
    <!-- css -->
    <link rel="stylesheet" href="css/Header.css">
    <link rel="stylesheet" href="css/Footer.css">
    <link rel="stylesheet" href="/care/css/CareDetail.css">
    
    <!-- 등록용 팝업창 -->
    <script>
		$(document).ready(function() {
		  // 등록하기 버튼 클릭 시 모달 표시
		  $("#registrationButton").click(function() {
		    $("#registrationModal").modal("show");
		  });
		});
	</script>
    
    <title>YOMUKA</title>

</head>
<body>
<!-- header -->
<jsp:include page="../main/Header.jsp" />

<div class="container-xl">
  <div class="row">
    <div class="col-lg-3 mb-4 memberInfo">
 		<img class="img-fluid" src="/care/img/CareDetail.png" alt="Management">
 			<div class="container mb-5" > 
 			
		        <div class="col justify-content-center mt-5">
		            <p class="fs-4 text-center">님의 반려동물 정보</p> <!-- userName -->
		        </div>
		        
		        
		        
		    </div> 
      <a href="#">
        
      </a>
    </div>		       
    
    <div class="col-lg-8 mb-4 right" >
	    
	    <div class="col-lg-8 mb-4 supplement">
			 <h2>케어내용 ${imageType}</h2>  <!-- c:if 방법도 있음 -->
			 
			 	<% if (imageType.equals("supplement")) { %>
			        <p>영양제</p>
			    <% } else if (imageType.equals("FoodWater" )) { %>
			        <p>사료음수량</p>
			    <% } else if (imageType.equals("Play" )) { %>
			        <p>사냥산책</p>
			    <% } else if (imageType.equals("Brush" )) { %>
			        <p>빗질</p>
			    <% } else if (imageType.equals("Teeth" )) { %>
			        <p>양치</p>
			    <% } else if (imageType.equals("Management" )) { %>
			        <p>기초관리</p>
			    <% } else if (imageType.equals("Shower" )) { %>
			        <p>목욕</p>
			    <% } else if (imageType.equals("Ectoparasite" )) { %>
			        <p>외부기생충</p>
			    <% } else if (imageType.equals("HeartWorm" )) { %>
			        <p>심장사상충</p>   
			    <% } else if (imageType.equals("ToiletCat" )) { %>
			        <p>화장실청소</p> 
			    <% } else { %>
			        <p>사용 가능한 정보 없음</p>
			    <% } %>
			    
			    <!--
			    <c:choose>
			        <c:when test="${imageType == 'supplement'}">
			            <p>영양제</p>
			        </c:when>
			        <c:when test="${imageType == 'FoodWater'}">
			            <p>사료음수량</p>
			        </c:when>
			        <c:when test="${imageType == 'Play'}">
			            <p>사냥산책</p>
			        </c:when>
			        <c:when test="${imageType == 'Brush'}">
			            <p>사냥산책</p>
			        </c:when>
			        <c:when test="${imageType == 'Teeth'}">
			            <p>사냥산책</p>
			        </c:when>
			        <c:when test="${imageType == 'Management'}">
			            <p>사냥산책</p>
			        </c:when>
			        <c:when test="${imageType == 'Shower'}">
			            <p>사냥산책</p>
			        </c:when>
			        <c:when test="${imageType == 'Ectoparasite'}">
			            <p>사냥산책</p>
			        </c:when>
			        <c:when test="${imageType == 'HeartWorm'}">
			            <p>사냥산책</p>
			        </c:when>
			        <c:when test="${imageType == 'ToiletCat'}">
			            <p>사냥산책</p>
			        </c:when>
			        <c:otherwise>
			            <p>사용 가능한 정보 없음</p>
			        </c:otherwise>
			    </c:choose>
			      -->
			    
		</div>
			        
		<div class="row right-bottom">
			<div class="randomRecommend">
				<h3>여긴 관련 물품 추천 목록</h3>
			</div>
				
			<div class="row right-bottom-right">
				<label class="form-label">포인트 적립용 사진 등록하기</label>
					<input type="file" name="file" class="form-control"/>
					
				<div id="registrationModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">등록 완료</h4>
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				      </div>
				      <div class="modal-body">
				        <p>등록이 성공적으로 완료되었습니다.</p>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				      </div>
				    </div>
				  </div>
				</div>
					
				<button id="registrationButton" class="btn btn-primary">등록하기</button>
				<button type="button" onClick="history.go(-1)">뒤로가기</button>
			</div>
		</div>	    
	</div>
  </div>  
</div>
    
<!-- footer -->
<jsp:include page="../main/Footer.jsp" />
</body>

</html>