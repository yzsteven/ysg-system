<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set value="${pageContext.request.contextPath}" var="contextPath"></c:set>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>订单详情</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="${contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="${contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="row">
        <div class="col-sm-12">
            <div class="wrapper wrapper-content animated fadeInUp">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="m-b-md">
                                    <h2>订单详情</h2>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-5">
                                <dl class="dl-horizontal">

                                    <dt>订单号：</dt>
                                    <dd>${order.orderno}</dd>
                                    <dt>订单总价：</dt>
                                    <dd>${order.totalprice}</dd>
                                    <dt>联系人：</dt>
                                    <dd>${order.contactname}</dd>
                                    <dt>联系电话：</dt>
                                    <dd>${order.contactphone}</dd>
                                    <dt>操作：</dt>
                                    <c:if test="${order.orderstatus == 1}">
                                        <dd><button name="edit" type="button" class="btn btn-primary btn-xs">取消订单</button></dd>
                                    </c:if>
                                    <c:if test="${order.orderstatus == 2}">
                                        <dd><button name="edit" type="button" class="btn btn-primary btn-xs">确认发货</button></dd>
                                    </c:if>
                                    <c:if test="${order.orderstatus == 3}">
                                        <dd><button name="edit" type="button" class="btn btn-primary btn-xs">确认收货</button></dd>
                                    </c:if>
                                </dl>
                            </div>
                            <div class="col-sm-7" id="cluster_info">
                                <dl class="dl-horizontal">

                                    <dt>联系地址：</dt>
                                    <dd>${order.address}</dd>
                                    <dt>支付单号：</dt>
                                    <dd>${order.payno}</dd>
                                    <dt>支付金额：</dt>
                                    <dd>${order.payprice}</dd>
                                    <dt>支付时间：</dt>
                                    <dd>${order.paytime}</dd>
                                    <dt>订单状态：</dt>
                                    <c:if test="${order.orderstatus == 1}">
                                        <dd>待支付</dd>
                                    </c:if>
                                    <c:if test="${order.orderstatus == 2}">
                                        <dd>待发货</dd>
                                    </c:if>
                                    <c:if test="${order.orderstatus == 3}">
                                        <dd>待收货</dd>
                                    </c:if>
                                    <c:if test="${order.orderstatus == 4}">
                                        <dd>已完成</dd>
                                    </c:if>
                                    <c:if test="${order.orderstatus == 5}">
                                        <dd>已取消</dd>
                                    </c:if>
                                </dl>
                            </div>
                        </div>
                        <div class="row m-t-sm">
                            <div class="col-sm-12">
                                <div class="panel blank-panel">
                                    <div class="panel-heading">
                                        <div class="panel-options">
                                            <ul class="nav nav-tabs">
                                                <li class=""><a href="project_detail.html#tab-2" data-toggle="tab">商品列表</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="panel-body">

                                            <div class="tab-pane" id="tab-2">

                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th>商品名称</th>
                                                            <th>商品图片</th>
                                                            <th>商品规格</th>
                                                            <th>商品数量</th>
                                                            <th>商品单价</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${order.goodsinfo}" var="good">
                                                        <tr>
                                                            <td>
                                                                ${good.name}
                                                            </td>
                                                            <td>
                                                                <img style="width:70px;height:30px;"  src="${good.thumb}" />
                                                            </td>
                                                            <td>
                                                                    ${good.spec}
                                                            </td>
                                                            <td>
                                                                    ${good.count}
                                                            </td>
                                                            <td>
                                                                    ${good.price}
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>

                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${contextPath}/js/content.min.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

    <script type="text/javascript">

        $("button[name='edit']").click(function(){
            $.ajax({
                url:"${contextPath}/shop/modifyOrderInfoByAdmin",
                data:{
                    id:${order.id},
                    orderstatus:${order.orderstatus}
                },
                type:"POST",
                success:function(data){
                    if(data.retValue != 'success'){
                        alert("修改失败!");
                    }else{
                        alert("修改成功!");
                        window.location.reload();
                        parent.RefreshCloudHomePageTab();
                    }

                }
            })
        })
    </script>
</body>
</html>
