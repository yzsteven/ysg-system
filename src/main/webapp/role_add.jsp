<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set value="${pageContext.request.contextPath}" var="contextPath"></c:set>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>新增角色</title>

<link href="${contextPath}/css/bootstrap.min.css?v=3.4.0"
	rel="stylesheet">
<link href="${contextPath}/css/font-awesome.min.css?v=4.3.0"
	rel="stylesheet">
<link href="${contextPath}/css/plugins/iCheck/custom.css"
	rel="stylesheet">
<link href="${contextPath}/css/animate.min.css" rel="stylesheet">
<link href="${contextPath}/css/style.min.css?v=3.2.0" rel="stylesheet">
<link rel="stylesheet"
	href="${contextPath}/css/bootstrap-multiselect.css" type="text/css" />
<script src="${contextPath}/js/ysg/template.js"></script>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">


		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							新增角色
						</h5>
					</div>
					<div class="ibox-content">
						<form method="get" class="form-horizontal">

							<div class="form-group">
								<label class="col-sm-2 control-label">角色名称</label>

								<div class="col-sm-2">
									<input type="text" id="role" name="role"
										class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">角色描述</label>

								<div class="col-sm-2">
									<input type="text" id="description" name="description"
										   class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">权限</label>

								<div class="col-sm-2">
									<select class="form-control m-b" id="resourceIds" multiple="multiple">
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<div class="col-sm-2 col-sm-offset-2">
									<button type="button" class="btn btn-primary" onclick="save();">保存</button>
									<button type="button" class="btn btn-white" onclick="cancel();">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="modal-form" class="modal fade" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-6 b-r">
							<h3 class="m-t-none m-b">登录</h3>

							<p>欢迎登录本站(⊙o⊙)</p>

							<form role="form">
								<div class="form-group">
									<label>用户名：</label> <input type="email" placeholder="请输入用户名"
										class="form-control">
								</div>
								<div class="form-group">
									<label>密码：</label> <input type="password" placeholder="请输入密码"
										class="form-control">
								</div>
								<div>
									<button class="btn btn-sm btn-primary pull-right m-t-n-xs"
										type="submit">
										<strong>登录</strong>
									</button>
									<label> <input type="checkbox" class="i-checks">自动登录
									</label>
								</div>
							</form>
						</div>
						<div class="col-sm-6">
							<h4>还不是会员？</h4>
							<p>您可以注册一个账户</p>
							<p class="text-center">
								<a href="form_basic.html"><i class="fa fa-sign-in big-icon"></i></a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 全局js -->
	<script src="${contextPath}/js/jquery-2.1.1.min.js"></script>
	<script src="${contextPath}/js/bootstrap.min.js?v=3.4.0"></script>
	<!-- 自定义js -->
	<script src="${contextPath}/js/content.min.js?v=1.0.0"></script>
	<script type="text/javascript"
		src="${contextPath}/js/ysg/bootstrap-multiselect.js"></script>

	<!-- iCheck -->
	<script src="${contextPath}/js/plugins/iCheck/icheck.min.js"></script>

	<script id="uresourceIds" type="text/html">
		{{each resourceList as value i}}
		<option value="{{value.id}}">{{value.name}}</option>
		{{/each}}
	</script>

	<script>
		$(document).ready(function() {
			$('.i-checks').iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
			});

			$('#resourceIds').multiselect({
				nonSelectedText : "请选择用户权限",
				allSelectedText : "全部",
                maxHeight:200
			});

            reflush();

		});


        function reflush(){
            $.ajax({
                url : "${contextPath}/role/initPage",
                type : "GET",
                success : function(data) {
                    var html2 = template('uresourceIds', data);
                    $("#resourceIds").html(html2);
                    $("#resourceIds").multiselect("destroy").multiselect({
                        nonSelectedText : "请选择用户权限",
                        allSelectedText : "全部",
                        maxHeight:200
                    })
                    return;
                },
                error : function() {
                    alert("系统错误");
                }
            })
        }
		

		function save() {
			var role = $("#role").val();
			var description = $("#description").val();
			var resourceIds = $("#resourceIds").val();
			$.ajax({
				url : "${contextPath}/role/doAddRole",
				data : {
                    description : description,
                    role : role,
                    resourceIds : resourceIds.toString(),
				},
				type : "POST",
				success : function(data) {
					if(data == "success"){
						alert("新增角色成功!");
						window.location.reload();
                        parent.RefreshCloudHomePageTab();
					}else{
						alert("新增角色失败!");
					}
					return;
				},
				error : function() {
					alert("系统错误!");
				}
			})
		}

		function cancel() {
			window.location.reload();
		}
	</script>

</body>

</html>