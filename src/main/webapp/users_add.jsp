<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set value="${pageContext.request.contextPath}" var="contextPath"></c:set>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>H+ 后台主题UI框架 - 基本表单</title>
<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

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
							新增员工 <small></small>
						</h5>
					</div>
					<div class="ibox-content">
						<form method="get" class="form-horizontal">

							<div class="form-group">
								<label class="col-sm-2 control-label">用户名</label>

								<div class="col-sm-2">
									<input type="text" id="username" name="username"
										class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">性别</label>

								<div class="col-sm-2">
									<div class="radio">
										<label> <input name="sex" checked type="radio"
											value="1">男
										</label>
									</div>
									<div class="radio">
										<label> <input name="sex" type="radio" value="2">女
										</label>
									</div>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">年龄</label>

								<div class="col-sm-2">
									<input type="text" id="age" name="age" class="form-control">
								</div>
							</div>

							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">所属公司</label>

								<div class="col-sm-2">
									<select name="company" id="company" class="form-control m-b">
										<c:forEach items="${companyList}" var="company">
											<option value="${company.registerNum}">${company.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">角色</label>

								<div class="col-sm-2">
									<select class="form-control m-b" id="role" multiple="multiple">
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">手机号码</label>

								<div class="col-sm-2">
									<input name="phone" id="phone" type="text" class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">身份证</label>

								<div class="col-sm-2">
									<input name="cardid" id="cardid" type="text"
										class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">真实姓名</label>

								<div class="col-sm-2">
									<input name="realname" id="realname" type="text"
										class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>


							<div class="form-group">
								<label class="col-sm-2 control-label">家庭地址</label>

								<div class="col-sm-2">
									<input name="address" id="address" type="text"
										class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>


							<div class="form-group">
								<label class="col-sm-2 control-label">紧急联系人</label>

								<div class="col-sm-2">
									<input name="contactname" id="contactname" type="text"
										class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>


							<div class="form-group">
								<label class="col-sm-2 control-label">紧急联系人手机号</label>

								<div class="col-sm-2">
									<input name="contactphone" id="contactphone" type="text"
										class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<div class="col-sm-2 col-sm-offset-2">
									<button type="button" class="btn btn-primary" onclick="save();">保存内容</button>
									<button type="button" class="btn btn-white" onclick="cancel();">清空内容</button>
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
	<script id="urole" type="text/html">
			{{each roleList as value i}}
				<option value="{{value.id}}">{{value.description}}</option>
			{{/each}}
	</script>
	<script>
		$(document).ready(function() {
			$('.i-checks').iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
			});

			$('#role').multiselect({
				nonSelectedText : "请选择用户权限",
				allSelectedText : "全部",
                maxHeight:200
			});
			
			reflush();
			/*  var selected = [];
			 $(‘#Items option:selected’).each(function () {
			 selected.push($(this).val());
			 }); */

		});

		function reflush(){
			$.ajax({
				url : "${contextPath}/user/searchRoles",
				type : "GET",
                async : false,
				success : function(data) {
					var html = template('urole', data);
					$("#role").html(html);
					$("#role").multiselect("destroy").multiselect({
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
			var username = $("#username").val();
			var roleIds = $("#role").val();
			var realname = $("#realname").val();
			var cardid = $("#cardid").val();
			var company = $("#company").val();
			var address = $("#address").val();
			var age = $("#age").val();
			var sex = $("input[name='sex']:checked").val();
			var phone = $("#phone").val();
			var contactname = $("#contactname").val();
			var contactphone = $("#contactphone").val();
			var type = 1;// 1 sys 2 app
			$.ajax({
				url : "${contextPath}/user/doAddUser",
				data : {
					username : username,
					roleIds : roleIds.toString(),
					realname : realname,
					cardid : cardid,
					company : company,
					address : address,
					age : age,
					sex : sex,
					phone : phone,
					contactname : contactname,
					contactphone : contactphone,
					type : type
				},
				type : "POST",
				success : function(data) {
					if(data == "success"){
						alert("新增用户成功!");
						window.location.reload();
                        parent.RefreshCloudHomePageTab();
					}else{
						alert("新增用户失败!");
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