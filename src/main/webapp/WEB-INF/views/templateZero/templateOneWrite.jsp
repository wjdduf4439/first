<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script>
	
	$(document).ready(function(){

		
	});
	
	function fn_validate(){
		
		/*if($("#title").val() == "")
			{ alert("제목 항목은 필수 항목입니다."); return false; }
		if($("#frstRegistNm").val() == "")
			{ alert("작성자 이름 항목은 필수 항목입니다."); return false; }
		if($("#writerID").val() == "")
			{ alert("작성자 id 항목은 필수 항목입니다."); return false; }*/
		
		//var context = oEditors.getById["contexteditor"].getIR();
		
		if($("#contexteditor").val() == "")
				{ alert("내용 항목은 필수 항목입니다."); return false; }
			
		$("#context").val($('#contexteditor').val());
		//oEditors.getById["contexteditor"].exec("UPDATE_CONTENTS_FIELD", []);
		
		return true;
	}
	
	function fn_writefrm(){ //등록을 클릭했을때 write, update 둘중 하나가 submit!
		
		
		if (!confirm("등록하시겠습니까?")) { return; }
		
		var writeSignal = fn_validate();
		if(writeSignal == false){ return; }
		
		
		/*<c:if test="${empty resultList}">
			document.writefrm.action = '<c:url value="/template/templateOneInsert.go"/>';
		</c:if>
		<c:if test="${not empty resultList}">
			document.writefrm.action = '<c:url value="/template/templateOneUpdate.go"/>';
		</c:if>*/
		
		//delArray.length 길이를 따져서 delarray input에 값을 입력한 다음 update를 수행함
		//delArray.length 길이를 따져서 없으면 write임
		
		
		
		if(delArray[0] != ""){
			
			var delValue = "";
			
			for(var i = 0; i < delArray.length; i++){ delValue += delArray[i] + ","; } 
		
			$("#delValue").val(delValue);
		
		} //삭제할 파일 ,묶음String로 변환해서 컨트롤러에 전달
		
		document.writefrm.context.value = $("#contexteditor").val();
		
		document.writefrm.submit();

	}
	
	
	
</script>

<div class="write_messenger">

	<form name="writefrm" method="post" enctype="multipart/form-data">
	
		<input type="hidden" name="pageIndex" value="${searchVO.pageIndex}" />
		<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}" />
		<input type="hidden" name="searchCnd" value="${fn:contains(searchVO.searchCnd, '') != true? 'searchVO.searchCnd' : ''}" />
		<input type="hidden" name="searchWrd" value="${fn:contains(searchVO.searchWrd, '') != true? 'searchVO.searchWrd' : ''}" />
		<input type="hidden" name="recordCountPerPage" value="${searchVO.recordCountPerPage}" />
		
		<input type="hidden" id="siteCode" name="siteCode" value="${searchVO.siteCode }"/>
		<input type="hidden" id="code" name="code" value="${searchVO.code }"/>
		<input type="hidden" id="context" name="context" value="${searchVO.context }"/>
		<input type="hidden" id="siteTitle" name="siteTitle" value="${searchVO.siteTitle }"/>
		<input type="hidden" id="atchFileId" name="atchFileId" value=""/>
		<input type="hidden" id="delValue" name="delValue" value=""/>
		
		
		-내용-	<img class="xicon" src="${pageContext.request.contextPath}/resources/image/xicon.png" onclick="javascript:fn_vaild();"/></br>
		
		<textarea id="contexteditor" name="contexteditor" rows="4" cols="40"></textarea>
		
		<br/>
		<img  id="previewImg" name="previewImg" class="fileicon" src="${pageContext.request.contextPath}/resources/image/plusimage.jpg" onclick="javascript:fn_imgclick('#b_filename');" />
		<!-- <input type="file" name="b_filename" id="b_filename" style="display: none;" onchange="javascript:fn_imgTokenClick(b_filename);" multiple /> -->
		<input type="file" name="b_filename" id="b_filename" style="display: none;" multiple />
		
			
			<c:import url="/file/fileIconView.go" charEncoding="utf-8">
				<c:param name="fid" value="${result.atchFileId}" />					
			</c:import>
		
		<script>
			//이미지를 클릭하고 미리보기하기 위한 스크립트
			
			var sellFiles = [];				//페이지 내의 file클릭으로 인한 이미지 정보를 담을 배열
			var i = 0;						//페이지 내의 이미지 배열의 인덱스
		
			function fn_imgclick(id) { $(id).click(); }
			
			var bFileName = document.getElementById('b_filename');	
				
			bFileName.onchange = function(e) { // .onchage가 맨 위의 스크립트에는 set property not null 오류로 인해 따로 분리함
				
				if(i > 0 ){ for(temp = 0; temp < sellFiles.length; temp++){ $('#previewImg'+temp+'').remove(); } i = 0; }
				
				var files = e.target.files;
				var filesArr = Array.prototype.slice.call(files);
				var previewText = "";
				sellFiles = [];
				
				filesArr.forEach(function(f) {

					sellFiles.push(f);		//write에서 plusicon클릭 후, 파일 지정 후 sellfiles에 넣어 관리함
					
					var reader = new FileReader();
					reader.onload =  function(g){
						
						console.log("파일 설정됨!");	
						$('#previewImg').after('<img  id="previewImg'+ i + '" name="previewImg'+ i + '" class="fileicon" src="${pageContext.request.contextPath}/resources/image/plusimage.jpg" onclick="javascript:deletePreviewImg('+ i + ');" />');
						$('#previewImg'+i+'').attr('src', g.target.result);
						i++;
						
					}
					
					console.log(f);
					
					reader.readAsDataURL(f);
					
				});
				
			}
			
			function deletePreviewImg(i){
				
				if (!confirm("삭제하시겠습니까?")) { return; }
				
				$("#b_filename").val(""); 
				sellFiles.splice(i,1);
				
				console.log("sellFiles 길이 : " + sellFiles.length);
				//console.log(sellFiles);
				
				delArray.push(dataArray[i]);
				
				for(var j = 0; j < delArray.length; j++){ console.log("del 후 delarray : " + delArray[j]); }
				for(var j = 0; j < dataArray.length; j++){ console.log("del 후 dataarray : " + dataArray[j]); }
				
				$('#previewImg'+i+'').remove();
				
			}
		
		
		</script>
			
			<div class="nonedisplay"></div>
		<br/>
		
		<button class="write_button" onclick="javascript:fn_writefrm();" type="button">등록</button>
	</form>
</div>