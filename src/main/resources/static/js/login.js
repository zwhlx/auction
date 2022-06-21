//1.监听注册按钮是否被点击
function reg() {
	$.ajax({
		url: "/users/reg",
		type: "POST",
		data: $("#form-reg").serialize(),
		dataType: "JSON",
		success: function (json) {
			if (json.state===200){
				alert("用户注册成功")
			}else if(json.state==4000){
				alert("用户名重复")
			}else{alert("未知错误")}
		},
		error: function (xhr) {
			alert("未知异常！"+xhr.status)
		}
	})
};
//1.监听登录按钮是否被点击
function login() {
	$.ajax({
		url: "/users/login",
		type: "POST",
		data: $("#form-reg").serialize(),
		dataType: "JSON",
		success: function (json) {
			if (json.state==200){
				alert("登录成功")
			}else if(json.state==5001){
				alert("用户名不存在")
			}else if(json.state==5002){
				alert("密码错误")
			}else {
				alert("未知错误")
			}
		},
		error: function (xhr) {
			alert("未知异常！"+xhr.status)
		}
	})
};