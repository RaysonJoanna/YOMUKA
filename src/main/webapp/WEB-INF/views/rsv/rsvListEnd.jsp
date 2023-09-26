<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진료기록조회</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="/css/rsv.css" media="all" />

<!-- jquery 기동 후 datepicker 기동 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript" src="/libs/jquery-1.10.2.min.js"></script> <!-- 제이쿼리 라이이브러리 연동 -->  
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.ko.min.js" integrity="sha512-L4qpL1ZotXZLLe8Oo0ZyHrj/SweV7CieswUODAAPN/tnqN3PA1P+4qPu5vIryNor6HQ5o22NujIcAZIfyVXwbQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript" src="/js/rsvListEnd.js"></script>  
</head>
<header>  
</header>
<body>
<jsp:include page="../main/Header.jsp"></jsp:include>

	<!-- 진료결과 입력 -->
	<div class="modal fade" id="resultInput">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title">진료결과등록</h5>	                
	            </div>	            
	            <div class="modal-body">	
	            	<div>
		            	<label>>> 예약ID&nbsp;&nbsp;&nbsp;&nbsp;: </label>
		            	<input type="text" id="reserveid" disabled />
	            	</div>  
	            	<label>>> 진료일자 : </label>   
	            	<div class="d-inline-block col align-self-center">
			  		    <input 	type="text" 
			  		     		class="form-control datepicker"   		     		
			  		     		aria-label="resultdate" 
			  		     		id="resultdate" 
			  		     		/>
			  		</div><br/>
	            	<label>>> 진료결과 : </label>            	
	                <div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="resultgubun" id="resultgubun1" value="정상진료">
					  <label class="form-check-label" for="resultgubun1">정상진료</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="resultgubun" id="resultgubun2" value="미진료">
					  <label class="form-check-label" for="resultgubun2">미진료</label>
					</div>	
					<div>				
						<label for="resultcontent">>> 진료내용 : </label>
					</div>
					<div class="form-floating"> 
					  	<textarea class="form-control" placeholder="진료내용을 상세히 입력하세요" id="resultcontent" style="height: 100px"></textarea>					  
					</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	                <button type="button" class="btn btn-primary" id="resultSave" >저장</button>
	            </div>
	        </div>
	    </div>
	</div>
	
   <!-- 진료기록 명세조회  -->
   <div class="container w-95 mt-5 mx-auto">
      <div class="row">
  		  <div class="d-inline-block col align-self-center">
  		  	<label for="From" class="form-label" >조회진료일 : &nbsp;</label>
  		  </div>
  		  <div class="d-inline-block col align-self-center">
  		     <input type="text" 
  		     		class="form-control datepicker"   		     		
  		     		aria-label="From" 
  		     		id="From">
  		  </div>
  		  <div class="d-inline-block col align-self-center">
  		    <input 	type="text" 
  		     		class="form-control datepicker"   		     		
  		     		aria-label="To" 
  		     		id="To">
  		  </div>
  		  <div class="d-inline-block col align-self-center"></div>
  		  <div class="d-inline-block col align-self-center"></div>
  		  <div class="d-inline-block col align-self-center text-center">
  		  	<button type="button" class="btn btn-secondary mb2" id="inquiry01" onClick="handleButtonClick(event)">조회</button>
  		  </div>
  		  <input type="hidden" id="npage" value="${paging.pageno}">
  		  <input type="hidden" id="fromDate" value="${rsvInqEnd.fromDate}">
  		  <input type="hidden" id="toDate" value="${rsvInqEnd.toDate}">
      </div>	    
      <hr />  	   
   </div>      
   <div class="container w-95 mt-4 mx-auto">      
      <p class="h4">진료기록조회</p>
      <hr />
	  <table class="table table-bordered border-secondary">
	  	 <tr align="center">
	  		<th >진료일자</th>
	  		<th >예약시간</th>
	  		<th >예약ID</th>
	  		<th >병원ID</th>
	  		<th >회원ID</th>
	  		<th >예약내용</th>
	  		<th >진료일자</th>
	  		<th >진료결과</th>
	  		<th >진료내용</th>
	  		<th >비고</th>
	  	 </tr>
	  	 <c:forEach var="rsvItem" items="${rsvListEnd}" varStatus="status">
	  	 <tr >
	  		<td align="center">${rsvItem.rsvdate}</td>
	  		<td align="center">${rsvItem.rsvtime}</td>
	  		<td align="center">${rsvItem.reserveid} </td>
	  		<td align="center">${rsvItem.hospitalid}</td>
	  		<td align="center">${rsvItem.memberid}</td>
	  		<td >${rsvItem.content}</td>
	  		<td align="center">${rsvItem.resultdate}</td>
	  		<td align="center">${rsvItem.resultgubun}</td>
	  		<td >${rsvItem.resultcontent}</td>
	  		<td ><button type="button" class="btn btn-primary resultInput" id="openModalBtn" 
	  					value="${rsvItem.reserveid}$#${rsvItem.resultgubun}$#${rsvItem.resultcontent}$#${rsvItem.rsvdate}$#${rsvItem.resultdate}">
	  			  진료결과입력
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