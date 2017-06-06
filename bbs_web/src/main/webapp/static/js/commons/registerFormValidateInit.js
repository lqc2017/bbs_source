/* 注册表单验证 */
$("form[name='register_form']").bootstrapValidator({
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		'username' : {
			trigger : 'change',
			validators : {
				notEmpty : {
					message : '用户名不能为空'
				},
				regexp : {
					regexp : /^[a-zA-Z0-9_\.]+$/,
					message : '只能是数字和字母_.'
				},
				stringLength : {
					min : 6,
					max : 20,
					message : '用户名长度必须在6到30之间'
				},
				callback : {
					message : '用户名已存在',
					callback : function(value, validator) {
						var flag = false;
						if (value.toString().trim() == '')
							return false;
						$.ajax({
							async : false,
							type : "get",
							url : "/validate_username/" + value,
							datatype : 'json',
							success : function(result) {
								if (result == "success") {
									flag = true;
								} else {
									flag = false;
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								flag = false;
								alert(textStatus);
							}
						})
						if (flag)
							return true;
						return false;
					}
				}
			}
		},
		'password' : {
			validators : {
				notEmpty : {
					message : '密码不能为空'
				},
				identical : {
					field : 'confirm',
					message : '密码不一致'
				},
				stringLength : {
					min : 6,
					max : 20,
					message : '用户名长度必须在6到30之间'
				},
			}
		},
		'confirm' : {
			validators : {
				notEmpty : {
					message : '确认密码不能为空'
				},
				identical : {
					field : 'password',
					message : '密码不一致'
				},
				stringLength : {
					min : 6,
					max : 20,
					message : '用户名长度必须在6到30之间'
				},
			}
		},
		'nickname' : {
			trigger : 'change',
			validators : {
				notEmpty : {
					message : '昵称不能为空'
				},
				callback : {
					message : '超过输入限制,请控制在12个字符以内',
					callback : function(value, validator) {
						if (getLen(value) <= 12)
							return true
						return false;
					}
				}
			}
		}
	}
})