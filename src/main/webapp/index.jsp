<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	margin: 0;
	padding: 0;
	height: 100%;
	width: auto;
}

#header {
	margin: auto;
	width: 1300px;
	height: 10%;
	text-align: center;
	border: 1px red solid;
}

#container {
	margin: auto;
	width: 1300px;
	height: 700px;
	border: 1px blue solid;
}

#container:after {
	content: '';
	display: block;
	clear: both;
	float: none;
}

#nav {
	margin-left: 10px;
	width: 25%;
	height: 100%;
	float: left;
}

#section {
	width: 70%;
	height: 100%;
	float: left;
}

#footer {
	margin: auto;
	width: 1300px;
	height: 10%;
	border: 1px white solid;
}

</style>
</head>
<body style="background: linear-gradient(to right, #ffe4e1, #ffebcd); color: black;">
<div id="header">
	<h1>
		<img alt="망상토끼" src="/SpringProject/image/rabbit.png" width="70" height="70" 
		     onclick="location.href='/SpringProject/index.jsp'" style="cursor: pointer;">
		MVC기반의 미니 프로젝트
	</h1>
	<br>
	<jsp:include page="/main/menu.jsp"/>
</div>
	
<div id="container">
	<div id="nav" style="border: 1px white solid;">
		<jsp:include page="/main/nav.jsp"/>
		
	</div>
	
	<div id="section" style="border: 1px green solid;">
		<h1>
			<c:if test="${empty display }">
				홈페이지를 방문해주셔서 감사합니다<br>
				Have a nice day!!<br>
				<img src="/SpringProject/image/kakao.jpg" alt="카카오 단체 사진">
			</c:if>
			<c:if test="${ not empty display }">
				<jsp:include page="${display }"/>
			</c:if>
			
		</h1>
	</div>
</div>

<div id="footer">
	<p>비트캠프</p>
</div>
</body>
</html>












