<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>YOMUKA</title>

</head>
<body>
<!-- header -->
<jsp:include page="../main/Header.jsp" />

<div class="container">
  <div class="row">
    <div class="col-lg-4 col-md-6 mb-4 custom-div"> 
      <a href=CareDetail?imageType=Management">
        <img class="img-fluid" src="/care/img/CareDetail.png" alt="Management">
      </a>
    </div>

    <div class="col-lg-4 col-md-6 mb-4 custom-div">
      <a href="CareDetail?imageType=Shower">
        <img class="img-fluid" src="/care/img/CareDetail.png" alt="Shower">
      </a>
    </div>

    
    
  </div>
</div>



<!-- footer -->
<jsp:include page="../main/Footer.jsp" />
</body>

</html>