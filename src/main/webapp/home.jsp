<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set value="${pageContext.request.contextPath}" var="contextPath"></c:set>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>eshop</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="${contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg top-navigation">
<h1>更多功能敬请期待！</h1>
<%--<div id="wrapper">
  &lt;%&ndash;  <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content">
            <div class="container">
                <div class="row">
                    <div class="col-md-2">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <span class="label label-primary pull-right">当前</span>
                                <h5>总订单</h5>
                            </div>
                            <div class="ibox-content">
                                <h1 class="no-margins">&yen;80,800</h1>
                                <small>当前总收入</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <span class="label label-primary pull-right">月</span>
                                <h5>浏览量</h5>
                            </div>
                            <div class="ibox-content">
                                <h1 class="no-margins">386,200</h1>
                                <small>当月浏览量</small>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <span class="label label-primary pull-right">当月</span>
                                <h5>访问人次</h5>
                            </div>
                            <div class="ibox-content">

                                <div class="row">
                                    <div class="col-md-6">
                                        <h1 class="no-margins">406,420</h1>
                                        <div class="font-bold text-navy">
                                            <small>访问次数（自然月内汇总）</small>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <h1 class="no-margins">206,120</h1>
                                        <div class="font-bold text-navy">
                                            <small>访问人数（自然月内去重）</small>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>总收入</h5>
                                <div class="ibox-tools">
                                    <span class="label label-primary"></span>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <h1 class="no-margins">&yen;386,200</h1>
                                <small>总计</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-8">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="m-t-sm">

                                    <div class="row">
                                        <div class="col-md-8">
                                            <div>
                                                <canvas id="lineChart" height="114"></canvas>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="stat-list m-t-lg">
                                                <li>
                                                    <h2 class="no-margins">2,346</h2>
                                                    <small>最近一周订单</small>
                                                    <div class="progress progress-mini">
                                                        <div class="progress-bar" style="width: 48%;"></div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <h2 class="no-margins ">4,422</h2>
                                                    <small>最近一个月订单</small>
                                                    <div class="progress progress-mini">
                                                        <div class="progress-bar" style="width: 60%;"></div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>用户行为统计</h5>
                            </div>
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <small class="stats-label">打开次数（自然周内汇总）</small>
                                        <h4>236 321.80</h4>
                                    </div>

                                    <div class="col-xs-4">
                                        <small class="stats-label">新用户数（自然周内去重）</small>
                                        <h4>46.11</h4>
                                    </div>
                                    <div class="col-xs-4">
                                        <small class="stats-label">最后一天</small>
                                        <h4>432.021</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <small class="stats-label">打开次数（自然周内汇总）</small>
                                        <h4>643 321.10</h4>
                                    </div>

                                    <div class="col-xs-4">
                                        <small class="stats-label">新用户数（自然周内去重）</small>
                                        <h4>92.43</h4>
                                    </div>
                                    <div class="col-xs-4">
                                        <small class="stats-label">最后一周</small>
                                        <h4>564.554</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row">

                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>访问页面统计</h5>
                            </div>
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-9 m-b-xs">
                                        <div data-toggle="buttons" class="btn-group">
                                            <label class="btn btn-sm btn-white">
                                                <input type="radio" id="option1" name="options">天</label>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="input-group">
                                            <input type="text" placeholder="搜索" class="input-sm form-control"> <span
                                                class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary">搜索</button> </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>页面路径</th>
                                            <th>访问次数</th>
                                            <th>访问人数</th>
                                            <th>次均停留时长</th>
                                            <th>进入页次数</th>
                                            <th>退出页次数</th>
                                            <th>转发次数</th>
                                            <th>转发人数</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>pages/main/main.html</td>
                                            <td>213429</td>
                                            <td>55423</td>
                                            <td>8.139198</td>
                                            <td>117922</td>
                                            <td>61304</td>
                                            <td>180</td>
                                            <td>166</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>
    </div>&ndash;%&gt;
</div>--%>

<script src="${contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${contextPath}/js/content.min.js?v=1.0.0"></script>
<script src="${contextPath}/js/plugins/flot/jquery.flot.js"></script>
<script src="${contextPath}/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="${contextPath}/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="${contextPath}/js/plugins/chartJs/Chart.min.js"></script>
<script src="${contextPath}/js/plugins/peity/jquery.peity.min.js"></script>
<script src="${contextPath}/js/demo/peity-demo.min.js"></script>
<script>
    $(document).ready(function () {
        var lineData = {
            labels: ["一月", "二月", "三月", "四月", "五月", "六月", "七月"],
            datasets: [{
                label: "示例数据",
                fillColor: "rgba(26,179,148,0.5)",
                strokeColor: "rgba(26,179,148,0.7)",
                pointColor: "rgba(26,179,148,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(26,179,148,1)",
                data: [100, 300, 500, 800, 1000, 210, 560]
            }]
        };
        var lineOptions = {
            scaleShowGridLines: true,
            scaleGridLineColor: "rgba(0,0,0,.05)",
            scaleGridLineWidth: 1,
            bezierCurve: true,
            bezierCurveTension: 0.4,
            pointDot: true,
            pointDotRadius: 4,
            pointDotStrokeWidth: 1,
            pointHitDetectionRadius: 20,
            datasetStroke: true,
            datasetStrokeWidth: 2,
            datasetFill: true,
            responsive: true,
        };
        var ctx = document.getElementById("lineChart").getContext("2d");
        var myNewChart = new Chart(ctx).Line(lineData, lineOptions)
    });
</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/graph_flot.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:02 GMT -->
</html>
