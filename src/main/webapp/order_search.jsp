<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set value="${pageContext.request.contextPath}" var="contextPath"></c:set>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>H+ 后台主题UI框架 - Bootstrap Table</title>
<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

<link rel="shortcut icon" href="favicon.ico">
<link href="${contextPath}/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
<link href="${contextPath}/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
<link
	href="${contextPath}/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link href="${contextPath}/css/animate.min.css" rel="stylesheet">
<link href="${contextPath}/css/style.min862f.css?v=4.1.0"
	rel="stylesheet">


</head>

<body class="gray-bg">

	<!-- Panel Other -->
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<h5>订单列表</h5>
		</div>
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<!-- Example Pagination -->
					<div class="example-wrap">
						<div class="example">
							<table id="roletable"></table>
						</div>
					</div>
					<!-- End Example Pagination -->
				</div>

			</div>
		</div>
	</div>
	<!-- End Panel Other -->
	</div>
	<script src="${contextPath}/js/ysg/jquery.min.js"></script>
	<script src="${contextPath}/js/ysg/bootstrap.js"></script>
	<script src="${contextPath}/js/ysg/bootstrap-table.js"></script>
	<script src="${contextPath}/js/ysg/bootstrap-table-zh-CN.js"></script>
	<script src="${contextPath}/js/plugins/layer/laydate/laydate.js"></script>
	<script>
		$(function() {

			//1.初始化Table
			var oTable = new TableInit();
			oTable.Init();

			//2.初始化Button的点击事件
			var oButtonInit = new ButtonInit();
			oButtonInit.Init();

		});

		var TableInit = function() {
			var oTableInit = new Object();
			//初始化Table
			oTableInit.Init = function() {
				$('#roletable').bootstrapTable({
					url : '${contextPath}/shop/queryOrderList', //请求后台的URL（*）
					method : 'POST', //请求方式（*）
                    contentType:"application/json;charset=utf-8",
                    toolbar : '#toolbar', //工具按钮用哪个容器
					striped : true, //是否显示行间隔色
					cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					pagination : true, //是否显示分页（*）
					sortable : false, //是否启用排序
					sortOrder : "asc", //排序方式
					queryParams : oTableInit.queryParams,//传递参数（*）
					queryParamsType : "",
					sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
					pageNumber : 1, //初始化加载第一页，默认第一页
					pageSize : 10, //每页的记录行数（*）
					pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
					search : true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
					strictSearch : true,
					showColumns : false, //是否显示所有的列
					showRefresh : false, //是否显示刷新按钮
					minimumCountColumns : 2, //最少允许的列数
					clickToSelect : true, //是否启用点击选中行
					height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
					uniqueId : "ID", //每一行的唯一标识，一般为主键列
					showToggle : false, //是否显示详细视图和列表视图的切换按钮
					cardView : false, //是否显示详细视图
					detailView : false, //是否显示父子表
					columns : [{
						field : 'orderNo',
                        title : '订单号'
					},{
						field : 'totalPrice',
						title : '订单总价'
					}, {
                        field: 'contactName',
                        title: '联系人'
                    }, {
                        field: 'contactPhone',
                        title: '联系人手机号'
                    }, {
                        field: 'address',
                        title: '联系地址'
                    }, {
                        field: 'payNo',
                        title: '支付单号'
                    }, {
                        field: 'paytime',
                        title: '支付时间'
                    }, {
					    field: 'payPrice',
						title: '支付金额'
                    }, {
                        field: 'orderstatus',
                        title: '订单状态',
                        formatter:function(value,row,index){
                            var s = "待支付"
							switch (row.orderstatus){
								case 2 : s="待发货"; break;
                                case 3 : s="待收货"; break;
                                case 4 : s="已完成"; break;
                                case 5 : s="已取消"; break;
							}
                            return s;
                        },
                    }, {
                        field: 'createTime',
                        title: '下单时间'
                    }, {
						field : 'Button',
						title : '操作',
						events : operateEvents,
						formatter : operateFormatter
					}]
				});
			};
			
			//得到查询的参数
			oTableInit.queryParams = function(params) {
				var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                   pageHelper : {
                        pageSize : params.pageSize, //页面大小
                        pageNumber : params.pageNumber, //页码
						searchParam :　params.searchText
					},
                    startTime : "",
                    endTime :"",
					orderstatus : ""
				};
				return JSON.stringify(temp);
			};
			return oTableInit;
		};

		var ButtonInit = function() {
			var oInit = new Object();

			oInit.Init = function() {
				//初始化页面上面的按钮事件
			};

			return oInit;
		};
		
		function operateFormatter(value, row, index) {
			var msg = "取消订单";
			var arr = [
		        '<shiro:hasPermission name="order:view"><button id="searchDetail" type="button" class="btn btn-primary btn-xs">查看订单</button></shiro:hasPermission>&nbsp;&nbsp;'
			]

		    switch (row.orderstatus){
                case 1 : msg = "取消订单";arr.push('<shiro:hasPermission name="order:update"><button id="edit" type="button" class="btn btn-primary btn-xs">'+msg+'</button></shiro:hasPermission>');break;
				case 2 : msg = "确认发货";arr.push('<shiro:hasPermission name="order:update"><button id="edit" type="button" class="btn btn-primary btn-xs">'+msg+'</button></shiro:hasPermission>');break;
                case 3 : msg = "确认收货";arr.push('<shiro:hasPermission name="order:update"><button id="edit" type="button" class="btn btn-primary btn-xs">'+msg+'</button></shiro:hasPermission>');break;
			}

            return arr.join('');
		}
		      
		      
		      window.operateEvents = {
		    	      'click #searchDetail': function (e, value, row, index) {
		    	    	  window.location.href = "${contextPath}/shop/toModifyCategory?id="+row.id;
		    	      },
		    	      'click #edit': function (e, value, row, index) {
		    	        	$.ajax({
		    	        		url:"${contextPath}/shop/modifyOrderInfoByAdmin",
		    	        		data:{
		    	        			id:row.id,
                                    orderstatus:row.orderstatus
		    	        		},
		    	        		type:"POST",
		    	        		success:function(data){
		    	        			if(data.retValue != 'success'){
		    	        				alert("修改失败!");
		    	        			}else{
		    	        				alert("修改成功!");
		    	        				$("#roletable").bootstrapTable('refresh',{url:"${contextPath}/shop/queryOrderList"});
		    	        			}
		    	        			
		    	        		}
		    	        	})
		    	      }
		    	   };
		      
	</script>
</body>

</html>
