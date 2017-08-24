<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">收货计划</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="datatablesmanaged">物流管理</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="datatablesmanaged">收货计划</a></li>
	</ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box red">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>收货计划
				</div>
				<!--  <div class="actions">
                                        <a class="btn btn-default btn-sm" data-toggle="modal" data-target="#basic">
                                            <i class="fa fa-plus"></i> 新增 </a>
                                        <a href="javascript:;" class="btn btn-default btn-sm">
                                            <i class="fa fa-print"></i> 打印 </a>
                                    </div> -->
				<div class="actions">
					<a href="javascript:;" class="btn btn-default btn-sm btn-circle"
						ui-sref="takeDeliveryAdd" > <i class="fa fa-plus"></i> 添加
					</a><!--  <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
						ng-click="toEditCompany()"> <i class="fa fa-edit"></i> 修改
					</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
						ng-click="deleteCompanyBatch()"> <i class="fa fa-minus"></i>
						删除
					</a> -->
					<div class="btn-group">
						<a class="btn btn-default btn-outline btn-circle"
							href="javascript:;" data-toggle="dropdown"> <i
							class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
							class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right" id="sample_3_tools">
							<li><a data-action="0" class="tool-action"
								data-toggle="modal" data-target="#import"> <i
									class="fa fa-upload"></i> 导入
							</a></li>
							<li><a href="javascript:;" data-action="1"
								class="tool-action" ng-click="exportCompany()"> <i
									class="fa fa-file-excel-o"></i> 导出
							</a></li>
							<li><a href="javascript:;" data-action="2"
								class="tool-action"> <i class="fa fa-print"></i> 打印
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="portlet-body">
				<!-- <div class="table-responsive"> -->
				<table class="table table-bordered" id="select_sample_2">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all" 
								value="1" id="example-select-all" type="checkbox" /></th>
							<th>收货单编号</th>
							<th>订单编号</th>
							<th>发货方</th>
							<th>物料</th>
							<th>包装数量</th>
							<th>使用包装</th>
							<th>发货地点</th>
							<th>发货日期</th>
							<th>运输方式</th>
							<th>收货/提货点</th>
							<th>备注</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>

		<!--   </div>
        </div> -->
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>


<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<script>
	// TableDatatablesManaged.init();
	//$('.date-picker').datepicker();
</script>
<!-- END MAIN JS -->
