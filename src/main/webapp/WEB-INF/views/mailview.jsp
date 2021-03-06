<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 

<link rel="stylesheet" href='<c:url value="/resources/bootstrap-3.3.2-dist/css/bootstrap.css" />' >
<link rel="stylesheet" href='<c:url value="/resources/bootstrap-3.3.2-dist/css/bootstrap.min.css" />' >
<link rel="stylesheet" href='<c:url value="/resources/bootstrap-3.3.2-dist/css/bootstrap-theme.css" />' >
<link rel="stylesheet" href='<c:url value="/resources/bootstrap-3.3.2-dist/css/bootstrap-theme.min.css" />' >
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> <!-- 제이쿼리 참조 -->
<script src='<c:url value="/resources/bootstrap-3.3.2-dist/js/bootstrap.min.js"/>'></script>
 
<title>메일 보내기</title>
</head>

<script>

function gotomailsend(){
	
	listform.submit();
	
}

</script>

<body>
<div class="container">
  <h4>메일 보내기</h4>
  <form action="mailsendboard.go" method="post" name="listform">
    <div align="center"><!-- 받는 사람 이메일 -->
      <input type="text" name="tomail" size="12" style="width:100%" placeholder="상대의 이메일" class="form-control" >
    </div>     
    <div align="center"><!-- 제목 -->
      <input type="text" name="title" size="12" style="width:100%" placeholder="제목을 입력해주세요" class="form-control" >
    </div>
    <p>
    <div align="center"><!-- 내용 --> 
      <textarea name="content" cols="50" rows="12" style="width:100%; resize:none" placeholder="내용#" class="form-control"></textarea>
    </div>
    <p>
      <button type="button" class="btn btn-danger"  onclick="javascript:gotomailsend()">메일 보내기</button>
  </form>
</div>
</body>
</html> 