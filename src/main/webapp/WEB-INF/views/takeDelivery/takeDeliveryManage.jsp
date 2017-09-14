<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<!-- <h3 class="page-title">收货</h3> -->
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="datatablesmanaged">仓储</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a id="tip" ui-sref="datatablesmanaged">收货</a></li>
	</ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
	<ul class="nav nav-tabs" id="delivery_tab">
		<li class="active"><a data-target="#tab_15_1" data-toggle="tab">收货计划</a>
		</li>
		<li><a data-target="#tab_15_2" data-toggle="tab">入库记录</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="tab_15_1">
<!-- 		    <ul class="nav nav-tabs" id="takeDelivery_tab">
				<li class="active"><a data-target="#tab_25_1" data-toggle="tab">收货计划</a>
				</li>
				<li><a data-target="#tab_25_2" data-toggle="tab" ng-click="toDaiban()">待办流程</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_25_1"> -->
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet light">
								<div class="portlet-title">
									<div class="caption">
										<!-- <i class="fa fa-globe font-green"></i> <span
											class="caption-subject font-green bold uppercase">收货计划</span> -->
									</div>
									<!--  <div class="actions">
				                                        <a class="btn btn-default btn-sm" data-toggle="modal" data-target="#basic">
				                                            <i class="fa fa-plus"></i> 新增 </a>
				                                        <a href="javascript:;" class="btn btn-default btn-sm">
				                                            <i class="fa fa-print"></i> 打印 </a>
				                                    </div> -->
									<div class="actions">
										<div class="btn-group btn-group-devided" data-toggle="buttons">
											<label class="btn btn-transparent green btn-circle btn-sm"
												ui-sref="takeDeliveryAdd"> <i class="fa fa-plus"></i>
												代发货
											</label>
											<label class="btn btn-transparent purple btn-circle btn-sm"
												ng-click="takeDelivery()"> <i class="fa fa-edit"></i>
												收货
											</label>
<!-- 											<label class="btn btn-transparent purple btn-circle btn-sm"
												ng-click="takeDeliveryEdit()"> <i class="fa fa-edit"></i>
												修改
											</label>
 -->											<label class="btn btn-transparent red btn-circle btn-sm"
												ng-click="takeDeliveryDelete()"> <i
												class="fa fa-minus"></i> 删除
											</label>
											<label
												class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
												ng-click="exportTakeDelivery()"> <i
												class="fa fa-file-excel-o"></i> 导出
											</label>
										</div>
										<!-- <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
										ui-sref="takeDeliveryAdd" > <i class="fa fa-plus"></i> 添加
									</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
										ng-click="takeDeliveryEdit()"> <i class="fa fa-edit"></i> 修改
									</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
										ng-click="takeDeliveryDelete()"> <i class="fa fa-minus"></i>
										删除
									</a> 
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
									<table class="table table-bordered" id="takeDeliveryTable">
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
 <!-- 				</div>
				<div class="tab-pane" id="tab_25_2">
						
					<div class="row">
						<div class="col-md-12">
	
							BEGIN EXAMPLE TABLE PORTLET
							<div class="portlet light">
								<div class="portlet-title">
									<div class="actions">
												<div class="btn-group btn-group-devided" data-toggle="buttons">
													<label class="btn btn-transparent green btn-circle btn-sm"
														ng-click="takeDeliveryAudit()" > <i class="fa fa-plus"></i>
														办理
													</label>
												</div>
									</div>
								</div>
								<div class="portlet-body">
									<table class="table table-striped table-bordered table-hover"
										id="sample_2">
										<thead>
											<tr>
												<th style="text-align: center"><input name="select_all_apply"
													value="1" id="example-select-all-apply" type="checkbox" /></th>
												<th>任务状态</th>
												<th>单据类型</th>
												<th>申请人</th>
												<th>标题</th>
												<th>当前节点</th>
												<th>负责人</th>
												<th>任务创建时间</th>
												<th>流程状态</th>
											</tr>
										</thead>
										<tbody>
	
										</tbody>
									</table>
								</div>
	
	
							</div>
							END EXAMPLE TABLE PORTLET
						</div>
					</div>
						
				</div>
			</div>  -->
		</div>
		<div class="tab-pane" id="tab_15_2">
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe font-green"></i> <span
									class="caption-subject font-green bold uppercase">入库记录</span>
							</div>
							<!--  <div class="actions">
		                                        <a class="btn btn-default btn-sm" data-toggle="modal" data-target="#basic">
		                                            <i class="fa fa-plus"></i> 新增 </a>
		                                        <a href="javascript:;" class="btn btn-default btn-sm">
		                                            <i class="fa fa-print"></i> 打印 </a>
		                                    </div> -->
							<div class="actions">
								<div class="btn-group btn-group-devided" data-toggle="buttons">
									<label class="btn btn-transparent green btn-circle btn-sm"
										ui-sref="stockInAdd"> <i class="fa fa-plus"></i> 添加
									</label> <label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="stockInEdit()"> <i class="fa fa-edit"></i>
										修改
									</label> <label class="btn btn-transparent red btn-circle btn-sm"
										ng-click="stockInDelete()"> <i class="fa fa-minus"></i>
										删除
									</label> <label
										class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
										ng-click="exportStockIn()"> <i
										class="fa fa-file-excel-o"></i> 导出
									</label>
								</div>
								<!-- <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
								ui-sref="stockInAdd" > <i class="fa fa-plus"></i> 添加
							</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
								ng-click="stockInEdit()"> <i class="fa fa-edit"></i> 修改
							</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
								ng-click="stockInDelete()"> <i class="fa fa-minus"></i>
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
							<table class="table table-bordered" id="stockInTable">
								<thead>
									<tr>
										<th style="text-align: center"><input name="select_all_2"
											value="1" id="example-select-all-2" type="checkbox" /></th>
										<th>入库明细号</th>
										<!-- <th>入库类型</th> -->
										<th>商品名称</th>
										<th>规格型号</th>
										<th>入库日期</th>
										<th>入库数量</th>
										<th>关联批次号</th>
										<th>供应商</th>
										<th>关联单据</th>
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