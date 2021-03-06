<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/AdminHeader.go" />

<script>

	function fn_delete(){
		
		document.listform.action = "deleteboard.go";
		document.listform.submit();
		
	}
	
	function fn_Board(){
		
		document.listform.action = "mainboard.go";
		document.listform.submit();
		
	}
	
	function fn_write(){
		
		document.listform.mode.value = "update";
		document.listform.action = "writeboard.go";
		document.listform.submit();
		
	}
	function fn_download(filename){
		
		document.listform.b_file_name.value = filename;
		document.listform.action = "fileboard.go";
		document.listform.submit();
		
	}

</script>

<body>

	this is lookboard
	
	<form  id="listform" name="listform">
	
		<input type="hidden" id="b_code" name="b_code"  value = "${resultList.b_code }"/>
		<input type="hidden" id="b_file_name" name="b_file_name"  value = "" />
		<input type="hidden" id="mode" name="mode"  value = "" />
		
		<table  border="1" cellpadding="1" cellspacing="1">
		
			<tr>
			
				<th>작성자</th>
				<th>${resultList.b_id }</th>
			
			</tr>
			<tr>
			
				<th>조회수</th>
				<th>${resultList.b_hit }</th>
			
			</tr>
			<tr>
			
				<th>제목</th>
				<th>${resultList.b_title }</th>
			
			</tr>
			<tr>
			
				<th>내용</th>
				<th>${resultList.b_context }</th>
			
			</tr>
			
			<c:forEach var="resultFile" items="${resultFileList}" varStatus="status">
			
				<tr>
			
				<th>파일</th>
				<th><a href="javascript:fn_download('${resultFile.f_name }')">${resultFile.f_name }</a></th>
			
				</tr>
			
			</c:forEach>
			
			
		
		</table>
	
	</form>
	
	<input type="button" value="게시글 삭제" onclick="javascript:fn_delete()"/>
	<input type="button" value="게시글 수정" onclick="javascript:fn_write()"/>
	<input type="button" value="뒤로" onclick="javascript:fn_Board()"/>

</body>
</html>