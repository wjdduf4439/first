<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<tr>
	<th>
		첨부파일
	</th>
	<td colspan="3">
		<input type="file" name="b_filename" id="b_filename" class="inputFile width400 atchFile" multiple>
		<ul class="attach">
			<c:forEach var="fileList" items="${fileList}" varStatus="status">
				<li id="${fileList.fid}">
					<a href="javascript:fn_downFile('<c:out value="${fileList.code}"/>')"><c:out value="${fileList.fname}"/></a>
					<a href="javascript:fn_deleteFile('<c:out value="${fileList.code}"/>','<c:out value="${fileList.fid}"/>','<c:out value="${fileList.fsign}"/>','<c:out value="${fileList.fname}"/>')" title="삭제"><i class="fal icono-cross"></i></a>
				</li>
			</c:forEach>
		</ul>
	</td>
</tr>
	
<script>

function fn_downFile(code){
	//window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
	window.open("<c:url value='/filedown.go?code="+code+"'/>");
}

function fn_deleteFile(code, fid, fileSn, fname) {
	
	if(!confirm('삭제하시겠습니까?')){ return; }
	
	var sitecode = document.frm.siteCode.value;
	var formData = new FormData($("#frm")[0]);
	var url = "<c:url value='/fileDelete.go?code="+code+"&fid="+fid+"&fsign="+fileSn+"&sitecode="+sitecode+"&fname="+fname+"'/>";
	
	$.ajax({
        type : 'post',			//ajax 가상 폼의 전송 방식
        url : url,				//submit 할 링크
        data : formData,		//폼에서 담을 폼의 데이터
        enctype : "multipart/form-data",
        processData : false,
        contentType : false,
        beforeSend : function(xmlHttpRequest){
        	   xmlHttpRequest.setRequestHeader("AJAX", "true");
        	  },
        success : function(d) {  
        	
        	$("#"+fid).remove();
        	
        },
        error : function(error) {
           alert(error + "임");
        }
    });
}

</script>