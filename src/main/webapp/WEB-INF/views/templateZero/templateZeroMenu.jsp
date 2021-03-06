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
	
	function fn_list(searchCnd) {
		//페이지 선택
		document.frm.pageIndex.value = searchCnd;
		document.frm.action = '<c:url value="/template/templateInfo.go"/>';
		document.frm.submit();
	}
	
	function fn_write(){

		document.frm.action = '<c:url value="/template/templateZeroWrite.go"/>';
		document.frm.submit();
	}
	
	function fn_update(code){
		
		document.frm.code.value = code ;
		document.frm.action = '<c:url value="/template/templateZeroWrite.go"/>';
		document.frm.submit();
		
	}

</script>

<div class="contents_wrap">
		
		<form name="frm" method="post">
			<input type="hidden" name="siteCode" value="${templateInfoVO.siteCode}" />
			<input type="hidden" name="pageIndex" value="${searchVO.pageIndex}" />
			<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}"/>
			<!-- 검색해서 온게 아니라 searchVO로 페이지를 활용하지 않음 -->
			<input type="hidden" id="siteTitle" name="siteTitle" value="${searchVO.siteTitle }"/>
			<input type="hidden" id="code" name="code" value=""/>
			
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
				</div>
				
				<div class="search_wrap fr">
					<select class="width150 selectText mb05" name="searchCnd">
						<option value="" <c:if test='${searchVO.searchCnd == ""}'> selected </c:if>>전체</option>
						<option value="0" <c:if test='${searchVO.searchCnd == "0"}'> selected </c:if>>제목</option>
						<option value="1" <c:if test='${searchVO.searchCnd == "1"}'> selected </c:if>>관리자</option>
					</select>
					<input type="text" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeypress="javascript:fn_searchKeyPressed(event);" class="inputText width240 mr0" placeholder="검색어를 입력하세요">
					<button type="button" class="btn01" onclick="javascript:fn_list('1')">검색</button>
				</div>
				
					<table class="admin_table tableline">
						<colgroup>
							<c:forEach var="fieldList" items="${fieldList}" varStatus="status">
									
							</c:forEach>
						</colgroup>
						<thead>
							<tr>
								<th class="width80">No</th>
								<c:forEach var="fieldList" items="${fieldList }" varStatus="status">
									<th style="width:<c:out value="${fieldWidth[status.index] }"/>px">${fieldList.column_Comment}</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
						
							<c:if test="${ not empty noticeList }">
								<c:out value="${fn:length(noticeList)}"/><c:out value="${noticeList[0].code}"/> 
								<c:forEach var="notice" items="${noticeList }" varStatus="status1">
									<tr onclick="javascript:fn_update('${notice.code}')">
										<td> [공지] </td>
										<c:forEach var="notice2" items="${fieldList }" varStatus="status2">
											<c:if test="${notice2.ordinal_Position == 2 }"><td>${notice.title}</td></c:if>
											<c:if test="${notice2.ordinal_Position == 3 }"><td>${notice.context}</td></c:if>
											<c:if test="${notice2.ordinal_Position == 4 }"><td>${notice.atchFileId}</td></c:if>
											<c:if test="${notice2.ordinal_Position == 5 }"><td>${notice.writerID}</td></c:if>
											<c:if test="${notice2.ordinal_Position == 6 }"><td>${notice.frstRegistPnttm}</td></c:if>
											<c:if test="${notice2.ordinal_Position == 7 }"><td>${notice.frstRegistNm}</td></c:if>
											<c:if test="${notice2.ordinal_Position == 8 }"><td>${notice.lastUpdtPnttm}</td></c:if>
											<c:if test="${notice2.ordinal_Position == 9 }"><td>${notice.lastUpdtNm}</td></c:if>
										</c:forEach>
									</tr>
								</c:forEach>
							</c:if>
							
							<c:forEach var="result" items="${resultList }" varStatus="status1">
								<tr onclick="javascript:fn_update('${result.code}')">
									<td> <c:out value="${paging.dbCount - (paging.currPage * paging.recordCountPerPage + status1.index) }" /> </td>
									<c:forEach var="result2" items="${fieldList }" varStatus="status2">
										<c:if test="${result2.ordinal_Position == 2 }"><td>${result.title}</td></c:if>
										<c:if test="${result2.ordinal_Position == 3 }"><td>${result.context}</td></c:if>
										<c:if test="${result2.ordinal_Position == 4 }"><td>${result.atchFileId}</td></c:if>
										<c:if test="${result2.ordinal_Position == 5 }"><td>${result.writerID}</td></c:if>
										<c:if test="${result2.ordinal_Position == 6 }"><td>${result.frstRegistPnttm}</td></c:if>
										<c:if test="${result2.ordinal_Position == 7 }"><td>${result.frstRegistNm}</td></c:if>
										<c:if test="${result2.ordinal_Position == 8 }"><td>${result.lastUpdtPnttm}</td></c:if>
										<c:if test="${result2.ordinal_Position == 9 }"><td>${result.lastUpdtNm}</td></c:if>
									</c:forEach>
								</tr>
							</c:forEach>
							
							<c:if test="${empty resultList}">
								<tr> 
									<td colspan="7">
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