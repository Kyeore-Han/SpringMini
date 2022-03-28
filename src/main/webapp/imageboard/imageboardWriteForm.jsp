<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>이미지 등록</h3>

<!-- 1. 단순 submit -->
<%-- <form id="imageboardWriteForm" method="post" enctype="multipart/form-data"  action="/SpringProject/imageboard/imageboardWrite">  --%>
	
<!-- 2. AJax 통신 -->
<form id="imageboardWriteForm">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="100" align="center">상품코드</td>
			<td><input type="text" name="imageId" size="50"></td>
		</tr>
		
		<tr>
			<td width="100" align="center">상품명</td>
			<td><input type="text" name="imageName" size="50"></td>
		</tr>
		
		<tr>
			<td width="100" align="center">단가</td>
			<td><input type="text" name="imagePrice" value='0' size="50"></td>
		</tr>
		
		<tr>
			<td width="100" align="center">개수</td>
			<td><input type="text" name="imageQty" value='0' size="50"></td>
		</tr>
		
		<tr>
			<td width="100" align="center">내용</td>
			<td><textarea cols="50" rows="15" name="imageContent"></textarea></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="file" name="img"></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="file" name="img"></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="file" name="img[]" multiple></td> <!-- 파일을 한번에 여러개 선택, 드래그 -->
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="imageboardWriteBtn" value="이미지등록"> 
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$('#imageboardWriteBtn').click(function(){
	//1. 단순 submit
	//$('#imageboardWriteForm').submit();	
	
	//2. AJax통신
	var formData = new FormData($('#imageboardWriteForm')[0]); //<form 안에 있는 모든 것
	
	$.ajax({
		type: 'post',
		url: '/SpringProject/imageboard/imageboardWrite',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData, //serialized 를 못하기 때문에 객체 자체를 넘길 수 있도록 해주었다.
		success: function(){
			alert('이미지 등록 완료');
			location.href='/SpringProject/imageboard/imageboardList';
		},
		error: function(err){
			console.log(err);
		}
	});
	
});
</script>



<!-- form테그는 여러개 만들어도 되지만 form안에 form을 만들면 안된다. form태그들을 관리해주는 태그는 forms 태그이다.-->
<!-- forms[0] forms[1] forms[2] -->
<!--
processData
 - 기본값은 true
 - 기본적으로 Query String으로 변환해서 보내진다('변수=값&변수=값')
 - 파일 전송시에는 반드시 false로 해야 한다.(formData를 문자열로 변환하지 않는다)
 
contentType
  - 기본적으로 "application/x-www-form-urlencoded; charset=UTF-8"
  - 파일 전송시에는 'multipart/form-data'로 전송이 될 수 있도록 false로 설정한다
-->




