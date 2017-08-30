<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">收付款记录</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="datatablesmanaged">收付款</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a  id="tip" ui-sref="datatablesmanaged">收付款记录</a></li>
	</ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs" id="delivery_tab">
        <li class="active">
            <a data-target="#tab_15_1" data-toggle="tab" >收款记录</a>
        </li>
        <li>
            <a data-target="#tab_15_2" data-toggle="tab" >付款记录</a>
        </li>
    </ul>
<div class="tab-content">
	 <div class="tab-pane active" id="tab_15_1">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>收款记录
						</div>
						<!--  <div class="actions">
		                                        <a class="btn btn-default btn-sm" data-toggle="modal" data-target="#basic">
		                                            <i class="fa fa-plus"></i> 新增 </a>
		                                        <a href="javascript:;" class="btn btn-default btn-sm">
		                                            <i class="fa fa-print"></i> 打印 </a>
		                                    </div> -->
						<div class="actions">
							<!-- <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
								ui-sref="takeDeliveryAdd" > <i class="fa fa-plus"></i> 添加
							</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
								ng-click="takeDeliveryEdit()"> <i class="fa fa-edit"></i> 修改
							</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
								ng-click="takeDeliveryDelete()"> <i class="fa fa-minus"></i>
								删除
							</a>  -->
							<!-- <div class="btn-group">
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
									<li><a href="javascript:;" data-action="0"
										class="tool-action" ng-click="exportTakeDelivery()"> <i
											class="fa fa-file-excel-o"></i> 导出
									</a></li>
									<li><a href="javascript:;" data-action="2"
										class="tool-action"> <i class="fa fa-print"></i> 打印
									</a></li>
								</ul>
							</div> -->
						</div>
					</div>
					<div class="portlet-body">
						<!-- <div class="table-responsive"> -->
						<table class="table table-bordered" id="cc">
							<thead>
								<tr>
									<th style="text-align: center"><input name="select_all" 
										value="1" id="example-select-all" type="checkbox" /></th>
									<th>收款计划编号</th>
									<th>收款计划日期</th>
									<th>描述</th>
									<th>金额</th>
									<th>提货单</th>
									<th>客户</th>
									<th>供应商</th>
									<th>操作人</th>
									<th>实际收款日期</th>
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
	</div>
		 <div class="tab-pane" id="tab_15_2">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>付款记录
						</div>
						<!--  <div class="actions">
		                                        <a class="btn btn-default btn-sm" data-toggle="modal" data-target="#basic">
		                                            <i class="fa fa-plus"></i> 新增 </a>
		                                        <a href="javascript:;" class="btn btn-default btn-sm">
		                                            <i class="fa fa-print"></i> 打印 </a>
		                                    </div> -->
						<div class="actions">
							<!-- <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
								ui-sref="paymentRecordAdd" > <i class="fa fa-plus"></i> 添加
							</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
								ng-click="paymentRecordEdit()"> <i class="fa fa-edit"></i> 修改
							</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
								ng-click="paymentRecordDelete()"> <i class="fa fa-minus"></i>
								删除
							</a>  -->
							<!-- <div class="btn-group">
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
									<li><a href="javascript:;" data-action="0"
										class="tool-action" ng-click="exportStockIn()"> <i
											class="fa fa-file-excel-o"></i> 导出
									</a></li>
									<li><a href="javascript:;" data-action="2"
										class="tool-action"> <i class="fa fa-print"></i> 打印
									</a></li>
								</ul>
							</div> -->
						</div>
					</div>
					<div class="portlet-body">
						<!-- <div class="table-responsive"> -->
						<table class="table table-bordered" id="paymentRecordTable">
							<thead>
								<tr>
									<th style="text-align: center"><input name="select_all_2" 
										value="1" id="example-select-all-2" type="checkbox" /></th>
									<th>付款计划编号</th>
									<th>付款计划日期</th>
									<th>描述</th>
									<th>金额</th>
									<th>提货单</th>
									<th>供应商</th>
									<th>采购商</th>
									<th>操作人</th>
									<th>实际付款日期</th>
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
	</div>
</div>

<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<script>
	// TableDatatablesManaged.init();
	//$('.date-picker').datepicker();
</script>
<!-- END MAIN JS -->