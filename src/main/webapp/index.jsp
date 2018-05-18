<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set value="${pageContext.request.contextPath}" var="contextPath"></c:set>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>eshop</title>


    <!--[if lt IE 8]>
    <script>
        alert('H+已不支持IE6-8，请使用谷歌、火狐等浏览器\n或360、QQ等国产浏览器的极速模式浏览本页面！');
    </script>
    <![endif]-->

    <link href="${contextPath}/css/bootstrap.min.css?v=3.4.0"
          rel="stylesheet">
    <link href="${contextPath}/css/font-awesome.min.css?v=4.3.0"
          rel="stylesheet">
    <link href="${contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${contextPath}/css/style.min.css?v=3.2.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close">
            <i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
						<span><img alt="image" class="img-circle"
                                   src="${contextPath}/img/profile_small.jpg"/></span> <a
                            data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
							<span class="clear"> <span class="block m-t-xs"><strong
                                    class="font-bold">${user.username}</strong></span> <%--<span
                                    class="text-muted text-xs block">${user.position}<b class="caret"></b></span>--%>
						</span>
                    </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                          <%--  <li><a class="J_menuItem" href="form_avatar.html">修改头像</a></li>
                            <li><a class="J_menuItem" href="profile.html">个人资料</a></li>
                            <li><a class="J_menuItem" href="contacts.html">联系我们</a></li>
                            <li><a class="J_menuItem" href="mailbox.html">信箱</a></li>
                            <li class="divider"></li>--%>
                            <li><a href="${contextPath}/logout">安全退出</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">H+</div>
                </li>


                <shiro:hasPermission name="user:view">
                    <li>
                        <a href="index.html#">
                            <i class="fa fa-user"></i>
                            <span class="nav-label">用户管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="user:create">
                                <li>
                                    <a class="J_menuItem" href="${contextPath}/user/add" data-index="0">用户新增</a>
                                </li>
                            </shiro:hasPermission>
                            <li>
                                <a class="J_menuItem" href="${contextPath}/user/search" data-index="0">用户查询</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="company:view">
                 <li>
                    <a href="index.html#">
                        <i class="fa fa-institution"></i>
                        <span class="nav-label">公司管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <shiro:hasPermission name="company:create">
                            <li>
                            <a class="J_menuItem" href="${contextPath}/toAddCompany" data-index="0">公司新增</a>
                            </li>
                        </shiro:hasPermission>
                        <li>
                        <a class="J_menuItem" href="${contextPath}/toSearchCompany" data-index="0">公司查询</a>
                        </li>
                    </ul>
                </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="role:view">
                    <li>
                        <a href="index.html#">
                            <i class="fa fa-users"></i>
                            <span class="nav-label">角色管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="role:create">
                                <li>
                                    <a class="J_menuItem" href="${contextPath}/role/toadd" data-index="0">角色新增</a>
                                </li>
                            </shiro:hasPermission>
                            <li>
                                <a class="J_menuItem" href="${contextPath}/role/tosearch" data-index="0">角色查询</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasPermission>


                <shiro:hasPermission name="banner:view">
                    <li>
                        <a href="index.html#">
                            <i class="fa fa-file-image-o"></i>
                            <span class="nav-label">轮播图管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="banner:create">
                                <li>
                                    <a class="J_menuItem" href="${contextPath}/shop/toAddBanner"
                                       data-index="0">新增轮播图</a>
                                </li>
                            </shiro:hasPermission>
                            <li>
                                <a class="J_menuItem" href="${contextPath}/shop/toSearchBanner"
                                   data-index="0">轮播图查询</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="category:view">
                    <li>
                        <a href="index.html#">
                            <i class="fa fa-list-ol"></i>
                            <span class="nav-label">分类管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="category:create">
                                <li>
                                    <a class="J_menuItem" href="${contextPath}/shop/toAddCategory"
                                       data-index="0">新增分类</a>
                                </li>
                            </shiro:hasPermission>
                            <li>
                                <a class="J_menuItem" href="${contextPath}/shop/toSearchCategory"
                                   data-index="0">分类查询</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="order:view">
                    <li>
                        <a href="index.html#">
                            <i class="fa fa-book"></i>
                            <span class="nav-label">订单管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${contextPath}/shop/toOrderSearch" data-index="0">订单查询</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="good:view">
                    <li>
                        <a href="index.html#">
                            <i class="fa fa-shopping-cart"></i>
                            <span class="nav-label">商品管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="good:create">
                            <li>
                                <a class="J_menuItem" href="${contextPath}/shop/toAddGoods" data-index="0">新增商品</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="good:view">
                            <li>
                                <a class="J_menuItem" href="${contextPath}/shop/toGoodSearch" data-index="0">商品查询</a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                </shiro:hasPermission>
                <%--<li>
                    <a href="index.html#">
                        <i class="fa fa-gear"></i>
                        <span class="nav-label">权限管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                        <a class="J_menuItem" href="employee.jsp" data-index="0">权限分配</a>
                        </li>
                        <li>
                        <a class="J_menuItem" href="employee.jsp" data-index="0">权限查询</a>
                        </li>
                    </ul>
                </li>--%>


                <!--  <li>
                        <a class="J_menuItem" href="layouts.html"><i class="fa fa-columns"></i> <span class="nav-label">布局</span></a>
                    </li>
                    <li>
                        <a href="index.html#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">统计图表</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="graph_echarts.html">百度ECharts</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_flot.html">Flot</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_morris.html">Morris.js</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_rickshaw.html">Rickshaw</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_peity.html">Peity</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_sparkline.html">Sparkline</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_metrics.html">图表组合</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="mailbox.html"><i class="fa fa-envelope"></i> <span class="nav-label">信箱 </span><span class="label label-warning pull-right">16</span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="mailbox.html">收件箱</a>
                            </li>
                            <li><a class="J_menuItem" href="mail_detail.html">查看邮件</a>
                            </li>
                            <li><a class="J_menuItem" href="mail_compose.html">写信</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">表单</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="form_basic.html">基本表单</a>
                            </li>
                            <li><a class="J_menuItem" href="form_validate.html">表单验证</a>
                            </li>
                            <li><a class="J_menuItem" href="form_advanced.html">高级插件</a>
                            </li>
                            <li><a class="J_menuItem" href="form_wizard.html">表单向导</a>
                            </li>
                            <li>
                                <a href="index.html#">文件上传 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="form_webuploader.html">百度WebUploader</a>
                                    </li>
                                    <li><a class="J_menuItem" href="form_file_upload.html">DropzoneJS</a>
                                    </li>
                                    <li><a class="J_menuItem" href="form_avatar.html">头像裁剪上传</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="index.html#">编辑器 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="form_editors.html">富文本编辑器</a>
                                    </li>
                                    <li><a class="J_menuItem" href="form_simditor.html">simditor</a>
                                    </li>
                                    <li><a class="J_menuItem" href="form_markdown.html">MarkDown编辑器</a>
                                    </li>
                                    <li><a class="J_menuItem" href="code_editor.html">代码编辑器</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="suggest.html">搜索自动补全</a>
                            </li>
                            <li><a class="J_menuItem" href="layerdate.html">日期选择器layerDate</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-desktop"></i> <span class="nav-label">页面</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="contacts.html">联系人</a>
                            </li>
                            <li><a class="J_menuItem" href="profile.html">个人资料</a>
                            </li>
                            <li>
                                <a href="index.html#">项目管理 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="projects.html">项目</a>
                                    </li>
                                    <li><a class="J_menuItem" href="project_detail.html">项目详情</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="teams_board.html">团队管理</a>
                            </li>
                            <li><a class="J_menuItem" href="social_feed.html">信息流</a>
                            </li>
                            <li><a class="J_menuItem" href="clients.html">客户管理</a>
                            </li>
                            <li><a class="J_menuItem" href="file_manager.html">文件管理器</a>
                            </li>
                            <li><a class="J_menuItem" href="calendar.html">日历</a>
                            </li>
                            <li>
                                <a href="index.html#">博客 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="blog.html">文章列表</a>
                                    </li>
                                    <li><a class="J_menuItem" href="article.html">文章详情</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="faq.html">FAQ</a>
                            </li>
                            <li>
                                <a href="index.html#">时间轴 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="timeline.html">时间轴</a>
                                    </li>
                                    <li><a class="J_menuItem" href="timeline_v2.html">时间轴v2</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="pin_board.html">标签墙</a>
                            </li>
                            <li>
                                <a href="index.html#">单据 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="invoice.html">单据</a>
                                    </li>
                                    <li><a class="J_menuItem" href="invoice_print.html">单据打印</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="search_results.html">搜索结果</a>
                            </li>
                            <li><a class="J_menuItem" href="forum_main.html">论坛</a>
                            </li>
                            <li>
                                <a href="index.html#">即时通讯 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="chat_view.html">聊天窗口</a>
                                    </li>
                                    <li><a class="J_menuItem" href="webim.html">layIM</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="index.html#">登录注册相关 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a href="login.html" target="_blank">登录页面</a>
                                    </li>
                                    <li><a href="login_v2.html" target="_blank">登录页面v2</a>
                                    </li>
                                    <li><a href="register.html" target="_blank">注册页面</a>
                                    </li>
                                    <li><a href="lockscreen.html" target="_blank">登录超时</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="404.html">404页面</a>
                            </li>
                            <li><a class="J_menuItem" href="500.html">500页面</a>
                            </li>
                            <li><a class="J_menuItem" href="empty_page.html">空白页</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-flask"></i> <span class="nav-label">UI元素</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="typography.html">排版</a>
                            </li>
                            <li>
                                <a href="index.html#">字体图标 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li>
                                        <a class="J_menuItem" href="icons.html">Font Awesome</a>
                                    </li>
                                    <li>
                                        <a class="J_menuItem" href="iconfont.html">阿里巴巴矢量图标库</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="index.html#">拖动排序 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="draggable_panels.html">拖动面板</a>
                                    </li>
                                    <li><a class="J_menuItem" href="agile_board.html">任务清单</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="buttons.html">按钮</a>
                            </li>
                            <li><a class="J_menuItem" href="tabs_panels.html">选项卡 &amp; 面板</a>
                            </li>
                            <li><a class="J_menuItem" href="notifications.html">通知 &amp; 提示</a>
                            </li>
                            <li><a class="J_menuItem" href="badges_labels.html">徽章，标签，进度条</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="grid_options.html">栅格</a>
                            </li>
                            <li>
                                <a href="index.html#">弹框插件 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="layer.html">Web弹层组件layer</a>
                                    </li>
                                    <li><a class="J_menuItem" href="modal_window.html">模态窗口</a>
                                    </li>
                                    <li><a class="J_menuItem" href="sweetalert.html">SweetAlert</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="index.html#">树形视图 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="jstree.html">jsTree</a>
                                    </li>
                                    <li><a class="J_menuItem" href="tree_view.html">Bootstrap Tree View</a>
                                    </li>
                                    <li><a class="J_menuItem" href="nestable_list.html">nestable</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="toastr_notifications.html">Toastr通知</a>
                            </li>
                            <li><a class="J_menuItem" href="diff.html">文本对比</a>
                            </li>
                            <li><a class="J_menuItem" href="spinners.html">加载动画</a>
                            </li>
                            <li><a class="J_menuItem" href="widgets.html">小部件</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-table"></i> <span class="nav-label">表格</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="table_basic.html">基本表格</a>
                            </li>
                            <li><a class="J_menuItem" href="table_data_tables.html">DataTables</a>
                            </li>
                            <li><a class="J_menuItem" href="table_jqgrid.html">jqGrid</a>
                            </li>
                            <li><a class="J_menuItem" href="table_foo_table.html">Foo Tables</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-picture-o"></i> <span class="nav-label">相册</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="basic_gallery.html">基本图库</a>
                            </li>
                            <li><a class="J_menuItem" href="carousel.html">图片切换</a>
                            </li>
                            <li><a class="J_menuItem" href="layerphotos.html">layer相册</a>
                            </li>
                            <li><a class="J_menuItem" href="blueimp.html">Blueimp相册</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="J_menuItem" href="css_animation.html"><i class="fa fa-magic"></i> <span class="nav-label">CSS动画</span></a>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-cutlery"></i> <span class="nav-label">工具 </span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="form_builder.html">表单构建器</a>
                            </li>
                        </ul>
                    </li> -->

            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
       <%-- <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="index.html#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                        </div>
                    </form>
                </div>
            </nav>
        </div>--%>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft">
                <i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab"
                       data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight">
                <i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">
                    关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a></li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
                </ul>
            </div>
            <a href="${contextPath}/logout" class="roll-nav roll-right J_tabExit"><i
                    class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%"
                    src="home.jsp" frameborder="0" data-id="index_v1.html"
                    seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">
                &copy; v1.0
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
    <form class="form-horizontal" id="modifypassword">
        <div class="form-group col-sm-12">
            <label class="col-sm-3 control-label">新密码：</label>

            <div class="col-sm-8">
                <input type="password" id="password" placeholder="新密码" class="form-control">
            </div>
        </div>
        <div class="form-group col-sm-12">
            <label class="col-sm-3 control-label">确认密码：</label>

            <div class="col-sm-8">
                <input type="password" id = "comfirmPassword" placeholder="确认密码" class="form-control">
            </div>
        </div>

        <div class="form-group col-sm-12">
            <div class="col-sm-offset-3 col-sm-5">
                <button class="btn btn-sm btn-primary" type="button" onclick="save();">确认修改</button>
            </div>
        </div>
    </form>
</div>
<!-- 全局js -->
<script src="${contextPath}/js/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js?v=3.4.0"></script>
<script src="${contextPath}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script
        src="${contextPath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${contextPath}/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="${contextPath}/js/hplus.min.js?v=3.2.0"></script>
<script type="text/javascript" src="${contextPath}/js/contabs.min.js"></script>

<!-- 第三方插件 -->
<script src="${contextPath}/js/plugins/pace/pace.min.js"></script>
<script src="${contextPath}/js/plugins/layer/layer.min.js"></script>
    <script>
        var index;
        $(function () {
            if("${ifShow}" == "true"){//当前密码为初始密码，强制修改密码
                index = layer.open({
                    title:"修改密码",
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    shadeClose: false, //开启遮罩关闭
                    area: ['500px', '250px'], //宽高
                    content: $("#modifypassword"),
                });
            }
        })

        function save() {
            var password = $("#password").val();
            var comfirmPassword = $("#comfirmPassword").val();

            if(password == "" || comfirmPassword == ""){
                alert("请完整输入信息");
                return;
            }

            if(password != comfirmPassword){
                alert("两次输入密码不一致");
                return;
            }

            if(password == "123456"){
                alert("密码不能为初始密码");
                return;
            }

            $.ajax({
                url : "${contextPath}/user/doModify",
                data : {
                    password : password
                },
                type : "POST",
                success : function(data) {
                    if(data == "success"){
                        layer.close(index);
                    }
                    return;
                },
                error : function() {
                    alert("系统错误!");
                }
            })
        }
</script>
<script src="${contextPath}/js/ysg/common.js"></script>
</body>

</html>