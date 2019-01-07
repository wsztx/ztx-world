function doLogin(baseUrl){
	$.ajax({
		type: "POST",
		url: baseUrl + "/base/user/login",
		dataType: "json",
		data: {"userCode":$("#userCode").val(), "password":$("#password").val(), "rememberMe":$("#rememberMe").prop('checked')},
		success: function(result){
			if(result.success){
				location.href = baseUrl + "/index";
			}else{
				alert(result.message);
			}
		},
		error: function(result){
			alert("服务器异常.");
		}
	});
}

function toRegister(){
	
}
