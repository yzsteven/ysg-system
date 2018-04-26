<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
          href="${contextPath}/css/bootstrap-multiselect.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/demo/webuploader-demo.min.css">
    <script src="${contextPath}/js/ysg/template.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">


    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>
                        新增分类
                        <small></small>
                    </h5>
                </div>
                <div class="ibox-content">
                    <form method="get" class="form-horizontal">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">分类名称</label>

                            <div class="col-sm-2">
                                <input type="text" id="name" name="name" value="${category.name}"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">分类拼音</label>

                            <div class="col-sm-2">
                                <input type="text" id="description" name="description" value="${category.description}"
                                       class="form-control">
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>

                        <label class="col-sm-2 control-label">分类图片</label>
                        <div class="form-group">
                            <div class="col-sm-8">
                                <div id="uploader" class="wu-example">
                                    <div class="queueList">
                                        <div id="dndArea" class="placeholder">
                                            <div id="filePicker"></div>
                                            <p>或将照片拖到这里，单次最多可选300张</p>
                                        </div>
                                    </div>
                                    <div class="statusBar" style="display:none;">
                                        <div class="progress">
                                            <span class="text">0%</span>
                                            <span class="percentage"></span>
                                        </div>
                                        <div class="info"></div>
                                        <div class="btns">
                                            <div id="filePicker2"></div>
                                            <div class="uploadBtn">开始上传</div>
                                        </div>
                                    </div>
                                </div>
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
<input type="hidden" value="" id="url"/>
<input type="hidden" value="${category.id}" id="id"/>
<!-- 全局js -->
<script src="${contextPath}/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js?v=3.4.0"></script>
<!-- 自定义js -->
<script src="${contextPath}/js/content.min.js?v=1.0.0"></script>
<script src="${contextPath}/js/ysg/bootstrap-multiselect.js"></script>
<script type="text/javascript">
    var BASE_URL = 'js/plugins/webuploader/.indexhtml';
</script>
<script src="${contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${contextPath}/js/demo/webuploader-demo.min.js"></script>
<script id="ucompany" type="text/html">
    {{each companyList as value i}}
    <option value="{{value.registerNum}}">{{value.name}}</option>
    {{/each}}
</script>

<script>
    $(document).ready(function () {
        reflush();
    });


    function reflush() {
        $.ajax({
            url: "${contextPath}/shop/initPage",
            type: "GET",
            success: function (data) {
                var html = template('ucompany', data);
                $("#company").html(html);
                return;
            },
            error: function () {
                alert("系统错误");
            }
        })
    }


    function save() {
        var id = $("#id").val();
        var name = $("#name").val();
        var description = $("#description").val();
        var banner = $("#url").val();
        $.ajax({
            url: "${contextPath}/shop/modifyCategory",
            data: {
                "id": id,
                "name": name,
                "description": description,
                "banner": banner
            },
            type: "POST",
            success: function (data) {
                if (data.retValue.result == "success") {
                    alert("保存成功!");
                    window.location.href = "${contextPath}/shop/toSearchCategory";
                } else {
                    alert("保存失败!");
                }
                return;
            },
            error: function () {
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