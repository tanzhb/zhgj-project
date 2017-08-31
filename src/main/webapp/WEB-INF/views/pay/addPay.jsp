<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<h3 class="page-title">采购付款</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="delivery">付款</a> <i class="fa fa-angle-right"></i>
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
						<div class="caption">支付信息</div>
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
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>付款单号:</label>
											<div class="col-md-9">
												<input type="text" name="deliverNum" class="form-control"
													ng-model="delivery.deliverNum" ng-show="input">
												<div class="form-control-focus"></div>
												<span class="help-block">请输入发货单号</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliverNum}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>收款方:</label>
											<div class="col-md-7">
												<input type="text" name="orderNum" class="form-control"
													ng-model="saleOrder.orderNum" ng-show="input"
													style="width: 110%;" readonly />
												<div class="form-control-focus"></div>
												<input type="text" ng-model="orderSerial" ng-hide="true" />
												<span class="help-block">请输入销售订单号</span>

												<p class="form-control-static" ng-show="span">
													{{delivery.orderNum}}</p>
											</div>
											<div class="col-md-2" ng-show="input">
												<input type="text" ng-model="delivery.serialNum"
													ng-hide="true" /> <span class="input-inline-btn">
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
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>付款类型:</label>
											<div class="col-md-9">
												<input type="text" name="deliverNum" class="form-control"
													ng-model="delivery.deliverNum" ng-show="input">
												<div class="form-control-focus"></div>
												<span class="help-block">请输入发货单号</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliverNum}}</p>
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
											<div class="col-md-8">
												<input type="text" name=supplyComId class="form-control"
													ng-model="delivery.supplyComId" ng-show="input" />
												<p class="form-control-static" ng-show="span">
													{{delivery.supplyComId}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请选择供应商</span>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>支付节点:</label>
											<div class="col-md-9">
												<input type="text" name="shipper" class="form-control"
													ng-model="delivery.shipper" ng-show="input" />
												<p class="form-control-static" ng-show="span">
													{{delivery.shipper}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请输入发货方</span>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>支付单据号:</label>
											<div class="col-md-9">
												<input type="text" class="form-control" name="receiver"
													ng-model="delivery.receiver" ng-show="input" />
												<p class="form-control-static" ng-show="span">
													{{delivery.receiver}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请选择收货方</span>
											</div>

										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>支付比率:</label>
											<div class="col-md-9">
												<input type="text" class="form-control" name="maker"
													ng-model="delivery.maker" ng-show="input" />
												<p class="form-control-static" ng-show="span">
													{{delivery.maker}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请选择制单人</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>支付金额:</label>
											<div class="col-md-9">
												<input type="text" id="makeDate" ng-show="input"
													name="makeDate" data-date-format="yyyy-mm-dd"
													data-date-viewmode="years" size="16"
													ng-model="delivery.makeDate" class="form-control" />
												<p class="form-control-static" ng-show="span">
													{{delivery.makeDate}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请选择制单日期</span>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>账期:</label>
											<div class="col-md-9">
												<input type="text" class="form-control" name="approval"
													ng-model="delivery.approval" ng-show="input" />
												<p class="form-control-static" ng-show="span">
													{{delivery.approval}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请选择审批人</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>支付方式:</label>
											<div class="col-md-9">
												<input type="text" id="approvalDate"
													data-date-format="yyyy-mm-dd" name="approvalDate"
													ng-show="input" data-date-viewmode="years" size="16"
													ng-model="delivery.approvalDate" class="form-control" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择审批日期</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.approvalDate}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">开票方式:</label>
											<div class="col-md-9">
												<input type="text" class="form-control"
													ng-model="delivery.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.remark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">已开金额:</label>
											<div class="col-md-9">
												<input type="text" class="form-control"
													ng-model="delivery.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.remark}}</p>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->
								
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>未开金额:</label>
											<div class="col-md-9">
												<input type="text" id="approvalDate"
													data-date-format="yyyy-mm-dd" name="approvalDate"
													ng-show="input" data-date-viewmode="years" size="16"
													ng-model="delivery.approvalDate" class="form-control" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择审批日期</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.approvalDate}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">发票单据号:</label>
											<div class="col-md-9">
												<input type="text" class="form-control"
													ng-model="delivery.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.remark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4">计划付款日期:</label>
											<div class="col-md-8">
												<input type="text" class="form-control"
													ng-model="delivery.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.remark}}</p>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->
								
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>申请日期:</label>
											<div class="col-md-9">
												<input type="text" id="approvalDate"
													data-date-format="yyyy-mm-dd" name="approvalDate"
													ng-show="input" data-date-viewmode="years" size="16"
													ng-model="delivery.approvalDate" class="form-control" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择审批日期</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.approvalDate}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">申请人:</label>
											<div class="col-md-9">
												<input type="text" class="form-control"
													ng-model="delivery.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.remark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">审批日期:</label>
											<div class="col-md-9">
												<input type="text" class="form-control"
													ng-model="delivery.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.remark}}</p>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->
								
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>审批人:</label>
											<div class="col-md-9">
												<input type="text" id="approvalDate"
													data-date-format="yyyy-mm-dd" name="approvalDate"
													ng-show="input" data-date-viewmode="years" size="16"
													ng-model="delivery.approvalDate" class="form-control" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择审批日期</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.approvalDate}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">备注:</label>
											<div class="col-md-9">
												<input type="text" class="form-control"
													ng-model="delivery.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.remark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">状态:</label>
											<div class="col-md-9">
												<input type="text" class="form-control"
													ng-model="delivery.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.remark}}</p>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->
							</div>
						</div>


						<div class="portlet-title">
							<div class="caption">付款信息</div>
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
												class="required" aria-required="true"> * </span>实际付款日期:</label>
											<div class="col-md-8">
												<input type="text" name="materielCount" class="form-control"
													ng-model="delivery.materielCount" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择物料数</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.materielCount}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>付款金额:</label>
											<div class="col-md-9">
												<input type="text" name="warehouseAddress"
													class="form-control" readonly ng-model="warehouseAddress"
													ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请输入仓库地址</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliveryAddress}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>操作人:</label>
											<div class="col-md-9">
												<input type="text" name="deliverDate" class="form-control"
													id="deliverDate" data-date-format="yyyy-mm-dd"
													ng-show="input" data-date-viewmode="years" size="16"
													ng-model="delivery.deliverDate" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择发货日期</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliverDate}}</p>
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
												class="required" aria-required="true"> * </span>银行汇款编号:</label>
											<div class="col-md-8">
												<input type="text" name="materielCount" class="form-control"
													ng-model="delivery.materielCount" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择物料数</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.materielCount}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4" ng-app="app">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>付款凭证:</label>
											<div class="col-md-9" >
												<div class="sp-page-content"
													ng-controller="addPhotoCtrl as ctl">
													<div class="sp-page-box">
														<div class="sp-page-boxcolumn span2">
															<span class="sp-upload"> <img
																class="sp-upload-photo" alt=""
																 ng-src="{{ctl.info.photo1}}" /> <input type="file"
																upload-img class="sp-upload-img" />
															</span>
														</div>
													</div>
												</div>
											</div>

										</div>
									</div>
									
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>备注:</label>
											<div class="col-md-9">
												<input type="text" name="materielCount" class="form-control"
													ng-model="delivery.materielCount" ng-show="input" />
												<div class="form-control-focus"></div>
												<span class="help-block">请选择物料数</span>
												<p class="form-control-static" ng-show="span">
													{{delivery.materielCount}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								
								<!-- <div class="row" ng-app="app">
									<div class="sp-page-content" ng-controller="addPhotoCtrl as ctl">
										<div class="sp-page-box">
											<div class="sp-page-boxcolumn span1">付款凭证：</div>
											<div class="sp-page-boxcolumn span2">
												<span class="sp-upload"> <img class="sp-upload-photo"
													alt="" ng-src="{{ctl.info.photo1}}" /> <input type="file"
													upload-img class="sp-upload-img" />
												</span>
											</div>
										</div>
									</div>
								</div> -->
								
							</div>
						</div>

					<!-- 物料信息 start-->
					<div class="portlet-title">
						<div class="caption">物料信息</div>
					</div>
					<div class="portlet-body form">
							<div class="table-scrollable">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>物料编号</th>
											<th>物料名称</th>
											<th>规格型号</th>
											<th>单位</th>
											<th>批次号</th>
											<th>生产日期</th>
											<th>订单数量</th>
											<th>发货数量</th>
											<th>备注</th>
											<th style="width: 100px;">操作</th>
										</tr>
									</thead>
									<tbody>
										<tr
											ng-repeat="_deliveryMateriel in deliveryMaterielE track by $index"
											ng-mouseover="showOperation('deliveryMateriel',$index)"
											ng-mouseleave="hideOperation('deliveryMateriel',$index)"
											repeat-done="repeatDone()">
											<td>
												<!--  <span ><a href="javascript:;" ng-click="addMateriel('single',$index)">{{_orderMateriel.materiel.materielNum}}</a></span> -->
												<p class="form-control-static">
													{{_deliveryMateriel.materielNum}}</p>
											</td>
											<td>
												<p class="form-control-static">
													{{_deliveryMateriel.materielName}}</p>
											</td>
											<td>
												<p class="form-control-static">
													{{_deliveryMateriel.specifications}}</p>
											</td>
											<td>
												<p class="form-control-static">
													{{_deliveryMateriel.unit}}</p>
											</td>
											<td><input type="text" name="batchNum"
												class="form-control" ng-hide="orderMaterielInput{{$index}}"
												ng-model="deliveryMaterielE[$index].batchNum">
												<p class="form-control-static"
													ng-show="orderMaterielShow{{$index}}">
													{{_deliveryMateriel.batchNum}}</p></td>
											<td><input type="text" name="manufactureDate{{$index}}"
												class="form-control form-control-inline input-medium date-picker"
												data-date-format="yyyy-mm-dd" data-date-viewmode="years"
												size="16" ng-hide="orderMaterielInput{{$index}}"
												ng-model="deliveryMaterielE[$index].manufactureDate" />

												<p class="form-control-static"
													ng-show="orderMaterielShow{{$index}}">
													{{_deliveryMateriel.manufactureDate}}</p></td>
											<td>
												<!-- <input type="text"  name="amount{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="deliveryMateriel[$index].amount">
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> </p> -->
												<p class="form-control-static">{{_deliveryMateriel.amount}}
												</p>
											</td>
											<td><input type="text" name="deliverCount{{$index}}"
												class="form-control" ng-hide="orderMaterielInput{{$index}}"
												ng-model="deliveryMaterielE[$index].deliverCount">
												<p class="form-control-static"
													ng-show="orderMaterielShow{{$index}}">
													{{_deliveryMateriel.deliverCount}}</p></td>
											<td><input type="text" name="deliverRemark{{$index}}"
												class="form-control" ng-hide="orderMaterielInput{{$index}}"
												ng-model="deliveryMaterielE[$index].remark">
												<p class="form-control-static"
													ng-show="orderMaterielShow{{$index}}">
													{{_deliveryMateriel.remark}}</p></td>
											<td><span ng-hide="orderMaterielInput{{$index}}">
													&nbsp;&nbsp;&nbsp;&nbsp; <a
													ng-click="saveOrderMateriel(_deliveryMateriel,$index)"><i
														class="fa fa-save"></i></a> <!-- &nbsp;&nbsp;&nbsp; <a
													ng-click="cancelOrderMateriel(_orderMateriel,$index)"><i
														class="fa fa-undo"></i></a> -->
											</span> </td>
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
				<h4 class="modal-title">选择销售订单</h4>
			</div>
			<div class="modal-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_21">
					<thead>
						<tr>
							<th style="text-align: center"></th>
							<th>销售订单号</th>
							<th>采购方</th>
							<th>销售商品</th>
							<th>金额</th>
							<th>配送</th>
							<th>服务模式</th>
							<th>关联销售合同</th>
							<th>关联采购单</th>
							<th>下单日期</th>
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
