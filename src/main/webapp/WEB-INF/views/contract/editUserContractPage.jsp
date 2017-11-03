<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<!-- <div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="userContract">合同</a> <i class="fa fa-angle-right"></i>
		</li>
		<li><a>编辑合同</a></li>
	</ul>
	<div class="page-toolbar">
		<div class="btn-group pull-right">
			<button type="button"
				class="btn btn-fit-height grey-salt dropdown-toggle"
				onclick="printdiv('saleOrderPrint')">
				<i class="fa fa-print"></i> 打印
			</button>

		</div>
	</div>
</div> -->
<div class="row" id="saleOrderPrint">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet bordered">
			<div class="portlet-body">
				<form action="#" id="form_sample_1" class="">
					<div class="portlet light ">
						<ul class="nav nav-tabs">
							<li class="dropdown pull-right tabdrop">
								<button type="button" ng-click="goback()" class="btn default">
									<i class="fa fa-reply"></i>取消
								</button>
								<button type="button" ng-click="saveUserContract()"
									class="btn blue">
									<i class="fa fa-check"></i> 保存
								</button>
							</li>
							<li class="active bold"><a data-target="#tab_1_1"
								data-toggle="tab">基本信息</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
									<div class="form-body">
										<div class="alert alert-danger display-hide">
											<button class="close" data-close="alert"></button>
											请先输入正确数据！
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">合同编号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" id="contractNum" name="contractNum"
															class="form-control" ng-model="contractVO.contractNum" />
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">合同类型<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control"
															ng-model="contractVO.contractType" id="contractType"
															name="contractType">
															<option value="">合同类型</option>
															<option value="服务合同">服务合同</option>
															<option value="其他合同">其他合同</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">甲方<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" id="firstParty" name="firstParty"
															class="form-control" ng-model="contractVO.firstParty" />
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
										</div>
										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">甲方签订人<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" class="form-control"
															placeholder="甲方签订人"
															ng-model="contractVO.firstPartySigner"
															name="firstPartySigner" />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">乙方<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" placeholder="乙方"
															ng-model="contractVO.secondParty" name="secondParty" />
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">乙方签订人<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" class="form-control"
															placeholder="乙方签订人"
															ng-model="contractVO.secondPartySigner"
															name="secondPartySigner" />
													</div>
												</div>
											</div>
										</div>
										<!--/row-->



										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">签订日期<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control"
															ng-model="contractVO.signDate" id="signDate"
															placeholder="签订日期" data-date-format="yyyy-mm-dd"
															data-date-viewmode="years" size="16" name="signDate" />
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">签订地点<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" class="form-control"
															name="signerAddress" placeholder="签订地点"
															ng-model="contractVO.signerAddress" />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">开始日期<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" class="form-control date-picker"
															ng-model="contractVO.startDate" placeholder="开始日期"
															id="startDate" data-date-format="yyyy-mm-dd"
															data-date-viewmode="years" size="16" name="startDate" />
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">结束日期<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" class="form-control"
															ng-model="contractVO.endDate" id="endDate"
															placeholder="结束日期" data-date-format="yyyy-mm-dd"
															data-date-viewmode="years" size="16" name="endDate" />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">对方合同号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control"
															ng-model="contractVO.otherPartyContractNum"
															id="otherPartyContractNum" name="otherPartyContractNum"
															placeholder="对方合同号" />
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" class="form-control"
															ng-model="contractVO.remark" id="remark" placeholder="备注" />
													</div>
												</div>
											</div>
										</div>
										<!--/row-->


										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">电子合同<span
														class="required" aria-required="true"> * </span></label>

													<div class="" style="display: none;">
														<input type="file" id="electric" name=""
															class="form-control" />
														<div class="form-control-focus"></div>
													</div>
													<div class="">
														<div class="input-icon right">
															<input type="text" class="form-control"
																ng-model="contractVO.electronicContract" readonly
																ng-hide="true"> <a
																title="{{contractVO.electronicContract}}"
																ng-click="download(contractVO.electronicContract)">
																{{contractVO.electronicContract}} </a> <i class="fa fa-edit"
																style="margin-top: 0px;"
																ng-click="editElectronicContract($event)"></i>


														</div>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">签字合同<span
														class="required" aria-required="true"> * </span></label>
													<div class="" style="display: none;">
														<input type="file" id="signContract" name=""
															class="form-control" />
													</div>
													<div class="">
														<div class="input-icon right">
															<input type="text" class="form-control"
																ng-model="contractVO.electronicContract" readonly
																ng-hide="true"> <a
																title="{{contractVO.signContract}}"
																ng-click="download(contractVO.signContract)">
																{{contractVO.signContract}} </a> <i class="fa fa-edit"
																style="margin-top: 0px;"
																ng-click="editSignContract($event)"></i>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!--/row-->
									</div>
								</div>

							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script>
	$('#signDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	}).on('changeDate', function(ev) {
		var endDate = $("#endDate").val();
		var signDate = $("#signDate").val();

		if (endDate < signDate && $.trim(endDate) != "" && endDate != null) {
			toastr.warning('签订时间不能大于结束时间 ！');
			$("#signDate").val("");
		} else {
		}
	});

	$('#startDate').datepicker({
		language : "zh-CN",
		autoclose : true,
		todayBtn : 'linked',
		todayHighlight : true
	}).on('changeDate', function(ev) {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if (startDate > endDate && endDate != null && $.trim(endDate) != "") {
			toastr.warning('开始时间不能大于结束时间  ！');
			$("#startDate").val("");
		}

	});

	$('#endDate').datepicker({
		language : "zh-CN",
		autoclose : true,
		todayBtn : 'linked',
		todayHighlight : true
	}).on(
			'changeDate',
			function(ev) {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var signDate = $("#signDate").val();

				if (endDate < startDate && startDate != null
						&& $.trim(startDate) != "") {
					toastr.warning('结束时间不能小于开始时间 ！');
					$("#endDate").val("");
				} else {
				}

				if (endDate < signDate && signDate != null
						&& $.trim(signDate) != "") {
					toastr.warning('结束时间不能小于签订时间 ！');
					$("#endDate").val("");
				} else {
				}
			});
</script>
