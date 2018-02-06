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
                                <label class="col-sm-2 control-label">姓名</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
							<div class="hr-line-dashed"></div>
							
							 <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>

                                <div class="col-sm-8">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios">男</label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" value="option2" id="optionsRadios2" name="optionsRadios">女</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
							
							<div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
							<div class="form-group">
                                <label class="col-sm-2 control-label">手机号码</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
							
							<div class="form-group">
                                <label class="col-sm-2 control-label">身份证</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
								<label class="col-sm-2 control-label">工种</label>

								<div class="col-sm-8">
									<select class="form-control m-b" name="account">
										<option>选项 1</option>
										<option>选项 2</option>
										<option>选项 3</option>
										<option>选项 4</option>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">工价</label>

                                <div class="col-sm-8">
                                    <div class="input-group m-b"><span class="input-group-addon">&yen;</span>
                                        <input type="text" class="form-control"> <span class="input-group-addon">.00</span>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="hr-line-dashed"></div>

							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button class="btn btn-primary" type="submit">保存内容</button>
									<button class="btn btn-white" type="submit">取消</button>
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

	<!-- iCheck -->
	<script src="${contextPath}/js/plugins/iCheck/icheck.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.i-checks').iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
			});
		});
	</script>

</body>

</html>