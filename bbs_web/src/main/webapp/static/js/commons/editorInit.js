var editor = CKEDITOR.replace('ckeditor',{
	        customConfig : '/ckeditor/question_config.js'
	    });

//登陆提示
editor.on("focus", function() {
	if ($("a.sign").text() != "") {
		toastr.info("请登录");
		$(".sign").trigger("click");
	}
})
