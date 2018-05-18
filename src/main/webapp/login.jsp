<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  	<c:set value="${pageContext.request.contextPath}" var="contextPath"></c:set>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>eshop登录</title>

    <link href="${contextPath}/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${contextPath}/css/font-awesome.min.css?v=4.3.0" rel="stylesheet">

    <link href="${contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${contextPath}/css/style.min.css?v=3.2.0" rel="stylesheet">
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <%--<h1 class="logo-name">易</h1>--%>

            </div>
            <h3>欢迎使用 eshop</h3>

            <form class="m-t" role="form" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="username" placeholder="用户名" required="required" value="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="密码">
                </div>
                <input type="button" onclick="doLogin();" class="btn btn-primary block full-width m-b" value="登 录" />

                <%--<p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>--%>
                </p>

            </form>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${contextPath}/js/jquery-2.1.1.min.js"></script>
    <script src="${contextPath}/js/bootstrap.min.js?v=3.4.0"></script>
	<script>
		function doLogin(){
			var username = $("input[name='username']").val();
			var password = $("input[name='password']").val();
			$.ajax({
			    url:'${contextPath}/dologin',
			    type:'POST', //GET
			    data:{
			    	username:username,
			    	password:password
			    },
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data){
			        console.log(data);
			        if(data.result == "success"){
			        	window.location.href = "${contextPath}/index";
			        }else{
			        	alert(data.msg);
			        }
			    },
			    error:function(xhr){
			        alert('系统错误');
			    }
			})
		}
	</script>
</body>

</html>