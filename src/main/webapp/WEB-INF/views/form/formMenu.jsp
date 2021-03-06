<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />


<script>
	
	function fn_searchKeyPressed(event) {
		//검색 상자 키 이벤트
		if (event.keyCode == 13) {
			fn_egov_select_List('1');
		}
	}
	
	function fn_list(pageNo) {
		//페이지 선택
		document.frm.pageIndex.value = pageNo;
		document.frm.action = '<c:url value="/form/formAdmin.go"/>';
		document.frm.submit();
	}
	
	function fn_write(){

		document.frm.action = '<c:url value="/form/formWrite.go"/>';
		document.frm.submit();
	}
	
	function fn_update(code){
		
		document.frm.formCode.value = code ;
		document.frm.action = '<c:url value="/form/formWrite.go"/>';
		document.frm.submit();
		
	}

</script>
<body>

<div class="contents_wrap">
		
		<form name="frm" method="post">
			<input type="hidden" name="pageIndex" value="${searchVO.pageIndex}" />
			<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}"/>
			<input type="hidden" id="formCode" name="formCode" value=""/>
			
			<div class="contents">
				<div class="space10"></div>
				<div class="board_page fl mt0">				    
				    <div class="board_page fl mr15 mt10">
						<span class="total">Total <strong>${paging.dbCount}</strong>건</span>
						<span>Page <strong>${paging.currPage + 1}</strong>/<fmt:parseNumber value="${paging.dbCount/paging.recordCountPerPage+1}" type="number" integerOnly="true"/></span>
					</div>&nbsp;&nbsp; 
					<select name="recordCountPerPage" onchange="javascript:fn_list('0');" class="selectText width80 mt10">
						<option value="10" <c:out value="${paging.recordCountPerPage == 10 ? 'selected' : ''}"/>>10</option>
						<option value="20" <c:out value="${paging.recordCountPerPage == 20 ? 'selected' : ''}"/>>20</option>
						<option value="30" <c:out value="${paging.recordCountPerPage == 30 ? 'selected' : ''}"/>>30</option>
					</select>
					<span>게시판의 묶음인 항목을 만드는 게시판입니다</span>
				</div>
				
				<div class="search_wrap fr mt10">
					<select class="width150 selectText mb05" name="searchCnd">
						<option value="" <c:if test='${searchVO.searchCnd == ""}'> selected </c:if>>전체</option>
						<option value="0" <c:if test='${searchVO.searchCnd == "0"}'> selected </c:if>>제목</option>
						<option value="1" <c:if test='${searchVO.searchCnd == "1"}'> selected </c:if>>관리자</option>
					</select>
					<input type="text" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeypress="javascript:fn_searchKeyPressed(event);" class="mt15 width240 mr0" placeholder="검색어를 입력하세요">
					<button type="button" class="btn01" onclick="javascript:fn_list('0')">검색</button>
				</div>
				
					<table class="admin_table tableline">
						<colgroup>
							<col class="width80">
							<col class="width200">
							<col class="width80">
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>게시판명</th>
								<th>관리자</th>
							</tr>
						</thead>
						<tbody>
							
							<c:forEach var="result" items="${resultList }" varStatus="status1">
								<tr onclick="javascript:fn_update('${result.formCode}')">
									<td> <c:out value="${paging.dbCount - (paging.currPage * paging.recordCountPerPage + status1.index) }" /> </td>
									<td> ${result.formName }</td>
									<td> ${result.frstRegistNm }</td>
									
								</tr>
							</c:forEach>
							
							<c:if test="${empty resultList}">
								<tr> 
									<td colspan="3">
										조회된 내역이 없습니다.
										<spring:message code="common.nodata.msg" />
									</td>
								</tr>   	          				 			   
							</c:if>
							
							
						</tbody>
					</table>
				
				<div class="pageNum">
					<div class="pagination">
						<ul class="pagination pagination-sm">
							<c:out value="${paging.pageList }" escapeXml="false"/>
						</ul>
					</div>
				</div>
				
				<div class="btngroup mt0">
					<button class="btn02 fr" onclick="javascript:fn_write();" type="button">등록</button>
				</div>
			</div>
		</form>

</div>

</body>
</html>