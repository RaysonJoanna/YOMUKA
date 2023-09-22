<%@page import="ch.qos.logback.core.recovery.ResilientSyslogOutputStream"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="org.springframework.web.bind.annotation.SessionAttribute" %>
<%@ page import="com.yomuka.yomuka.petc.DTO.Pet" %>
<%@ page import="com.yomuka.yomuka.petc.service.PetCalc" %>
<%@ page import="java.util.ArrayList" %>
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
    <link href="/petc/css/pet_mgr.css" rel="stylesheet">
    <title>YOMUKA</title>
</head>
<body>   
	 <%
	 	ArrayList<Pet> petList = (ArrayList<Pet>)request.getAttribute("petList");
        String memberid = (String)session.getAttribute("memberid");
        if(memberid == null){
    %>
    <script type="text/javascript">
        alert("로그인 후 이용가능합니다.");
		location.href("http://localhost:8085/yomuka/main");        
    </script>
    <%
        }
    %>
	<jsp:include page="../main/Header.jsp" />
    <div class="container mb-5" > 
        <div class="col justify-content-center mt-5">
            <p class="fs-4 text-center"><%= memberid %>님의 반려동물</p>
        </div>
        <% if(petList == null){ %>
        <div class="container col-10">
    		<div class="row mt-5 gap-4">
       			<div class="card col">
			        <a id="paran" class="mx-auto" href="/yomuka/petc/register1">
			            <div class="row mx-auto mt-5">
			                <img src="/petc/img/nopet.png" class="card-img-top img-thumbnail rounded-circle" alt="myPet1" style="width: 15rem; height: 15rem;">
			            </div>
			            <div class="row mx-auto mt-5">
			                <p id="paranletter" class="fs-3 text-center">반려동물 등록</p>
			            </div>
			        </a>
			    </div>
			    <div class="col"></div>
			    <div class="col"></div>
		    </div>
	    </div>
	    <% } %>
        <!-- 한줄 -->
        <% if(petList != null){ 
    	   int lastCard = petList.size();
        %>
 		<div class="container col-10">
       		<% for(int i=0; i<petList.size(); i++){
       				if(i%3 == 0) {  %>
        		<div class="row mt-5 gap-4">
       		<%
       				}
       		%>
        		<div class="card col">
    				<a href="update?petNum=<%= petList.get(i).getPetNum()%>" class="mx-auto">
       				<% if(petList.get(i).getPetProfile() == null){ %>
            			<img src="/petc/img/nopet.png" class="card-img-top img-thumbnail rounded-circle mx-auto mt-5" alt="nopetphoto" style="width: 15rem; height: 15rem;">
       				<% } else { %>
            			<img src="/petc/image/<%= petList.get(i).getPetProfile()%>" class="card-img-top img-thumbnail rounded-circle mx-auto mt-5" alt="myPet1" style="width: 15rem; height: 15rem;">
       				<% } %>
    				</a>
				    <div class="card-body">
				        <ul class="list-group list-group-flush">
				            <li class="list-group-item">이름 : <%= petList.get(i).getPetName() %></li>
				            <li class="list-group-item">나이 : <%= petList.get(i).getPetAge() %><%= petList.get(i).getPetAgeUnit() %></li>
				            <li class="list-group-item">몸무게 : <%= petList.get(i).getPetWeight() %>kg</li>
				            <li class="list-group-item">중성화 : <%= petList.get(i).getPetNeu() %></li>
				            <li class="list-group-item">주의질병 : <%= petList.get(i).getDiesease() %></li>
				            <li class="list-group-item">품종 : <%= petList.get(i).getBreed() %></li>
				            <!-- 추천 양 표시 -->
							<%
				            	if(!petList.get(i).getKind().equals("other")){
					            	PetCalc c = new PetCalc();
					            	int recommendFood = c.calcFood(petList.get(i));
					            	int recommendWater = c.calcWater(petList.get(i));
				            %>
				            <li class="list-group-item">
				                <div class="container" style="padding-left: 0;">
				                    <img src="/petc/img/pet-food.png" class="col-3 d-inline-block">
				                    <p class="d-inline-block">추천 칼로리 <%= recommendFood %>kcal</p>
				                </div>
				            </li>
				            <li class="list-group-item">
				                <div class="container" style="padding-left: 0;">
				                    <img src="/petc/img/water.png" class="col-3 d-inline-block">
				                    <p class="d-inline-block">추천 음수량 <%= recommendWater %>ml</p>
				                </div>
				            </li>
				            <%
				            	}
				            %>
				        </ul>
			        </div>
			    </div>  
				    <% if(i%3 ==2 ){ %>  
				    	</div>
			    	<% } %>
			    <%
       				}
			    %>        
			    <!-- 추가카드 -->
			    <% if(lastCard%3 ==0){ %>
		    		<div class="row mt-5 gap-4">
	    		<% } %>
				    <div class="card col">
				        <a id="paran" class="mx-auto" href="/yomuka/petc/register1">
				            <div class="row mx-auto mt-5">
				                <img src="/petc/img/nopet.png" class="card-img-top img-thumbnail rounded-circle" alt="myPet" style="width: 15rem; height: 15rem;">
				            </div>
				            <div class="row mx-auto mt-5">
				                <p id="paranletter" class="fs-3 text-center">반려동물 등록</p>
				            </div>
				        </a>
				    </div>
				<% if(lastCard%3 ==1){ %>   
					<div class="col"></div>
				<% } %>
	 			<% if(lastCard%3 ==0){ %>
	 						<div class="col"></div>
				   			<div class="col"></div>
		    		</div>
	    		<% } %>
	        </div>
	        <% } %>
 		</div>
    </div>
    <jsp:include page="../main/Footer.jsp"/>
</body>
</html>