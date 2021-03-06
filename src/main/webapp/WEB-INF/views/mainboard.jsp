<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<style>

#slide2{

	max-width:115px;
	overflow: hidden; 
	/* 내용이 넘치면 자르기 */
}

#slide2 img{

	transition: transform 1s;
	/*오른쪽으로 100 이동*/

}

</style>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> <!-- 제이쿼리 참조 -->


<script>

function gotolist(b_code){

	//listform.target = "pop";//새 탭을 띄우면서 표현
	listform.b_codestring.value = b_code;
	listform.submit();
	
}

function gotoinsert(){
	
	document.listform.mode.value = "insert";
	listform.action = "writeboard.go";
	listform.submit();
	
}

function fn_list(page){
	
	listform.pageIndex.value = page;
	listform.action = "mainboard.go";
	listform.submit();
	
}

function gotoexcel(){
	listform.action = "excelDown.go";
	listform.submit();
	
}

function gotochart(){
	
	listform.action = "chartboard.go";
	listform.submit();
	
}
function gotomail(){
	
	listform.action = "mailboard.go";
	listform.submit();
	
}

function initMap(){
	
	var latitude;
	var longitude;
	var myside;
	
	 if (navigator.geolocation) {
	        //위치 정보를 얻기
	        navigator.geolocation.getCurrentPosition (function(pos) {
	        	
	        	latitude = pos.coords.latitude;
	        	longitude = pos.coords.longitude;
	        	
	        	myside = {lat: latitude, lng : longitude};
	            
	            var map = new google.maps.Map(document.getElementById('map'),{ zoom : 15, center : myside }); //map 좌표 설정
	        	var marker = new google.maps.Marker({ position : myside, title : "hello marker" });  marker.setMap(map);
	            
	        });
	    }
	
}

var index = 0;
var index2 = 0;
var returnslide = 0;
window.onload = function(){ slideShow(); moveSlideShow();  }

function slideShow(){
	
	//슬라이드 쇼의 배열은 원래 한 div안에 나열된 여러개의 그림이다
	//그림의 style.display속성을 안보일때는 none, 보일때는 block로 설정하여 표현한다.
	//전역변수로 선언된 index함수에 따라 해당 이미지를 표현하고(block) 다음 인덱스의 지표가 된다.
	//4초가 지나면 해당 함수를 다시 호출한다.
	
	var i;
	var x = document.getElementsByClassName("slide1");
	
	for(i = 0; i < x.length; i++){ x[i].style.display = "none"; x[i].style.transition = "2s";   }
	index++;
	
	if(index > x.length){ index = 1; }
	
	x[index-1].style.display = "block"
	
	setTimeout(slideShow,3000);
	
}

function moveSlideShow(){
	
	if(index2 > 4){ returnslide = 1; index2 = 0; }
	
	if( returnslide == 1){ returnslide = 0; }
	
	$(".slide2").css("transform","translateX(-" + index2 * 114 +"px)");
	index2++;
	
	setTimeout(moveSlideShow,3000);
	
	
}

</script>

<style media="screen">


</style>

<body>

	<form method="post" id="listform" name="listform" action="lookboard.go" >
	<!-- action속성은 해당 프로젝트의 context path경로와 함께 조합되서 submit 된다. -->
	
		<input type="hidden"  id="b_codestring"  name="b_codestring" value="" />
		<input type="hidden" id="mode" name="mode"  value = "" />
		<input type="hidden"  id="pageIndex"  name="pageIndex" value="${searchVO.pageIndex}" />
	
		<table border="1" cellpadding="1" cellspacing="1">
			
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
				</tr>
				<c:if test="${empty resultList }">
					
					<tr>
						<th colspan="4">데이터가 없습니다.</th>
					</tr>
					
				</c:if>
				
				<!-- jstl은 jsp문서가 호출되고 먼저  var에 해당하는 변수정보를 먼저 읽고 바인드한다. -->
				<c:forEach var="resultList" items="${resultList }" varStatus="status" >
				
					<tr>
						<th>${resultList.b_code}</th>
						<th><a href="javascript:gotolist('${resultList.b_code}')" >${resultList.b_title }</a></th>
						<th>${resultList.b_id }</th>
						<th>${resultList.b_hit }</th>
					</tr>
				
				</c:forEach>
		
		</table>
	</form>
	
	<c:out value="${paging.pageList }" escapeXml="false"/>
	
	</br></br>
	<input type="button" name="insertboard" id="insertboard"  value="게시글 작성" onclick="javascript:gotoinsert()" />
	<input type="button" name="excelboard" id="excelboard"  value="현재 게시판 엑셀 작성" onclick="javascript:gotoexcel()" />
	<input type="button" name="chartboard" id="chartboard"  value="차트 페이지" onclick="javascript:gotochart()" />
	<input type="button" name="mailview" id="mailview"  value="이메일 보내기" onclick="javascript:gotomail()" />
	
	<div id="map" style="width:400px; height:400px"></div>

	<div id="slide">
	
		<img class="slide1" src="${pageContext.request.contextPath}/resources/image/thisiszero.PNG"/>
		<img class="slide1" src="${pageContext.request.contextPath}/resources/image/thisisone.PNG"/>
		<img class="slide1" src="${pageContext.request.contextPath}/resources/image/thisistwo.PNG"/>
		<img class="slide1" src="${pageContext.request.contextPath}/resources/image/thisisthree.PNG"/>
	
	</div>
	
	<div id="slide2">
	
		<img class="slide2" src="${pageContext.request.contextPath}/resources/image/zerotofour.PNG"/>
	
	</div>
		비디오
		</br>
	<iframe src="https://www.youtube.com/embed/dNDoWkG1FDs" width="600" height="300" scrolling="yes"></iframe>
	

</body>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDCJ3Gs3Iyw9xnKwojd0LXIaGBhZLiRljw&callback=initMap"> </script>
	
   <!-- async defer : 구글 맵 동기 설정 : 비동기 -->
   <!-- callback : 구글 맵 동기 설정 : 비동기 -->
   <!-- src : 구글 맵 api 설정 -->
   <!-- callback : 반응 자바스크립트 함수 -->
   
</html>