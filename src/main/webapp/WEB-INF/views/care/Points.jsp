<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.yomuka.yomuka.care.PointsBean" %>
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
    
    <script>
    $(document).ready(function() {
        // 포인트 테이블을 업데이트하는 함수
        function updatePointsTable() {
            $.ajax({
                type: "POST",
                url: "/your-endpoint-url", // 실제 서버 측 엔드포인트로 교체
                data: { /* 이미지 등록에 필요한 데이터 */ },
                dataType: "json", // 서버가 JSON을 반환하는 것으로 가정
                success: function(data) {
                    // 현재 테이블 데이터를 지웁니다.
                    $("#pointTableBody").empty();

                    // 받은 데이터를 반복하며 테이블을 채웁니다.
                    $.each(data, function(index, item) {
                        var row = "<tr>" +
                            "<td>" + item.date + "</td>" +
                            "<td class='right'>" + item.points + "</td>" +
                            "<td class='left'>" + item.description + "</td>" +
                            "</tr>";
                        $("#pointTableBody").append(row);
                    });
                },
                error: function(xhr, status, error) {
                    console.error("오류: " + error);
                }
            });
        }

        // "등록하기" 버튼을 클릭하면 이미지 등록 후 포인트 테이블을 업데이트합니다.
        $("#registrationButton").click(function() {
            // 이미지 등록을 수행합니다.

            // 성공적으로 등록한 후 포인트 테이블을 업데이트합니다.
            updatePointsTable();
            
            // 등록 모달을 표시합니다.
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
                    <p class="fs-4 petInfo">"${loggedUser.name}"님의 반려동물 정보</p>
                    <img alt="petImg" src="${loggedUser.profile}">
                    <p class="fs-4 text-center"></p>
                </div>
                <script type="text/javascript" src="..." id="..." data-name="..."></script>
            </div> 
      <a href="#">
        
      </a>
    </div>
    

    <div class="col-lg-8 mb-4 pointBoard">
	    <table class="rn_table" border="1" summary="">
			<h3>포인트 내역</h3>
			<p>${loggedUser.name}님의 총 포인트 : ${pointsBean.totalPoints}</p>	
			<colgroup>
				<col style="width:15%">
				<col style="width:15%">
				<col style="width:25%">
				<col style="width:auto">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">적립 날짜</th>
                    <th scope="col">적립금</th>
                    <th scope="col">내용</th>
                </tr>
            </thead>
			<tbody class=" center" id="pointTableBody">
			<tr class="xans-record">
				<td>날짜 들어가</td>
	            <td class="right">적립 포인트 들어가</td>
	            <td class="left">이거 사떠</td>
	        </tr>
			
			</tbody>
		</table>	

	    

    </div>   

  </div>
</div>

<!-- footer -->

</body>
<footer>
	<jsp:include page="../main/Footer.jsp" />	 
</footer>
</html>