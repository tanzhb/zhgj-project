<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<!-- <h3 class="page-title">收货</h3> -->
<!-- <div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="datatablesmanaged">仓储</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a id="tip" ui-sref="datatablesmanaged">收货</a></li>
	</ul>
</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
	<ul class="nav nav-tabs" id="delivery_tab">
		<shiro:hasPermission name="zhgj:takeDeliveryPlan:*">
		<li class="active"><a data-target="#tab_15_1" data-toggle="tab">入库通知</a>
		</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="zhgj:stockInRecord:*">
		<li><a data-target="#tab_15_2" data-toggle="tab">入库记录</a></li>
		</shiro:hasPermission>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="tab_15_1">


			<div class="portlet-body">
				<!-- <div class="table-responsive"> -->
				<div class="tabbable-custom">
					<ul class="nav nav-tabs" id="takeDelivery_tab">
						<li class="active"><a data-target="#tab_25_1"
							data-toggle="tab">入库通知</a></li>
						<li><a data-target="#tab_25_2" data-toggle="tab"
							ng-click="toDaiban()">待办<dbQuantity/></a></li>
						<li><a data-target="#tab_25_3" data-toggle="tab"
							ng-click="toYiban()">已办 <ybQuantity/></a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tab_25_1">

							<div class="row">
								<div class="col-md-12">
									<!-- BEGIN EXAMPLE TABLE PORTLET-->
									<div class="portlet light">
										<div class="portlet-title">
											<div class="caption"></div>
											<div class="actions">
												<div class="btn-group btn-group-devided"
													data-toggle="buttons" id="buttons">
													<!-- <label class="btn btn-transparent green btn-circle btn-sm"
														ui-sref="takeDeliveryAdd"> <i class="fa fa-plus"></i>
														代发货
													</label> --> <!-- <label class="btn btn-transparent blue btn-circle btn-sm"
														ng-click="takeDelivery()"> <i class="fa fa-gift"></i>
														收货
													</label>  --><!-- <label class="btn btn-transparent red btn-circle btn-sm"
														ng-click="takeDeliveryDelete()"> <i
														class="fa fa-minus"></i> 删除
													</label> --> 
													<shiro:hasPermission name="stockInRecord:add">
													<label class="btn btn-transparent yellow btn-circle btn-sm"
														ng-click="takeDeliveryStockIn()"> <i class="fa fa-arrow-down"></i> 入库
													</label>
													</shiro:hasPermission>
													<label
														class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
														ng-click="exportTakeDelivery()"> <i
														class="fa fa-file-excel-o"></i> 导出
													</label>
												</div>
											</div>
										</div>
										<table
											class="table table-striped table-bordered table-hover table-checkable order-column"
											id="takeDeliveryTable">
											<thead>
												<tr>
													<th style="text-align: center"><label
														class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
															<input type="checkbox" class="group-checkable"
															data-set="#takeDeliveryTable .checkboxes" /> <span></span>
													</label></th>
													<!-- <th>收货单编号</th> -->
													<th>入库通知单号</th>
													<th>入库类型</th>
													<th>关联采购单号</th>
													<th>供应商</th>
													<th>包装数量</th>
													<th>物料数量</th>
													<th>实际入库数量</th>
													<th>发货日期</th>
													<th>预计到货日期</th><!-- 运输方式 -->
													<th>收货地址</th><!-- 发货/提货地址 -->
													<th>备注</th>
													<th>状态</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="tab_25_2">
							<div class="row">
								<div class="col-md-12">

									<!-- BEGIN EXAMPLE TABLE PORTLET -->
									<div class="portlet light"
										style="padding: 0px 15px; box-shadow: 0px 0px; margin-bottom: 0px;">
										<div class="portlet-title">
											<div class="actions">
												<div class="btn-group btn-group-devided"
													data-toggle="buttons">
													<label class="btn default btn-sm"
														ng-click="takeDeliveryAudit()"> 办理 </label>
												</div>
												<div class="btn-group btn-group-devided"
													data-toggle="buttons">
													<label class="btn default btn-sm"
														ng-click="takeDeliveryReceive()"> 签收 </label>
												</div>
											</div>
											<div class="tools"></div>
										</div>
										<div class="portlet-body">
											<table
												class="table table-striped table-bordered table-hover table-checkable order-column"
												id="sample_2">
												<thead>
													<tr>
														<th style="text-align: center"><label
															class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>
																<input type='checkbox' class='checkboxes' value='1' />
																<span></span>
														</label></th>
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
									<!-- END EXAMPLE TABLE PORTLET -->
								</div>
							</div>

						</div>
						<div class="tab-pane" id="tab_25_3">

							<div class="row">
								<div class="col-md-12">

									<!-- BEGIN EXAMPLE TABLE PORTLET -->
									<div class="portlet light"
										style="padding: 0px 15px; box-shadow: 0px 0px; margin-bottom: 0px;">
										<!-- <div class="portlet-title">
																	<div class="actions">
																	</div>
																</div> -->
										<div class="portlet-body">
											<table class="table table-striped table-bordered table-hover"
												id="ybTable">
												<thead>
													<tr>
														<th>单据类型</th>
														<th>申请人</th>
														<th>标题</th>
														<th>任务开始时间</th>
														<th>任务结束时间</th>
														<th>流程结束原因</th>
														<th>流程版本号</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>
									<!-- END EXAMPLE TABLE PORTLET -->
								</div>
							</div>

						</div>
					</div>
				</div>

				<!--   </div>
					        </div> -->
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>

		</div>
		<div class="tab-pane" id="tab_15_2">
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<!-- <i class="fa fa-globe font-green"></i> <span
									class="caption-subject font-green bold uppercase">入库记录</span> -->
							</div>
							<!--  <div class="actions">
		                                        <a class="btn btn-default btn-sm" data-toggle="modal" data-target="#basic">
		                                            <i class="fa fa-plus"></i> 新增 </a>
		                                        <a href="javascript:;" class="btn btn-default btn-sm">
		                                            <i class="fa fa-print"></i> 打印 </a>
		                                    </div> -->
							<div class="actions">
								<div class="btn-group btn-group-devided" data-toggle="buttons">
									<%-- <shiro:hasPermission name="stockInRecord:add">
									<label class="btn btn-transparent yellow btn-circle btn-sm"
										ng-click="stockIn()"> <i class="fa fa-arrow-down"></i> 入库
									</label>
									</shiro:hasPermission> --%>
									<shiro:hasPermission name="stockInRecord:add">
									 <label class="btn btn-transparent green btn-circle btn-sm"
										ui-sref="stockInAdd"> <i class="fa fa-plus"></i> 添加
									</label> 
									</shiro:hasPermission>
									<!--<shiro:hasPermission name="stockInRecord:edit"> <label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="stockInEdit()"> <i class="fa fa-edit"></i>
										修改
									</label></shiro:hasPermission> -->
									<shiro:hasPermission name="stockInRecord:import">
									<label class="btn btn-transparent red btn-circle btn-sm"
										ng-click="stockInDelete()"> <i class="fa fa-minus"></i>
										删除
									</label>
									</shiro:hasPermission>
									<shiro:hasPermission name="stockInRecord:export">
									 <label
										class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
										ng-click="exportStockIn()"> <i
										class="fa fa-file-excel-o"></i> 导出
									</label>
									</shiro:hasPermission>
								</div>
							</div>
						</div>
						<div class="portlet-body">
							<!-- <div class="table-responsive"> -->
							<table
								class="table table-striped table-bordered table-hover table-checkable order-column"
								id="stockInTable">
								<thead>
									<tr>
										<th><label
											class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
												<input type="checkbox" class="group-checkable"
												data-set="#stockInTable .checkboxes" /> <span></span>
										</label></th>
										<th>入库单号</th>
										<th>入库类型</th>
										<th>商品名称</th>
										<th>规格型号</th>
										<th>入库日期</th>
										<th>入库数量</th>
										<!-- <th>采购订单号</th>
										<th>供应商</th>
										<th>收货计划单号</th> -->
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
