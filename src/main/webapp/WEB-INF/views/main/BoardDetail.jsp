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
<link rel="stylesheet" href="/main/css/BoardDetail.css">

<!-- 구글폰트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
	<div class="container-fluid">
	    <div class="row">
        	<div class="col-6 no-padding">
            	<img src="/main/img/003.png" class="rounded" alt="..." width="100%" onclick="location.href='/yomuka/board/notice'">
        	</div>
        	<div class="col-6 no-padding">
            	<img src="/main/img/004.png" class="rounded" alt="..." width="100%" onclick="location.href='/yomuka/board/freeBoard'">
        	</div>
    	</div>
    </div>
    <div class="container w-75 mt-5 mx-auto">
		<h2>${board.title}</h2>
		<hr />
		<div class="card w-75 mx-auto">
			<img class="card-img-top w-80 mx-auto" src="${board.img}" alt="게시글 이미지" style="width: 400px; height: auto;"/>
			<div class="card-body">
				<p class="card-title">작성일 : ${board.date}</p>
				<p class="card-title">작성자 : ${board.memberid}</p>
				<h4 class="card-text">내용: ${board.content}</h4>
				<hr />
				<h3>댓글등록</h3>
				<form method="post" action="/yomuka/comment/addComment" id="commentForm">
			  		<input type="hidden" name="boardaid" value="${board.boardaid}" />
					<input type="hidden" name="memberid" value="${loggedUser.memberid}">
				<div class="card-body">
					<c:choose>
				    <c:when test="${empty loggedUser.memberid}">
				        <textarea class="form-control" name="content" rows="1" id="commentContent" disabled placeholder="로그인해주세요"></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea class="form-control" name="content" rows="1" id="commentContent"></textarea>
				    </c:otherwise>
				</c:choose>
				</div>
				<div class = "card-footer">
					<button type="submit" class="btn btn-primary">등록</button>
				</div>
				</form>
			<button id="showCommentsButton" class="btn btn-secondary">댓글보기</button>
				<div id="commentsDiv" style="display: none;">
					<jsp:include page="commentList.jsp"></jsp:include>
				</div>
		</div>
	</div>
		<c:if test = "${board.notice =='Y'}">
		<a href="/yomuka/board/notice" class="btn btn-primary">목록보기</a>
		</c:if>
		<c:if test = "${board.notice !='Y'}">
		<a href="/yomuka/board/freeBoard" class="btn btn-primary">목록보기</a>
		</c:if>
		<c:if test= "${not empty loggedUser && loggedUser.memberid == board.memberid}">
		<button type="button" class="btn btn-primary mb-0" data-bs-toggle="collapse"
		data-bs-target="#addForm" aria-expanded="false" aria-controls="addForm">수정</button>
		<div class="collapse" id="addForm">
			<div class="card card-body">
				<form method="post" action="/yomuka/board/upboard" enctype="multipart/form-data" >
				    <input type="hidden" name="boardaid" value="${board.boardaid}">
					<label class="form-label">제목</label>
					<input type="text" name="title" class="form-control" value="${board.title}" />
					<label class="form-label">이미지</label>
					<input type="file" name="file" class="form-control"/>
					<label class="form-label">내용</label>
					<textarea cols="50" rows="5"  name="content" class="form-control"/> </textarea>
					<button type="submit" class="btn btn-primary mt-3">저장</button>
				</form>
			</div>	
		</div>
		</c:if>
		<c:if test="${(not empty loggedUser && loggedUser.admin == 'Y') || (loggedUser.memberid == board.memberid)}">
			<form method="post" action="/yomuka/board/delboard">
			    <input type="hidden" name="boardaid" value="${board.boardaid}" />
			    <button type="submit" class="btn btn-primary mt-3">삭제</button>
			</form>
		</c:if>
	</div>
		
<jsp:include page="Footer.jsp"></jsp:include>
    </body>
  <script>
  document.addEventListener('DOMContentLoaded', function() {
	    var showCommentsButton = document.getElementById('showCommentsButton');
	    var commentsDiv = document.getElementById('commentsDiv');
	    
	    showCommentsButton.addEventListener('click', function() {
	        if(commentsDiv.style.display === 'none') {
	            commentsDiv.style.display = 'block';
	        } else {
	            commentsDiv.style.display = 'none';
	        }
	    });
	});

  document.addEventListener('DOMContentLoaded', function() {
	    const commentForm = document.getElementById('commentForm');
	    const commentContent = document.getElementById('commentContent');

	    commentForm.addEventListener('submit', function(e) {
	        if (commentContent.value.trim() === "") {
	            alert('내용을 입력해주세요!');
	            e.preventDefault();
	        }
	    });
	});
</script>
    
</html>
