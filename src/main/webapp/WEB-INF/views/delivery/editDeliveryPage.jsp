 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<!-- <div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="delivery">发货</a> <i class="fa fa-angle-right"></i>
		</li>
		<li><a>编辑发货</a></li>
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
					<ul class="nav nav-tabs" >
						<li class="dropdown pull-right tabdrop" >
	                       <button type="submit" ng-click="editBasicInfo()" ng-show="input"
								class="btn blue  btn-outline  btn-sm">
								<i class="fa fa-save"></i> 保存
							</button>
							<button ng-click="goBack()" type="button"
								ng-hide="saleOrderInput" class="btn red  btn-outline  btn-sm">
								<i class="fa fa-undo"></i> 取消
							</button>
						</li>
						<li class="active bold">
	                  	<a data-target="#tab_1_1" data-toggle="tab">基本信息</a>
	              		</li>
						<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">发货信息</a>
						</li>
						<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">运输信息</a></li>
						<li class="bold"><a data-target="#tab_1_4" data-toggle="tab">收货信息</a></li>
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
											<label class="control-label bold">发货单号<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<input type="text" name="deliverNum" class="form-control"
													ng-model="delivery.deliverNum" ng-show="input">
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliverNum}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">发货类型<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<select class="form-control" id="deliverType"
													name="deliverType" ng-model="delivery.deliverType"
													ng-show="input">
													<option value="">发货类型</option>
													<option value="贸易发货">贸易发货</option>
												</select>
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliverType}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">销售订单号<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<div class="input-group" data-target="#basicMaterielInfo" data-toggle="modal" ng-click="selectMateriel()" onclick="return false;">
                                                    <input id="orderSerial"  name="orderNum" type="text" ng-show="input" class="form-control" ng-model="delivery.orderNum" readonly="readonly" >
                                                    <span class="input-group-btn" style="vertical-align: top;" ng-show="input">
                                                        <button class="btn default" type="button">
                                                            <i class="fa fa-search"></i>
                                                        </button>
                                                    </span>
                                                </div>
												<input type="text" ng-model="delivery.orderSerial" ng-hide="true" /> 

												<p class="form-control-static" ng-show="span">
													{{delivery.orderNum}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">供应商<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<input type="text" name=supplyComId class="form-control"
													ng-model="supplyComId" ng-show="input" readonly/>
												<p class="form-control-static" ng-show="span">
													{{supplyComId}}</p>
												<div class="form-control-focus"></div>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">发货方<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<input type="text" name="shipper" class="form-control"
													ng-model="shipper" ng-show="input" readonly/>
												<p class="form-control-static" ng-show="span">
													{{shipper}}</p>
												<div class="form-control-focus"></div>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">收货方<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<input type="text" class="form-control" name="receiver"
													ng-model="delivery.receiver" ng-show="input" />
												<p class="form-control-static" ng-show="span">
													{{delivery.receiver}}</p>
												<div class="form-control-focus"></div>
											</div>

										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">制单人<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<input type="text" class="form-control" name="maker"
													ng-model="delivery.maker" ng-show="input" />
												<p class="form-control-static" ng-show="span">
													{{delivery.maker}}</p>
												<div class="form-control-focus"></div>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">制单日期<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<input type="text" id="makeDate" ng-show="input"
													name="makeDate" data-date-format="yyyy-mm-dd"
													data-date-viewmode="years" size="16"
													ng-model="delivery.makeDate" class="form-control" readonly="readonly"/>
												<p class="form-control-static" ng-show="span">
													{{delivery.makeDate}}</p>
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
													ng-model="delivery.remark" ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.remark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
							</div>
						</div>

					</div>
                   <div class="tab-pane fade" id="tab_1_2">
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
											<label class="control-label bold">发货仓库<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<select class="form-control" id="warehouseSerial"
													name="deliveryWarehouseSerial"
													ng-model="delivery.warehouseSerial"
													ng-change="selectAddressEdit()" ng-show="input">
													<option value="">发货仓库</option>
													<option ng-repeat="item in warehouseList"
														value="{{item.serialNum}}">{{item.warehouseName}}</option>
												</select>
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliveryWarehouseName}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">仓库地址</label>
											<div class="">
												<input type="text" name="warehouseAddress"
													class="form-control" disabled="disabled" ng-model="delivery.deliveryAddress"
													ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliveryAddress}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">发货日期<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<input type="text" name="deliverDate" class="form-control"
													id="deliverDate" data-date-format="yyyy-mm-dd"
													ng-show="input" data-date-viewmode="years" size="16"
													ng-model="delivery.deliverDate" readonly="readonly"/>
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliverDate}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row">
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">包装件数</label>
											<div class="">
												<input type="text" class="form-control" name="packageCount"
													ng-model="delivery.packageCount" ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.packageCount}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">包装类型</label>
											<div class="">
												<select class="form-control" id="packageType"
													name="packageType" ng-model="delivery.packageType"
													ng-show="input">
													<option value="">包装类型</option>
													<option value="类型1">类型1</option>
													<option value="类型2">类型2</option>
													<option value="类型3">类型3</option>
												</select>
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.packageType}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">包装规格</label>
											<div class="">
												<select class="form-control"
													id="ordpackageSpecificationserType"
													name="packageSpecifications"
													ng-model="delivery.packageSpecifications" ng-show="input">
													<option value="">包装规格</option>
													<option value="规格1">规格1</option>
													<option value="规格2">规格2</option>
													<option value="规格3">规格3</option>
												</select>
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.packageSpecifications}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">物料重量</label>
											<div class="">
												<input type="text" class="form-control" ng-show="input"
													name="materielWeight" ng-model="delivery.materielWeight" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.materielWeight}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">服务费</label>
											<div class="">
												<input type="text" class="form-control" name="serviceMoney"
													ng-model="delivery.serviceMoney" ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.serviceMoney}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">发货人</label>
											<div class="">
												<input type="text" class="form-control" name="deliverer"
													ng-model="delivery.deliverer" ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliverer}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">联系电话</label>
											<div class="">
												<input type="text" class="form-control" name="contactNum"
													ng-model="delivery.contactNum" ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.contactNum}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">备注</label>
											<div class="">
												<input type="text" class="form-control" name="deliverRemark"
													ng-model="delivery.deliverRemark" ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static"></p>
												<p class="form-control-static" ng-show="span">
													{{delivery.deliverRemark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
							</div>
						</div>

					</div>
					
                   <div class="tab-pane fade" id="tab_1_3">
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
											<label class="control-label bold">运输方式</label>
											<div class="">
												<select class="form-control"
													id="transportType"
													name="transportType"
													ng-model="delivery.transportType" ng-show="input">
													<option value="水路运输">水路运输</option>
													<option value="铁路运输">铁路运输</option>
													<option value="公路运输">公路运输</option>
													<option value="铁路运输">铁路运输</option>
												</select>
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.transportType}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">运输方<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<input type="text" class="form-control" name="transport"
													ng-model="delivery.transport" ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.transport}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">运单号<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<input type="text" class="form-control" name="shipNumber"
													ng-model="delivery.shipNumber" ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.shipNumber}}</p>
											</div>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">联系人</label>
											<div class="">
												<input type="text" class="form-control" name="deliveryTransportContact"
													ng-show="input" ng-model="delivery.transportContact" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.transportContact}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">联系电话</label>
											<div class="">
												<input type="text" class="form-control" name="deliveryTransportContactNum"
													ng-show="input" ng-model="delivery.transportContactNum" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.transportContactNum}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">备注</label>
											<div class="">
												<input type="text" class="form-control" name="remark"
													ng-show="input" ng-model="delivery.transportRemark" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.transportRemark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
							</div>
						</div>
						
				   </div>
                   <div class="tab-pane fade" id="tab_1_4">
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
											<label class="control-label bold">收货仓库<span class="required" aria-required="true"> * </span></label>
											<div class="">
												<select class="form-control"
													id="takeDeliveryWarehouseSerial"
													name="takeDeliveryWarehouseSerial"
													ng-model="delivery.takeWarehouseSerial"
													ng-change="selectAddressTakeDeliveryEdit()" ng-show="input">
													<option value="">收货仓库</option>
													<option ng-repeat="item in warehouseList"
														value="{{item.serialNum}}">{{item.warehouseName}}</option>
													<option value="无">无</option>
												</select>
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.takeWarehouseName}}</p>
											</div>
										</div>
									</div>
									
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">仓库地址</label>
											<div class="">
												<input type="text" name="takeDeliveryWarehouseAddress"
													class="form-control"
													ng-model="delivery.takeAddress" disabled="disabled"
													ng-show="input" />

												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.takeAddress}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">收货日期</label>
											<div class="">
												<input type="text" name="takeDeliverDate"
													id="takeDeliverDate" data-date-format="yyyy-mm-dd"
													data-date-viewmode="years" size="16" class="form-control"
													ng-model="delivery.takeDeliverDate" ng-show="input" readonly="readonly"/>

												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.takeDeliverDate}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">收货人</label>
											<div class="">
												<input type="text" name="takeDeliveryReceiver"
													class="form-control" ng-model="delivery.takeDeliveryReceiver"
													ng-show="input" />

												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.takeDeliveryReceiver}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">联系电话</label>
											<div class="">
												<input type="text" name="takeDeliveryContactNum"
													class="form-control" ng-model="delivery.takeDeliveryContactNum"
													ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.takeDeliveryContactNum}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold">备注</label>
											<div class="">
												<input type="text" name="remark" class="form-control"
													ng-model="delivery.takeDeliveryRemark" ng-show="input" />
												<div class="form-control-focus"></div>
												<p class="form-control-static" ng-show="span">
													{{delivery.takeDeliveryRemark}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
							</div>
						</div>
					</div></div>
					
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
											repeat-done="repeatDone1()"><!-- repeat-done="repeatDone()" -->
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
											<td><input type="text" id="manufactureDate{{$index}}" name="manufactureDate{{$index}}"
												class="form-control form-control-inline input-medium date-picker"
												data-date-format="yyyy-mm-dd" data-date-viewmode="years"
												size="16" ng-hide="orderMaterielInput{{$index}}"
												ng-model="deliveryMateriel[$index].manufactureDate" />

												<p class="form-control-static"
													ng-show="orderMaterielShow{{$index}}">
													{{_deliveryMateriel.manufactureDate}}</p>
											</td>
											<td>
												<p class="form-control-static">{{_deliveryMateriel.amount}}
												</p>
											</td>
											<td><input type="text" name="deliverCount{{$index}}"
												class="form-control" ng-hide="orderMaterielInput{{$index}}"
												ng-model="deliveryMaterielE[$index].deliverCount">
												<p class="form-control-static"
													ng-show="orderMaterielShow{{$index}}">
													{{_deliveryMateriel.deliverCount}}</p>
											</td>
											<td><input type="text" name="deliverRemark{{$index}}"
												class="form-control" ng-hide="orderMaterielInput{{$index}}"
												ng-model="deliveryMaterielE[$index].remark">
												<p class="form-control-static"
													ng-show="orderMaterielShow{{$index}}">
													{{_deliveryMateriel.remark}}</p></td>
													
											<td><span ng-hide="orderMaterielInput{{$index}}">
													&nbsp;&nbsp;&nbsp;&nbsp; <a
													ng-click="editOrderMateriel(_deliveryMateriel,$index)"><i
														class="fa fa-save"></i></a> &nbsp;&nbsp;&nbsp; <a
													ng-click="cancelOrderMateriel(_deliveryMateriel,$index)"><i
														class="fa fa-undo"></i></a>
											</span> 
											</td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<th>合计</th>
											<th>{{materielCount}}</th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
										</tr>
									</tfoot>
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
 