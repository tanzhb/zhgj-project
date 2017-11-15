<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<html>
<body>
	<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货单号<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"  >
															{{deliver.deliverNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货类型<!--<span class="required" aria-required="true"> * </span>--></label>
													<div class="">
														
														<div class="form-control-focus"></div>
														<p class="form-control-static"  >
															{{deliver.deliverType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group" ng-if="!otherMode&&record.takeDeliverSerial!=''">
													<label class="control-label bold">采购订单号<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<p class="form-control-static"  >
															{{deliver.orderNum}}</p>
													</div>
												</div>
												<div class="form-group" ng-if="!otherMode&&record.deliverSerial!=''">
													<label class="control-label bold">销售订单号<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<p class="form-control-static"  >
															{{deliver.orderNum}}</p>
													</div>
												</div>
												<div class="form-group" ng-if="otherMode">
													<label class="control-label bold">关联单据号</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''">
															{{deliver.docNum}}</p>
															<p class="form-control-static"   ng-if="record.deliverSerial!=''">
															{{deliver.orderNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">供应商<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''" >
															{{deliver.supplyName}}</p>
															<p class="form-control-static"   ng-if="record.deliverSerial!=''" >
															{{deliver.shipper}}</p>
														<div class="form-control-focus"></div>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">采购商<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''" >
															{{deliver.shipper}}</p>
															<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''" >
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
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left"  >{{deliver.packageType}}</p>
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
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left"  >{{totalDeliveryCount}}</p>
                                                    </div>
                                            </div>
                                            </div>
                                            <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverer">联系人 </label>
                                                    <div class="">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left"  >{{deliver.deliverer}}</p>
                                                    </div>
                                            </div>
										</div>
									<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"   >
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
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left"  >{{deliver.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="maker">制单人 <!-- <span class="required"> * </span> --></label>
                                                    <div class="">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left"   >{{deliver.maker}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="createTime">制单日期 </label>
                                                    <div class="">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left"  >{{deliver.approvalDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->

										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货仓库<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<div class="form-control-focus"></div>{{deliveryDetail.deliveryAddress}}
														<p class="form-control-static"  ng-if="record.takeDeliverSerial!=''"  >
														{{deliver.warehouse.warehouseName}}</p>
														<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.deliveryAddress}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发货地址</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''">
															{{deliver.deliverAddress}}</p>
															<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.deliveryWarehouseName}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverDate"><span ng-if="deliver.packageType=='配送'">发货</span><span ng-if="deliver.packageType=='自提'">提货</span>日期 </label>
                                                    <div class="">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left"  >{{deliver.deliverDate}}</p>
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
														<div class="form-control-focus"></div>
														<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''">
															{{deliver.takeDelivery.warehouse.warehouseName}}</p>
																<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.takeWarehouseName}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收货地址</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"  ng-if="record.takeDeliverSerial!=''" >
															{{deliver.takeDelivery.takeDeliverAddress}}</p>
																<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.takeAddress}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">预计到货日期</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"  ng-if="record.takeDeliverSerial!=''" >
																{{deliver.takeDelivery.takeDeliverDate}}</p>
																<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.takeDeliverDate}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
									
											<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运输方式</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''">
															{{deliver.deliveryTransport.transportType}}</p>
																<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.transportType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运输方<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-if="record.takeDeliverSerial!=''"  >
															{{deliver.deliveryTransport.transport}}</p>
																<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.transport}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运单号 </label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''">
															{{deliver.deliveryTransport.shipNumber}}</p>
																<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.shipNumber}}</p>
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
														<div class="form-control-focus"></div>
														<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''">
															{{deliver.deliveryTransport.contact}}</p>
																<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.transportContact}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"   ng-if="record.takeDeliverSerial!=''">
															{{deliver.deliveryTransport.contactNum}}</p>
																<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.transportContactNum}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static"  ng-if="record.takeDeliverSerial!=''" >
															{{deliver.deliveryTransport.remark}}</p>
																<p class="form-control-static"  ng-if="record.deliverSerial!=''"  >
														{{deliver.transportRemark}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
</body>
</html>