<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>

		<li><a ui-sref="gatheringMoneyRecord">收款</a><i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="gatheringMoneyRecord">收款信息</a></li>
	</ul>
</div> -->

<div class="row">
	<div class="col-md-12">

		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">收款列表</span>
				</div>
				<div class="actions" >
				<label class="btn btn-transparent green btn-circle btn-sm" ng-click="jumpToUrl('addGatheringMoney')"><i class="fa fa-plus"></i> 添加</label>
									
				<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="jumpToEdit()"> <i class="fa fa-edit"></i>修改</label>
									
									
				<label class="btn btn-transparent red btn-circle btn-sm" ng-click="del()"> <i class="fa fa-minus"></i> 删除</label>
									
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
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_2">
					<thead>
						<tr>
							<th style="text-align:center;">
                                <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                    <input type="checkbox" class="group-checkable" id="example-select-all"/>
                                    <span></span>
                                </label>
                            </th>
							<th style="white-space: nowrap;">应收帐单号</th>
							<th style="white-space: nowrap;">收款类型</th>
							<th style="white-space: nowrap;">币种</th>
							<th style="white-space: nowrap;">应收金额</th>
							<th style="white-space: nowrap;">应收日期</th>
							<th style="white-space: nowrap;">付款方</th>
							<th style="white-space: nowrap;">实收日期</th>
							<th style="white-space: nowrap;">实收金额</th>
							<th style="white-space: nowrap;">是否开票</th>
							<th style="white-space: nowrap;">状态</th>
							<th style="white-space: nowrap;">操作</th>
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