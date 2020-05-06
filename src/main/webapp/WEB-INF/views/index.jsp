<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:import url="./template/boot.jsp"></c:import>

</head>
<body>
	<c:import url="./template/header.jsp"></c:import>
	<button id="btn">BUTTON</button>

	<button id="btn2">BUTTON2</button>

	<button id="btn3">BUTTON3</button>

	Button
	<script type="text/javascript">
		$("#btn").click(function() {
			//jquery ajax
			//GET
			alert("start");
			$.get("./notice/noticeSelect?num=30", function(result) {
				console.log(result);
			});
			alert("finish");
		});

		$("#btn2").click(function() {
			$.get("./json/json1", function(data) {
				//0. data가 string 인지 object인지 판별
				//console.log(data); 그대로 나오면 STRING object로 뜨면 object

				//1.json object로 변환
				//data = data.trim();
				console.log(data);
				console.log(data.num);
				console.log(data.title);

			});
		});

		$("#btn3").click(function() {
			$.get(url="https://api.manana.kr/exchange/rate.json?base=KRW&code=KRW,USD,JPY", function(data){
				console.log(data[1].rate);
			})
		})
	</script>
</body>
</html>



