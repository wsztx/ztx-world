<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script>  
	var baseUrl = "${base}";  
</script>
<script type="text/javascript" src="${base}/resource/plugin/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="${base}/resource/plugin/bootstrap-4.0.0/css/bootstrap.min.css">
<script src="${base}/resource/plugin/bootstrap-4.0.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${base}/resource/style/login/login.css">
<script type="text/javascript" src="${base}/resource/javascript/login/login.js"></script>
</head>

<body>
	<div>
		<div class="jumbotron text-info bg-light jumheight1">
			<h1>完美世界</h1>
			<p>一个简单的系统</p>
		</div>
		<!-- 轮播图 -->
		<!-- 指示符 -->
  		<div class="row">
		<div class="left">
		<div id="demo" class="carousel slide " data-ride="carousel">
			<ul class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
			</ul>
			<!-- 轮播图片 -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="${base}/resource/image/login/login1.jpg">
				</div>
				<div class="carousel-item">
					<img src="${base}/resource/image/login/login2.jpg">
				</div>
				<div class="carousel-item">
					<img src="${base}/resource/image/login/login3.jpg">
				</div>
			</div>
			<!-- 左右切换按钮 -->
			<a href="#demo" class="carousel-control-prev" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a>
			<a href="#demo" class="carousel-control-next" data-slide=next>
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>
 		</div>
		<div class="right">
		<!-- 登陆窗口 -->
		<div class="card">
			<div class="card-header">
				用户登陆
			</div>
			<div class="card-body">
					<table style="border-collapse: separate;border-spacing: 0px 10px;">
						<tr>
							<td class="margin-top:10">
								<label>用户名：</label>
							</td>
							<td>
								<input type="text" id="userCode">
							</td>
						</tr>
						<tr>
							<td>
								<label for="pwd">密码:</label>
							</td>
							<td>
								<input type="password" id="password" >
							</td>
						</tr>
					</table>
					<div class="form-check">
						<label class="form-check-label">
							<input type="checkbox" name="" class="form-check-input">记住密码
						</label>
					</div>
					<div class="footer">
					<button class="btn btn-primary" onclick="doLogin('${base}');">登陆</button>
					<button type="" class="btn btn-secondary" onclick="toRegister();">注册</button>
					<button type="button" class="btn btn-link"><small>忘记密码？</small></button>
					</div>
			</div>
		</div>
		</div>
 		</div>
		<div class="jumbotron bg-light jumheight2">
			<p class="footer2">@Master_zhoutx</p>
		</div>
	</div>
</body>
</html>