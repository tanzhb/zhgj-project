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
						<li class="bold"><a data-target="#tab_1_9" data-toggle="tab">附件</a></li>
						<li class="dropdown pull-right tabdrop">
							<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
						</li>									
					</ul>

<!-- 发货基本信息 end -->

<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
							 <div class="portlet-title" style="min-height: 48px;">
               <div class="tools" style="float:right"><!-- ng-if="deliveryDetail.status=='0'" -->
               	<button type="button" ng-click="goDelivery('add')"     ng-if="(delivery.status=='0'||delivery.status==undefined)&&delivery.deliverType=='贸易发货'"
								class="btn blue  btn-circle  btn-sm">
								<i class="fa fa-save"></i> 确认发货
							</button>
                  <button ng-click="saveDeliveryInfo()" type="button"   ng-show="inputDeliveryInfo"   class="btn blue  btn-circle  btn-sm">
                  		<i class="fa fa-edit"></i> 保存 </button>
                  		 <button ng-click="editDeliveryInfo()" type="button"   ng-hide="inputDeliveryInfo"   class="btn purple  btn-circle  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button>
                  <button type="submit" ng-click="goBack()"  ng-show="inputDeliveryInfo"   class="btn green  btn-circle  btn-sm">
                 		<i class="fa fa-save"></i>取消</button>
                </div>
            </div>
            	<form action="#" id="form_sample_deliverInfo" class="">
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
													<label class="control-label bold">发货单号<span ng-hide="span"
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="deliverNum" class="form-control"
															ng-model="delivery.deliverNum" ng-show="inputDeliveryInfo" >
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{delivery.deliverNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货类型<span ng-hide="span"
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="deliverType"
															name="deliverType" ng-model="delivery.deliverType"
															ng-change="changeTakeDeliveryMode(delivery.deliverType)"
															ng-show="inputDeliveryInfo" ><!--ng-init="delivery.deliverType='贸易发货'"  -->
															<option   value=""></option>
															<option   value="贸易发货">贸易发货</option>
															<option value="个人借用">个人借用</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{delivery.deliverType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group" ng-if="!otherMode">
													<label class="control-label bold">销售订单号<span  ng-hide="span"
														class="required" aria-required="true"> * </span></label>
													<div class=""><!--ng-click="selectMateriel()"  -->
														<div class="input-group" data-target="#basicMaterielInfo"
															data-toggle="modal" 
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

														<p class="form-control-static" ng-show="span">
															{{delivery.orderNum}}</p>
													</div>
												</div>
												<div class="form-group" ng-if="otherMode">
													<label class="control-label bold">关联单据号</label>
													<div class="">
														<input type="text" class="form-control"
															ng-model="delivery.orderSerial" ng-show="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
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
													<label class="control-label bold">发货方<span   ng-hide="span"
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="shipper" class="form-control"
															ng-model="shipper" ng-show="inputDeliveryInfo" />
														<p class="form-control-static" ng-show="span">
															{{shipper}}</p>
														<div class="form-control-focus"></div>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货方<span  ng-hide="span"
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="receiver"
															ng-model="delivery.receiver" ng-show="inputDeliveryInfo" />
														<p class="form-control-static" ng-show="span">
															{{delivery.receiver}}</p>
														<div class="form-control-focus"></div>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="handWay">交付方式</label>
                                                    <div class=""><!-- 包装类型暂时先做为交付方式 -->
                                                        <select class="form-control" id="packageType"   ng-model="deliver.packageType" ng-show="inputDeliveryInfo"   >
	                                                    	<option value=""></option>
	                                                    	<option value="配送">配送</option>
	                                                    	<option value="自提">自提</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="span">{{delivery.packageType}}</p>
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
                                                         <p class="control-label left" ng-show="span">{{totalDeliveryCount}}</p>
                                                    </div>
                                            </div>
                                            </div>
                                            <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverer">联系人 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverer"  ng-model="deliver.deliverer" ng-show="inputDeliveryInfo" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="span">{{delivery.deliverer}}</p>
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
														<p class="form-control-static" ng-show="span">
															{{delivery.contactNum}}</p>
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
                                                         <p class="control-label left" ng-show="span">{{delivery.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="maker">制单人 <span  ng-hide="span" class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.maker" ng-show="inputDeliveryInfo" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="span">{{delivery.maker}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="approvalDate">制单日期 </label>
                                                    <div class="">
                                 <input type="text" class="form-control date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="approvalDate"  ng-model="deliver.approvalDate" ng-show="inputDeliveryInfo" readonly="readonly">
                                                      <!--   <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.createTime" ng-hide="deliverAdd" > -->
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="span">{{delivery.approvalDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									</div>
								</div>

								<div class="portlet-body form"  style="border-top:1px solid #dddddd;" >
									<!-- BEGIN FORM-->
									<div class="form-body">
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货仓库<!-- <span  ng-hide="span"
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
													<div  ng-hide="span">
														<select class="form-control"  id="deliverWarehouse"  data-live-search="true" data-size="" 
															name="deliveryWarehouseSerial"
															ng-model="delivery.warehouseSerial"
															ng-change="selectAddress()" ng-show="inputDeliveryInfo">
															<option ng-repeat="item in warehouseListf"
																value="{{item.serialNum}}">{{item.warehouseName}}</option>
															<option value=""></option>
														</select>
														</div>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{delivery.deliveryWarehouseName}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货地址<span ng-hide="span"
														class="required" aria-required="true"> * </span></label>
												<!-- 	<div class="" ng-if="showSXf!='1'">
													<div class="input-group"  ng-if="showSXf!='1'">
															<input type="text" name="warehouseAddress"   
															class="form-control" ng-model="warehouseAddress"
															ng-show="inputDeliveryInfo"   >
															<span ng-show="inputDeliveryInfo" class="input-group-btn" ng-click="showSX('f')"
																style="vertical-align: top;">
																<button class="btn default" type="button" >
																	筛选
																</button>
															</span>
														</div>
														</div>
														<div class="" ng-show="showSXf=='1'"  >
														<select class="form-control"   data-live-search="true" data-size=""  
															name="warehouseAddress1"
															ng-model="warehouseAddress"
															ng-show="inputDeliveryInfo">
															<option ng-repeat="item in companyAddressesf"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														<span ng-show="inputDeliveryInfo" class="input-group-btn" ng-click="showSX('f')"
																style="vertical-align: top;">
																<button class="btn default" type="button"  >
																	<i class="fa fa-search"></i>输入
																</button>
															</span>
														</div> -->
																  	<div class="" >
													<div class="input-group"  >
													<input type="text" name="warehouseAddress"
															class="form-control"
															ng-model="warehouseAddress"    ng-if="showSXf!='1'"  ng-show="inputDeliveryInfo"/>
															<div  ng-show="showSXf=='1'">
																<select class="form-control"   data-live-search="true" data-size=""   ng-show="inputDeliveryInfo"
															name="warehouseAddress1"
															ng-model="warehouseAddress"
															 >
															<option ng-repeat="item in companyAddressesf"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														</div>
															<span ng-show="inputDeliveryInfo"  class="input-group-btn" ng-click="showSX('f')"
																style="vertical-align: top;">
																<button class="btn default" type="button"  ng-if="showSXf!='1'">
																	筛选
																</button>
																	<button class="btn default" type="button"  ng-if="showSXf=='1'">
																	输入
																</button>
															</span>
														</div>
														</div>
													
														
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{delivery.deliveryAddress}}</p>
													
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverDate"><span ng-if="deliver.packageType=='配送'">发货</span><span ng-if="deliver.packageType=='自提'">提货</span>日期 <span ng-hide="span"
														class="required" aria-required="true"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control  date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="deliverDate"  name="deliverDate" ng-model="deliver.deliverDate" ng-show="inputDeliveryInfo" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="span">{{delivery.deliverDate}}</p>
                                                    </div>
                                            </div>
										</div>
											<!--/span-->
										</div>
										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货仓库<!-- <span  ng-hide="span"
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
													<div  ng-hide="span">
														<select class="form-control"  data-live-search="true" data-size=""   
															name="warehouseSerial"  id="takeDeliverWarehouse"
															ng-model="takeDelivery.warehouseSerial"
															ng-change="selectAddressTakeDelivery()" ng-show="inputDeliveryInfo">
															<option ng-repeat="item in warehouseLists"
																value="{{item.serialNum}}">{{item.warehouseName}}</option>
															<option value=""></option>
														</select>
														</div>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{delivery.takeWarehouseName}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货地址<span  ng-hide="span"
														class="required" aria-required="true"> * </span></label>
											
													<!-- 		<div class="" ng-if="showSXs!='1'">
													<div class="input-group"  ng-if="showSXs!='1'">
													<input type="text" name="takeDeliveryWarehouseAddress"  
															class="form-control"
															ng-model="takeDeliveryWarehouseAddress" ng-show="inputDeliveryInfo"   />
															<span ng-show="inputDeliveryInfo" class="input-group-btn" ng-click="showSX('s')"
																style="vertical-align: top;">
																<button class="btn default" type="button" >
																	筛选
																</button>
															</span>
														</div>
														</div>
														<div class="" ng-show="showSXs=='1'">
														<select class="form-control"   data-live-search="true" data-size="" 
															name="takeDeliveryWarehouseAddress1"
															ng-model="takeDeliveryWarehouseAddress"
															ng-show="inputDeliveryInfo">
															<option ng-repeat="item in companyAddressess"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														<span ng-show="inputDeliveryInfo" class="input-group-btn" ng-click="showSX()"
																style="vertical-align: top;">
																<button class="btn default" type="button"  >
																	<i class="fa fa-search"></i>筛选
																</button>
															</span>
														</div> -->
																	  	<div class="" >
													<div class="input-group"  >
													<input type="text" name="takeDeliveryWarehouseAddress"
															class="form-control"
															ng-model="takeDeliveryWarehouseAddress"   ng-show="inputDeliveryInfo"  ng-if="showSXs!='1'"/>
															<div  ng-show="showSXs=='1'">
																<select class="form-control"   data-live-search="true" data-size=""   ng-show="inputDeliveryInfo"
															name="takeDeliveryWarehouseAddress1"
															ng-model="takeDeliveryWarehouseAddress"
															 >
															<option ng-repeat="item in companyAddressess"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														</div>
															<span ng-show="inputDeliveryInfo"  class="input-group-btn" ng-click="showSX('s')"
																style="vertical-align: top;">
																<button class="btn default" type="button"  ng-if="showSXs!='1'">
																	筛选
																</button>
																	<button class="btn default" type="button"  ng-if="showSXs=='1'">
																	输入
																</button>
															</span>
														</div>
														</div>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{delivery.takeAddress}}</p>
													
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
														<p class="form-control-static" ng-show="span">
															{{delivery.takeDeliverDate}}</p>
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
															{{delivery.deliverRemark}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
										/row -->
											<div class="row"  style="border-top:1px solid #dddddd;padding-top: 20px;"  ng-show="showTransport">
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
														<p class="form-control-static" ng-show="span">
															{{delivery.transportType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运输方</label>
													<div class="">
														<input type="text" class="form-control" name="transport"  
															ng-model="deliveryTransport.transport" ng-show="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{delivery.transport}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运单号 </label>
													<div class="">
														<input type="text" class="form-control" name="shipNumber"
															ng-model="deliveryTransport.shipNumber" ng-show="inputDeliveryInfo" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{delivery.shipNumber}}</p>
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
															name="deliveryTransportContact" ng-show="inputDeliveryInfo"
															ng-model="deliveryTransport.contact" />
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
														<input type="text" class="form-control"
															name="deliveryTransportContactNum" ng-show="inputDeliveryInfo"
															ng-model="deliveryTransport.contactNum" />
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
															ng-show="inputDeliveryInfo"  ng-model="deliveryTransport.remark" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{delivery.transportRemark}}</p>
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
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
														<p class="form-control-static" ng-show="span">
															{{delivery.takeDeliveryRemark}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
									</div></div>
								</form>
							</div> -->
							
							<div class="tab-pane fade" id="tab_1_4">
							 <div class="portlet-title" style="min-height: 48px;">
               <div class="tools" style="float:right">
                 <!--  <button ng-click="saveMasterielInfo()" type="button"   ng-if="otherMode"  class="btn blue  btn-circle  btn-sm">
                  		<i class="fa fa-edit"></i> 保存 </button>
                  <button type="submit" ng-click="goBack()"    ng-if="otherMode"  class="btn green  btn-circle  btn-sm">
                 		<i class="fa fa-save"></i>取消</button> -->
                 		<button ng-click="saveAllOrderMateriel()" type="button"   ng-hide="saveMateriel" class="btn blue  btn-circle  btn-sm">
                  		<i class="fa fa-save"></i> 保存 </button>
                  		<button ng-click="editAllOrderMateriel()" type="button"   ng-show="editMateriel" class="btn purple  btn-circle  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button>
                </div>
            </div>
								<!-- 物料信息 start-->
								<div class="portlet-title">
									<!-- <div class="caption">物料信息</div> -->
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
														<th>未发数量</th>
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
														<td class="form-group">
															<p id="batchNumReal{{$index}}" ng-hide="true"></p>

															<p class="form-control-static" id="batchNum{{$index}}">
																<a href="javascript:;" class="btn btn-xs green"
																	id="addBatchNum{{$index}}"
																	ng-click="addAttachFile($index)"
																	onclick="return false;"> <i class="fa fa-plus"></i>添加
																</a>
															</p>
														</td>
														<td>
															<p class="form-control-static">{{_deliveryMateriel.amount}}
															</p>
														</td>
														<td>
															<p class="form-control-static">{{_deliveryMateriel.amount-_deliveryMateriel.deliveredCount}}
															</p>
														</td>
													<td class="form-group"><input type="text"  ng-if="!otherMode&&(_deliveryMateriel.amount-_deliveryMateriel.deliveredCount)<=_deliveryMateriel.currentCount&&_deliveryMateriel.deliverCount==null"
															name="deliverCount" id="deliverCount{{$index}}"  ng-init="deliveryMaterielE[$index].deliverCount=(_deliveryMateriel.amount-_deliveryMateriel.deliveredCount)"
															class="form-control"
															ng-hide="orderMaterielInput{{$index}}"
															ng-model="deliveryMaterielE[$index].deliverCount"
															data-ordercount="{{_deliveryMateriel.amount-_deliveryMateriel.deliveredCount}}"
															/>
															<input type="text"  ng-if="!otherMode&&(_deliveryMateriel.amount-_deliveryMateriel.deliveredCount)<=_deliveryMateriel.currentCount&&_deliveryMateriel.deliverCount!=null"
															name="deliverCount" id="deliverCount{{$index}}"  
															class="form-control"
															ng-hide="orderMaterielInput{{$index}}"
															ng-model="deliveryMaterielE[$index].deliverCount"
															data-ordercount="{{_deliveryMateriel.amount-_deliveryMateriel.deliveredCount}}"
															/>
															<input type="text"  ng-if="!otherMode&&(_deliveryMateriel.amount-_deliveryMateriel.deliveredCount)>_deliveryMateriel.currentCount&&_deliveryMateriel.deliverCount==null"
															name="deliverCount" id="deliverCount{{$index}}"  ng-init="deliveryMaterielE[$index].deliverCount=_deliveryMateriel.currentCount"
															class="form-control"
															ng-hide="orderMaterielInput{{$index}}"
															ng-model="deliveryMaterielE[$index].deliverCount"
															data-ordercount="{{_deliveryMateriel.currentCount}}"
															/>
																<input type="text"  ng-if="!otherMode&&(_deliveryMateriel.amount-_deliveryMateriel.deliveredCount)>_deliveryMateriel.currentCount&&_deliveryMateriel.deliverCount!=null"
															name="deliverCount" id="deliverCount{{$index}}"  
															class="form-control"
															ng-hide="orderMaterielInput{{$index}}"
															ng-model="deliveryMaterielE[$index].deliverCount"
															data-ordercount="{{_deliveryMateriel.currentCount}}"
															/>
															<input type="text"  ng-if="otherMode"
															name="deliverCount" id="deliverCount{{$index}}"  
															class="form-control"
															ng-hide="orderMaterielInput{{$index}}"
															ng-model="deliveryMaterielE[$index].deliverCount"   data-ordercount="100000000000"
															 
															/><!-- ng-blur="getTotalDeliveryCount()"  --><!--  ng-blur="judgeNumber($index)"   -->
															<div class="form-control-focus"></div>
															<p class="form-control-static"
																ng-show="orderMaterielShow{{$index}}">
																{{_deliveryMateriel.deliverCount}}</p></td>
														<td><input type="text" name="deliverRemark{{$index}}"
															class="form-control"
															ng-hide="orderMaterielInput{{$index}}"
															ng-model="deliveryMaterielE[$index].remark">
															<p class="form-control-static"
																ng-show="orderMaterielShow{{$index}}">
																{{_deliveryMateriel.remark}}</p></td>
														<td><span ng-show="orderMaterielInput{{$index}}">
																&nbsp;&nbsp;&nbsp;&nbsp; <a
																ng-click="editOrderMaterielOne(_deliveryMateriel,$index)"><i
																	class="fa fa-edit"></i></a></span></td>
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
														<td>{{totalUnDeliveryCount}}</td>
														<td>{{calcTotalDeliveryCount()}} {{totalDeliveryCount}}</td>
														<td></td>
													</tr>
												</tfoot>
											</table>
										</div>
									</form>
								</div>
								<!-- 供应商 end-->
							</div>
							<div class="tab-pane fade " id="tab_1_9">
		                   <!-- 附件 start-->
					         <div class="portlet-title" style="min-height: 48px;">
					              <div class="tools" style="float:right">
					              	 	<button type="submit" ng-click="savedeliverFile()" ng-hide="deliverFileInfoInput" class="btn green  btn-circle  btn-sm">
					                 		<i class="fa fa-save"></i> 保存 </button>
					                 <button ng-click="canceldeliverFile()" type="button" ng-hide="deliverFileInfoInput" class="btn defualt  btn-circle  btn-sm">
					                 		<i class="fa fa-undo"></i> 取消 </button>
					                 <button ng-click="editdeliverFile()" type="button" ng-show="deliverFileInfoShow&&noShow" class="btn purple  btn-circle  btn-sm">
					                 		<i class="fa fa-edit"></i> 编辑 </button>
					               </div>
					           </div>
					          <div class="portlet-body form">
							     <form id="form_sample_9"   >
								     <div class="table-scrollable">
					                         <table class="table table-bordered table-hover">
					                             <thead>
					                                 <tr>
					                                     <th>附件类型</th>
					                                     <th>描述</th>
					                                     <th>文件</th>
					                                     <th>备注</th>
					                                     <th>上传人</th>
					                                     <th>上传时间</th>
					                                     <th style="width:100px;"></th>
					                                 </tr>
					                             </thead>
					                             <tbody>
					                                 <tr ng-repeat="_deliverFile in deliverFile track by $index" ng-mouseover="showOperation('deliverFile',$index)" ng-mouseleave="hideOperation('deliverFile',$index)">
							                          <td>
						                                 	<select class="form-control" id="deliverFileType[$index]" name="deliverFileType" class="form-control" ng-hide="deliverFileInfoInput" ng-model="deliverFile[$index].fileType"  >
					                                             <option value=""></option>
					                                            	<option value="物料图片" >物料图片</option>
					                                              <option value="图纸" >图纸</option>
					                                              <option value="其他附件" >其他附件</option>
					                                            </select>
							                                <p class="form-control-static" ng-show="deliverFileInfoShow"> {{_deliverFile.fileType}} </p>
							                          </td>
							                           <td>
					                                     		<input type="text" id="deliverFileDescribe[$index]" name="deliverFileDescribe" class="form-control" ng-hide="deliverFileInfoInput" ng-model="deliverFile[$index].fileDescribe"  >
							                                <p class="form-control-static" ng-show="deliverFileInfoShow"> {{_deliverFile.fileDescribe}} </p>
							                          </td>
					                                      <td>
					                                        <div ng-hide="deliverFileInfoInput"   ng-if="deliverFile[$index].file==null||deliverFile[$index].file==''"  class="fileinput fileinput-new" data-provides="fileinput">
					                                            <span class="btn blue btn-circle btn-file">
					                                                <span class="fileinput-new">上传附件</span>
					                                                <span class="fileinput-exists">更改</span>
					                                                <input type="file" name="..." nv-file-select uploader="DeliverUploader" onchange="angular.element(this).scope().deliverUp(this.files[0])" ng-model="deliverFile[$index].file" ng-click="uploaddeliverFile($index)" > </span>
					                                            <span class="fileinput-filename">{{_deliverFile.file.substring(_deliverFile.file.indexOf("_")+1)}}</span> &nbsp;
					                                            <a href="javascript:;" class="close fileinput-exists" ng-click="removedeliverFile($index)" data-dismiss="fileinput"> </a>
					                                        </div>
					                                        <div ng-hide="deliverFileInfoInput"   ng-if="deliverFile[$index].file!=null&&deliverFile[$index].file!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
					                                            <span class="btn blue btn-circle btn-file">
					                                                <span class="fileinput-new">上传附件</span>
					                                                <span class="fileinput-exists">更改</span>
					                                                <input type="file" name="..." nv-file-select uploader="DeliverUploader" onchange="angular.element(this).scope().deliverUp(this.files[0])" ng-model="deliverFile[$index].file" ng-click="uploaddeliverFile($index)" > </span>
					                                            <span class="fileinput-filename">{{_deliverFile.file.substring(_deliverFile.file.indexOf("_")+1)}}</span> &nbsp;
					                                            <a href="javascript:;" class="close fileinput-exists"  ng-click="removedeliverFile($index)" data-dismiss="fileinput"> </a>
					                                        </div>
					                                      	<label   ng-show="deliverFileInfoShow" ng-if="deliverFile[$index].file==null||deliverFile[$index].file==''" class="c_edit" >未上传附件</label>
					                                      	<label   ng-show="deliverFileInfoShow" ng-if="deliverFile[$index].file!=null&&deliverFile[$index].file!=''" class="c_edit" ><a href="javascript:;" ng-click="downloadFile(deliverFile[$index])">{{_deliverFile.file.substring(_deliverFile.file.indexOf("_")+1)}}</a></label>
					                                     		<!-- <input type="text" name="file[$index]" name="file" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].file"  >
							                                <p class="form-control-static" ng-show="fileInfoShow"> {{_file.file}} </p> -->
							                          </td>
							                           <td>
					                                     		<input type="text" name="remark[$index]" name="remark" class="form-control" ng-hide="deliverFileInfoInput" ng-model="deliverFile[$index].remark"  >
							                                <p class="form-control-static" ng-show="deliverFileInfoShow"> {{_deliverFile.remark}} </p>
							                          </td>
					                                     <td><p class="form-control-static"> {{_deliverFile.uploader}} </p></td>
					                                     <td><p class="form-control-static"> {{_deliverFile.uploadDate}} </p></td>
					                                     
					                                     <td ng-show="operation_f{{$index}}">
					                                     	<a href="javascript:;"  class="btn red btn-sm" ng-hide="deliverFileInfoInput" ng-click="deletedeliverFile($index)">
					                                   			<i class="fa fa-close"></i> 
					                            				</a>
					                                     </td>
					                                 </tr>
					                             </tbody>
					                         </table>
					                     </div>
					                </form>
					                     <div class="form-actions right">
											<a  class="btn blue btn-sm"  ng-hide="deliverFileInfoInput" ng-click="adddeliverFile()"   >
					                              <i class="fa fa-plus"></i> 增加
					                       	</a> 
					                 		</div>
					           </div>
					         <!-- 附件 end-->
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