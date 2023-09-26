<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약하기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css"/>
<link rel="stylesheet" type="text/css" href="/css/rsv.css" media="all" />

<!-- jquery 기동 후 datepicker 기동 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript" src="/libs/jquery-1.10.2.min.js"></script> <!-- 제이쿼리 라이이브러리 연동 -->  
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.ko.min.js" ></script>
<script type="text/javascript" src="/js/rsvListStart.js"></script>  
</head>
<header>  
</header>
<body>
<jsp:include page="../main/Header.jsp"></jsp:include>

	<!-- 예약내용 입력 및 수정 -->
	<div class="modal fade" id="reserveInput">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title">예약내용 신규 등록 및 수정</h5>	                
	            </div>	            
	            <div class="modal-body row">
	            	<div class="col-6 align-self-center text-start">  	
	            		<label>>> 예약ID : </label>
	            	</div>	
	            	<div class="col-6 align-self-center text-start">		            	
		            	<input type="text" id="preserveid" disabled />
	            	</div>  
	            	<div class="col-6 align-self-center text-start">  	
	            		<label>>> 멤버ID : </label>
	            	</div>	
	            	<div class="col-6 align-self-center text-start">		            	
		            	<input type="text" id="pmemberid" disabled />
	            	</div>  
	            	<div class="col-6 align-self-center text-start" >  	
	            		<label>>> 병원명 : </label>	
	            	</div>	            			  		
			  		<div class="col-6 align-self-center text-start">  		    
			  		     <select class="form-select" aria-label="병원명 선택" id="phospitalid">							  			  
							  <c:forEach var="hspItem" items="${hospitalList}" varStatus="status">
							  	 <tr >				  		
							  		<option value="${hspItem.hospitalid}">${hspItem.hospitalid} : ${hspItem.hospitalname}</option>
							  	 </tr>
							  </c:forEach>
						 </select>
			  		</div>
			  		<div class="col-6 align-self-center text-start" >  	
	            		<label>>> 예약일자 : </label>
	            	</div>	            	   
	            	<div class="col-6 align-self-center text-start" >  	
			  		    <input 	type="text" 
			  		     		class="form-control datepicker"   		     		
			  		     		aria-label="prsvdate" 
			  		     		id="prsvdate" 
			  		     		/>
			  		</div><br/>
			  		<div class="col-6 align-self-center text-start" >  	
	            		<label>>> 예약시간(ex. 10:30) : </label> 
	            	</div>			  		  
	            	<div class="col-6 align-self-center text-start" >  	
			  		    <input 	type="text" 
			  		     		class="form-control"   		     		
			  		     		aria-label="prsvtime" 
			  		     		id="prsvtime" 
			  		     		/>
			  		</div>
			  		<div class="col-6 align-self-center text-start" >  	
	            		<label>>> 예약내용(증상 등) : </label>			
	            	</div> 
					<div class="form-floating"> 
					  	<textarea class="form-control" placeholder="병 증상을 상세히 입력하세요" id="pcontent" style="height: 100px"></textarea>					  
					</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	                <button type="button" class="btn btn-primary" id="reserveSave" >저장</button>
	            </div>
	        </div>
	    </div>
	</div>
	
   <!-- 예약현황 명세조회  -->
   <div class="container w-95 mt-5 mx-auto">
      <div class="row d-flex ">
  		  <div class="col align-self-center text-center" style="min-width: 8rem;" >
  		  	<label for="From" class="form-label" >조회예약일 : &nbsp;</label>
  		  </div>
  		  <div class="col align-self-center text-center">
  		     <input type="text" 
  		     		class="form-control datepicker"   		     		
  		     		aria-label="From" 
  		     		id="From">
  		  </div>
  		  <div class="col align-self-center text-center" style="max-width: 2rem;">&nbsp;~&nbsp;</div>
  		  <div class="col align-self-center text-center">
  		    <input 	type="text" 
  		     		class="form-control datepicker"   		     		
  		     		aria-label="To" 
  		     		id="To">
  		  </div>  		  
  		  <div class="col align-self-center text-center">
  		  	<label for="Hospital" class="form-label" >병원명 : &nbsp;</label>
  		  </div>
  		  <div class="col align-self-center text-center">  		    
  		     <select class="form-select" aria-label="병원명 선택" id="hospitalid">
				  <option value="All" selected>All</option>			  
				  <c:forEach var="hspItem" items="${hospitalList}" varStatus="status">
				  	 <tr >				  		
				  		<option value="${hspItem.hospitalid}">${hspItem.hospitalid} : ${hspItem.hospitalname}</option>
				  	 </tr>
				  </c:forEach>
			 </select>
  		  </div>
  		  <div class="col align-self-center text-center">
  		  	<label for="Member" class="form-label" >회원명 : &nbsp;</label>
  		  </div>
  		  <div class="col align-self-center text-center">  		  
  		    <select class="form-select" aria-label="회원명 선택" id="memberid">
				  <option value="All" selected>All</option>			  
				  <c:forEach var="memItem" items="${memberList}" varStatus="status">
				  	 <tr >				  		
				  		<option value="${memItem.memberid}">${memItem.name} : ${memItem.memberid}</option>
				  	 </tr>
				  </c:forEach>
			</select> 
  		  </div>  		    		  
  		  <div class="col align-self-center text-center">
  		  	<button type="button" class="btn btn-secondary mb2" id="inquiry01" onClick="handleButtonClick(event)">조회</button>
  		  </div>
  		  
  		  <input type="hidden" id="loginmemberid" value="testid01">
  		  <input type="hidden" id="npage" value="${paging.pageno}">
  		  <input type="hidden" id="fromDate" value="${rsvInqStart.fromDate}">
  		  <input type="hidden" id="toDate" value="${rsvInqStart.toDate}">
  		  <input type="hidden" id="nhospitalid" value="${rsvInqStart.nhospitalid}">
  		  <input type="hidden" id="nmemberid" value="${rsvInqStart.nmemberid}">
  		  
      </div>	    
      <hr />  	   
   </div>      
   <div class="container w-95 mt-4 mx-auto">
   	  <div class="row">
	      <div class="col-10 align-self-center text-start">  	
	         <p class="h4">예약현황</p>
	      </div>
	      <div class="col-2 align-self-center text-center">  	
	         <button type="button" class="btn btn-primary reserveInput" id="openModalBtn" 
	  					value="0$#$#$#$#$#${loggedUser.memberid}">
	  			  예약등록
	  			 </button>
	      </div>
	  </div>
      <hr />
	  <table class="table table-bordered border-secondary">
	  	 <tr align="center">
	  		<th >병원명</th>
	  		<th >병원ID</th>
	  		<th >예약일</th>
	  		<th >예약시간</th>
	  		<th >예약ID</th>
	  		<th >예약자명</th>
	  		<th >회원ID</th>
	  		<th >예약내용</th>	  		
	  	 </tr>
	  	 <c:forEach var="rsvItem" items="${rsvListStart}" varStatus="status">
	  	 <tr >
	  		<td align="center">${rsvItem.hospitalname}</td>
	  		<td align="center">${rsvItem.hospitalid}</td>
	  		<td align="center">${rsvItem.rsvdate}</td>
	  		<td align="center">${rsvItem.rsvtime}</td>
	  		<td align="center">${rsvItem.reserveid} </td>
	  		<td align="center">${rsvItem.membername}</td>
	  		<td align="center">${rsvItem.memberid}</td>
	  		<td >${rsvItem.content}</td>	  		
	  		<td ><button type="button" class="btn btn-primary reserveInput" id="openModalBtn" 
	  					value="${rsvItem.reserveid}$#${rsvItem.hospitalid}$#${rsvItem.rsvdate}$#${rsvItem.rsvtime}$#${rsvItem.content}$#${rsvItem.memberid}">
	  			  예약내용수정
	  			 </button></td>
	  	 </tr>
		 </c:forEach>
	  </table>	 		 
      <hr />
       <c:if test="${error != null}">
         <div class="alert alert-warning alert-dismissible fade show mt-3" role="alert">
              에러발생 : ${error}
              <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
         </div>
       </c:if>                
	</div>          
	<div class="container w-95 mt-4 mx-auto">
		<nav aria-label="...">
		  <ul class="pagination  justify-content-center">
			  <c:if test="${paging.hasBef}">
			     <li class="page-item">
			      	<a id="b2" class="page-link" href="#" tabindex="-1" onClick="handleButtonClick(event)">&lt;&lt;</a>
			     </li>
			     <li class="page-item">
			      	<a id="b1" class="page-link" href="#" tabindex="-1" onClick="handleButtonClick(event)">&lt;</a>
			     </li> 
			  </c:if> 
			  <c:if test="${!paging.hasBef}">
			     <li class="page-item disabled">
			      	<a class="page-link" href="#" tabindex="-1">&lt;&lt;</a>
			     </li>
			     <li class="page-item disabled">
			      	<a class="page-link" href="#" tabindex="-1">&lt;</a>
			     </li> 
			  </c:if>       		    
		    
		    <c:forEach var="i" begin="${paging.pagestart}" end="${paging.pageend}">
		    	<c:if test="${i.equals(paging.pageno)}">
		    		<li class="page-item active">
		    			<a id="page${i}" class="page-link" href="#" onClick="handleButtonClick(event)">${i}</a>
		    		</li>     
		    	</c:if>    
		    	<c:if test="${!i.equals(paging.pageno)}">
		    		<li class="page-item">
		    			<a id="page${i}" class="page-link" href="#" onClick="handleButtonClick(event)">${i}</a>
		    		</li>     
		    	</c:if>    	
        	</c:forEach>
        	
        	<c:if test="${paging.hasNext}">
			    <li class="page-item">
		      		<a id="n1" class="page-link" href="#" onClick="handleButtonClick(event)">&gt;</a>
		    	</li>
		    	<li class=-"page-item">
		      		<a id="n2" class="page-link" href="#" onClick="handleButtonClick(event)">&gt;&gt;</a>
		    	</li>
			</c:if>
			<c:if test="${!paging.hasNext}">
			    <li class="page-item disabled">
		      		<a class="page-link" href="#">&gt;</a>
		    	</li>
		    	<li class="page-item disabled">
		      		<a class="page-link" href="#">&gt;&gt;</a>
		    	</li>
			</c:if>
		  </ul>
		</nav>
	</div>	
<jsp:include page="../main/Footer.jsp"></jsp:include>	
</body>
</html>