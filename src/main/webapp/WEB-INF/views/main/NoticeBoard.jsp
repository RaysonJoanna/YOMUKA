<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>요무까</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/main/css/BoardList.css">
<!-- 구글폰트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<script type="text/javascript" src="/main/js/noticeboard.js"></script> 	
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>

<div class="container-fluid">
	    <div class="row">
        <div class="col-6 no-padding">
            <img src="/main/img/003.png" class="rounded" alt="..." width="100%" onclick="location.href='/yomuka/board/notice'">
        </div>
        <div class="col-6 no-padding">
            <img src="/main/img/002.png" class="rounded" alt="..." width="100%" onclick="location.href='/yomuka/board/freeBoard'">
        </div>
    </div>
    <h2>공지사항</h2>
    <table class="table table-striped text-center">
        <thead>
            <tr>
                <th class="th1">제목</th>
                <th class="th2">작성자</th>
                <th class="th3">작성일자</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${boards}">
                <c:if test= "${board.notice == 'Y'}">
                    <tr>
                        <td><a href="/yomuka/board/detail?boardaid=${board.boardaid}">${board.title}</a></td>
                        <td>${board.memberid}</td>
                        <td>${board.date}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </tbody>
    </table>
 <div class="container w-95 mt-5 mx-auto">
      <div class="row d-flex ">
  		  <div class="col align-self-center text-center" style="min-width: 8rem;" >
  		  	<label for="title" class="form-label" >제목 : &nbsp;</label>
  		  </div>
  		  <div class="col align-self-center text-center">
  		     <input type="text" 
  		     		class="form-control datepicker"   		     		
  		     		aria-label="title" 
  		     		id="title">
  		  </div>
  		  <div class="col align-self-center text-center" style="min-width: 8rem;" >
  		  	<label for="title" class="form-label" >작성자 : &nbsp;</label>
  		  </div>
  		  <div class="col align-self-center text-center">
  		    <input 	type="text" 
  		     		class="form-control datepicker"   		     		
  		     		aria-label="memberid" 
  		     		id="memberid">
  		  </div>  		    		    		  
  		  <div class="col align-self-center text-center">
  		  	<button type="button" class="btn btn-secondary mb2" id="inquiry01" onClick="handleButtonClick(event)">조회</button>
  		  </div> 
  		 	<input type="hidden" id="npage" value="${paging.pageno}">		  
   		  	<input type="hidden" id="title" value="${board.title}">
  		  	<input type="hidden" id="memberid" value="${board.memberid}">		  
      </div>	    
      <hr />
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
</div>	
    <c:if test = "${loggedUser.admin == 'Y'}">
        <button class="btn btn btn-primary" onclick="location.href='/yomuka/board/addboard'">등록</button>
    </c:if>
</div>

<jsp:include page="Footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
