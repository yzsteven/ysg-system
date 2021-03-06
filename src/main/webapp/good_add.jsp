<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set value="${pageContext.request.contextPath}" var="contextPath"></c:set>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>新增商品</title>

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
    <link href="${contextPath}/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${contextPath}/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${contextPath}/css/demo/bootstrap-fileinput.css" rel="stylesheet">
    <script src="${contextPath}/js/ysg/template.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">


    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>
                        新增商品
                    </h5>
                </div>
                <div class="ibox-content">
                    <form method="get" class="form-horizontal">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品名称</label>

                            <div class="col-sm-2">
                                <input type="text" id="name" name="name"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品封面图片</label>

                            <div class="col-sm-5">
                                    <div class="form-group" id="uploadForm"  enctype='multipart/form-data'>
                                        <div class="fileinput fileinput-new" data-provides="fileinput"
                                             id="exampleInputUpload">
                                            <div class="fileinput-new thumbnail"
                                                 style="width: 200px;height: auto;max-height:150px;">
                                                <img id='picImg' style="width: 100%;height: auto;max-height: 140px;"
                                                     src="${contextPath}/img/noimage.png" alt=""/>
                                            </div>
                                            <div class="fileinput-preview fileinput-exists thumbnail"
                                                 style="max-width: 200px; max-height: 150px;"></div>
                                            <div>
                                            <span class="btn btn-primary btn-file">
                                                <span class="fileinput-new">选择文件</span>
                                                <span class="fileinput-exists">换一张</span>
                                                <input type="file" name="pic1" id="picID" accept="image/gif,image/jpeg,image/x-png">
                                            </span>
                                                <a href="javascript:;" class="btn btn-warning fileinput-exists"
                                                   data-dismiss="fileinput">移除</a>
                                            </div>
                                        </div>
                                    </div>
                                    <button type="button" id="uploadSubmit" class="btn btn-info">提交</button>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品货号</label>

                            <div class="col-sm-2">
                                <input type="text" id="goodno" name="goodno"
                                       class="form-control">
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label">所属分类</label>

                            <div class="col-sm-2">
                                <select name="category" id="category" class="form-control m-b">
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品规格</label>

                            <div class="col-sm-2">
                                <input type="text" id="specname" name="specname"
                                       class="form-control">
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品单价</label>

                            <div class="col-sm-2">
                                <input type="text" id="price" name="price"
                                       class="form-control">
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品详情</label>
                            <div class="col-sm-8">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-content no-padding">

                                        <div class="summernote" id="detail">

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">产品参数</label>
                            <div class="col-sm-8">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-content no-padding">

                                        <div class="summernote" id="parameter">

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">售后保障</label>
                            <div class="col-sm-8">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-content no-padding">

                                        <div class="summernote" id="service">

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品属性</label>

                            <div class="col-sm-10">
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="isNew">新品</label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="isRecommend">推荐</label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="isSelected">精选</label>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <label class="col-sm-2 control-label">商品图片</label>
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
<input type="hidden" value="" id="coverImgUrl"/>
<!-- 全局js -->
<script src="${contextPath}/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 自定义js -->
<script src="${contextPath}/js/content.min.js?v=1.0.0"></script>
<script src="${contextPath}/js/ysg/bootstrap-multiselect.js"></script>
<script src="${contextPath}/js/plugins/summernote/summernote.min.js"></script>
<script src="${contextPath}/js/plugins/summernote/summernote-zh-CN.js"></script>
<script src="${contextPath}/js/demo/bootstrap-fileinput.js"></script>
<script>
    $(document).ready(function () {
        $("#detail").summernote({
            lang: "zh-CN",
            onImageUpload: function (files, editor, welEditable) { //the onImageUpload API
                sendFile(files[0], editor, welEditable);
            }
        });

        $("#parameter").summernote({
            lang: "zh-CN",
            onImageUpload: function (files, editor, welEditable) { //the onImageUpload API
                sendFile_parameter(files[0], editor, welEditable);
            }
        });

        $("#service").summernote({
            lang: "zh-CN",
            onImageUpload: function (files, editor, welEditable) { //the onImageUpload API
                sendFile_service(files[0], editor, welEditable);
            }
        });
    });
</script>
<script type="text/javascript">
    var BASE_URL = 'js/plugins/webuploader/.indexhtml';
</script>
<script src="${contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${contextPath}/js/demo/webuploader-demo.min.js"></script>
<script id="ucategory" type="text/html">
    {{each categoryList as value i}}
    <option value="{{value.id}}">{{value.name}}</option>
    {{/each}}
</script>

<script>
    $(document).ready(function () {
        reflush();
        $('#uploadSubmit').click(function () {
            var formData  = new FormData();
            formData.append("file",$('#picID')[0].files[0]);
            $.ajax({
                url: '${contextPath}/upload',
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    console.log(data);
                    if(data.retValue){
                        console.log('upload success');
                        $("#coverImgUrl").val(data.retValue);
                        alert("上传成功！")
                    }else{
                        console.log(data.retMsg);
                    }
                },
                error: function (data) {
                    console.log(data.retMsg);
                }
            });
        });

    });

    function sendFile(file, editor, welEditable) {
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            data: formData,
            type: "POST",
            url: "${contextPath}/upload",
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data.retValue);
                editor.insertImage(welEditable,data.retValue);
            },
            error:function(){
                alert("上传失败");
            }
        });
    }
    function sendFile_parameter(file, editor, welEditable) {
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            data: formData,
            type: "POST",
            url: "${contextPath}/upload",
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                editor.insertImage(welEditable,data.retValue);
            },
            error:function(){
                alert("上传失败");
            }
        });
    }

    function sendFile_service(file, editor, welEditable) {
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            data: formData,
            type: "POST",
            url: "${contextPath}/upload",
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                editor.insertImage(welEditable,data.retValue);
            },
            error:function(){
                alert("上传失败");
            }
        });
    }


    function reflush() {
        $.ajax({
            url: "${contextPath}/shop/categoryInitPage",
            type: "GET",
            success: function (data) {
                var html = template('ucategory', data);
                $("#category").html(html);
                return;
            },
            error: function () {
                alert("系统错误");
            }
        })
    }


    function save() {
        var name = $("#name").val();
        var goodno = $("#goodno").val();
        var category = $("#category").val();
        var specname = $("#specname").val();
        var price = $("#price").val();
        var detail = $("#detail").code();
        var parameter = $("#parameter").code();
        var service = $("#service").code();
        var isNew = $("#isNew").is(":checked") ? 1 : 0;
        var isRecommend = $("#isRecommend").is(":checked") ? 1 : 0;
        var isSelected = $("#isSelected").is(":checked") ? 1 : 0;
        var banner = $("#url").val();
        var coverImgUrl = $("#coverImgUrl").val();
        if(name == ""){
            alert("商品名称不能为空！");
            return ;
        }
        if(specname == ""){
            alert("商品规格不能为空！");
            return ;
        }
        if(price == ""){
            alert("商品价格不能为空！");
            return ;
        }
        if(coverImgUrl == ""){
            alert("请上传商品封面图片！");
            return ;
        }
        if(banner == ""){
            alert("请上传商品图片！");
            return ;
        }
        var param = {
            "name": name,
            "coverimg":coverImgUrl,
            "goodno": goodno,
            "spec": [{
                "name": specname,
                "price": price
            }],
            "categoryid": category,
            "detail": detail,
            "parameter": parameter,
            "service": service,
            "isnew": isNew,
            "isrecommend": isRecommend,
            "isselected": isSelected,
            "images": banner
        };
        $.ajax({
            url: "${contextPath}/shop/doAddGoods",
            data: JSON.stringify(param),
            type: "POST",
            contentType: "application/json",
            success: function (data) {
                if (data.retValue == "success") {
                    alert("保存成功!");
                    window.location.reload();
                    parent.RefreshCloudHomePageTab();
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