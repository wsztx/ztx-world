<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<script type="text/javascript" src="${base}/resource/plugin/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function doLogout(){
	$.ajax({
		type: "POST",
		url: "${base}/base/user/logout", 
		dataType: "json",
		success: function(result){
			if(result.success){
				location.href = "${base}/base/user/tologin";
			}else{
				alert(result.message);
			}
		},
		error: function(result){
			alert("服务器异常.")
		}
	});
}
</script>
</head>
<body>
Welcome to home page.
<button onclick = "doLogout()">退出</button>
</body>
</html>