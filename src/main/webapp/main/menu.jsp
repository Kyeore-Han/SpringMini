<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.mainnav {
	background-color: orange;
	list-style: none;
	margin-top: 4px;
    margin-bottom: 4px;
}

.mainnav li {
	display: inline-block;
}

.mainnav li a {
	display: block;
	padding: 0 13px; /* 위아래, 좌우 */
	color: #ffffff;
	font: bold       16px/40px 'Nanum Gothic', sans-serif;
	    /* 폰트 굵기 | 크기/line-height : 글꼴  */
	text-decoration: none;
}

.mainnav li a:hover {
	color: #660000;
	background-color: #ffcc00;
}
</style>

<ul class="mainnav">
	<c:if test="${memId != null }">
		<li><a href="/SpringProject/board/boardWriteForm">글쓰기</a></li>
		<li><a href="/SpringProject/imageboard/imageboardWriteForm">이미지등록</a></li>
	</c:if>
	
	<li><a href="/SpringProject/board/boardList">목록</a></li>
	<li><a href="/SpringProject/imageboard/imageboardList">이미지목록</a></li>
	<li><a href="/SpringProject/guestbook/guestBookWriteForm">방명록등록</a></li>
	<li><a href="/SpringProject/guestbook/guestBookList?pg=1">방명록목록</a></li>
</ul>























