<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="expires" content="-1" >
<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icono.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/siteMenu.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/headMenu.css" />


<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/smarteditor2-master/workspace/js/service/HuskyEZCreator.js"  charset="utf-8"></script>

<style>
			
</style>

<title>header 포함</title>

		<nav id="topMenu">
				<ul>
						
					 	<c:forEach var="form" items="${formList }" varStatus="status1">
	
							<li onmouseover="javascript:fn_show();" ><a class="menuLink" >${form.formName }</a></li>
								
						</c:forEach>
						
						<c:forEach var="form" items="${formList }" varStatus="status1">
							<ul onmouseout="javascript:fn_noshow();" class="sub-menu"style="margin-left: ${status1.index * 100}px;" >
									<c:forEach var="sites" items="${sites }" varStatus="status1" begin="${form.startIndex }" end = "${form.endIndex }" >
									
											<ol onmouseover="javascript:fn_show();" ><a onmouseover="javascript:fn_show();" href="javascript:fn_SiteLink('${sites.siteCode }');" >${sites.title }</a></ol>
														
									</c:forEach>
							</ul>
						</c:forEach>	
						
						<li><a class="menuLink" href=<c:url value="/site/siteAdmin.go"/>>게시판 관리</a></li>
					 	<li><a class="menuLink" href=<c:url value="/form/formAdmin.go"/>>항목 관리</a></li>
						
						<form id="sitefrm" name="sitefrm" method="post">

							<input type="hidden" id="siteCode" name="siteCode" value="" />
						
						</form>
					 	
					 	<script>

							function fn_SiteLink(siteCode){
							
								document.sitefrm.siteCode.value = siteCode;
								document.sitefrm.action = '<c:url value="/template/templateInfo.go"/>';
								document.sitefrm.submit();
								
							}
							
							function fn_show(){ $(".sub-menu").css("visibility", "visible"); }
							
							function fn_noshow(){ $(".sub-menu").css("visibility", "hidden"); }
						
						</script>
					 	
				</ul>
		</nav>

</head>