<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.btn-default.active, .btn-default:active{
color: #32c5d2;
background-color: #fff;
border-color: #32c5d2;
}
.btn-default.active.focus, .btn-default.active:hover{
color: #32c5d2;
background-color: #fff;
border-color: #32c5d2;
}
.btn-default-margin{
margin-right: 20px;
}



</style>
<!-- 采购订单基本信息 START -->
<ul class="nav nav-tabs">
					
						<li class="active bold"><a data-target="#tab_1_1"
							data-toggle="tab">发货信息</a></li>
						<!-- <li class="bold"><a data-target="#tab_1_3" data-toggle="tab">收货信息</a>
						</li> -->
						<!-- <li class="bold"><a data-target="#tab_1_3" data-toggle="tab">运输信息</a></li>-->
						<li class="bold"><a data-target="#tab_1_4" data-toggle="tab">物料信息</a></li>
						<li class="dropdown pull-right tabdrop">
							<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
						</li>						
					</ul>
<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
            	<form action="#" id="form_sample_deliverInfo" class="">
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
								
									<div class="form-body">
										<div class="alert alert-danger display-hide">
											<button class="close" data-close="alert"></button>
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货单号<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" name="deliverNum" class="form-control"
															ng-model="delivery.deliverNum" ng-hide="inputDeliveryInfo" >
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.deliverNum}}
															</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货类型<!--<span class="required" aria-required="true"> * </span>--></label>
													<div class="">
														<select class="form-control" id="deliverType"
															name="deliverType" ng-model="delivery.deliverType"
															ng-change="changeTakeDeliveryMode(delivery.deliverType)"
															ng-hide="inputDeliveryInfo" ><!--ng-init="delivery.deliverType='贸易发货'"  -->
															<option   value=""></option>
															<option   value="贸易发货">贸易发货</option>
															<option value="其他发货">其他发货</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.deliverType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group" ng-if="!otherMode">
													<label class="control-label bold">销售订单号<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<div class="input-group" data-target="#basicMaterielInfo"
															data-toggle="modal" ng-click="selectMateriel()"
															onclick="return false;">
															<input id="orderSerial" name="orderSerial" type="text"
																ng-hide="inputDeliveryInfo" class="form-control"
																ng-model="saleOrder.orderNum" >
															<span ng-hide="inputDeliveryInfo" class="input-group-btn"
																style="vertical-align: top;">
																<button class="btn default" type="button">
																	<i class="fa fa-search"></i>
																</button>
															</span>
														</div>
														<input type="hidden" ng-model="orderSerial"
															name="orderSerial" ng-hide="span" />

														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.orderNum}}</p>
													</div>
												</div>
												<div class="form-group" ng-if="otherMode">
													<label class="control-label bold">关联单据号</label>
													<div class="">
														<input type="text" class="form-control"
															ng-model="delivery.orderSerial" ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.orderNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货方<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" name="shipper" class="form-control"
															ng-model="shipper" ng-hide="inputDeliveryInfo" />
														<p class="form-control-static" ng-hide="span">
															{{shipper}}</p>
														<div class="form-control-focus"></div>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货方<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" class="form-control" name="receiver"
															ng-model="delivery.receiver" ng-hide="inputDeliveryInfo" />
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.receiver}}</p>
														<div class="form-control-focus"></div>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="handWay">交付方式</label>
                                                    <div class=""><!-- 包装类型暂时先做为交付方式 -->
                                                        <select class="form-control" id="packageType"   ng-model="deliver.packageType" ng-hide="inputDeliveryInfo"    ng-init="deliver.packageType='配送'">
	                                                    	<option value=""></option>
	                                                    	<option value="配送">配送</option>
	                                                    	<option value="自提">自提</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliver.packageType}}</p>
                                                    </div>
                                            </div>
										</div>
											<!--/span-->
										</div>
										<!--/row-->
												<div class="row">
									<div class="col-md-4">
									<div class="form-group">
                                                    <label class="control-label bold" for="">发货数量 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverer"  ng-model="totalDeliveryCount" ng-hide="inputDeliveryInfo"  readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{totalDeliveryCount}}</p>
                                                    </div>
                                            </div>
                                            </div>
                                            <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverer">联系人 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverer"  ng-model="deliver.deliverer" ng-hide="inputDeliveryInfo" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliveryDetail.deliverer}}</p>
                                                    </div>
                                            </div>
										</div>
									<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" class="form-control" name="contactNum"
															ng-model="delivery.contactNum" ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.contactNum}}</p>
													</div>
												</div>
											</div>
										<!--/span-->
									</div>
									<!--/row-->
										<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" > 备注</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" ng-model="deliver.remark" ng-hide="inputDeliveryInfo" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliveryDetail.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="maker">制单人 <!-- <span class="required"> * </span> --></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.maker" ng-hide="inputDeliveryInfo" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliveryDetail.maker}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="createTime">制单日期 </label>
                                                    <div class="">
                                 <input type="text" class="form-control date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="createTime"  ng-model="deliver.createTime" ng-hide="inputDeliveryInfo" readonly="readonly">
                                                      <!--   <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.createTime" ng-hide="deliverAdd" > -->
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliveryDetail.approvalDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									</div>
								</div>

								<div class="portlet-body form"  style="border-top:1px solid #dddddd;">
									<!-- BEGIN FORM-->
									<div class="form-body">
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货仓库<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
													<div  ng-hide="span">
														<select class="form-control"  id="deliverWarehouse"  data-live-search="true" data-size="" 
															name="deliveryWarehouseSerial"
															ng-model="delivery.warehouseSerial"
															ng-change="selectAddress()" ng-hide="inputDeliveryInfo">
															<option ng-repeat="item in warehouseList"
																value="{{item.serialNum}}">{{item.warehouseName}}</option>
															<option value=""></option>
														</select>
														</div>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.deliveryWarehouseName}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货地址</label>
													<div class="">
														<input type="text" name="warehouseAddress"
															class="form-control" ng-model="warehouseAddress"
															ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.deliveryAddress}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverDate"><span ng-if="deliver.packageType=='配送'">发货</span><span ng-if="deliver.packageType=='自提'">提货</span>日期 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control  date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="deliverDate"  name="deliverDate" ng-model="deliver.deliverDate" ng-hide="inputDeliveryInfo" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliveryDetail.deliverDate}}</p>
                                                    </div>
                                            </div>
										</div>
											<!--/span-->
										</div>
										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货仓库<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
													<div  ng-hide="span">
														<select class="form-control"  data-live-search="true" data-size=""   id="takeDeliverWarehouse"
															name="warehouseSerial"
															ng-model="takeDelivery.warehouseSerial"
															ng-change="selectAddressTakeDelivery()" ng-hide="inputDeliveryInfo">
															<option ng-repeat="item in warehouseList"
																value="{{item.serialNum}}">{{item.warehouseName}}</option>
															<option value=""></option>
														</select>
														</div>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.takeWarehouseName}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货地址</label>
													<div class="">
														<input type="text" name="takeDeliveryWarehouseAddress"
															class="form-control"
															ng-model="takeDeliveryWarehouseAddress" ng-hide="inputDeliveryInfo" />

														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.takeAddress}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">预计到货日期</label>
													<div class="">
														<input type="text" name="takeDeliverDate"
															id="takeDeliverDate" data-date-format="yyyy-mm-dd"
															data-date-viewmode="years" size="16" class="form-control"
															ng-model="takeDelivery.takeDeliverDate" ng-hide="inputDeliveryInfo"
															readonly="readonly" />

														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
																{{deliveryDetail.takeDeliverDate}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<!--/row-->
										<!-- <div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">包装件数</label>
													<div class="">
														<input type="text" class="form-control"
															name="packageCount" ng-model="delivery.packageCount"
															ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.packageCount}}</p>
													</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">包装类型</label>
													<div class="">
														<select class="form-control" id="packageType"
															name="packageType" ng-model="delivery.packageType"
															ng-show="inputDeliveryInfo">
															<option value="">包装类型</option>
															<option value="类型1">类型1</option>
															<option value="类型2">类型2</option>
															<option value="类型3">类型3</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.packageType}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
										/row
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">包装规格</label>
													<div class="">
														<select class="form-control"
															id="ordpackageSpecificationserType"
															name="packageSpecifications"
															ng-model="delivery.packageSpecifications" ng-hide="inputDeliveryInfo"    >
															<option value="">包装规格</option>
															<option value="原厂包装">原厂包装</option>
															<option value="标准料箱">标准料箱</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.packageSpecifications}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">物料重量</label>
													<div class="">
														<input type="text" class="form-control" ng-show="inputDeliveryInfo"
															name="materielWeight" ng-model="delivery.materielWeight" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.materielWeight}}</p>
													</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">服务费</label>
													<div class="">
														<input type="text" class="form-control"
															name="serviceMoney" ng-model="delivery.serviceMoney"
															ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.serviceMoney}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货人</label>
													<div class="">
														<input type="text" class="form-control" name="deliverer"
															ng-model="delivery.deliverer" ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.deliverer}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" class="form-control" name="contactNum"
															ng-model="delivery.contactNum" ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.contactNum}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" class="form-control"
															name="deliverRemark" ng-model="delivery.deliverRemark"
															ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static"></p>
														<p class="form-control-static" ng-hide="span">
															{{delivery.deliverRemark}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
										/row -->
											<div class="row"     style="border-top:1px solid #dddddd;padding-top: 20px;"  ng-show="showTransport">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运输方式</label>
													<div class="">
														<!-- <input type="text" class="form-control" name="transportType"
													ng-model="deliveryTransport.transportType" ng-show="input" /> -->
														<select class="form-control" id="transportType"
															name="transportType" ng-model="transportType"
															ng-hide="inputDeliveryInfo">
															<option value="水路运输">水路运输</option>
															<option value="铁路运输">铁路运输</option>
															<option value="公路运输">公路运输</option>
															<option value="铁路运输">铁路运输</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.transportType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运输方<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" class="form-control" name="transport"  
															ng-model="deliveryTransport.transport" ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.transport}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运单号 <!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<input type="text" class="form-control" name="shipNumber"
															ng-model="deliveryTransport.shipNumber" ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.shipNumber}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<div class="row"  ng-show="showTransport">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系人</label>
													<div class="">
														<input type="text" class="form-control"
															name="deliveryTransportContact" ng-hide="inputDeliveryInfo"
															ng-model="deliveryTransport.contact" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.transportContact}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" class="form-control"
															name="deliveryTransportContactNum" ng-hide="inputDeliveryInfo"
															ng-model="deliveryTransport.contactNum" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.transportContactNum}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" class="form-control" name="remark"
															ng-hide="inputDeliveryInfo"  ng-model="deliveryTransport.remark" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliveryDetail.transportRemark}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										
										<!-- <div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货人</label>
													<div class="">
														<input type="text" name="takeDeliveryReceiver"
															class="form-control" ng-model="takeDelivery.receiver"
															ng-hide="inputDeliveryInfo" />

														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.takeDeliveryReceiver}}</p>
													</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" name="takeDeliveryContactNum"
															class="form-control" ng-model="takeDelivery.contactNum"
															ng-hide="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.takeDeliveryContactNum}}</p>
													</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" name="remark" class="form-control"
															ng-model="takeDelivery.remark"    ng-hide="inputDeliveryInfo"  />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.takeDeliveryRemark}}</p>
													</div>
												</div>
											</div>
											/span
										</div> -->
									</div>
								</div>
								</form>

							</div>
					<!-- 		<div class="tab-pane fade" id="tab_1_3">
									 <div class="portlet-title" style="min-height: 48px;">
               <div class="tools" style="float:right">
                  <button ng-click="saveTakeDeliveryInfo()" type="button"    ng-show="inputTakeDeliveryInfo"  class="btn blue  btn-circle  btn-sm">
                  		<i class="fa fa-edit"></i> 保存 </button>
                  		<button ng-click="editTakeDeliveryInfo()" type="button"   ng-hide="inputTakeDeliveryInfo"   class="btn purple  btn-circle  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button>
                  <button type="submit" ng-click="goBack()"  ng-show="inputTakeDeliveryInfo"  class="btn green  btn-circle  btn-sm">
                 		<i class="fa fa-save"></i>取消</button>
                 		
                </div>
            </div>
            <form id="form_sample_takeDeliveryInfo" class="">
								<div class="portlet-body form">
									BEGIN FORM
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
														<input type="text" class="form-control" name="transportType"
													ng-model="deliveryTransport.transportType" ng-show="input" />
														<select class="form-control" id="transportType"
															name="transportType" ng-model="transportType"
															ng-show="inputTakeDeliveryInfo">
															<option value="水路运输">水路运输</option>
															<option value="铁路运输">铁路运输</option>
															<option value="公路运输">公路运输</option>
															<option value="铁路运输">铁路运输</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.transportType}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运输方<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="transport"  
															ng-model="deliveryTransport.transport" ng-show="inputTakeDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="spancontrol">
															{{deliveryTransport.transport}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运单号 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="shipNumber"
															ng-model="deliveryTransport.shipNumber" ng-show="inputTakeDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="spancontrol">
															{{deliveryTransport.shipNumber}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系人</label>
													<div class="">
														<input type="text" class="form-control"
															name="deliveryTransportContact" ng-show="inputTakeDeliveryInfo"
															ng-model="deliveryTransport.contact" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.transportContact}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" class="form-control"
															name="deliveryTransportContactNum" ng-show="inputTakeDeliveryInfo"
															ng-model="deliveryTransport.contactNum" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.transportContactNum}}</p>
													</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" class="form-control" name="remark"
															ng-show="inputTakeDeliveryInfo"  ng-model="deliveryTransport.remark" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.transportRemark}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货仓库<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control"
															name="warehouseSerial"
															ng-model="takeDelivery.warehouseSerial"
															ng-change="selectAddressTakeDelivery()" ng-show="inputTakeDeliveryInfo">
															<option ng-repeat="item in warehouseList"
																value="{{item.serialNum}}">{{item.warehouseName}}</option>
															<option value=""></option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.takeWarehouseName}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">仓库地址</label>
													<div class="">
														<input type="text" name="takeDeliveryWarehouseAddress"
															class="form-control"
															ng-model="takeDeliveryWarehouseAddress" ng-show="inputTakeDeliveryInfo" />

														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.takeAddress}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">预计到货日期</label>
													<div class="">
														<input type="text" name="takeDeliverDate"
															id="takeDeliverDate" data-date-format="yyyy-mm-dd"
															data-date-viewmode="years" size="16" class="form-control"
															ng-model="takeDelivery.takeDeliverDate" ng-show="inputTakeDeliveryInfo"
															readonly="readonly" />

														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.takeDeliverDate}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
										/row
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货人</label>
													<div class="">
														<input type="text" name="takeDeliveryReceiver"
															class="form-control" ng-model="takeDelivery.receiver"
															ng-show="inputTakeDeliveryInfo" />

														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.takeDeliveryReceiver}}</p>
													</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" name="takeDeliveryContactNum"
															class="form-control" ng-model="takeDelivery.contactNum"
															ng-show="inputTakeDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.takeDeliveryContactNum}}</p>
													</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" name="remark" class="form-control"
															ng-model="takeDelivery.remark"    ng-show="inputTakeDeliveryInfo"  />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{delivery.takeDeliveryRemark}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
									</div></div>
								</form>
							</div> -->
							
							<!-- <div class="tab-pane fade" id="tab_1_4">
							 <div class="portlet-title" style="min-height: 48px;">
               <div class="tools" style="float:right">
                </div>
            </div>
								物料信息 start
								<div class="portlet-title">
									<div class="caption">物料信息</div>
									<div class="actions" ng-if="otherMode">
										<button class="btn blue btn-sm btn-circle"
											ng-click="addMateriel()" onclick="return false;">
											<i class="fa fa-plus"></i> 添加物料
										</button>
									</div>
								</div>
								<div class="portlet-body form">
									<form action="#" id="form_sample_3" class="">
										<div class="table-scrollable">
											<table
												class="table table-striped table-bordered table-advance table-hover">
												<thead>
													<tr>
														<th>物料编号</th>
														<th>物料名称</th>
														<th>规格型号</th>
														<th>单位</th>
														<th>附件</th>
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
															 <span ><a href="javascript:;" ng-click="addMateriel('single',$index)">{{_orderMateriel.materiel.materielNum}}</a></span>
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
														<td class="form-group">
															<p id="batchNumReal{{$index}}" ng-hide="true"></p>

															
														</td>
														<td>
															<p class="form-control-static">{{_deliveryMateriel.amount}}
															</p>
														</td>
														<td class="form-group">
															<div class="form-control-focus"></div>
															<p class="form-control-static"
																>
																{{_deliveryMateriel.deliverCount}}</p></td>
														<td>
															<p class="form-control-static"
																>
																{{_deliveryMateriel.remark}}</p></td>
														<td></td>
													</tr>

												</tbody>

												<tfoot>
													<tr>
														<td>合计</td>
														<td>{{materielCount}}</td>
														<td></td>
														<td></td>
														<td></td>
														<td>{{totalOrderCount}}</td>
														<td>{{calcTotalDeliveryCount()}} {{totalDeliveryCount}}</td>
														<td></td>
														<td></td>
													</tr>
												</tfoot>
											</table>
										</div>
									</form>
								</div>
								供应商 end
							</div> -->
							<div class="tab-pane fade" id="tab_1_4">
		<div class="portlet-body">
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="dataTables_length" id="sample_5_length">
						<label>每页显示 <select name="sample_5_length"
							aria-controls="sample_5" ng-model="pageSize" ng-change="createDispalyList()"
							class="form-control input-sm input-xsmall input-inline">
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="99999">All</option>
							</select> 条数据
						</label>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div id="sample_5_filter" style="text-align: right;">
						<label>查询:<input type="search" ng-model="queryStr"  ng-change="queryForPage()"
							class="form-control input-sm input-small input-inline"
							placeholder="" aria-controls="sample_5"></label>
					</div>
				</div>
			</div>
			<div class="table-scrollable">
				<table id="deliveryMaterielTable"
					class="table table-striped table-bordered table-advance table-hover">
					<thead>
						<tr>
							<th rowspan="2">物料编号</th>
							<th rowspan="2">物料名称</th>
							<th rowspan="2">规格型号</th>
							<th rowspan="2">单位</th>
							<!-- <th rowspan="2">生产日期</th> -->
							<th colspan="4" style="text-align: center;">发货</th>
							<!-- <th colspan="4" style="text-align: center;">收货</th> -->
						<!-- 	<th colspan="3" style="text-align: center;">检验</th>
							<th colspan="5" style="text-align: center;">入库</th> -->
							<th rowspan="2">状态</th>
							<th rowspan="2">备注</th>
						</tr>
						<tr>
							<th>订单数量</th>
							<th>未发数量</th>
							<th>发货数量</th>
							<th>附件</th>
							<!-- <th>备注</th> -->
							<!-- <th>实收数量</th>
							<th>拒收数量</th>
							<th>附件</th>
							<th>备注</th> -->
							<!-- <th>合格数量</th>
							<th>不合格数量</th>
							<th>备注</th>
							<th>入库数量</th>
							<th>未入数量</th>
							<th>仓库</th>
							<th>库区</th>
							<th>备注</th> -->
						</tr>
					</thead>
					<tbody>
						<tr
							ng-repeat="materiel in dispalyDeliveryMateriel  track by $index">
							<td>{{materiel.materielNum}}</td>
							<td>{{materiel.materielName}}</td>
							<td>{{materiel.specifications}}</td>
							<td>{{materiel.unit}}</td>
							<!-- <td>{{materiel.manufactureDate}}</td> -->
							<td>{{materiel.amount}}</td>
							<td>{{materiel.amount-materiel.deliveredCount}}</td>
							<td>{{materiel.deliverCount}}</td>
							<td>
								<a href="javascript:;" ng-click="downloadFile1(item.file)" ng-repeat="item in materiel.files">{{item.file|limitTo:30:item.file.indexOf('_')+1}}&nbsp;</a>
							</td>
							<td>{{materiel.remark}}</td>
						<!-- 	<td>{{materiel.acceptCount}}</td>
							<td>{{materiel.refuseCount}}</td>
							<td>
								<a href="javascript:;" ng-click="downloadFile1(item.file)" ng-repeat="item in materiel.files">{{item.file|limitTo:30:item.file.indexOf('_')+1}}&nbsp;</a>
							</td>
							<td>{{materiel.takeRemark}}</td> -->
							<!-- <td>{{materiel.stockInQualifiedCount}}</td>
							<td>{{materiel.stockInUnqualifiedCount}}</td>
							<td>{{materiel.stockInCheckRemark}}</td>
							<td>{{materiel.stockInCount}}</td>
							<td>{{materiel.unstockInCount}}</td>
							<td>{{materiel.stockInWarehouse.warehouseName}}</td>
							<td>{{materiel.stockInPosition.positionName}}</td>
							<td>{{materiel.stockInRemark}}</td> -->
							<td></td>
						</tr>
						<tr
							ng-if="dispalyDeliveryMateriel==0">
							<td colspan="13" align="center">没有符合条件的物料信息</td>
						</tr>
					</tbody>
					<tfoot>
													<tr>
														<td>合计</td>
														<td></td>
														<td></td>
														<td> {{materielCount}}</td>
														<td>{{totalOrderCount}}</td>
														<td>{{totalUnDeliveryCount}}</td>
														<td>{{totalDeliveryCount}}</td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
												</tfoot>
				</table>
			</div>
			
			<div class="row">
				<div class="col-md-5 col-sm-5">
					<div class="dataTables_info" id="sample_5_info" role="status"
						aria-live="polite">从 {{(pageIndex-1)*pageSize+1>filterDeliveryMateriel.length?filterDeliveryMateriel.length:(pageIndex-1)*pageSize+1}}
						到 {{pageIndex*pageSize>filterDeliveryMateriel.length?filterDeliveryMateriel.length:pageIndex*pageSize}} /共 {{filterDeliveryMateriel.length}} 条数据（从{{deliver.deliveryMateriels.length}}条数据中筛选）</div>
				</div>
				<div class="col-md-7 col-sm-7">
					<div  style="text-align: right;" id="sample_5_paginate">
						<ul class="pagination" style="visibility: visible;">
							<li class="prev" ng-if="pageIndex>1"><a href="#" ng-click="link2PreviousPage()" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li class="prev disabled" ng-if="1>=pageIndex"><a href="#" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li ng-if="pageIndex-2>0"><a href="#" ng-click="link2ThisPage(pageIndex-2)">{{pageIndex-2}}</a></li>
							<li ng-if="pageIndex-1>0"><a href="#" ng-click="link2ThisPage(pageIndex-1)">{{pageIndex-1}}</a></li>
							<li class="active"><a href="#">{{pageIndex}}</a></li>
							<li ng-if="totalPage>pageIndex"><a href="#" ng-click="link2ThisPage(pageIndex+1)">{{pageIndex+1}}</a></li>
							<li ng-if="totalPage>pageIndex+1"><a href="#" ng-click="link2ThisPage(pageIndex+2)">{{pageIndex+2}}</a></li>
							<li class="next disabled" ng-if="pageIndex>=totalPage"><a href="#" ><i
									class="fa fa-angle-right"></i></a></li>
							<li class="next" ng-if="totalPage>pageIndex"><a href="#" ng-click="link2NextPage()" title="后一页"><i
									class="fa fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
			
		</div>
	</div>
						</div>
<!-- 发货基本信息 end -->
