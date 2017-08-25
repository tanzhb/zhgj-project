<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<h3 class="page-title">发货信息</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="delivery">发货</a> <i class="fa fa-angle-right"></i>
		</li>
		<li><a>发货详情</a></li>
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
						<div class="caption">基本信息</div>
						<div class="tools" id="noprintdiv">
						
							<button type="button" ng-click="goDelivery(deliveryDetail.serialNum)" ng-if="deliveryDetail.status=='0'"
								class="btn blue  btn-outline  btn-sm">
								<i class="fa fa-save"></i> 确认发货
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
												class="required" aria-required="true"> * </span>发货单号:</label>
											<div class="col-md-9">
												<div class="form-control-focus"></div>
												<span class="help-block">请输入发货单号</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.deliverNum}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>销售订单号:</label>
											<div class="col-md-9">
												<div class="form-control-focus"></div>
												<input type="text" ng-model="orderSerial" ng-hide="true" />
												<span class="help-block">请输入销售订单号</span>

												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.orderNum}}</p>
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
												class="required" aria-required="true"> * </span>供应商:</label>
											<div class="col-md-9">
												<!-- <input type="text" name=supplyComId class="form-control"
													ng-model="delivery.supplyComId" ng-show="input" /> -->
												<p class="form-control-static" ng-show="input" >
													{{deliveryDetail.supplyComId}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请选择供应商</span>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>发货方:</label>
											<div class="col-md-9">
												<!-- <input type="text" name="shipper" class="form-control"
													ng-model="delivery.shipper" ng-show="input" /> -->
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.shipper}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请输入发货方</span>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>收货方:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="receiver"
													ng-model="delivery.receiver" ng-show="input" /> -->
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.receiver}}</p>
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
												class="required" aria-required="true"> * </span>制单人:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="maker"
													ng-model="delivery.maker" ng-show="input" /> -->
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.maker}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请选择制单人</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>制单日期:</label>
											<div class="col-md-9">
												<!-- <input type="text" id="makeDate" ng-show="input"
													name="makeDate" data-date-format="yyyy-mm-dd"
													data-date-viewmode="years" size="16"
													ng-model="delivery.makeDate" class="form-control" /> -->
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.makeDate}}</p>
												<div class="form-control-focus"></div>
												<span class="help-block">请选择制单日期</span>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>审批人:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="approval"
													ng-model="delivery.approval" ng-show="input" /> -->
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.approval}}</p>
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
												class="required" aria-required="true"> * </span>审批日期:</label>
											<div class="col-md-9">
												<!-- <input type="text" id="approvalDate"
													data-date-format="yyyy-mm-dd" name="approvalDate"
													ng-show="input" data-date-viewmode="years" size="16"
													ng-model="delivery.approvalDate" class="form-control" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请选择审批日期</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.approvalDate}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">备注:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control"
													ng-model="delivery.remark" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.remark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
							</div>
						</div>


						<div class="portlet-title">
							<div class="caption">发货信息</div>
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
												class="required" aria-required="true"> * </span>发货仓库:</label>
											<div class="col-md-9">
												<!-- <select class="form-control" id="warehouseSerial"
													name="deliveryWarehouseSerial"
													ng-model="delivery.warehouseSerial"
													ng-change="selectAddress()" ng-show="input">
													<option value="">发货仓库</option>
													<option ng-repeat="item in warehouseList"
														value="{{item.serialNum}}">{{item.warehouseName}}</option>
												</select> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请选择发货仓库</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.deliveryWarehouseName}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>仓库地址:</label>
											<div class="col-md-9">
												<!-- <input type="text" name="warehouseAddress"
													class="form-control" readonly ng-model="warehouseAddress"
													ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入仓库地址</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.deliveryAddress}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>发货日期:</label>
											<div class="col-md-9">
												<!-- <input type="text" name="deliverDate" class="form-control"
													id="deliverDate" data-date-format="yyyy-mm-dd"
													ng-show="input" data-date-viewmode="years" size="16"
													ng-model="delivery.deliverDate" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请选择发货日期</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.deliverDate}}</p>
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
												class="required" aria-required="true"> * </span>物料数:</label>
											<div class="col-md-9">
												<!-- <input type="text" name="materielCount" class="form-control"
													ng-model="delivery.materielCount" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请选择物料数</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.materielCount}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>包装件数:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="packageCount"
													ng-model="delivery.packageCount" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入包装件数</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.packageCount}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>包装类型:</label>
											<div class="col-md-9">
												<!-- <select class="form-control" id="packageType"
													name="packageType" ng-model="delivery.packageType"
													ng-show="input">
													<option value="">包装类型</option>
													<option value="类型1">类型1</option>
													<option value="类型2">类型2</option>
													<option value="类型3">类型3</option>
												</select> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请选择包装类型</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.packageType}}</p>
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
												class="required" aria-required="true"> * </span>包装规格:</label>
											<div class="col-md-9">
												<!-- <select class="form-control"
													id="ordpackageSpecificationserType"
													name="packageSpecifications"
													ng-model="delivery.packageSpecifications" ng-show="input">
													<option value="">包装规格</option>
													<option value="规格1">规格1</option>
													<option value="规格2">规格2</option>
													<option value="规格3">规格3</option>
												</select> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请选择包装规格</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.packageSpecifications}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>物料重量:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" ng-show="input"
													name="materielWeight" ng-model="delivery.materielWeight" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入物料重量</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.materielWeight}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>服务费:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="serviceMoney"
													ng-model="delivery.serviceMoney" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入服务费</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.serviceMoney}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>发货人:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="deliverer"
													ng-model="delivery.deliverer" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入发货人</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.deliverer}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>联系电话:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="contactNum"
													ng-model="delivery.contactNum" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入联系电话</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.contactNum}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">备注:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="deliverRemark"
													ng-model="delivery.deliverRemark" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static"></p>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.deliverRemark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
							</div>
						</div>


						<div class="portlet-title">
							<div class="caption">运输信息</div>
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
												class="required" aria-required="true"> * </span>运输方式:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="transportType"
													ng-model="deliveryTransport.transportType" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入运输方式</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.transportType}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>运输方:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="transport"
													ng-model="deliveryTransport.transport" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入运输方</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.transport}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>港口:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="port"
													ng-model="deliveryTransport.port" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入港口</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.port}}</p>
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
												class="required" aria-required="true"> * </span>船号:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="shipNumber"
													ng-model="deliveryTransport.shipNumber" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入船号</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.shipNumber}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>预计到港日期:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control"
													data-date-format="yyyy-mm-dd" ng-show="input"
													data-date-viewmode="years" size="16" name="playArrivalDate"
													id="playArrivalDate"
													ng-model="deliveryTransport.playArrivalDate" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请选择预计到港日期</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.playArrivalDate}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>预计到库日期:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control"
													data-date-format="yyyy-mm-dd" data-date-viewmode="years"
													ng-show="input" size="16" name="playWarehouseDate"
													id="playWarehouseDate"
													ng-model="deliveryTransport.playWarehouseDate" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请选择预计到库日期</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.playWarehouseDate}}</p>
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
												class="required" aria-required="true"> * </span>联系人:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="deliveryTransportContact"
													ng-show="input" ng-model="deliveryTransport.contact" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入联系人</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.transportContact}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>联系电话:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="deliveryTransportContactNum"
													ng-show="input" ng-model="deliveryTransport.contactNum" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入联系电话</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.transportContactNum}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">备注:</label>
											<div class="col-md-9">
												<!-- <input type="text" class="form-control" name="remark"
													ng-show="input" ng-model="deliveryTransport.remark" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.transportRemark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
							</div>
						</div>
						<div class="portlet-title">
							<div class="caption">收货信息</div>
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
												class="required" aria-required="true"> * </span>收货仓库:</label>
											<div class="col-md-9">
												<!-- <select class="form-control"
													id="takeDeliveryWarehouseSerial"
													name="takeDeliveryWarehouseSerial"
													ng-model="takeDelivery.warehouseSerial"
													ng-change="selectAddressTakeDelivery()" ng-show="input">
													<option value="">收货仓库</option>
													<option ng-repeat="item in warehouseList"
														value="{{item.serialNum}}">{{item.warehouseName}}</option>
												</select> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请选择收货仓库</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.takeWarehouseName}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>仓库地址:</label>
											<div class="col-md-9">
												<!-- <input type="text" name="takeDeliveryWarehouseAddress"
													class="form-control"
													ng-model="takeDeliveryWarehouseAddress" readonly
													ng-show="input" /> -->

												<div class="form-control-focus"></div>
												<span class="help-block">请输入仓库地址</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.takeAddress}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>收货日期:</label>
											<div class="col-md-9">
												<!-- <input type="text" name="takeDeliverDate"
													id="takeDeliverDate" data-date-format="yyyy-mm-dd"
													data-date-viewmode="years" size="16" class="form-control"
													ng-model="takeDelivery.takeDeliverDate" ng-show="input" /> -->

												<div class="form-control-focus"></div>
												<span class="help-block">请选择收货日期</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.takeDeliverDate}}</p>
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
												class="required" aria-required="true"> * </span>收货人:</label>
											<div class="col-md-9">
												<!-- <input type="text" name="takeDeliveryReceiver"
													class="form-control" ng-model="takeDelivery.receiver"
													ng-show="input" /> -->

												<div class="form-control-focus"></div>
												<span class="help-block">请输入收货人</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.takeDeliveryReceiver}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3"><span
												class="required" aria-required="true"> * </span>联系电话:</label>
											<div class="col-md-9">
												<!-- <input type="text" name="takeDeliveryContactNum"
													class="form-control" ng-model="takeDelivery.contactNum"
													ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入联系电话</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.takeDeliveryContactNum}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-3">备注:</label>
											<div class="col-md-9">
												<!-- <input type="text" name="remark" class="form-control"
													ng-model="takeDelivery.remark" ng-show="input" /> -->
												<div class="form-control-focus"></div>
												<span class="help-block">请输入备注</span>
												<p class="form-control-static" ng-show="input">
													{{deliveryDetail.takeDeliveryRemark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
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
											
											<th>出库数量</th>
											<th>未出数量</th>
											<th>仓库</th>
											<th>库位</th>
											<th>备注</th>
											
											<th>合格数量</th>
											<th>不合格数量</th>
											<th>备注</th>
											<th>实收数量</th>
											<th>拒收数量</th>
											<th>备注</th>
											<th>状态</th>
										</tr>
									</thead>
									<tbody>
										<tr
											ng-repeat="_deliveryMateriel in deliveryDetailMateriel track by $index"
											ng-mouseover="showOperation('deliveryMateriel',$index)"
											ng-mouseleave="hideOperation('deliveryMateriel',$index)"
											repeat-done="repeatDone()">
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.materielNum}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.materielName}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.specifications}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.unit}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.batchNum}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.manufactureDate}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.amount}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.deliverCount}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.remark}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.stockCount}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.unstockCount}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.warehouseName}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.positionName}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.stockRemark}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.qualifiedCount}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.unqualifiedCount}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.checkRemark}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.acceptCount}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.refuseCount}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.takeRemark}}</p></td>
											<td style="white-space: nowrap;"><p class="form-control-static">{{_deliveryMateriel.status}}</p></td>
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