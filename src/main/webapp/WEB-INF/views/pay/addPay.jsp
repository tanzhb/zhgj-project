<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<h3 class="page-title">采购付款</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="paymentRecordC">付款</a> <i class="fa fa-angle-right"></i>
		</li>
		<li><a>新增付款</a></li>
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
</div>
<div class="row" id="saleOrderPrint">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet bordered">
			<div class="portlet-body">
			<form action="#" id="form_sample_1" class="form-horizontal">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption">付款信息</div>
						<div class="tools" id="noprintdiv">
							<button type="submit" ng-click="saveBasicInfo()" ng-show="input"
								class="btn blue  btn-outline  btn-sm">
								<i class="fa fa-save"></i> 保存
							</button>
							<button ng-click="goBack()" type="button"
								ng-hide="saleOrderInput" class="btn red  btn-outline  btn-sm">
								<i class="fa fa-undo"></i> 取消
							</button>
						</div>
					</div>
					
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									请先输入正确数据！
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>付款单号:</label>
											<div class="col-md-8">
												<input type="text" name="paymentNum" class="form-control"
													ng-model="paymentRecord.paymentNum">
												<div class="form-control-focus"></div>
												<span class="help-block">请输入付款单号</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>付款类型:</label>
											<div class="col-md-8">
												<input type="text" name=paymentType class="form-control"
													ng-model="paymentRecord.paymentType" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择付款类型</span>
											</div>

										</div>
									</div>
									
								</div>


								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>关联采购订单号:</label>
											<div class="col-md-6">
												<input type="text" name="orderNum" class="form-control"
													ng-model="saleOrder.orderNum" ng-show="input"
													style="width: 110%;" readonly />
												<div class="form-control-focus"></div>
												<input type="text" ng-model="orderSerial" ng-hide="true" name="orderSerial"/>
												<span class="help-block">请选择关联采购订单号</span>
											</div>
											<div class="col-md-2" ng-show="input">
												<span class="input-inline-btn">
													<button class="btn default" type="button"
														ng-click="selectMateriel()"
														data-target="#basicMaterielInfo" data-toggle="modal">
														<i class="fa fa-search"></i>
													</button>
												</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>订单金额:</label>
											<div class="col-md-8">
												<input type="text" name="paymentStyle" class="form-control"
													ng-model="paymentRecord.paymentStyle" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择订单金额</span>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>申请付款金额  :</label>
											<div class="col-md-8">
												<input type="text" name="paymentStyle" class="form-control"
													ng-model="paymentRecord.paymentStyle" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入申请付款金额</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								
								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>大写金额:</label>
											<div class="col-md-8">
												<input type="text" name="orderNum" class="form-control"
													ng-model="saleOrder.orderNum" ng-show="input"
													style="width: 110%;" readonly />
												<div class="form-control-focus"></div>
												<input type="text" ng-model="orderSerial" ng-hide="true" name="orderSerial"/>
												<span class="help-block">请选择大写金额</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>申请币种 :</label>
											<div class="col-md-8">
												<select class="form-control" id="warehouseSerial"
													name="deliveryWarehouseSerial"
													ng-model="delivery.warehouseSerial"
													ng-change="selectAddress()" ng-show="input">
													<option value="">申请币种</option>
													<option value="人民币">人民币</option>
													<option value="美元">美元</option>
													<option value="欧元">欧元</option>
													<option value="日币">日币</option>
												</select>
												<div class="form-control-focus"></div>
												<span class="help-block">请选择申请币种</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>计划付款日期 :</label>
											<div class="col-md-8">
												<input type="text" name="paymentStyle" class="form-control"
													ng-model="paymentRecord.paymentStyle" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择计划付款日期</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								
								
								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>支付类型:</label>
											<div class="col-md-8">
												<select class="form-control" id="warehouseSerial"
													name="deliveryWarehouseSerial"
													ng-model="delivery.warehouseSerial"
													ng-change="selectAddress()" ng-show="input">
													<option value="">支付类型</option>
													<option value="预付款">预付款</option>
													<option value="中期款">中期款</option>
													<option value="全款">全款</option>
													<option value="尾款">尾款</option>
												</select>
												<div class="form-control-focus"></div>
												<input type="text" ng-model="orderSerial" ng-hide="true" name="orderSerial"/>
												<span class="help-block">请选择支付类型</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>支付节点 :</label>
											<div class="col-md-8">
												<select class="form-control" id="warehouseSerial"
													name="deliveryWarehouseSerial"
													ng-model="delivery.warehouseSerial"
													ng-change="selectAddress()" ng-show="input">
													<option value="">支付节点</option>
													<option value="合同签订">合同签订</option>
													<option value="提货前">提货前</option>
													<option value="到货后">到货后</option>
													<option value="验收后">验收后</option>
													<option value="质保期满">质保期满</option>
													<option value="销售出库">销售出库</option>
													<option value="收到委托方付款">收到委托方付款</option>
												</select>	
												<div class="form-control-focus"></div>
												<span class="help-block">请选择支付节点</span>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>节点单据号 :</label>
											<div class="col-md-8">
									        <input type="text" name="orderNum" class="form-control"
													ng-model="saleOrder.orderNum"/>
												<div class="form-control-focus"></div>
												<input type="text" ng-model="orderSerial" ng-hide="true" name="orderSerial"/>
												<span class="help-block">请输入节点单据号</span>
											</div>

										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								
								
								<!--/row-->
								<div class="row">
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>发票方式 :</label>
											<div class="col-md-8">
									<input type="radio"  ng-model="paymentRecord.billStyle" ng-checked="true" name="billStyle" value="1">先票后款
									<input type="radio"  ng-model="paymentRecord.billStyle" name="billStyle" value="0">先款后票
											</div>

										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>是否开票:</label>
											<div class="col-md-8">
									<input type="radio"   ng-model="paymentRecord.isBill" ng-checked="true" name="isBill" value="1">是
                        			<input type="radio"   ng-model="paymentRecord.isBill" name="isBill" value="0">否
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4">关联发票单号:</label>
											<div class="col-md-8">
												<input type="text"  ng-show="input" name="makeDate" ng-model="paymentRecord.invoiceSerial" class="form-control" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择关联发票单号</span>
											</div>

										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>开票日期:</label>
											<div class="col-md-8">
												<input type="text"  ng-show="input" name="makeDate" ng-model="paymentRecord.invoiceSerial" class="form-control" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择开票日期</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>申请日期:</label>
											<div class="col-md-8">
												<input type="text"  ng-show="input" name="makeDate" ng-model="paymentRecord.invoiceSerial" class="form-control" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择申请日期</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>申请部门:</label>
											<div class="col-md-8">
												<input type="text"  ng-show="input" name="makeDate" ng-model="paymentRecord.invoiceSerial" class="form-control" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择申请部门</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>申请人:</label>
											<div class="col-md-8">
												<input type="text"  name="applicant"
													ng-model="paymentRecord.applicant" class="form-control" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入申请人</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4">备注:</label>
											<div class="col-md-8">
												<input type="text" class="form-control"
													ng-model="paymentRecord.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->
								<!--/row-->
								<div class="row">
									<div class="col-md-4" ng-app="app">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>付款凭证:</label>
											<div class="col-md-8" >
												<div class="sp-page-content"
													ng-controller="addPhotoCtrl as ctl">
													<div class="sp-page-box">
														<div class="sp-page-boxcolumn span2">
															<span class="sp-upload"> <img
																class="sp-upload-photo" alt=""
																 ng-src="{{ctl.info.photo1}}" /> <input type="file" name="paymentVoucher" id="paymentVoucher"
																upload-img class="sp-upload-img" />
															</span>
														</div>
													</div>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>

					<!-- 物料信息 start-->
					<div class="portlet-title">
						<div class="caption">订单结算条款</div>
					</div>
					<div class="portlet-body form">
							<div class="table-scrollable">
								<table class="table table-bordered table-hover" id="sample_22">
								<thead>
										<tr>
										    <th colspan='4' style="text-align:center;">付款方:中航国际</th>
											<th colspan='4' style="text-align:center;">收款方:{{saleOrder.supplyComId}}</th>
											<th colspan='4' style="text-align:center;">本次应付:<font color="red">{{clauseSettlement.totalAmount|currency:'￥'}}</font></th>
										</tr>
									</thead>
									<thead>
										<tr>
										    <th></th>
											<th>支付类型</th>
											<th>支付节点</th>
											<th>账期</th>
											<th>支付比率%</th>
											<th>支付金额</th>
											<th>支付方式</th>
											<th>开票方式</th>
											<th>开票金额</th>
											<th>未开金额</th>
											<th>备注</th>
											<th>状态</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="_deliveryMateriel in deliveryMaterielE track by $index" repeat-done="repeatDone()">
										    <td><p class="form-control-static"><input type="radio" name="serialNumClause" value="{{_deliveryMateriel.serialNum}}" ng-click="selectClauseDetail(_deliveryMateriel.serialNum)"/> </p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.paymentType}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.deliveryNode}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.accountPeriod}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.deliveryRate}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.deliveryAmount|currency:'￥'}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.paymentMethod}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.billingMethod}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.billingAmount|currency:'￥'}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.unbilledAmount|currency:'￥'}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.remark}}</p></td>
											<td><p class="form-control-static">{{_deliveryMateriel.status}}</p></td>
										</tr>
									</tbody>
								</table>
							</div>
					</div>
					<!-- 供应商 end-->
				</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div id="basicMaterielInfo" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">选择采购订单</h4>
			</div>
			<div class="modal-body">
				<table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_21">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" id="example-select-all" type="checkbox"/></th>
	                            <th> 采购订单号 </th>
	                            <th> 供应方 </th>
	                            <th> 采购商品 </th>
	                            <th> 金额 </th>
	                            <th> 配送 </th>
	                            <th> 服务模式 </th>
	                            <th> 关联采购合同 </th>
	                            <th> 关联销售单 </th>
	                            <th> 下单日期 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"
					class="btn dark btn-outline">取消</button>
				<button type="button" ng-click="confirmSelect()" class="btn green">确定
				</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$('#playArrivalDate').datepicker({
		rtl: App.isRTL(),
		orientation: "left",
		autoclose: true,
		dateFormat:"yyyy-mm-dd",
		language: "zh-CN"
	});
	
	$('#playWarehouseDate').datepicker({
		rtl: App.isRTL(),
		orientation: "left",
		autoclose: true,
		dateFormat:"yyyy-mm-dd",
		language: "zh-CN"
	});
	
	$('#approvalDate').datepicker({
		rtl: App.isRTL(),
		orientation: "left",
		autoclose: true,
		dateFormat:"yyyy-mm-dd",
		language: "zh-CN"
	});
	
	$('#makeDate').datepicker({
		rtl: App.isRTL(),
		orientation: "left",
		autoclose: true,
		dateFormat:"yyyy-mm-dd",
		language: "zh-CN"
	});
	
	$('#deliverDate').datepicker({
		rtl: App.isRTL(),
		orientation: "left",
		autoclose: true,
		dateFormat:"yyyy-mm-dd",
		language: "zh-CN"
	});
	
	$('#takeDeliverDate').datepicker({
		rtl: App.isRTL(),
		orientation: "left",
		autoclose: true,
		dateFormat:"yyyy-mm-dd",
		language: "zh-CN"
	});
	
	</script>
