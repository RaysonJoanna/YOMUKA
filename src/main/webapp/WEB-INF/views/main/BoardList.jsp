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
</head>
<script>
document.addEventListener('DOMContentLoaded', function() {
    const noticeImage = document.getElementById('noticeImage');
    const freeboardImage = document.getElementById('freeboardImage');

    // 공지사항버튼누르기
    document.getElementById('collapseOne').addEventListener('show.bs.collapse', function() {
        noticeImage.src = '/main/img/003.png';
        freeboardImage.src = '/main/img/002.png';
    });

    // 공지사항버튼접기
    document.getElementById('collapseOne').addEventListener('hide.bs.collapse', function() {
        noticeImage.src = '/main/img/001.png';
    });

    // 자유게시판버튼누르기
    document.getElementById('collapseTwo').addEventListener('show.bs.collapse', function() {
        freeboardImage.src = '/main/img/004.png';
        noticeImage.src = '/main/img/001.png';
    });

    // 자유게시판버튼접기
    document.getElementById('collapseTwo').addEventListener('hide.bs.collapse', function() {
        freeboardImage.src = '/main/img/002.png';
    });
});

</script>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="accordion" id="accordionExample">
    <div class="accordion-headers">
        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
  			<img src="/main/img/003.png" alt="공지사항" class="accordion-image" id="noticeImage">
		</button>
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
    		<img src="/main/img/002.png" alt="자유게시판" class="accordion-image" id="freeboardImage">
		</button>
    </div>
    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
        <div class="accordion-body">
        <table class="table table-striped text-center">
   			<thead>
       			<tr>
		            <th>제목</th>
		            <th>작성자</th>
		            <th>작성일자</th>
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

	<c:if test = "${member.admin == 'Y'}">
       	 <button class="btn btn btn-primary" onclick="location.href='/yomuka/board/addboard'">등록</button>
	</c:if>
        </div>
    </div>
    <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
        <div class="accordion-body">
             <table class="table table-striped text-center">
    <thead>
        <tr>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일자</th>
        </tr>
    </thead>
     		<tbody>
   				 <c:forEach var="board" items="${boards}">
                 <c:if test= "${board.notice == 'N'}">
            <tr>
                <td><a href="/yomuka/board/detail?boardaid=${board.boardaid}">${board.title}</a></td>
                <td>${board.memberid}</td>
                <td>${board.date}</td>
            </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>
       	 <button class="btn btn btn-primary" onclick="location.href='/yomuka/board/addboard'">등록</button>
        </div>
    </div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
