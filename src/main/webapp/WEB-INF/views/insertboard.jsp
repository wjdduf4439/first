<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

function fn_addAtchFile() {
	
	var template = "<tr>";
		template += "<th>추가 첨부파일</th>";
		template += "<th><input type='file' class='bFile' id='b_filename' name='b_filename'/> <input type='button' onclick='javascript:fn_addAtchFile()' value='파일추가' /></th>";
		template += "</tr>";
		$("#btable").append(template);
}

$(function() {
    $("#imgInp").on('change', function(){  readURL(this);  } );
}); //해당 페이지가 로드되면 주기적으로 반응하도록 짜여진 스크립트

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
    }
}

</script>

<body>

	this is insertboard
	
	<form  id="BoardVO" name="BoardVO" method="post" enctype="multipart/form-data"  runat="server" action="insertboard.go" >
	<!-- enctype="multipart/form-data"를 기입하지 않으면 파일 경로만 전송된다 -->
	
		<table id="btable" border="1" cellpadding="1" cellspacing="1">
		
			<tr>
			
				<th>작성자</th>
				<th> <input type="text" id="b_id" name="b_id" value="${resultList.b_id }" > </th>
			
			</tr>
			<tr>
			
				<th>비밀번호</th>
				<th> <input type="password" id="b_password" name="b_password" > </th>
			
			</tr>
			<tr>
			
				<th>제목</th>
				<th> <input type="text" id="b_title" name="b_title"  value="${resultList.b_title }"> </th>
			
			</tr>
			<tr>
			
				<th>내용</th>
				<th> <input type="textarea" id="b_context" name="b_context" value="${resultList.b_context }" > </th>
			
			</tr>
			<tr>
			
				<th>이미지 첨부</th>
				<th> <input type='file' id="imgInp" /> </th>
			
			</tr>
			
			<tr>
				<th></th>
				<th><input type="button" onclick="javascript:fn_addAtchFile()" value="파일 첨부하기" />  </th>
			</tr>
		
		</table>
		<input type="submit" value="게시글 작성">
		
		
		
	</form>
	
    <img id="blah" src="#" alt="your image" />

</body>


</html>