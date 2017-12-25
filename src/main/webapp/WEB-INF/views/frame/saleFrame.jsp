<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- BEGIN PAGE HEADER-->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs">
			<li class="active"><a data-target="#tab_15_1" data-toggle="tab">销售框架</a>
			</li>
	</ul>
    <div class="tab-content">
    	<!-- 普通订单---START -->
        <div class="tab-pane active" id="tab_15_1">
        
        <div class="portlet-body">
			<div class="tabbable-custom ">
			<ul class="nav nav-tabs " id="frameTab">
				<li class="active"><a href="#apply" data-toggle="tab"
					ng-click="toApply()"> 框架协议 </a></li>
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
										ng-click="submitBuyApply()"> <i class="glyphicon glyphicon-play"></i> 申请</label>
									<label class="btn btn-transparent green btn-circle btn-sm"
										ui-sref="addSaleFrame"> <i class="fa fa-plus"></i> 添加
									</label>
									<label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="editSaleFrame()"> <i class="fa fa-edit"></i>
										修改
									</label>
										<label class="btn btn-transparent red btn-circle btn-sm"
											ng-click="deleteSaleFrame()"> <i class="fa fa-minus"></i>
											删除
										</label>
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
			                            <th> 框架协议号 </th>
			                            <th> 采购商 </th>
			                            <th> 框架类型 </th>
			                            <th> 框架合同号 </th>
			                            <th> 生效日期 </th>
			                            <th> 失效日期 </th>
			                            <th> 订单 </th>
			                            <th> 签订人 </th>
			                            <th> 版本 </th>
			                            <th> 状态</th>
			                            <th> 操作 </th>
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
 </div>
 </div>
 <!-- 删除订单modal 开始 -->
	<div id="delSaleFrameModal" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">确认</h4>
				</div>
				<div class="modal-body">
					<p>是否删除已选框架协议?</p>
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

