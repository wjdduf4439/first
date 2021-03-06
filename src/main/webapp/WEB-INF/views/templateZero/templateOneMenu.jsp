<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="vaild_effect" onclick="javascript:fn_vaild();"></div><!--  메신저 글쓰기기능을 위한 div -->
<c:import url="/AdminHeader.go" />

<script>

	var dataArray = [];
	var delArray = [];
	
	$(document).ready(function(){
		
		
	});
	
	function fn_searchKeyPressed(event) {
		//검색 상자 키 이벤트
		if (event.keyCode == 13) {
			fn_egov_select_List('1');
		}
	}
	
	function fn_list(pageNo) {
		//페이지 선택
		document.frm.pageIndex.value = pageNo;
		document.frm.action = '<c:url value="/template/templateInfo.go"/>';
		document.frm.submit();
	}
	
	function fn_write(){

		$(".vaild_effect").css("visibility","visible");
		$(".write_messenger").css("visibility","visible");
		
		document.writefrm.action = '<c:url value="/template/templateOneInsert.go"/>';
		//등록만 눌렀을시 insert 전환, update에서 별도로 update전환
		
	}
	
	function fn_vaild(){
		
		$(".vaild_effect").css("visibility","hidden");
		$(".write_messenger").css("visibility","hidden");
		$("#screenViewFrm").empty();
		
		$('html, body').removeClass('scrollHidden');
		
		$("#contexteditor").val("");
		$('#atchFileId').val("");
		

		for(var i = 0; i < dataArray.length; i++){$('#previewImg'+i+'').remove(); }
		
		var sellFiles = [];
		dataArray = [];
		delArray = [];
		
			
		/*if($('#contexteditor').val() == ""){
			
			document.writefrm.action = '<c:url value="/template/templateOneInsert.go?refreshSignal='+0+'"/>';
			console.log("비어있음");
			
		}else{
			
			document.writefrm.action = '<c:url value="/template/templateOneInsert.go?refreshSignal='+1+'"/>';
			console.log("안비어있음");
			
		}*/
		
	}
	
	function fn_update(typecode, writecode, sitecode){

		
		var url = "<c:url value='/template/templateOneWrite.go'/>"
		
		var ajaxreturn;
		document.writefrm.code.value = writecode ;
		document.frm.code.value = writecode ;//게시글 코드를 추출하기 위한 코드
		
		if(typecode == "update"){
		
			//alert("update");
			fn_write(); //수정 부분을 on
			document.writefrm.action = '<c:url value="/template/templateOneUpdate.go"/>';//수정 부분의 경로를 update로 변경
			
			url = "<c:url value='/template/templateOneAjaxReturn.go?code="+writecode+"&siteCode="+sitecode+"'/>";
			//sns 페이지의 전 글을 가져옴, sitecode는 searchvo에 없기때문에 수동으로 사이트값을 가져옴
			var formData = new FormData($("#frm")[0]);
			
			if(writecode != null){	
				
				$.ajax({
				    type : 'post',			//ajax 가상 폼의 전송 방식
				    async : false,			//ajax 동기 비동기 형식(동기처리시 ajax 작동중에 다른 함수의 작동을 막음)
				    url : url,				//submit 할 링크
				    data : formData,		//폼에서 담을 폼의 데이터
				    dataType: "json",
					enctype : "multipart/form-data",
				    processData : false, 
				    contentType: false,//utf8 처리
				    success : function(data) {  
				    	
				    	console.log(data);//ajax는 컨트롤러에서 return 값을 받아 해당 데이터를 data로 받
				    	$("#contexteditor").val(data.context);

				    	for(var i = 0; i < data.fileCount; i++){
				    		
				    		$('#previewImg').after('<img  id="previewImg'+ i + '" name="previewImg'+ i + '" class="fileicon" src="${pageContext.request.contextPath}/resources/image/plusimage.jpg" onclick="javascript:deletePreviewImg('+ i + ');" />');
				    		$('#previewImg'+i+'').attr('src', '${pageContext.request.contextPath}/resources/image/' + data.fname[i] );
				    		$('#atchFileId').val(data.atchFileId);
				    		
				    		dataArray.push(data.fcode[i]);
				    		
				    	}
			    		
			    		console.log($('#atchFileId').val());
				    	
				    	//for(var j = 0; j < dataArray.length; j++){ console.log(dataArray[j]); }
				    	
				    },
				    error : function(error) {
				       alert(error + "임");
				    }
				});
				
			}
	    	
			
		}
		
	}
	
	function fn_delete(mcode){
		
		if (!confirm("삭제하시겠습니까?")) { return; }
		
		document.frm.code.value = mcode;
		
		document.frm.action = '<c:url value="/template/templateOneDelete.go"/>';
		document.frm.submit();
		
	}
	
	function fn_screenView(fname){
		
		
		//var pathStr = "/img/" + fname;
		
		//$("#screenView").attr("src", "/img/" + fname);
		
		$("#fname").val(fname);
		
		var url = "<c:url value='/file/fileScreenView.go?fname="+fname+"'/>";
		//var formData = new FormData($("#screenViewFrm")[0]);
		
		$.ajax({
		    type : 'get',			//ajax 가상 폼의 전송 방식
		    async : false,			//ajax 동기 비동기 형식(동기처리시 ajax 작동중에 다른 함수의 작동을 막음)
		    url : url,				//submit 할 링크
		    //data : formData,		//폼에서 담을 폼의 데이터
		    dataType: "text",
			enctype : "multipart/form-data",
		    processData : false,
		    contentType: "application/x-www-form-urlencoded; charset=UTF-8",//utf8 처리
		    beforeSend : function(xmlHttpRequest){
		    	   xmlHttpRequest.setRequestHeader("AJAX", "true");
		    	  },
		    success : function(data) {  
		    	
		    	console.log(data);//ajax는 컨트롤러에서 return 값을 받아 해당 데이터를 data로 받는 아주 귀중한 경험을 했다 씨발아
		    	$("#screenViewFrm").append(data);
		    	
		    	$('html, body').addClass('scrollHidden');

				$(".screen_View").css("visibility","visible");
				$(".vaild_effect").css("visibility","visible");
		    	
		    },
		    error : function(error) {
		       alert(error + "임");
		    }
		});
		
	}

</script>



<c:import url="/template/templateOneWrite.go" charEncoding="utf-8"/>
<div id="screenViewFrm" name="screenViewFrm" method="get"> </div>
<div class="contents_wrap">
		
		
		<form name="frm" method="post">
				<input type="hidden" id="code" name="code" value=""/>
				<input type="hidden" id="siteCode" name="siteCode" value="${templateInfoVO.siteCode}" />
				<input type="hidden" name="pageIndex" value="${searchVO.pageIndex}" />
				<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}"/>
				<input type="hidden" id="siteTitle" name="siteTitle" value="${searchVO.siteTitle }"/>
				<input type="hidden" id="context" name="context" value="${writeVO.context}"/>
				
				<div class="space10"></div>
					
				
				
				<div class="contents">
				
					<span class="total">Total <strong>${paging.dbCount}</strong>건</span>
					<div class="space10"></div>
					
					
					
					<div class="write_contents" onclick="javascript:fn_write();">
						<img class="my_icon" src="${pageContext.request.contextPath}/resources/image/iconplus.png"/>
						<span>메신저 등록</span>
					</div>
					
					<div class ="messenger_Space">	
						<c:forEach var="result" items="${messengerList}" varStatus="status1">
							<div class="messenger_One">
								<div class="update_Box">
									<span class="update_Button" onclick=" javascript:fn_update('update','${result.code}','${templateInfoVO.siteCode}'); ">수정  /</span>
									<span class="delete_Button" onclick=" javascript:fn_delete('${result.code}');">삭제</span>
								</div>
								<div class="space20"></div>
								
								<c:forEach var="notice2" items="${fieldList }" varStatus="status2">	
									<c:if test="${notice2.ordinal_Position == 2 }"><span>제목 : ${result.title}</span></br></c:if>
									<c:if test="${notice2.ordinal_Position == 3 }"><span>내용 : ${result.context}</span></br></c:if>
									<c:if test="${notice2.ordinal_Position == 4 }"><span>첨부파일 : ${result.atchFileId}</span></br></c:if>
									<c:if test="${notice2.ordinal_Position == 5 }"><span>아이디 : ${result.writerID}</span></br></c:if>
									<c:if test="${notice2.ordinal_Position == 6 }"><span>최조 착성일 : ${result.frstRegistPnttm}</span></br></c:if>
									<c:if test="${notice2.ordinal_Position == 7 }"><span>최초 작성자 : ${result.frstRegistNm}</span></br></c:if>
								</c:forEach>
								
								<c:if test="${not empty result.atchFileId}">
									<c:import url="/file/fileIconView.go" charEncoding="utf-8">
										<c:param name="code" value="${result.code}" />
										<c:param name="fid" value="${result.atchFileId}" />
									</c:import>
								</c:if>
								
							</div>
							<div class="space10"></div>
						
						</c:forEach>
					</div>
				</div>
		</form>

</div>

</body>