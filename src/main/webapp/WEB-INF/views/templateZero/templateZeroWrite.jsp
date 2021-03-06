<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<script>

var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
var flag = 0;
$(document).ready(function() {
	// Editor Setting 
	nhn.husky.EZCreator.createInIFrame({ 
		oAppRef : oEditors, // 전역변수 명과 동일해야 함. 
		elPlaceHolder : "contexteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함. 
		sSkinURI : "${pageContext.request.contextPath}/resources/js/smarteditor2-master/workspace/SmartEditor2Skin.html", // Editor HTML 
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X 
		htParams : { 
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseToolbar : true, 
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseVerticalResizer : true, 
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseModeChanger : true, 
		} 
	});
});


function fn_validate(){
	
	if($("#title").val() == "")
		{ alert("제목 항목은 필수 항목입니다."); return false; }
	if($("#frstRegistNm").val() == "")
		{ alert("작성자 이름 항목은 필수 항목입니다."); return false; }
	if($("#writerID").val() == "")
		{ alert("작성자 id 항목은 필수 항목입니다."); return false; }
	
	var context = oEditors.getById["contexteditor"].getIR();
	$("#context").val(context);
	oEditors.getById["contexteditor"].exec("UPDATE_CONTENTS_FIELD", []);
	
	return true;
}

function fn_insert(){
	
	if(fn_validate() == false) { return;}
	
	if (!confirm("등록하시겠습니까?")) { return; }
	
	fn_pageReset();
	
	if($("input:checkbox[name=noticeSwitchBox]").is(":checked") == true){ $("#noticeSwitch").val("1"); }else{ $("#noticeSwitch").val("0"); }
	
	<c:if test="${empty resultList}">
		document.frm.action = '<c:url value="/template/templateZeroInsert.go"/>';
	</c:if>
	<c:if test="${not empty resultList}">
		document.frm.action = '<c:url value="/template/templateZeroUpdate.go"/>';
	</c:if>
	
	document.frm.submit();
	
}

function fn_delete(){
	
	if (!confirm("삭제하시겠습니까? 삭제한 데이터는 복구가 불가능합니다.")) { return; }
	
	fn_pageReset();
	
	document.frm.action = '<c:url value="/template/templateZeroDelete.go"/>';
	document.frm.submit();
	
}

function fn_back(){
	
	document.frm.action = '<c:url value="/template/templateInfo.go"/>';
	document.frm.submit();
	
}

function fn_pageReset(){ $("#pageIndex").val(${searchVO.pageIndex/searchVO.recordCountPerPage});} 

</script>

<div class="contents_wrap">
		<div class="contents">
		<h3 class="fl">${searchVO.siteTitle }</h3>
		<div class="essential fr">
	      <i class="icono-asterisk"></i>필수입력항목
	    </div>
		<form id="frm" name="frm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}" />
			<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}" />
			<input type="hidden" name="searchCnd" value="${searchVO.searchCnd}" />
			<input type="hidden" name="searchWrd" value="${searchVO.searchWrd}" />
			<input type="hidden" name="recordCountPerPage" value="${searchVO.recordCountPerPage}" />
			
			<input type="hidden" id="siteCode" name="siteCode" value="${searchVO.siteCode }"/>
			<input type="hidden" id="code" name="code" value="${searchVO.code }"/>
			<input type="hidden" id="siteTitle" name="siteTitle" value="${searchVO.siteTitle }"/>
			<input type="hidden" id="atchFileId" name="atchFileId" value="${resultList.atchFileId }"/>
			<input type="hidden" id="context" name="context" value=""/>
			<input type="hidden" id="noticeSwitch" name="noticeSwitch" value="${resultList.noticeSwitch }"/>
			
			<table class="tablewrite tableline">
				<colgroup>
					<col style="width: 20%;" >
					<col style="width: 30%;" >
					<col style="width: 20%;" >
					<col style="width: 30%;" >
				</colgroup>
				
				<tr>
					<th> <i class="icono-asterisk"></i>   제목</th>
					<td><input type="text" name="title" id="title" class="width500" value="${resultList.title }"/></td>
					<th>공지여부확인</th>
					<td >
						<input type='checkbox' id='noticeSwitchBox' name='noticeSwitchBox' value='1' <c:out value="${resultList.noticeSwitch == '1' ? 'checked' : ''}"/> >
					</td>
				</tr>
				<tr>
					<th> <i class="icono-asterisk"></i>   작성자 이름</th>
					<td colspan="3">
						<input type="text" name="frstRegistNm" id="frstRegistNm" value="${resultList.frstRegistNm }">
					</td>
				</tr>
				<tr>
					<th> <i class="icono-asterisk"></i>   작성자 id</th>
					<td colspan="3"><input type="text" name="writerID" id="writerID" class="width500" value="${resultList.writerID }" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"><input type="text" name="contexteditor" id="contexteditor" class="width800" value="${resultList.context }" /></td>
				</tr>
				
				<c:import url="/file/fileAdmin.go" charEncoding="utf-8">        
					<c:param name="atchFileId" value="${resultList.atchFileId}" />
				</c:import>
			
			</table>

		</form>
		
		<div class="btngroup mt0">
			<button class="btn03 fl" onclick="javascript:fn_back();" type="button">취소</button>
			<c:if test="${empty resultList }"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">등록</button> </c:if>
			<c:if test="${not empty resultList}"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">수정</button> </c:if>
			<c:if test="${not empty resultList}"> <button class="btn05 fr" onclick="javascript:fn_delete();" type="button">삭제</button> </c:if>
		</div>
		</div>

</div>

</body>