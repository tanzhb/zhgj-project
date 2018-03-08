<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- BEGIN PAGE HEADER-->
<!-- <h3 class="page-title"> 采购订单
    <small></small>
</h3> -->
<!-- <div class="page-bar">

    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="buyOrder">采购管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="buyOrder">采购订单</a>
        </li>
    </ul>
</div> -->

<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs">
		<shiro:hasPermission name="zhgj:buyNormalOrder">
			<li class="active"><a data-target="#tab_15_1" data-toggle="tab">采购订单</a>
			</li>
		</shiro:hasPermission>
<%-- 		<shiro:hasPermission name="zhgj:buyFrameOrder">
			<li><a data-target="" data-toggle="tab">框架合同</a></li>
		</shiro:hasPermission> --%>
		<li><a data-target="#tab_15_3" data-toggle="tab">收货计划</a></li>
		<li><a data-target="#tab_15_4" data-toggle="tab">付款计划</a></li>
	</ul>
    <div class="tab-content">
    	<!-- 普通订单---START -->
        <div class="tab-pane active" id="tab_15_1">
        
        <div class="portlet-body">
			<div class="tabbable-custom ">
			<ul class="nav nav-tabs " id="orderTab">
				<li class="active"><a href="#apply" data-toggle="tab"
					ng-click="toApply()"> 订单 </a></li>
				<li><a href="#daiban" data-toggle="tab" ng-click="toDaiban()">
						待办 <dbQuantity/></a></li>
				<li><a href="#yiban" data-toggle="tab" ng-click="toYiban()">
						已办 <ybQuantity/></a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="apply">
			        <div class="portlet light">
			            <div class="portlet-title">
							<div class="actions">
								<shiro:hasPermission name="buyNormalOrder:add">
									<label class="btn btn-transparent yellow btn-circle btn-sm"
											ng-click="submitBuyApply()"> <i class="glyphicon glyphicon-play"></i> 申请</label>
								</shiro:hasPermission>
								<shiro:hasPermission name="buyNormalOrder:add">
									<label class="btn btn-transparent green btn-circle btn-sm"
										ui-sref="addBuyOrder"> <i class="fa fa-plus"></i> 添加
									</label>
								</shiro:hasPermission>
								<shiro:hasPermission name="buyNormalOrder:edit">
									<label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="editBuyOrder()"> <i class="fa fa-edit"></i>
										修改
									</label>
								</shiro:hasPermission>
								<shiro:hasPermission name="buyNormalOrder:add">
									<label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="copyOrder()"> <i class="fa fa-plus"></i> 复制
									</label>
								</shiro:hasPermission>
									<shiro:hasPermission name="buyNormalOrder:delete">
										<label class="btn btn-transparent red btn-circle btn-sm"
											ng-click="deleteBuyOrder()"> <i class="fa fa-minus"></i>
											删除
										</label>
									</shiro:hasPermission>
									<shiro:hasPermission name="buyNormalOrder:import">
										<label
											class="btn btn-transparent green btn-outline btn-circle btn-sm"
											data-toggle="modal" data-target="#import"> <i
											class="fa fa-upload"></i> 导入
										</label>
									</shiro:hasPermission>
									<shiro:hasPermission name="buyNormalOrder:export">
										<label
											class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
											ng-click="exportBuyOrder()"> <i
											class="fa fa-file-excel-o"></i> 导出
										</label>
									</shiro:hasPermission>
							</div>
			            </div>
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
			                            <th> 采购订单号 </th>
			                            <th> 供应商 </th>
			                            <th> 采购数量 </th>
			                            <th> 金额 </th>
			                            <!-- <th> 配送 </th> -->
			                            <th> 采购类型 </th>
			                            <th> 采购计划单号 </th>
			                            <th> 关联销售单 </th>
			                            <th> 下单日期 </th>
			                            <th> 操作 </th>
			                            <!-- <th style="white-space: nowrap;">状态</th> -->
			                        </tr>
			                    </thead>
			                    <tbody>
			                    </tbody>
			                </table>
			            </div>
			        </div>
				</div>
				<div class="tab-pane" id="daiban">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet box ">
								<div class="portlet-title"></div>
	
								<div class="portlet-body">
									<table class="table table-striped table-bordered table-hover table-checkable order-column"
										id="dbTable">
										<thead>
											<tr>
											<th>
	                                            <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
	                                                <input type="checkbox" class="group-checkable" data-set="#dbTable .checkboxes" />
	                                                <span></span>
	                                            </label>
	                                        </th>
											<th style="white-space: nowrap;">任务状态</th>
											<th style="white-space: nowrap;">采购订单号</th>
											<th style="white-space: nowrap;">关联销售单号</th>
											<th style="white-space: nowrap;">供应商</th>
											<th style="white-space: nowrap;">申请人</th>
											<th style="white-space: nowrap;">申请原因</th>
											<th style="white-space: nowrap;">当前节点</th>
											<th style="white-space: nowrap;">负责人</th>
											<th style="white-space: nowrap;">申请时间</th>
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
				<div class="tab-pane" id="yiban">
							<div class="portlet box">
								<div class="portlet-title"></div>
	
								<div class="portlet-body">
									<table class="table table-striped table-bordered table-hover"
										id="endTaskTable">
										<thead>
											<tr>
												<th>单据类型</th>
												<th>采购订单号</th>
												<th>供应商</th>
												<th>申请人</th>
												<th>申请原因</th>
												<th>申请时间</th>
												<th>审批节点</th>
												<th>审批完成日期 </th>
												<th>审批失败原因</th>
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
<!-- 普通订单---end -->
        <!-- 框架订单---START -->
        <div class="tab-pane" id="tab_15_2">
        	<div class="row">
	<div class="col-md-12">
	        <div class="portlet light">
	            <div class="portlet-title">
<!-- 				<div class="caption">
					<i class="fa fa-globe"></i>采购订单
				</div> -->
				<div class="actions">
					<!-- <a href="javascript:;" ui-sref="addBuyOrder"
						 class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</a> 
					<a href="javascript:;" ng-click="editBuyFramOrder()"
						 class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-edit"></i> 修改
					</a>
					<a href="javascript:;" ng-click="deleteBuyFramOrder()"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</a>
					<div class="btn-group">
						<a class="btn btn-default btn-outline btn-circle"
							href="javascript:;" data-toggle="dropdown"> <i
							class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
							class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right" id="sample_3_tools">
							<li><a data-action="0"
								class="tool-action" data-toggle="modal" data-target="#import"> <i class="fa fa-upload"></i> 导入
							</a></li> 
							<li><a href="javascript:;" data-action="1"
								class="tool-action" ng-click="exportBuyFramOrder()"> <i class="fa fa-file-excel-o"></i> 导出
							</a></li>
						</ul>
					</div> -->
								<shiro:hasPermission name="buyFrameOrder:add">
									<label class="btn btn-transparent green btn-circle btn-sm"
										ui-sref="addBuyOrder"> <i class="fa fa-plus"></i> 添加
									</label>
								</shiro:hasPermission>
								<shiro:hasPermission name="buyFrameOrder:edit">
									<label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="editBuyFramOrder()"> <i class="fa fa-edit"></i>
										修改
									</label>
								</shiro:hasPermission>
								<shiro:hasPermission name="buyFrameOrder:delete">
									<label class="btn btn-transparent red btn-circle btn-sm"
										ng-click="deleteBuyFramOrder()"> <i
										class="fa fa-minus"></i> 删除
									</label>
								</shiro:hasPermission>
								
									<!-- <label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
                                              <i class="fa fa-upload"></i> 导入</label> -->
                                <shiro:hasPermission name="buyFrameOrder:export">
									<label
										class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
										ng-click="exportBuyFramOrder()"> <i
										class="fa fa-file-excel-o"></i> 导出
									</label>
								</shiro:hasPermission>
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
	                            <th> 采购订单号 </th>
	                            <th> 供应商 </th>
	                            <th> 采购数量 </th>
	                            <th> 金额 </th>
	                            <!-- <th> 配送 </th> -->
	                            <th> 采购类型 </th>
	                            <th> 关联采购合同 </th>
	                            <th> 关联销售单 </th>
	                            <th> 下单日期 </th>
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
        <!-- 收货计划---START -->
        <div class="tab-pane" id="tab_15_3">
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
										发货通知
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
        <!-- 付款计划---end -->
        <div class="tab-pane" id="tab_15_4">
        	<div class="row">
	<div class="col-md-12">

		<script type="text/javascript">

		function MyCtrl($rootScope, $scope, $location,$stateParams) {
			  $scope.jumpToUrl = function(path) {
				  $location.path(path);
				  initPageBar($rootScope, path);
			  };
			}
		
		</script>

		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">付款计划列表</span>
				</div>
				<div class="actions" ng-controller='MyCtrl' id="buttons">
				<label class="btn btn-transparent yellow btn-circle btn-sm" ng-click="jumpToApplyPay()"><i class="glyphicon glyphicon-play"></i> 申请</label>
				<label class="btn btn-transparent green btn-circle btn-sm" ng-click="jumpToUrl('addPayForBuyOrder')"><i class="fa fa-plus"></i> 添加</label>
									
				<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="jumpToEdit()"> <i class="fa fa-edit"></i>修改</label>
									
									
				<label class="btn btn-transparent red btn-circle btn-sm" ng-click="delPay()"> <i class="fa fa-minus"></i> 删除</label>
									
				<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportPay()"> <i class="fa fa-file-excel-o"></i> 导出</label>
				</div>
			</div>

			<div id="delUsersModal" class="modal fade" tabindex="-1"
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
							<button type="button" ng-click="confirmDel()" class="btn green">确定
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="portlet-body">
			
					<div class="tabbable-custom ">
						<ul class="nav nav-tabs " id="accountPayableTab">
							<li class="active"><a href="#applyPay" data-toggle="tab"
								ng-click="toApplyPay()"> 待申请 </a></li>
							<li><a href="#daibanPay" data-toggle="tab" ng-click="toDaibanPay()">待办流程<dbQuantity1/> </a></li>
							<li><a href="#yibanPay" data-toggle="tab" ng-click="toYibanPay()">
									已办流程 </a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="applyPay">
								<table
									class="table table-striped table-bordered table-hover table-checkable order-column"
									id="sample_4">
									<thead>
										<tr>
											<!-- <th style="text-align: center"><input name="select_all"
												value="1" id="example-select-all" type="checkbox" /></th> -->
												
											<th>
                                                 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                                     <input type="checkbox" class="group-checkable" data-set="#sample_4 .checkboxes" />
                                                     <span></span>
                                                 </label>
                                             </th>
												
							<th style="white-space: nowrap;">应付帐单号</th>
							<th style="white-space: nowrap;">付款类型</th>
							<th style="white-space: nowrap;"> 采购订单号 </th>
							<th style="white-space: nowrap;">币种</th>
							<th style="white-space: nowrap;">应付金额</th>
							<th style="white-space: nowrap;">应付日期</th>
							<th style="white-space: nowrap;">收款方</th>
							<th style="white-space: nowrap;">实付日期</th>
							<th style="white-space: nowrap;">实付金额</th>
							<th style="white-space: nowrap;">是否开票</th>
							<th style="white-space: nowrap;">状态</th>
							<th style="white-space: nowrap;">操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
							<div class="tab-pane" id="daibanPay">
								<div class="row">
									<div class="col-md-12">
				
										<!-- BEGIN EXAMPLE TABLE PORTLET-->
										<div class="portlet box green">
											<div class="portlet-title" style="height:50px"></div>
				
											<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-checkable order-column"
													id="dbTableForPay">
													<thead>
														<tr>
															<!-- <th style="text-align: center"><input name="select_all"
																value="1" id="example-select-all" type="checkbox" /></th> -->
																
															 <th>
			                                                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                                                        <input type="checkbox" class="group-checkable" data-set="#dbTableForPay .checkboxes" />
			                                                        <span></span>
			                                                    </label>
			                                                </th>
															
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
							<div class="tab-pane" id="yibanPay">
								<div class="row">
									<div class="col-md-12">
				
										<!-- BEGIN EXAMPLE TABLE PORTLET-->
										<div class="portlet box green">
											<div class="portlet-title" style="height:50px"></div>
				
											<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover"
													id="ybTableForPay">
													<thead>
														<tr>
																														
															<th>申请人</th>
															<th>标题</th>
															<th>任务开始时间</th>
															<th>任务签收时间</th>
															<th>任务结束时间 </th>
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
        <!-- 付款计划---end -->
 </div>
 </div>
 <!-- 删除订单modal 开始 -->
	<div id="delBuyOrderModal" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">确认</h4>
				</div>
				<div class="modal-body">
					<p>是否删除已选订单?</p>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="del()" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 删除订单modal 结束 -->
<!-- 导入订单modal 开始 -->
<jsp:include  page="importOrder.jsp"/>
<jsp:include  page="viewOperateLog.jsp"/>
<jsp:include  page="viewDeliverOperateLog.jsp"/>
<jsp:include  page="viewPayOperateLog.jsp"/>
<jsp:include page="showInRecord.jsp"/>
