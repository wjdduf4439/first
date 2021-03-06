<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<c:forEach var="resultFile" items="${mfile}" varStatus="status1">

		<img  id="${code}iconViewImg${status1.index}" name="${code}iconViewImg${status1.index}" class="fileicon" src="${pageContext.request.contextPath}/resources/image/${resultFile.fname}" onclick="fn_screenView('${resultFile.fname}');" />
		<!-- fn_screenView는 각 페이지 함수에서 자체적으로 만들것 -->
		
</c:forEach>