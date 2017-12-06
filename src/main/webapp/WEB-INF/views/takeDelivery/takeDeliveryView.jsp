<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<div class="row" id="saleOrderPrint">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet bordered">
			<div class="portlet-body">
			<!-- <form action="#" id="form_sample_1" class=""> -->
				<div class="portlet light ">
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
							 <div class="portlet-title" style="min-height: 48px;">
               <div class="tools" style="float:right"><!-- ng-if="deliver.status=='0'" -->
               	<button type="button" ng-click="confirmDelivery()"     ng-if="deliver.status=='0'||deliver.status==undefined"
								class="btn blue  btn-circle  btn-sm">
								<i class="fa fa-save"></i> 确认代发货
							</button>
                
                </div>
            </div>
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
															ng-model="delivery.deliverNum" ng-show="inputDeliveryInfo" >
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.deliverNum}}</p>
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
															ng-show="inputDeliveryInfo" ><!--ng-init="delivery.deliverType='贸易发货'"  -->
															<option   value=""></option>
															<option   value="贸易发货">贸易发货</option>
															<option value="其他发货">其他发货</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.deliverType}}</p>
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
																ng-show="inputDeliveryInfo" class="form-control"
																ng-model="saleOrder.orderNum" >
															<span ng-show="inputDeliveryInfo" class="input-group-btn"
																style="vertical-align: top;">
																<button class="btn default" type="button">
																	<i class="fa fa-search"></i>
																</button>
															</span>
														</div>
														<input type="hidden" ng-model="orderSerial"
															name="orderSerial" ng-hide="span" />

														<p class="form-control-static" ng-hide="span">
															{{deliver.docNum==null?deliver.orderNum:deliver.docNum}}</p>
													</div>
												</div>
												<div class="form-group" ng-if="otherMode">
													<label class="control-label bold">关联单据号</label>
													<div class="">
														<input type="text" class="form-control"
															ng-model="delivery.orderSerial" ng-show="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.docNum==null?deliver.orderNum:deliver.docNum}}</p>
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
															ng-model="shipper" ng-show="inputDeliveryInfo" />
														<p class="form-control-static" ng-hide="span">
															{{deliver.supplyName}}</p>
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
															ng-model="delivery.receiver" ng-show="inputDeliveryInfo" />
														<p class="form-control-static" ng-hide="span">
															{{deliver.receiver}}</p>
														<div class="form-control-focus"></div>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="handWay">交付方式</label>
                                                    <div class=""><!-- 包装类型暂时先做为交付方式 -->
                                                        <select class="form-control" id="packageType"   ng-model="deliver.packageType" ng-show="inputDeliveryInfo"    ng-init="deliver.packageType='配送'">
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
                                                        <input type="text" class="form-control" id="deliverer"  ng-model="totalDeliveryCount" ng-show="inputDeliveryInfo"  readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{totalDeliveryCount}}</p>
                                                    </div>
                                            </div>
                                            </div>
                                            <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverer">联系人 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverer"  ng-model="deliver.deliverer" ng-show="inputDeliveryInfo" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliver.deliverer}}</p>
                                                    </div>
                                            </div>
										</div>
									<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" class="form-control" name="contactNum"
															ng-model="delivery.contactNum" ng-show="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.contactNum}}</p>
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
                                                        <input type="text" class="form-control" ng-model="deliver.remark" ng-show="inputDeliveryInfo" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliver.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="maker">制单人 <!-- <span class="required"> * </span> --></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.maker" ng-show="inputDeliveryInfo" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliver.maker}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="createTime">制单日期 </label>
                                                    <div class="">
                                 <input type="text" class="form-control date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="approvalDate"  ng-model="deliver.approvalDate" ng-show="inputDeliveryInfo" readonly="readonly">
                                                      <!--   <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.createTime" ng-hide="deliverAdd" > -->
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliver.approvalDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									</div>
								</div>

								<div class="portlet-body form">
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
															ng-change="selectAddress()" ng-show="inputDeliveryInfo">
															<option ng-repeat="item in warehouseList"
																value="{{item.serialNum}}">{{item.warehouseName}}</option>
															<option value=""></option>
														</select>
														</div>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
														{{deliver.warehouse.warehouseName}}</p>
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
															ng-show="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.deliverAddress}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverDate"><span ng-if="deliver.packageType=='配送'">发货</span><span ng-if="deliver.packageType=='自提'">提货</span>日期 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control  date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="deliverDate"  name="deliverDate" ng-model="deliver.deliverDate" ng-show="inputDeliveryInfo" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-hide="span">{{deliver.deliverDate}}</p>
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
															ng-change="selectAddressTakeDelivery()" ng-show="inputDeliveryInfo">
															<option ng-repeat="item in warehouseList"
																value="{{item.serialNum}}">{{item.warehouseName}}</option>
															<option value=""></option>
														</select>
														</div>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.takeDelivery.warehouse.warehouseName}}</p>
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
															ng-model="takeDeliveryWarehouseAddress" ng-show="inputDeliveryInfo" />

														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.takeDelivery.takeDeliverAddress}}</p>
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
															ng-model="takeDelivery.takeDeliverDate" ng-show="inputDeliveryInfo"
															readonly="readonly" />

														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
																{{deliver.takeDelivery.takeDeliverDate}}</p>
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
															ng-show="inputDeliveryInfo" />
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
															ng-model="delivery.packageSpecifications" ng-show="inputDeliveryInfo"    >
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
															ng-show="inputDeliveryInfo" />
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
															ng-model="delivery.deliverer" ng-show="inputDeliveryInfo" />
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
															ng-model="delivery.contactNum" ng-show="inputDeliveryInfo" />
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
															ng-show="inputDeliveryInfo" />
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
											<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运输方式</label>
													<div class="">
														<!-- <input type="text" class="form-control" name="transportType"
													ng-model="deliveryTransport.transportType" ng-show="input" /> -->
														<select class="form-control" id="transportType"
															name="transportType" ng-model="transportType"
															ng-show="inputDeliveryInfo">
															<option value="水路运输">水路运输</option>
															<option value="铁路运输">铁路运输</option>
															<option value="公路运输">公路运输</option>
															<option value="铁路运输">铁路运输</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.deliveryTransport.transportType}}</p>
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
															ng-model="deliveryTransport.transport" ng-show="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.deliveryTransport.transport}}</p>
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
															ng-model="deliveryTransport.shipNumber" ng-show="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.deliveryTransport.shipNumber}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系人</label>
													<div class="">
														<input type="text" class="form-control"
															name="deliveryTransportContact" ng-show="inputDeliveryInfo"
															ng-model="deliveryTransport.contact" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.deliveryTransport.contact}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" class="form-control"
															name="deliveryTransportContactNum" ng-show="inputDeliveryInfo"
															ng-model="deliveryTransport.contactNum" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.deliveryTransport.contactNum}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" class="form-control" name="remark"
															ng-show="inputDeliveryInfo"  ng-model="deliveryTransport.remark" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.deliveryTransport.remark}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
									<!-- 	<div class="row" ng-if="oprateType==null||oprateType==''">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="inOutNum"> 入库单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{stockInOutRecord.inOutNum}}</p>
                                                    </div>
                                            </div>
										</div>
										/span
										
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="inOutType">入库类型 ：</label>
	                                                 <div class="col-md-8">
			                                              <p class="control-label left" >{{stockInOutRecord.inOutType}}</p>
                                                     </div>
                                            </div>
										</div>
										/span
										
										<div class="col-md-4">
											<div class="form-group form-md-line-input" ng-if="record.inOutType=='贸易'">
                                                    <label class="col-md-4 control-label" for="takeDeliverSerial"> 收货单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.delivery.takeDelivery.takeDeliverNum}}</p>
                                                    </div>
                                            </div>
											<div class="form-group form-md-line-input" ng-if="record.inOutType!='贸易'">
                                                    <label class="col-md-4 control-label" for="docNum"> 关联单据号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.docNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="inOutType">入库日期  ：</label>
	                                                 <div class="col-md-8">
			                                              <p class="control-label left" >{{stockInOutRecord.stockDate}}</p>
                                                     </div>
                                            </div>
											
										</div>
										/span
										
									</div> -->
									<!--/row-->
									<div class="row"    ng-if="oprateType==undefined">
									
										
										<div class="col-md-4">
										<div class="form-group">
													<label class="control-label bold">入库单号</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.inOutNum}}</p>
													</div>

												</div>
                                            
										</div>
										<div class="col-md-4">
										<div class="form-group">
													<label class="control-label bold">入库类型</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.inOutType}}</p>
													</div>

												</div>
                                            
										</div>
										<div class="col-md-4">
										<div class="form-group">
													<label class="control-label bold">入库日期</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.stockDate}}</p>
													</div>

												</div>
                                            
										</div>
										
										
									</div>
									<!--/row-->
									<div class="row"   ng-if="oprateType==undefined">
										<div class="col-md-4">
										<div class="form-group">
													<label class="control-label bold">入库仓库</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{deliver.takeDelivery.takeDeliverAddress}}</p>
													</div>

												</div>
                                            
										</div>
							<div class="col-md-4">
							<div class="form-group">
													<label class="control-label bold">入库数量</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.materielCount}}</p>
													</div>
												</div>
										</div>
										<div class="col-md-4">
										
                                            <div class="form-group">
													<label class="control-label bold">包装类型</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.packageType}}</p>
													</div>
												</div>
											</div>
										
						
										<!--/span-->
									
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row"   ng-if="oprateType==undefined">
									<div class="col-md-4">
									
                                                  <div class="form-group">
													<label class="control-label bold">包装规格</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.packageSpecifications}}</p>
													</div>
												</div>
                                            
										</div>
										<div class="col-md-4">
										
											<div class="form-group">
													<label class="control-label bold">包装件数</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.packageCount}}</p>
													</div>
												</div>
										</div>
										<div class="col-md-4">
										
                                            <div class="form-group">
													<label class="control-label bold">操作员</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.operator}}</p>
													</div>
												</div>
										</div>
										<div class="col-md-4">
											
                                            <div class="form-group">
													<label class="control-label bold">联系方式</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.contactNum}}</p>
													</div>
												</div>
										</div>
										<div class="col-md-4">
										
											   <div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{stockInOutRecord.remark}}</p>
													</div>
												</div>
										</div>
										<!--/span-->
										<div class="col-md-4">
										<div class="form-group">
													<label class="control-label bold">状态</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" >
														<span ng-if="stockInOutRecord.status==0"  class="label label-sm label-warning ng-scope">待入库</span>
														<span ng-if="stockInOutRecord.status==1" class="label label-sm label-success ng-scope">已入库</span>
															</p>
															
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
															ng-show="inputDeliveryInfo" />

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
															ng-show="inputDeliveryInfo" />
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
															ng-model="takeDelivery.remark"    ng-show="inputDeliveryInfo"  />
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
							<div class="tab-pane fade" id="tab_1_4"  ng-if="oprateType!=undefined"><!--  采购订单列表收货计划物料列表-->
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
							<th colspan="5" style="text-align: center;">发货</th>
							<!-- <th colspan="4" style="text-align: center;">收货</th> -->
						<!-- 	<th colspan="3" style="text-align: center;">检验</th>
							<th colspan="5" style="text-align: center;">入库</th> -->
							<th rowspan="2">状态</th>
						</tr>
						<tr>
							<th>订单数量</th>
							<th>未发数量</th>
							<th>发货数量</th>
							<th>附件</th>
							<th>备注</th>
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
							<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
							<td>{{materiel.orderMateriel.materiel.materielName}}</td>
							<td>{{materiel.orderMateriel.materiel.specifications}}</td>
							<td>{{materiel.orderMateriel.materiel.unit}}</td>
							<!-- <td>{{materiel.manufactureDate}}</td> -->
							<td>{{materiel.orderMateriel.amount}}</td>
							<td>{{materiel.orderMateriel.amount-materiel.deliveredCount}}</td>
							<td>{{materiel.deliverCount}}</td>
							<td>
								<a href="javascript:;" ng-click="downloadFile1(item.file)" ng-repeat="item in materiel.deliveryFiles">{{item.file|limitTo:30:item.file.indexOf('_')+1}}&nbsp;</a>
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
														<td>{{calcTotalNum()}} {{materielCount}}</td>
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
	<div class="tab-pane fade" id="tab_1_4"  ng-if="oprateType==undefined"><!--  入库计划列表收货计划物料列表-->
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
							<th rowspan="2">订单数量</th>
							<th rowspan="2">发货数量</th>
							<th rowspan="2">合格数量</th>
							<th rowspan="2">生产批次</th>
							<th rowspan="2">入库数量</th>
							<th rowspan="2">未入数量</th>
							<th rowspan="2">备注</th>
							<th rowspan="2">附件</th>
							<th rowspan="2">状态</th>
						</tr>
					</thead>
					<tbody>
						<tr
							ng-repeat="materiel in dispalyDeliveryMateriel  track by $index">
							<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
							<td>{{materiel.orderMateriel.materiel.materielName}}</td>
							<td>{{materiel.orderMateriel.materiel.specifications}}</td>
							<td>{{materiel.orderMateriel.materiel.unit}}</td>
							<td>{{materiel.orderMateriel.amount}}</td>
							<td>{{materiel.deliverCount}}</td>
							<td>{{materiel.stockInQualifiedCount==null?"无入库检验":materiel.stockInQualifiedCount}}</td>
							<td>{{materiel.inOutNums}}<span ng-repeat="stockInBatch in materiel.stockInBatchs track by $index">
												<span ng-if="!$first">;</span> {{stockInBatch.batchNum}}({{stockInBatch.stockInCount}})
												</span></td>
							<td>{{materiel.stockInCount}}</td>
							<td>{{materiel.unstockInCount}}</td>
							<td>{{materiel.stockInRemark}}</td>
							<td>
								<a href="javascript:;" ng-click="downloadFile1(item.file)" ng-repeat="item in materiel.deliveryFiles">{{item.file|limitTo:30:item.file.indexOf('_')+1}}&nbsp;</a>
							</td>
							<td><span ng-if="stockInOutRecord.status==0"  class="label label-sm label-warning ng-scope">待入库</span>
														<span ng-if="stockInOutRecord.status==1" class="label label-sm label-success ng-scope">已入库</span></td>
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
														<td>{{totalDeliveryCount}}</td>
														<td>{{totalQualifiedCount}}</td>
														<td></td>
														<td>{{totalStockInCount}}</td>
														<td>{{totalUnstockInCount}}</td>
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
				</div>
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
							<th> 销售订单号 </th>
                            <th> 采购商 </th>
                            <th> 销售数量 </th>
                            <th> 金额 </th>
                            <!-- <th> 配送 </th> -->
                            <th> 销售类型 </th>
                            <!-- <th> 关联销售合同 </th> -->
                            <th> 关联采购单 </th>
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

<div id="basicMaterielInfoI" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 800px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">选择物料</h4>
				<a ng-hide="selectedMaterielHide" href="javascript:;"
					style="margin: 0px 5px 2px 0px;" class="btn btn-xs green"
					ng-repeat="data in serialNums"
					ng-click="getCheckedIds(data.serialNum)">
					{{data.materiel.materielNum}}<i class="fa fa-close"></i>
				</a>
			</div>
			<div class="modal-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_22">
					<thead>
						<tr>
							<th style="text-align: center">
								<!-- <input ng-if="modalType!='single'" ng-click="allClick" name="select_all" value="1" id="example-select-all" type="checkbox"/> -->
								<label ng-if="modalType!='single'"
								class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
									<input type="checkbox" ng-click="allClick"
									class="group-checkable" data-set="#sample_22.checkboxes" /> <span></span>
							</label>
							</th>
							<th>物料编码</th>
							<th>物料名称</th>
							<th>规格型号</th>
							<th>单位</th>
							<th>供应商</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"
					class="btn dark btn-outline">取消</button>
				<button type="button" ng-click="confirmSelectMateriel()"
					class="btn green">确定</button>
			</div>
		</div>
	</div>
</div>


<div id="basicMaterielInfoII" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 800px;">

			<div class="portlet-title" style="min-height: 48px;">
				<div class="tools" style="float: right; margin-top: 20px;"
					id="noprintdiv">
					<button type="submit" ng-click="saveFile(fileIndex)"
						class="btn green  btn-circle  btn-sm">
						<i class="fa fa-save"></i> 保存
					</button>
					<button data-dismiss="modal" type="button"
						class="btn defualt  btn-circle  btn-sm">
						<i class="fa fa-undo"></i> 取消
					</button>
				</div>
			</div>
			<div class="portlet-body form">
				<form id="form_sample_4">
					<div class="table-scrollable">
						<table class="table table-bordered table-hover" id="fileTable">
							<thead>
								<tr>
									<th>文件</th>
									<th>备注</th>
									<th>上传人</th>
									<th>上传时间</th>
									<th style="width: 100px;"></th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="_file in file track by $index"
									ng-mouseover="showOperation('file',$index)"
									ng-mouseleave="hideOperation('file',$index)">
									<td>
										<div ng-if="file[$index].file==null||file[$index].file==''"
											class="fileinput fileinput-new" data-provides="fileinput">
											<span class="btn blue btn-outline btn-file"> <span
												class="fileinput-new">上传附件</span> <span
												class="fileinput-exists">更改</span> <input type="file"
												name="..." nv-file-select uploader="uploader"
												onchange="angular.element(this).scope().up(this.files[0])"
												ng-model="file[$index].file" ng-click="uploadFile($index)">
											</span> <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span>
											&nbsp; <a href="javascript:;" class="close fileinput-exists"
												ng-click="removefile($index)" data-dismiss="fileinput">
											</a>
										</div>
										<div ng-if="file[$index].file!=null&&file[$index].file!=''"
											class="fileinput fileinput-exists" data-provides="fileinput">
											<span class="btn blue btn-outline btn-file"> <span
												class="fileinput-new">上传附件</span> <span
												class="fileinput-exists">更改</span> <input type="file"
												name="..." nv-file-select uploader="uploader"
												onchange="angular.element(this).scope().up(this.files[0])"
												ng-model="file[$index].file" ng-click="uploadFile($index)">
											</span> <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span>
											&nbsp; <a href="javascript:;" class="close fileinput-exists"
												ng-click="removefile($index)" data-dismiss="fileinput">
											</a>
										</div> <label ng-show="fileInfoShow"
										ng-if="file[$index].file==null||file[$index].file==''"
										class="c_edit">未上传附件</label> <label ng-show="fileInfoShow"
										ng-if="file[$index].file!=null&&file[$index].file!=''"
										class="c_edit"><a href="javascript:;"
											ng-click="downloadFile(file[$index])">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></label>
									</td>
									<td>
										<p class="form-control-static" ng-show="fileInfoShow">{{_file.remark}}</p>
										<input type="text" name="remark[$index]" name="remark"
										class="form-control" ng-model="file[$index].remark">
									</td>
									<td><p class="form-control-static">{{_file.uploader}}</p></td>
									<td><p class="form-control-static">{{_file.uploadDate}}</p></td>


									<td ng-show="operation_f{{$index}}"><a href="javascript:;"
										class="btn red btn-sm" ng-click="deleteFile($index)"> <i
											class="fa fa-close"></i>
									</a></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="form-actions right">
						<a class="btn blue btn-sm" ng-click="addFile()"> <i
							class="fa fa-plus"></i> 增加
						</a>
					</div>
				<!-- </form> -->
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('#playArrivalDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});

	$('#playWarehouseDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});

	$('#approvalDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});

	$('#makeDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});

	$('#deliverDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});

	$('#takeDeliverDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});
</script>
