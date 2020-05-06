/**
 * 
 */

var count = 1;

$("#file").on("click", ".remove", function() {
	$(this).parent().remove();
	count--;
});

$("#add")
		.click(
				function() {
					if (count < 6) {
						$("#file")
								.append(
										'<div class="form-group"> <label for="file"> File :</label> <input type="file" class="form-control files" name="files"> <i class="glyphicon glyphicon-remove remove"></i> </div> ');
						count++;
					} else {
						alert("파일은 최대 5개 만 가능");
					}
				});

$("#contents").summernote({
	height : 300,
	callbacks : {
		onImageUpload : function(files, editor) {

			var formData = new FormData();// <form>태그
			formData.append('files', files[0]);// <input type= "file"
			// name="files">

			$.ajax({
				type : "POST",
				url : "../boardFile/fileInsert",
				data : formData,
				enctype : "multipart/form-data",
				cache : false,
				contentType : false,
				processData : false,
				success : function(imageName) {
					imageName = imageName.trim();
					console.log(imageName);
					$("#contents").summernote('editor.insertImage', imageName);
				}
			});
		}, // on image upload
		onMediaDelete : function(files) {

			var fileName = $(files[0]).attr("src");
			fileName = fileName.substring(fileName.lastIndexOf("/"));
			console.log(fileName);
			$.ajax({
				type : "POST",
				url : "../boardFile/summerDelete",
				data : {
					fileName : fileName
				},
				success : function(data) {
					console.log(data);
				}

			});
		}// onmediaDelete
	}
// call back
})

$("#btn").click(function() {
	// title, contents 데이터 유무 검증
	var title = $("#title").val();
	var contents = $("#contents").summernote('code');

	var ch3 = true;
	$(".files").each(function() {
		if ($(this).val() == "") {
			ch3 = false;
		}
	});

	var ch1 = title != "";
	var ch2 = $("#contents").summernote('isEmpty');

	if (ch1 && !ch2 && ch3) {
		// form 전송(submit event 강제 발생)
		// contents Server null이 될때
		$("#con").val(contents);

		$("#frm").submit();
	} else {
		// submit event 종료
		alert("필수 요소는 다 입력하세요");
	}

	/*
	 * var contents = $("#contents").val(); console.log(title=='');
	 * console.log(contents==""); console.log(title.length);
	 * console.log(contents.length);
	 * 
	 * console.log($("#contents").summernote('isEmpty'));
	 */
});

// $("선택자").action();
/*
 * $("#contents").summernote({ height: 300, callbacks : { onImageUpload:
 * function(file){ console.log("upload"); } } });
 */

