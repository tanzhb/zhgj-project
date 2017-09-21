<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- BEGIN PAGE HEADER-->
<!-- <h3 class="page-title"> 销售订单
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
            <a ui-sref="saleOrder">销售管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="saleOrder">销售订单</a>
        </li>
    </ul>
</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs">
		<shiro:hasPermission name="zhgj:normalOrder">
			<li class="active"><a data-target="#tab_15_1" data-toggle="tab">销售订单</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="zhgj:frameOrder">
			<li><a data-target="" data-toggle="tab">框架合同</a></li>
		</shiro:hasPermission>
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
										<label class="btn btn-transparent yellow btn-circle btn-sm"
										ng-click="submitSaleApply()"> <i class="glyphicon glyphicon-play"></i> 申请</label>
										<shiro:hasPermission name="normalOrder:add">
											<label class="btn btn-transparent green btn-circle btn-sm"
												ui-sref="addSaleOrder"> <i class="fa fa-plus"></i> 添加
											</label>
										</shiro:hasPermission>
										<shiro:hasPermission name="normalOrder:edit">
											<label class="btn btn-transparent purple btn-circle btn-sm"
												ng-click="editSaleOrder()"> <i class="fa fa-edit"></i>
												修改
											</label>
										</shiro:hasPermission>
										<shiro:hasPermission name="normalOrder:delete">
											<label class="btn btn-transparent red btn-circle btn-sm"
												ng-click="deleteSaleOrder()"> <i class="fa fa-minus"></i>
												删除
											</label>
										</shiro:hasPermission>
										<shiro:hasPermission name="normalOrder:import">
											<label
												class="btn btn-transparent green btn-outline btn-circle btn-sm"
												data-toggle="modal" data-target="#import"> <i
												class="fa fa-upload"></i> 导入
											</label>
										</shiro:hasPermission>
										<shiro:hasPermission name="normalOrder:export">
											<label
												class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
												ng-click="exportSaleOrder()"> <i
												class="fa fa-file-excel-o"></i> 导出
											</label>
										</shiro:hasPermission>
									</div>
			            </div>
			            <div class="portlet-body">
			                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_2">
			                    <thead>
			                        <tr>
			                            <th>
		                                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
		                                        <input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" />
		                                        <span></span>
		                                    </label>
		                                </th>
			                            <th> 销售订单号 </th>
			                            <th> 采购方 </th>
			                            <th> 销售商品 </th>
			                            <th> 金额 </th>
			                            <th> 配送 </th>
			                            <th> 服务模式 </th>
			                            <th> 关联销售合同 </th>
			                            <th> 关联采购单 </th>
			                            <th> 下单日期 </th>
			                            <th> 状态 </th>
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
				<div class="tab-pane" id="yiban">
							<div class="portlet box">
								<div class="portlet-title"></div>
	
								<div class="portlet-body">
									<table class="table table-striped table-bordered table-hover"
										id="endTaskTable">
										<thead>
											<tr>
												<th>单据类型</th>
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
<!-- 普通订单---end -->
        <!-- 框架订单---START -->
        <div class="tab-pane" id="tab_15_2">
        	<div class="row">

	<div class="col-md-12">
	        <div class="portlet light">
	            <div class="portlet-title">
				<!-- <div class="caption">
					<i class="fa fa-globe"></i>销售订单
				</div> -->
				<div class="actions">
					<!-- <a href="javascript:;" ui-sref="addSaleOrder"
						 class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</a> 
					<a href="javascript:;" ng-click="editSaleFramOrder()"
						 class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-edit"></i> 修改
					</a>
					<a href="javascript:;" ng-click="deleteSaleFramOrder()"
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
								class="tool-action" ng-click="exportSaleFramOrder()"> <i class="fa fa-file-excel-o"></i> 导出
							</a></li>
						</ul>
					</div> -->
								<shiro:hasPermission name="frameOrder:add">
									<label class="btn btn-transparent green btn-circle btn-sm"
										ui-sref="addSaleOrder"> <i class="fa fa-plus"></i> 添加
									</label>
								</shiro:hasPermission>
								<shiro:hasPermission name="frameOrder:edit">
									<label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="editSaleFramOrder()"> <i class="fa fa-edit"></i>
										修改
									</label>
								</shiro:hasPermission>
								<shiro:hasPermission name="frameOrder:delete">
									<label class="btn btn-transparent red btn-circle btn-sm"
										ng-click="deleteSaleFramOrder()"> <i
										class="fa fa-minus"></i> 删除
									</label>
								</shiro:hasPermission>
									<!-- <label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
                                              <i class="fa fa-upload"></i> 导入</label> -->
									<shiro:hasPermission name="frameOrder:export">
										<label
											class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
											ng-click="exportSaleFramOrder()"> <i
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
	                            <th> 销售订单号 </th>
	                            <th> 采购方 </th>
	                            <th> 销售商品 </th>
	                            <th> 金额 </th>
	                            <th> 配送 </th>
	                            <th> 服务模式 </th>
	                            <th> 关联销售合同 </th>
	                            <th> 关联采购单 </th>
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
 </div>
 </div>
 
 <!-- 删除订单modal 开始 -->
	<div id="delSaleOrderModal" class="modal fade" tabindex="-1"
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