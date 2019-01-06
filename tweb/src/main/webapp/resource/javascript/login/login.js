function doLogin(){
	alert(baseUrl + "/base/user/login")
	$.ajax({
		type: "POST",
		url: baseUrl + "/base/user/login", 
		dataType: "json",
		data: {"userCode":$("#userCode"), "password":$("#password")},
		success: function(result){
			if(result.success){
				location.href = "${base}/index";
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