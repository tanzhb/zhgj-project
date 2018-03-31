<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- BEGIN PAGE HEADER-->


<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs">
			<li class="active"><a data-target="#tab_15_1" data-toggle="tab">订单</a>
			</li>
			<!-- <li><a data-target="" data-toggle="tab">框架合同</a></li> -->
			<li><a data-target="#tab_15_3" data-toggle="tab">发货计划</a></li>
			<!-- <li><a data-target="#tab_15_4" data-toggle="tab">收款计划</a></li> -->
	</ul>
    <div class="tab-content">
    	<!-- 普通订单---START -->
        <div class="tab-pane active" id="tab_15_1">
<div class="row">

	<div class="col-md-12">
	        <div class="portlet light">
	            <div class="portlet-body">
	                <table class="table table-striped table-bordered table-hover table-checkable order-column" style="text-align: center" id="sample_2">
	                    <thead>
	                        <tr>
	                            <th>
                                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                        <input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" />
                                        <span></span>
                                    </label>
                                </th>
	                            <th> 销售订单号 </th>
	                            <th  > 采购商 </th>
	                            <th> 销售数量 </th>
	                            <th> 金额 </th>
	                            <!-- <th> 配送 </th> -->
	                            <th> 销售类型 </th>
	                            <th> 关联销售合同 </th>
	                            <th> 关联采购单 </th>
	                            <th> 下单日期 </th>
	                            <!-- <th> 状态 </th> -->
	                            <th> 操作 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
 </div>
 </div>
<!-- 普通订单---end -->
        <!-- 框架订单---START -->
        <div class="tab-pane" id="tab_15_2">
        	<div class="row">

	<div class="col-md-12">
	        <div class="portlet light">
	            <div class="portlet-title">
				<div class="actions">
							</div>
	            </div>
	            <div class="portlet-body">
	                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_3">
	                    <thead>
	                        <tr>
	                            <th>
                                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                        <input type="checkbox" class="group-checkable" data-set="#sample_3 .checkboxes" />
                                        <span></span>
                                    </label>
                                </th>
	                            <th> 销售订单号 </th>
	                            <th > 采购商 </th>
	                            <th> 销售数量 </th>
	                            <th> 金额 </th>
	                            <!-- <th> 配送 </th> -->
	                            <th> 销售类型 </th>
	                            <th> 关联销售合同 </th>
	                            <th> 关联采购单 </th>
	                            <th> 下单日期 </th>
	                            <th> 状态 </th>
	                            <th> 操作 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
 </div>
 		</div>
        <!-- 框架订单---end -->
        <!-- 发货计划---START -->
         <div class="tab-pane" id="tab_15_3">
	         <div class="row">
					<div class="col-md-12">
	
						<script type="text/javascript">
							function MyCtrl($scope, $location) {
								$scope.jumpToUrl = function(path) {
	
									$location.path(path);
								};
							} 
						</script>
	
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet light">
							<div class="portlet-title">
							
								<div class="caption">
									<i class="fa fa-globe font-green"></i> <span
										class="caption-subject font-green bold uppercase">发货列表</span>
								</div>
								<div class="actions" ><!--  ng-controller='MyCtrl'-->
										<label class="btn btn-transparent green btn-circle btn-sm"
										ng-click="jumpToUrl('forSupplyOrder')"> <i
										class="fa fa-plus"></i> 添加
									</label> 
									<label class="btn btn-transparent yellow btn-circle btn-sm"
									ng-click="jumpToConfirm()"> <i class="glyphicon glyphicon-play"></i>确认发货
								</label>
									<label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="jumpToEdit()"> <i class="fa fa-edit"></i> 修改
									</label> <label class="btn btn-transparent red btn-circle btn-sm"
										ng-click="del()"> <i class="fa fa-minus"></i> 删除
									</label>
									<label
										class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
										ng-click="exportContract()"> <i
										class="fa fa-file-excel-o"></i> 导出
									</label>
								</div>
							</div>
	
							<div id="delDeliveryModal" class="modal fade" tabindex="-1"
								data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true"></button>
											<h4 class="modal-title">确认</h4>
										</div>
										<div class="modal-body">
											<p>是否删除已选条目?</p>
										</div>
										<div class="modal-footer">
											<button type="button" data-dismiss="modal"
												class="btn dark btn-outline">取消</button>
											<button type="button" ng-click="confirmDel()"
												class="btn green">确定</button>
										</div>
									</div>
								</div>
							</div>
	
							<div class="portlet-body">
								<div class="tabbable-custom ">
									<div class="tab-content">
										<div class="tab-pane active" id="apply">
											<table
												class="table table-striped table-bordered table-hover table-checkable order-column"
												id="sample_deliveryTable">
												<thead>
													<tr>
														<th style="text-align: center;"><label
															class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
																<input type="checkbox" class="group-checkable" data-set="#sample_deliveryTable .checkboxes" />
																 <span></span>
														</label></th>
	
														<th style="white-space: nowrap;">发货单编号</th>
														<th style="white-space: nowrap;">关联销售单号</th>
														<th style="white-space: nowrap;">客户</th>
														<th style="white-space: nowrap;">发货数量</th>
														<th style="white-space: nowrap;">交付方式</th>
														<th style="white-space: nowrap;">发货/提货地址</th>
														<th style="white-space: nowrap;">发货/提货日期</th>
														<th style="white-space: nowrap;">运输方式</th>
														<th style="white-space: nowrap;">状态</th>
														<th style="white-space: nowrap;">操作</th>
														<!-- <th style="white-space: nowrap;"></th> -->
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
										<div class="tab-pane" id="daiban">
											<div class="row">
												<div class="col-md-12">
	
													<!-- BEGIN EXAMPLE TABLE PORTLET-->
													<div class="portlet box green">
														<div class="portlet-title" style="height: 50px"></div>
	
														<div class="portlet-body">
															<table
																class="table table-striped table-bordered table-hover"
																id="dbTable">
																<thead>
																	<tr>
																		<th style="text-align: center;"><label
																			class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
																				<input type="checkbox" class="group-checkable"
																				id="example-select-all" /> <span></span>
																		</label></th>
																		<th style="white-space: nowrap;">任务状态</th>
																		<th style="white-space: nowrap;">申请人</th>
																		<th style="white-space: nowrap;">标题</th>
																		<th style="white-space: nowrap;">当前节点</th>
																		<th style="white-space: nowrap;">负责人</th>
																		<th style="white-space: nowrap;">任务创建时间</th>
																		<th style="white-space: nowrap;">流程状态</th>
																	</tr>
																</thead>
																<tbody>
																</tbody>
															</table>
														</div>
													</div>
													<!-- END EXAMPLE TABLE PORTLET-->
												</div>
											</div>
										</div>
										<div class="tab-pane" id="yiban">
											<div class="row">
												<div class="col-md-12">
	
													<!-- BEGIN EXAMPLE TABLE PORTLET-->
													<div class="portlet box green">
														<div class="portlet-title" style="height: 50px"></div>
	
														<div class="portlet-body">
															<table
																class="table table-striped table-bordered table-hover"
																id="ybTable">
																<thead>
																	<tr>
	
																		<th>申请人</th>
																		<th>标题</th>
																		<th>任务开始时间</th>
																		<th>任务签收时间</th>
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
													<!-- END EXAMPLE TABLE PORTLET-->
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
         </div>
         <!-- 发货计划---end -->
           <!-- 收款计划---end -->
        <div class="tab-pane" id="tab_15_4">
        	<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption"></div>
							<div class="actions">
								<div class="btn-group btn-group-devided"
									data-toggle="buttons" id="buttons">
									<label class="btn btn-transparent green btn-circle btn-sm"
										ui-sref="takeDeliveryAdd"> <i class="fa fa-plus"></i>
										代发货
									</label><label class="btn btn-transparent yellow btn-circle btn-sm"
									ng-click="jumpToConfirm()"> <i class="glyphicon glyphicon-play"></i>确认代发货
								</label> <label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="takeDeliveryEdit()"> <i class="fa fa-edit"></i>
										修改
									</label> <label class="btn btn-transparent red btn-circle btn-sm"
										ng-click="takeDeliveryDelete()"> <i
										class="fa fa-minus"></i> 删除
									</label> <label
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
										<th style="white-space: nowrap;">收货计划号</th>
														<th style="white-space: nowrap;">关联采购单号</th>
														<th style="white-space: nowrap;">发货方</th>
														<th style="white-space: nowrap;">发货数量</th>
														<th style="white-space: nowrap;">交付方式</th>
														<th style="white-space: nowrap;">发货/提货地址</th>
														<th style="white-space: nowrap;">发货/提货日期</th>
														<th style="white-space: nowrap;">运输方式</th>
														<th style="white-space: nowrap;">状态</th>
														<th style="white-space: nowrap;">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
        </div>
        <!-- 收款计划---end -->
 </div>
 </div>
<jsp:include  page="viewOperateLog.jsp"/>
<jsp:include  page="viewDeliverOperateLog.jsp"/>
<jsp:include  page="viewPayOperateLog.jsp"/>