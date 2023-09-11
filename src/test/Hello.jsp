<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World</title>
</head>
<body>
	<h2>Hello World</h2>
	<hr />
	현재 날짜와 시간은 <%=java.time.LocalDateTime.now()%>
	<hr />
	요청 URL 의 test/hello/ 다음에 있는 path : ${msg}
</body>
</html>