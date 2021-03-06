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
<title>Insert title here</title>
</head>


<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/Chart.min.js"></script> --%>



<body onload="barChart(), lineChart();">

this is barchart


<canvas id="barCanvas" width="384" height="210"></canvas>

</br>

this is linechart

<canvas id="lineCanvas" width="384" height="210"></canvas>

</body>

<script>
function barChart(){

	var data = {

			labels: ["09시", "10시", "11시", "12시", "13시", "14시", "15시", "16시", "17시", "18시", "19시", "20시", "21시", "22시"],

			datasets: [

				{

					label: "",

					fillColor: "rgba(250,250,250,0.5)",

					strokeColor: "rgba(150,200,250,0.8)",

					highlightFill: "rgba(150,200,250,0.75)",

					highlightStroke: "rgba(150,200,250,1)",

					data: [65, 59, 80, 81, 56, 55, 30, 100, 120, 50, 11, 40, 70, 120]

				}

			]

		};

    var ctx = document.getElementById("barCanvas").getContext("2d");

    var options = { };

    var barChart = new Chart(ctx).Bar(data, options);

}

function lineChart(){

	var data = {

	        labels: ["월","화","수","목","금","토","일"],

	        datasets: [

	            {

	                label: "",
	                fillColor: "rgba(220,220,220,0.2)",
	                strokeColor: "rgba(220,220,220,1)",
	                pointColor: "rgba(220,220,220,1)",
	                pointStrokeColor: "#fff",
	                pointHighlightFill: "#fff",
	                pointHighlightStroke: "rgba(220,220,220,1)",
	                data: [2, 3, 5, 7, 11, 13, 17]

	            },

	            {

	                label: "",
	                fillColor: "rgba(151,187,205,0.2)",
	                strokeColor: "rgba(151,187,205,1)",
	                pointColor: "rgba(151,187,205,1)",
	                pointStrokeColor: "#fff",
	                pointHighlightFill: "#fff",
	                pointHighlightStroke: "rgba(151,187,205,1)",
	                data: [0, 1, 1, 2, 3, 5, 8]

	            }

	        ]

	    };

	    var ctx = document.getElementById("lineCanvas").getContext("2d");

	    var options = { };

	    var lineChart = new Chart(ctx).Line(data, options);

}

</script>

</html>