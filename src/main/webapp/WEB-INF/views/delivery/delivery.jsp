<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>

		<li><a ui-sref="delivery">仓储</a><i class="fa fa-angle-right"></i></li>
		<li><a id="tip">发货计划</a></li>
	</ul>
</div>
<div class="tabbable-line">
	<ul class="nav nav-tabs" id="delivery_tab">
		<li class="active"><a data-target="#tab_15_1" data-toggle="tab">发货计划</a>
		</li>
		<li><a data-target="#tab_15_2" data-toggle="tab">出库记录</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="tab_15_1">
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
							<div class="actions" ng-controller='MyCtrl'>
									<label class="btn btn-transparent yellow btn-circle btn-sm" ng-click="jumpToApplyPay()"><i class="glyphicon glyphicon-play"></i> 申请</label>
									
									<label class="btn btn-transparent green btn-circle btn-sm"
									ng-click="jumpToUrl('addDelivery')"> <i
									class="fa fa-plus"></i> 添加
								</label> <label class="btn btn-transparent purple btn-circle btn-sm"
									ng-click="jumpToEdit()"> <i class="fa fa-edit"></i> 修改
								</label> <label class="btn btn-transparent red btn-circle btn-sm"
									ng-click="del()"> <i class="fa fa-minus"></i> 删除
								</label>
								<!-- <label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label> -->
								<label
									class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
									ng-click="exportContract()"> <i
									class="fa fa-file-excel-o"></i> 导出
								</label>
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
										<button type="button" ng-click="confirmDel()"
											class="btn green">确定</button>
									</div>
								</div>
							</div>
						</div>

						<div class="portlet-body">
							<div class="tabbable-custom ">
								<ul class="nav nav-tabs " id="accountPayableTab">
									<li class="active"><a href="#apply" data-toggle="tab"
										ng-click="toApply()"> 待申请 </a></li>
									<li><a href="#daiban" data-toggle="tab"
										ng-click="toDaiban()"> 待办流程 </a></li>
									<li><a href="#yiban" data-toggle="tab"
										ng-click="toYiban()"> 已办流程 </a></li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="apply">
										<table
											class="table table-striped table-bordered table-hover table-checkable order-column"
											id="sample_2">
											<thead>
												<tr>
													<!-- <th style="text-align: center"><input
														name="select_all" value="1" id="example-select-all"
														class="group-checkable" type="checkbox" /></th> -->
													<th style="text-align: center;"><label
														class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
															<input type="checkbox" class="group-checkable"
															id="example-select-all" /> <span></span>
													</label></th>

													<th style="white-space: nowrap;">发货单编号</th>
													<th style="white-space: nowrap;">关联销售单号</th>
													<th style="white-space: nowrap;">物料</th>
													<th style="white-space: nowrap;">包装数量</th>
													<th style="white-space: nowrap;">收货方</th>
													<th style="white-space: nowrap;">发货地点</th>
													<th style="white-space: nowrap;">发货日期</th>
													<th style="white-space: nowrap;">运输方式</th>
													<th style="white-space: nowrap;">收货地点</th>
													<th style="white-space: nowrap;">备注</th>
													<th style="white-space: nowrap;">状态</th>
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
		<div class="tab-pane" id="tab_15_2">
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe font-green"></i> <span
									class="caption-subject font-green bold uppercase">出库记录</span>
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
										ui-sref="stockOutAdd"> <i class="fa fa-plus"></i> 添加
									</label> <label class="btn btn-transparent purple btn-circle btn-sm"
										ng-click="stockOutEdit()"> <i class="fa fa-edit"></i>
										修改
									</label> <label class="btn btn-transparent red btn-circle btn-sm"
										ng-click="stockOutDelete()"> <i class="fa fa-minus"></i>
										删除
									</label> <label
										class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
										ng-click="exportStockOut()"> <i
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
							<table class="table table-striped table-bordered table-hover table-checkable order-column" id="stockInTable">
								<thead>
									<tr>
										<th>
												<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
				                                     <input type="checkbox" class="group-checkable" data-set="#stockInTable .checkboxes" />
				                                     <span></span>
				                                 </label>
										</th>
										<th>出库明细号</th>
										<th>销售订单号</th>
										<th>商品名称</th>
										<th>规格型号</th>
										<th>批次号</th>
										<th>出库日期</th>
										<th>出库数量</th>
										<th>库存数量</th>
										<th>客户</th>
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
</div>
<!-- 基本信息modal 开始 -->
<div id="basicContractInfo" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 750px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">基本信息</h4>
			</div>
			<div class="modal-body">
				<div class="row" style="line-height: 60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">合同编号</label> <label
								class="control-label col-md-7">
								{{contractVO.contractNum}} </label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">合同类型</label> <label
								class="control-label col-md-7">
								{{contractVO.contractType}} </label>

						</div>
					</div>
				</div>



				<div class="row" style="line-height: 60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">服务模式</label> <label
								class="control-label col-md-7">
								{{contractVO.serviceModel}} </label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">结算条款</label> <label
								class="control-label col-md-7">
								{{contractVO.settlementClause}} </label>

						</div>
					</div>
				</div>


				<div class="row" style="line-height: 60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">供应商</label> <label
								class="control-label col-md-7"> {{contractVO.comId}} </label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">备注</label> <label
								class="control-label col-md-7"> {{contractVO.remark}} </label>

						</div>
					</div>
				</div>


				<div class="row" style="line-height: 60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">开始日期</label> <label
								class="control-label col-md-7"> {{contractVO.startDate}}
							</label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">结束日期</label> <label
								class="control-label col-md-7"> {{contractVO.endDate}} </label>

						</div>
					</div>
				</div>


				<div class="row" style="line-height: 60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">签订日期</label> <label
								class="control-label col-md-7"> {{contractVO.signDate}}
							</label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">签订人</label> <label
								class="control-label col-md-7"> {{contractVO.signer}} </label>

						</div>
					</div>
				</div>


				<div class="row" style="line-height: 60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">电子合同</label> <label
								class="control-label col-md-7"> <a
								title="{{contractVO.electronicContract}}"
								ng-click="download(contractVO.electronicContract)">
									{{contractVO.electronicContract|limitTo:10}}... </a>
							</label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">签字合同</label> <label
								class="control-label col-md-7"> <a
								title="{{contractVO.signContract}}"
								ng-click="download(contractVO.signContract)">
									{{contractVO.signContract|limitTo:10}}... </a>
							</label>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 基本信息modal 结束 -->

<!-- 导入modal START -->
<div class="modal fade" id="import" role="import" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">合同信息导入</h4>
			</div>
			<div class="modal-body">
				<!-- <div class="col-md-12"> -->
				<div class="">
					<div class="portlet-body form">
						<!--  BEGIN FORM -->
						<form class="form-horizontal" role="form">
							<div class="form-body">
								<form id="fileImport" method="post"
									enctype="multipart/form-data">
									<div class="row">
										<div class="col-md-2">
											<div class="form-group">
												<!-- <div class="col-md-4">
	                                               		</div> -->
												<div class="col-md-12">
													<button type="button" class="btn blue"
														ng-click="downloadImportTemp()">下载模板</button>
												</div>
											</div>
										</div>
										<div class="col-md-7">
											<div class="form-group">
												<div class="fileinput fileinput-new"
													data-provides="fileinput">
													<div class="input-group input-large">
														<div
															class="form-control uneditable-input input-fixed input-medium"
															data-trigger="fileinput">
															<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
																class="fileinput-filename"> </span>
														</div>
														<span class="input-group-addon btn default btn-file"
															id="file_span"> <span class="fileinput-new">
																选择文件 </span> <span class="fileinput-exists">更换</span> <input
															type="file" file-model="excelFile" accept=".xls"
															name="...">
														</span> <a href="javascript:;" id="resetFile"
															class="input-group-addon btn red fileinput-exists"
															data-dismiss="fileinput"> 移除 </a>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-4"></div>
												<div class="col-md-8">
													<button type="button" class="btn blue"
														ng-click="uploadExcel()">导入</button>
												</div>
											</div>
										</div>

										<!--   /span -->
									</div>
									<!-- /row -->
								</form>
							</div>
						</form>
						<!--  END FORM -->
					</div>
				</div>
				<!-- </div> -->
				<!-- </div> -->
			</div>
		</div>
	</div>
</div>
<!-- 导入modal END-->

<!-- <script>
/* TableDatatablesFixedHeader.init();//页面元素载入结束后，装载datatables */
	$(document).ready(function(){
　　		
　　		
　　		
	});  
 
 

</script> -->
