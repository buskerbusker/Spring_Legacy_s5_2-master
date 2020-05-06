<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
<c:import url="../template/summer.jsp"></c:import>
</head>
<body>
	<c:import url="../template/header_sub.jsp"></c:import>
	<div class="container">
		<h1>${fn:toUpperCase(board)}UpdateForm</h1>


		<form action="./${board}Update" id="frm" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="num" value="${vo.num}">
			<div class="form-group">
				<label for="title">Title:</label> <input type="text"
					value="${vo.title}" class="form-control" id="title" name="title">
			</div>
			<div class="form-group">
				<label for="writer">Writer:</label> <input type="text"
					disabled="disabled" value="${vo.writer}" class="form-control"
					id="writer" name="writer">
			</div>
			<div class="form-group">
				<label for="contents">Contents:</label>
				<textarea rows="5" cols="" class="form-control" id="contents"
					name="contents"></textarea>
			</div>

			<input type="button" id="add" class="btn btn-info" value="AddFile">
			<div id="file"></div>

			<div class="form-group">
				<label for="contents">Files:</label>
				<c:forEach items="${vo.boardFileVOs}" var="fileVO">
					<p id="pdel">${fileVO.oriName}<i id="${fileVO.fileNum}"
							title="${fileVO.board}"
							class="glyphicon glyphicon-remove remove fileDelete"></i>
					</p>
				</c:forEach>

			</div>


			<input type="submit" id="btn" class="btn btn-default" value="Write">
		</form>

	</div>


	<script type="text/javascript" src="../resources/js/boardForm.js"></script>
	<script type="text/javascript">
	
	var size = ${size};
	size = ${vo.boardFileVOs.size()};
	
	size = ${fn:length(vo.boardFileVOs)};
	
	count = count+size;
		
		$("#contents").summernote('code', '${vo.contents}');

		$(".fileDelete").click(function() {

			var check = confirm("Delete??");

			if (check) {
			
			var s = $(this);
			
			$.post("../boardFile/fileDelete", {
				fileNum : $(this).attr("id"),
				board : $(this).attr("title")
			}, function(data) {
				if (data> 0) {
					s.parent().remove();
					count--;
				} else {
					alert("파일삭제 실패");
				}
			})
			
			}//if
		})
		
		
	</script>

</body>
</html>