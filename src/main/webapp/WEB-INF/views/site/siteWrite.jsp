<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />
<script>

$( document ).ready(function() {
	
	//console.log("${resultList.templateType}");
	
	<c:if test="${not empty resultList && resultList.templateType == '0' }">
	
		var placeRowStr = "${resultList.placeRow}";
		var placeRowArray = placeRowStr.split(",");
		
		var placeWidthStr = "${resultList.placeWidth}";
		var placeWidthArray = placeWidthStr.split(",");
		
		var template = '';
		for(var i = 0; i < placeRowArray.length; i++){
			
			template += '<input  type="number" id="placeWidthOption'+placeRowArray[i]+'" name="placeWidthOption'+placeRowArray[i]+'" class ="width150" value="" placeholder="속성'+(i+1)+'" /></br>';
			
		}
		
		$("#lengthInput").append(template);
		
		$("input:checkbox[name=placeRow]:checked").each(function(i){ 
			
			$("#placeWidthOption"+this.value+"").after(":"+$(this).attr('placeholder'));
			$("#placeWidthOption"+this.value+"").val(placeWidthArray[i]);
			
		});
	
	</c:if>
	
});

var firstlen = 0;

function fn_checked(m){
	
		var placeRowVal = '';
		var placeWidthVal = '';
		
		var placeRowArr = [];
		var placeWidthArr = [];
		
		$("input:checkbox[name=placeRow]:checked").each(function(i){
			
			if(this.value != m){
	
				console.log(this.value + " - " + m);
				
				placeRowVal += this.value;
				placeRowVal += ',';
				
				//console.log($('#placeWidthOption'+i+'').val());
				placeWidthVal += $('#placeWidthOption'+this.value+'').val();
				placeWidthVal += ',';	
				
			}else{
				
				placeRowVal += ',';
				placeWidthVal += ',';
				
			}
			
		});
		placeRowVal = placeRowVal.substring(0,placeRowVal.length-1);
		placeWidthVal = placeWidthVal.substring(0,placeWidthVal.length-1);
		
		var placeRowArr = placeRowVal.split(",");
		var placeWidthArr = placeWidthVal.split(",");
		
		console.log("placeRowVal : " + placeRowVal);
		console.log("placeWidthVal : " + placeWidthVal);

	$("#lengthInput").empty();

	var template = '';
	
	$("input:checkbox[name=placeRow]:checked").each(function(i){ 
		
		template += '<input  type="number" id="placeWidthOption'+this.value+'" name="placeWidthOption'+this.value+'" class ="width150" value="" placeholder="속성'+(i+1)+'" /> : ' + $(this).attr('placeholder') +'</br>';
		
	})
	
	$("#lengthInput").append(template);
	
	//insert 시 체크박스를 다시 만들고 값을 다시 배열하는 작업
	
	$("input:checkbox[name=placeRow]:checked").each(function(i){
		
		if(this.value == placeRowArr[i]){
			
			console.log(this.value + " : " + placeRowArr[i]);
			$('#placeWidthOption'+this.value+'').val(placeWidthArr[i]);
			
		}
		
	});
	
	//추가한 체크박스의 값을 서로 매칭시켜 체크박스 추가를 시켜도 width 값이 유지되도록 함, update시 result값을 고정값으로 받고 template에 적용함
	//row와 width 값을 매칭시키는 이중배열 생성
	//새로운 row 를 체크했을 시 해당 배열에는 값을 미배정
	//if index i+2 == placerowwidth[i]의 값	
		
}

function fn_template(){

	console.log($("#templateType").val());
	
	$("#templeteplace").empty();
	
	var template = '';
	
	if($("#templateTypeOption").val() == "0"){
		
			template +='<input type="checkbox" id="placeRow" name="placeRow" value="2" placeholder="제목" onclick="javascript:fn_checked(2);" /> 제목';
			template +='<input type="checkbox" id="placeRow" name="placeRow" value="3" placeholder="내용" onclick="javascript:fn_checked(3);" /> 내용';
			template +='<input type="checkbox" id="placeRow" name="placeRow" value="4" placeholder="첨부파일" onclick="javascript:fn_checked(4);" /> 첨부파일';
			template +='<input type="checkbox" id="placeRow" name="placeRow" value="5" placeholder="아이디" onclick="javascript:fn_checked(5);" /> 아이디';
			template +='<input type="checkbox" id="placeRow" name="placeRow" value="6" placeholder="최초작성일" onclick="javascript:fn_checked(6);" /> 최초작성일';
			template +='<input type="checkbox" id="placeRow" name="placeRow" value="7" placeholder="최초작성자" onclick="javascript:fn_checked(7);" /> 최초작성자';
			template +='<input type="checkbox" id="placeRow" name="placeRow" value="8" placeholder="최종수정일" onclick="javascript:fn_checked(8);" /> 최종수정일';
			template +='<input type="checkbox" id="placeRow" name="placeRow" value="9" placeholder="최종수정자" onclick="javascript:fn_checked(9);" /> 최종수정자';
			
			
		
	}else if($("#templateTypeOption").val() == "1"){
		
		template +='<input type="checkbox" id="placeRow" name="placeRow" value="2" placeholder="제목" /> 제목';
		template +='<input type="checkbox" id="placeRow" name="placeRow" value="3" placeholder="내용" /> 내용';
		template +='<input type="checkbox" id="placeRow" name="placeRow" value="4" placeholder="첨부파일" /> 첨부파일';
		template +='<input type="checkbox" id="placeRow" name="placeRow" value="5" placeholder="아이디" /> 아이디';
		template +='<input type="checkbox" id="placeRow" name="placeRow" value="6" placeholder="최초작성일" /> 최초작성일';
		template +='<input type="checkbox" id="placeRow" name="placeRow" value="7" placeholder="최초작성자" /> 최초작성자';
	
	}else{
		
	}
	
	$("#templateplace").append(template);
	
}

function fn_validate(){
	//항목 공백 검색
	//alert(document.frm.brthdy.value);
	
	if($("#title").val() == "")
		{ alert("제목 항목은 필수 항목입니다"); return false; }
	
	if($("#adminName").val() == "")
		{ alert("작성자 항목은 필수 항목입니다"); return false; }
	
	if($("#formCode").val() == "")
		{ alert("소속 항목 설정은 필수 항목입니다"); return false; }
	
	if($("#templateTypeSelect option:selected").val() == "")
		{ alert("유형 설정은 필수 항목입니다"); return false; }
	
	if($("input[name=placeRow]").val() == undefined )
		{ alert("표시항목 속성은 하나이상 등록되어야 합니다."); return false; }
	
	return true;
}

function fn_insert(){
	
	
	
	var placeWidthVal = '';
	for(var i = 0; i < $("input:checkbox[name=placeRow]:checked").length; i++){
		
		placeWidthVal += $('#placeWidthOption'+i+'').val();
		placeWidthVal += ',';
		
	}
	placeWidthVal = placeWidthVal.substring(0,placeWidthVal.length-1);
	$('#placeWidth').val(placeWidthVal);
	
	
	if(fn_validate() == false) { return;}
	
	if (!confirm("등록하시겠습니까?")) { return; }
	
	$("#templateType").val($("#templateTypeSelect option:selected").val());
	
	if($("#templateTypeSelect option:selected").val() == "0"){ //select-option 태그를 가져오는 val함수

		
		<c:if test="${not empty resultList }"> 
			document.frm.action = '<c:url value="/site/siteUpdate.go"/>';
		</c:if>
		<c:if test="${empty resultList }">
			document.frm.action = '<c:url value="/site/siteInsert.go"/>';
		</c:if>
	
	}else if($("#templateTypeSelect option:selected").val() == "1"){

		
		<c:if test="${not empty resultList }"> 
			document.frm.action = '<c:url value="/site/siteUpdate1.go"/>';
		</c:if>
		<c:if test="${empty resultList }">
			document.frm.action = '<c:url value="/site/siteInsert1.go"/>';
		</c:if>
		
	}else{
		
		alert("update실패" + $("#templateTypeSelect option:selected").val());
		
	}
	
	document.frm.submit();
	
}

function fn_delete(){
	
	if (!confirm("삭제하시겠습니까? 삭제한 데이터는 복구가 불가능합니다.")) { return; }
	
	
	document.frm.action = '<c:url value="/site/siteDelete.go"/>';
	document.frm.submit();
	
}

function fn_back(){
	
	document.frm.action = '<c:url value="/site/siteAdmin.go"/>';
	document.frm.submit();
	
}


</script>

<div class="contents_wrap">
		
		<div class="contents" >
			<div class="space20"></div>
			<h3 class="fl"><i class="icono-list"></i>   게시판 관리</h3>
			<div class="essential fr">
		      <i class="icono-asterisk"></i>필수입력항목
		    </div>
			<form id="frm" name="frm" method="post" enctype="multipart/form-data" >
				<input type="hidden" name="pageIndex" value="${searchVO.pageIndex}" />
				<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}" />
				<input type="hidden" name="searchCnd" value="${searchVO.searchCnd}" />
				<input type="hidden" name="searchWrd" value="${searchVO.searchWrd}" />
				
				<input type="hidden" name="recordCountPerPage" value="${searchVO.recordCountPerPage}" />
		
				<input type="hidden" id="siteCode" name="siteCode" value="${resultList.siteCode }"/>
				<input type="hidden" id="templateType" name="templateType" value="" />
				
				<!-- <input type="hidden" id="placeRow" name="placeRow" value="${resultList.placeRow}" /> -->
				<input type="hidden" id="placeWidth" name="placeWidth" value="${resultList.placeWidth}" />
				
				<input type="hidden" id="context" name="context" value=""/>
				<table class="tablewrite nohover">
					<colgroup>
						<col class="width150">
						<col class="width400">
						<col class="width150">
						<col class="width400">
					</colgroup>
					
					<tr>
						<th colspan="1"> <i class="icono-asterisk"></i>   제목</th>
						<td >
							<input type="text" name="title" id="title" class="width500" value="${resultList.title }"  />
						</td>
						<th colspan="1">공지여부확인</th>
						<td >
							<input type='checkbox' name='noticeSwitch' value='1' <c:out value="${resultList.noticeSwitch == '1' ? 'checked' : ''}"/> <c:out value="${resultList.templateType == '1' ? 'disabled' : ''}" /> ></td>
						</td>
					</tr>
					<tr>
						<th> <i class="icono-asterisk"></i>   작성자</th>
						<td class="width200" colspan="3">
							<input type="text" name="adminName" id="adminName" class="width200" value="${resultList.adminName }" />
						</td>
					</tr>
					<tr>
						<th> <i class="icono-asterisk"></i>   소속 항목</th>
						<td class="width200" colspan="3">
							<select class="width150 selectText mb05" id="formCode" name="formCode">
								
								<option value=""  selected>없음(표시안함)</option>
								<c:forEach var="form" items="${formList }" varStatus="status1">
									
									<option value="${ form.formCode }" <c:if test='${resultList.formCode eq form.formCode}'> selected </c:if> >${ form.formName }</option>
								
								</c:forEach>
								
							</select>
						</td>
					</tr>
					<tr>
		        		<th><i class="icono-asterisk"></i>   유형</th>
			            <td colspan="100">
				            <select  id="templateTypeSelect" name="templateTypeSelect" class="selectText width150" onchange="javascript:fn_template();" <c:out value="${ not empty resultList ? 'disabled' : '' }" /> >
				            	<!-- 차후 자바스크립트 수정 필요! -->
				            	<option value="">::: 선택 :::</option>
				            	<option id="templateTypeOption" value="0" <c:out value="${resultList.templateType == '0' ? 'selected' : ''}"/> <c:out value="${ not empty resultList ? 'disabled' : '' }" /> >일반</option>
				            	<option id="templateTypeOption" value="1" <c:out value="${resultList.templateType == '1' ? 'selected' : ''}"/> <c:out value="${ not empty resultList ? 'disabled' : '' }" /> >템플릿</option>
				            </select>
			            </td>
		        	</tr>
					<tr>
		        		<th><i class="icono-asterisk"></i>   표시항목</th>
			            <td id="templateplace" colspan="3">
			            
			            
			            	<c:if test="${resultList.templateType == '0' }">
			            	
			            		<input type="checkbox" id="placeRow" name="placeRow" value="2" placeholder="제목" <c:out value="${fn:contains(resultList.placeRow, '2') == true? 'checked' : ''}"/> onclick="javascript:fn_checked(2);"/> 제목
			            		<input type="checkbox" id="placeRow" name="placeRow" value="3" placeholder="내용" <c:out value="${fn:contains(resultList.placeRow, '3') == true? 'checked' : ''}"/> onclick="javascript:fn_checked(3);"/> 내용
								<input type="checkbox" id="placeRow" name="placeRow" value="4" placeholder="첨부파일" <c:out value="${fn:contains(resultList.placeRow, '4') == true? 'checked' : ''}"/> onclick="javascript:fn_checked(4);"/> 첨부파일
								<input type="checkbox" id="placeRow" name="placeRow" value="5" placeholder="아이디" <c:out value="${fn:contains(resultList.placeRow, '5') == true? 'checked' : ''}"/> onclick="javascript:fn_checked(5);"/> 아이디
								<input type="checkbox" id="placeRow" name="placeRow" value="6" placeholder="최초작성일" <c:out value="${fn:contains(resultList.placeRow, '6') == true? 'checked' : ''}"/> onclick="javascript:fn_checked(6);"/> 최초작성일
								<input type="checkbox" id="placeRow" name="placeRow" value="7" placeholder="최초작성자" <c:out value="${fn:contains(resultList.placeRow, '7') == true? 'checked' : ''}"/> onclick="javascript:fn_checked(7);"/> 최초작성자
								<input type="checkbox" id="placeRow" name="placeRow" value="8" placeholder="최종수정일" <c:out value="${fn:contains(resultList.placeRow, '8') == true? 'checked' : ''}"/> onclick="javascript:fn_checked(8);"/> 최종수정일
								<input type="checkbox" id="placeRow" name="placeRow" value="9" placeholder="최종수정자" <c:out value="${fn:contains(resultList.placeRow, '9') == true? 'checked' : ''}"/> onclick="javascript:fn_checked(9);"/> 최종수정자
			            	
			            		
			            	</c:if>
			            	
			            	<c:if test="${resultList.templateType == '1' }">
			            	
			            		<input type="checkbox" id="placeRow" name="placeRow" value="2" placeholder="제목" <c:out value="${fn:contains(resultList.placeRow, '2') == true? 'checked' : ''}"/>/> 제목
								<input type="checkbox" id="placeRow" name="placeRow" value="3" placeholder="내용" <c:out value="${fn:contains(resultList.placeRow, '3') == true? 'checked' : ''}"/>/> 내용
								<input type="checkbox" id="placeRow" name="placeRow" value="4" placeholder="첨부파일" <c:out value="${fn:contains(resultList.placeRow, '4') == true? 'checked' : ''}"/>/> 첨부파일
								<input type="checkbox" id="placeRow" name="placeRow" value="5" placeholder="아이디" <c:out value="${fn:contains(resultList.placeRow, '5') == true? 'checked' : ''}"/>/> 아이디			
								<input type="checkbox" id="placeRow" name="placeRow" value="6" placeholder="최초작성일" <c:out value="${fn:contains(resultList.placeRow, '6') == true? 'checked' : ''}"/>/> 최초작성일
								<input type="checkbox" id="placeRow" name="placeRow" value="7" placeholder="최초작성자" <c:out value="${fn:contains(resultList.placeRow, '7') == true? 'checked' : ''}"/>/> 최초작성자
			            	
			            	</c:if> <!-- 자바스크립트로 해당 코드를 넣는경오 fn:함수가 제대로 작동하지 않음 -->

			            </td>
		        	</tr>
		        	<tr>
		        		<th><i class="fa fa-asterisk" aria-hidden="true"></i>표시길이</th>
		        		<td id ="lengthInput" >
		        		
		        		
		        		</td>
		        	</tr>
				</table>
	
			</form>
			
			<div class="btngroup mt0">
				<c:if test="${empty resultList }"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">등록</button> </c:if>
				<c:if test="${not empty resultList}"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">수정</button> </c:if>
				<c:if test="${not empty resultList}"> <button class="btn05 fr" onclick="javascript:fn_delete();" type="button">삭제</button> </c:if>
				<button class="btn03 fr" onclick="javascript:fn_back();" type="button">취소</button>
			</div>
		</div>

</div>

</body>