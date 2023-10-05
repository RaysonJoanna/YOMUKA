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
	<div class= "card">
    <div class="card-header">댓글 목록</div>
    <table id="comment-table" class="table table-striped">
        <thead>
            <tr>
                <th class="th1">작성자</th>
                <th class="th2">작성시간</th>
                <th class="th3">내용</th>
                <th class="th4">삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="comment" items="${comments}">
			    <tr>
			        <td>${comment.memberid}</td>
			        <td>${comment.date}</td>
			        <td>${comment.content}</td>
				<td>
	               	<c:if test="${loggedUser.admin =='Y' ||loggedUser.memberid == comment.memberid}">
                    	<form method="post" action="/yomuka/comment/delComment">
                    	 	<input type="hidden" name="boardaid" value="${board.boardaid}">
                        	<input type="hidden" name="commentsaid" value="${comment.commentsaid}" />
                        	<button type="submit" class="btn btn-primary">삭제</button>
                    	</form>
               		</c:if>
            	</td>
			    </tr>		    
			</c:forEach>
        </tbody>
    </table>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
