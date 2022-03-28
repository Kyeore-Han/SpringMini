<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
#boardListTable th {
	font-size: 16px;
}

#boardListTable td {
	font-size: 13px;
}

#boardListTable {
	border-color: yellow;
	margin-left: 10pt;
}

.subjectA:link{ color: white; text-decoration: none; }
.subjectA:visited{ color: white; text-decoration: none;}
.subjectA:hover{ color: cyan; text-decoration: underline;}
.subjectA:active{ color: white; text-decoration: none;}

#boardPagingDiv{
	text-align: center;
	font-size: 13pt;
	margin-top: 20px;
}

#boardPagingDiv span {
	margin: 0 5px;
	padding: 10px;
	border: 1px white solid;
} 

#currentPaging {
	color: red;
	cursor: pointer;
}

#paging {
	color: white;
	cursor: pointer;
}
</style>

<input type="hidden" id="pg" value="${pg }">

<table id="boardListTable" border="1" cellspacing="0" cellpadding="5" frame="hsides" rules="rows">
	<tr>
		<th width="100">글번호</th>
		<th width="300">제목</th>
		<th width="100">작성자</th>
		<th width="100">작성일</th>
		<th width="100">조회수</th>
	</tr>
	
</table>
<div class="search_wrap">
	<div class="search_area">
		<input type="text" name="keyword">
		<button>Search</button>
	</div>
</div>
<div id="boardPagingDiv"></div>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/SpringProject/js/boardList.js"></script>
<script type="text/javascript">
function boardPaging(pg2){
	location.href = '/SpringProject/board/boardSearchList?pg='+pg2;
}
</script>





















