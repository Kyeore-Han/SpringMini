<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<input type="hidden" id="pg" value="${pg }">
<div id="guestbookPagingDiv"></div>
<div id="guestBookTable"></div>


<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/SpringProject/js/guestbookList.js"></script>
<script type="text/javascript">
function guestbookPaging(pg2){
	location.href = '/SpringProject/guestbook/guestBookList?pg='+pg2;
}
</script>





















