<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>

		<li><a ui-sref="userContract">基础数据</a><i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="userContract">合同信息</a></li>
	</ul>
</div> -->
<div class="tabbable-line">
	<ul class="nav nav-tabs" id="statement_tab">
		<li><a data-target="#tab_15_2" data-toggle="tab">销售合同</a></li>
		<li><a data-target="#tab_15_3" data-toggle="tab">采购合同</a></li>
		<li class="active"><a data-target="#tab_15_1" data-toggle="tab">其他合同</a>
		</li>
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
									class="caption-subject font-green bold uppercase">合同列表</span>
							</div>
							<div class="actions" ng-controller='MyCtrl'>
								<label class="btn btn-transparent green btn-circle btn-sm"
									ng-click="jumpToUrl('addUserContract')"><i
									class="fa fa-plus"></i> 添加</label> <label
									class="btn btn-transparent purple btn-circle btn-sm"
									ng-click="jumpToEdit()"> <i class="fa fa-edit"></i>修改
								</label> <label class="btn btn-transparent red btn-circle btn-sm"
									ng-click="del()"> <i class="fa fa-minus"></i> 删除
								</label> <label
									class="btn btn-transparent green btn-outline btn-circle btn-sm"
									data-toggle="modal" data-target="#import"><i
									class="fa fa-upload"></i> 导入</label> <label
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
							<table class="table table-bordered" id="sample_2">
								<thead>
									<tr>
										<th style="text-align:center;">
                                                 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                                     <input type="checkbox" class="group-checkable" id="example-select-all"/>
                                                     <span></span>
                                                 </label>
                                             </th>

										<th style="white-space: nowrap;">合同编号</th>
										<th style="white-space: nowrap;">甲方</th>
										<th style="white-space: nowrap;">乙方</th>
										<th style="white-space: nowrap;">合同类型</th>
										<th style="white-space: nowrap;">服务模式</th>
										<th style="white-space: nowrap;">签订日期</th>
										<th style="white-space: nowrap;">签订人</th>
										<th style="white-space: nowrap;">开始日期</th>
										<th style="white-space: nowrap;">结束日期</th>
										<th style="white-space: nowrap;">版本号</th>
										<th style="white-space: nowrap;">状态</th>
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


		<div class="tab-pane" id="tab_15_2">
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe font-green"></i> <span
									class="caption-subject font-green bold uppercase">销售合同列表</span>
							</div>
							<div class="actions">
								<label
									class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
									ng-click="exportSaleContract()"> <i
									class="fa fa-file-excel-o"></i> 导出
								</label>
							</div>
						</div>

						<div class="portlet-body">
							<table class="table table-bordered" id="sample_2_1">
								<thead>
									<tr>
										<th style="text-align: center">
										<label class="mt-radio mt-radio-outline">
											<input type="radio" value="1" name="id[]"/> <span></span>
									     </label>	
											</th>

										<th style="white-space: nowrap;">合同编号</th>
										<th style="white-space: nowrap;">企业名称</th>
										<th style="white-space: nowrap;">合同类型</th>
										<th style="white-space: nowrap;">关联订单</th>
										<th style="white-space: nowrap;">签订日期</th>
										<th style="white-space: nowrap;">签订人</th>
										<th style="white-space: nowrap;">开始日期</th>
										<th style="white-space: nowrap;">结束日期</th>
										<th style="white-space: nowrap;">版本号</th>
										<th style="white-space: nowrap;">状态</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>

							<div class="portlet light">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-globe font-green"></i> <span
											class="caption-subject font-green bold uppercase">关联订单</span>
									</div>
								</div>
								<table class="table table-bordered">
									<thead>
										<tr>
											<th style="white-space: nowrap;">订单编号</th>
											<th style="white-space: nowrap;">订单类型</th>
											<th style="white-space: nowrap;">销售类型</th>
											<th style="white-space: nowrap;">订单金额</th>
											<th style="white-space: nowrap;">物料</th>
											<th style="white-space: nowrap;">采购商</th>
											<th style="white-space: nowrap;">制单人</th>
											<th style="white-space: nowrap;">下单日期</th>
											<th style="white-space: nowrap;">订单状态</th>
										</tr>
									</thead>
									<tbody>
									<tr ng-repeat="item in orderInfo track by $index">
											<td><p class="form-control-static">{{item.orderNum}}</p></td>
											<td><p class="form-control-static">销售订单</p></td>
											<td><p class="form-control-static">{{item.orderType}}</p></td>	
											<td><p class="form-control-static">{{item.orderAmount}}</p></td>
											<td><p class="form-control-static">{{item.materielAmount}}</p></td>
											<td><p class="form-control-static">{{item.buyComId}}</p></td>
											<td><p class="form-control-static">{{item.maker}}</p></td>
											<td><p class="form-control-static">{{item.orderDate}}</p></td>
											<td><p class="form-control-static">{{item.status}}</p></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
		</div>
		
		
		<div class="tab-pane" id="tab_15_3">
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe font-green"></i> <span
									class="caption-subject font-green bold uppercase">采购合同列表</span>
							</div>
							<div class="actions">
								<label
									class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
									ng-click="exportBuyContract()"> <i
									class="fa fa-file-excel-o"></i> 导出
								</label>
							</div>
						</div>

						<div class="portlet-body">
							<table class="table table-bordered" id="sample_2_2">
								<thead>
									<tr>
										<th style="text-align: center"><label class="mt-radio mt-radio-outline">
											<input type="radio" value="1" name="id[]"/> <span></span>
									     </label></th>

										<th style="white-space: nowrap;">合同编号</th>
										<th style="white-space: nowrap;">企业名称</th>
										<th style="white-space: nowrap;">合同类型</th>
										<th style="white-space: nowrap;">关联订单</th>
										<th style="white-space: nowrap;">签订日期</th>
										<th style="white-space: nowrap;">签订人</th>
										<th style="white-space: nowrap;">开始日期</th>
										<th style="white-space: nowrap;">结束日期</th>
										<th style="white-space: nowrap;">版本号</th>
										<th style="white-space: nowrap;">状态</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>

							<div class="portlet light">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-globe font-green"></i> <span
											class="caption-subject font-green bold uppercase">关联订单</span>
									</div>
								</div>
								<table class="table table-bordered">
									<thead>
										<tr>
											<th style="white-space: nowrap;">订单编号</th>
											<th style="white-space: nowrap;">订单类型</th>
											<th style="white-space: nowrap;">销售类型</th>
											<th style="white-space: nowrap;">订单金额</th>
											<th style="white-space: nowrap;">物料</th>
											<th style="white-space: nowrap;">供应商</th>
											<th style="white-space: nowrap;">制单人</th>
											<th style="white-space: nowrap;">下单日期</th>
											<th style="white-space: nowrap;">订单状态</th>
										</tr>
									</thead>
									<tbody>
									<tr ng-repeat="item in orderInfo1 track by $index">
											<td><p class="form-control-static">{{item.orderNum}}</p></td>
											<td><p class="form-control-static">采购订单</p></td>
											<td><p class="form-control-static">{{item.orderType}}</p></td>	
											<td><p class="form-control-static">{{item.orderAmount}}</p></td>
											<td><p class="form-control-static">{{item.materielAmount}}</p></td>
											<td><p class="form-control-static">{{item.supplyComId}}</p></td>
											<td><p class="form-control-static">{{item.maker}}</p></td>
											<td><p class="form-control-static">{{item.orderDate}}</p></td>
											<td><p class="form-control-static">{{item.status}}</p></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
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
							<label class="control-label col-md-5">甲方</label> <label
								class="control-label col-md-7">
								{{contractVO.firstParty}} </label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">甲方签订人</label> <label
								class="control-label col-md-7">
								{{contractVO.firstPartySigner}} </label>

						</div>
					</div>
				</div>

				<div class="row" style="line-height: 60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">乙方</label> <label
								class="control-label col-md-7">
								{{contractVO.secondParty}} </label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">乙方签订人</label> <label
								class="control-label col-md-7">
								{{contractVO.secondPartySigner}} </label>

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
							<label class="control-label col-md-5">签订地点</label> <label
								class="control-label col-md-7">
								{{contractVO.signerAddress}} </label>

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
							<label class="control-label col-md-5">备注</label> <label
								class="control-label col-md-7"> {{contractVO.remark}} </label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">对方合同号</label> <label
								class="control-label col-md-7">
								{{contractVO.otherPartyContractNum}} </label>

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