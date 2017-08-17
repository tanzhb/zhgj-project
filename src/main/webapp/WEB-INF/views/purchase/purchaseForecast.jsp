<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3 class="page-title">
	采购预测信息 <small></small>
</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="purchaseForecast">采购订单</a><i class="fa fa-angle-right"></i></li>
		<li><a ui-sref="purchaseForecast">采购预测</a></li>
	</ul>
</div>

<div class="row" >
	<div class="col-md-12">

		<script type="text/javascript">

		function MyCtrl($scope, $location) {
			  $scope.jumpToUrl = function(path) {
			 
				  $location.path(path);
			  };
			}
		
		</script>
		
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box red">
			<div class="portlet-title">
			<div class="caption">
					<i class="fa fa-globe"></i>采购预测列表
				</div>
				<div class="actions" ng-controller='MyCtrl'>
					<!-- <a href="javascript:;" ng-click="jumpToUrl('addUserContract')"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-plus"></i> 添加
					</a> 
					<a href="javascript:;" ng-click="jumpToEdit()"
						class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-edit"></i> 修改
					</a> -->
					<a href="javascript:;" ng-click="del()"
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
							<!-- <li><a data-action="0" class="tool-action"
								data-toggle="modal" data-target="#import"> <i
									class="fa fa-upload"></i> 导入
							</a></li> -->
							<li><a href="javascript:;" data-action="1"
								class="tool-action" ng-click="exportPurchaseForecast()"> <i class="fa fa-file-excel-o"></i> 导出
							</a></li>
							<!-- <li><a href="javascript:;" data-action="2"
								class="tool-action" > <i class="fa fa-print"></i> 打印
							</a></li> -->
						</ul>
					</div>
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

			<div class="portlet-body" >
				<table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_2">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>

							<th style="white-space: nowrap;">采购预测编号</th>
							<th style="white-space: nowrap;">物料名称</th>
							<th style="white-space: nowrap;">规格型号</th>
							<th style="white-space: nowrap;">数量</th>
							<th style="white-space: nowrap;">使用客户</th>
							<th style="white-space: nowrap;">交付日期</th>
							<th style="white-space: nowrap;">交付地点</th>
							<th style="white-space: nowrap;">距交付</th>
							<th style="white-space: nowrap;">发布人</th>
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
				<div class="row" style="line-height:60px;">
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
				
				
				
				<div class="row" style="line-height:60px;">
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
				
				
				<div class="row" style="line-height:60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">供应商</label> <label
								class="control-label col-md-7">
								{{contractVO.comId}} </label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">备注</label> <label
								class="control-label col-md-7">
								{{contractVO.remark}} </label>

						</div>
					</div>
				</div>
				
				
				<div class="row" style="line-height:60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">开始日期</label> <label
								class="control-label col-md-7">
								{{contractVO.startDate}} </label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">结束日期</label> <label
								class="control-label col-md-7">
								{{contractVO.endDate}} </label>

						</div>
					</div>
				</div>
				
				
				<div class="row" style="line-height:60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">签订日期</label> <label
								class="control-label col-md-7">
								{{contractVO.signDate}} </label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">签订人</label> <label
								class="control-label col-md-7">
								{{contractVO.signer}} </label>

						</div>
					</div>
				</div>
				
				
				<div class="row" style="line-height:60px;">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">电子合同</label> <label
								class="control-label col-md-7">
								<a title="{{contractVO.electronicContract}}" ng-click="download(contractVO.electronicContract)">
								{{contractVO.electronicContract|limitTo:10}}... 
								</a>
								</label>

						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">签字合同</label> <label
								class="control-label col-md-7">
								<a title="{{contractVO.signContract}}" ng-click="download(contractVO.signContract)">
								{{contractVO.signContract|limitTo:10}}... 
								</a>
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