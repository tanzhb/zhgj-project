<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.left{
	float: left;
}
.required2{
	color:red;
}
.help-block {
    color: #e73d4a;
}

/* #deliveryMaterielTable thead tr th{
	text-align: center;
	vertical-align:middle;
} */
</style>
<!-- <h3 class="page-title d_tip"> 新建代发货信息
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">仓储</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="takeDelivery">收货计划</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a class="d_tip">新建代发货信息</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<form  id="takeDeliveryForm" class=""  >
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
				 <ul class="nav nav-tabs" >
					<li class="dropdown pull-right tabdrop" >
                       <button    class="btn green  btn-sm btn-circle" ng-click="saveTakeDelivery()"   ng-if="deliver.status=='0'||deliver.status==undefined">
                              <i class="fa fa-check"></i> 代发货 </button>
                               <button    class="btn green  btn-sm btn-circle" ng-click="saveTakeDelivery('notice')"  ng-hide="deliverAddForNotice" >
                              <i class="fa fa-check"></i> 通知发货 </button>
                              <button   ng-hide="deliverAdd" class="btn green  btn-sm btn-circle" ng-click="saveTakeDelivery(0)">
                              <i class="fa fa-check"></i> 保存 </button>
                              <button ng-click="editDeliveryInfo()" type="button"   ng-show="deliverView"   class="btn purple  btn-circle  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button>
                       <button   ng-hide="deliverAdd" class="btn defualt  btn-sm btn-circle" ng-click="cancelTakeDelivery()" onclick="return false;">
                              <i class="fa fa-mail-reply"></i> 取消 </button>
                                <button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
					</li>
					<li class="active bold">
                  	<a data-target="#tab_1_1" data-toggle="tab">发货信息</a>
              		</li>
					<!-- li class="bold"><a data-target="#tab_1_2" data-toggle="tab">收货信息</a>
					</li> -->
					<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">物料信息</a></li>
				</ul>
				<div class="tab-content">
				<div class="tab-pane fade active in" id="tab_1_1">
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverNum">发货单号 <span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverNum"  name="deliverNum" ng-model="deliver.deliverNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deliverNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverType">发货类型<span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                        <select class="form-control"  id="deliverType" ng-init="deliver.deliverType='代发货'"  disabled="disabled"     name="deliverType" ng-model="deliver.deliverType" ng-hide="deliverAdd"  data-size="8">
	                                                        <option  value="代发货" selected="selected" >代发货</option>
	                                                      <!--   <option  value="个人借用" selected="selected" >个人借用</option> -->
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">代发货</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group" ng-if="!otherMode">
                                                    <label class="control-label bold" for="orderSerial">采购订单号<span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                    <div   ng-hide="deliverAdd" >
                                                    	 <div class="input-group" data-toggle="modal" data-target="#buyOrderInfo" onclick="return false;">
	                                                        <input id="orderSerial"  name="orderSerial" type="text" class="form-control" ng-model="deliver.orderNum" readonly="readonly"   ng-hide="deliverAdd" >
	                                                        <span class="input-group-btn" style="vertical-align: top;">
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
	                                                    </div>
	                                                    </div>
	                                                    </div>
	                                                    <div class="form-control-focus"> </div>
	                                                     <p class="control-label left" ng-show="deliverView">{{deliver.orderNum}}</p>
                                                    </div>
                                            </div>
											<div class="form-group" ng-if="otherMode">
                                                    <label class="control-label bold" for="docNum">关联单据号</label>
                                                    <div class="">
	                                                    <input type="text" class="form-control" ng-model="deliver.docNum" ng-hide="deliverAdd" >
	                                                    <div class="form-control-focus"> </div>
	                                                     <p class="control-label left" ng-show="deliverView">{{deliver.orderNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="maker">关联销售单号 </label>
                                                    
                                                    <div class="">
														<div class="input-group" data-target="#saleOrderInfo"
															data-toggle="modal" ng-click="selectSaleOrderInfo()"
															onclick="return false;">
															<input id="saleOrderNum" name="saleOrderNum" type="text"   ng-hide="deliverAdd" 
																class="form-control"  ng-model="deliver.saleOrderNum"  
																readonly="readonly"> <span
																class="input-group-btn" ng-hide="deliverAdd"  style="vertical-align: top;">
																<button class="btn default" type="button">
																	<i class="fa fa-search"></i>
																</button>
															</span>
															<p class="control-label left" ng-show="deliverView" >{{deliver.saleOrderNum}}</p>
														</div>
													</div>
                                            </div>
										</div>
											<!--/span-->
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="handWay">交付方式</label>
                                                    <div class=""><!-- 包装类型暂时先做为交付方式 -->
                                                        <select class="form-control" id="packageType"   ng-model="deliver.packageType" ng-hide="deliverAdd"   >
	                                                    	<option value=""></option>
	                                                    	<option value="配送">配送</option>
	                                                    	<option value="自提">自提</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView" >{{deliver.packageType}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverDate"><span ng-if="deliver.packageType=='配送'">发货</span><span ng-if="deliver.packageType=='自提'">提货</span>日期 <span ng-hide="span"
														class="required" aria-required="true"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control  date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="deliverDate"  name="deliverDate" ng-model="deliver.deliverDate" ng-hide="deliverAdd" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView" >{{deliver.deliverDate}}</p>
                                                    </div>
                                            </div>
										</div>
											<!--/span-->
										</div>
										<!--/row-->
												
									<!--/row-->
										<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="takeDeliverDate">预计到库日期 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="takeDeliverDate"  ng-model="takeDeliver.takeDeliverDate" ng-hide="deliverAdd" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.takeDeliverDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" > 备注</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" ng-model="deliver.remark" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView" >{{deliver.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="maker">制单人 <span  ng-hide="span" class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.maker" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView" >{{deliver.maker}}</p>
                                                    </div>
                                            </div>
										</div>
									
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">	
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="approvalDate">制单日期 </label>
                                                    <div class="">
                                 <input type="text" class="form-control date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="approvalDate"  ng-model="deliver.approvalDate" ng-hide="deliverAdd" readonly="readonly">
                                                      <!--   <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.createTime" ng-hide="deliverAdd" > -->
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView" >{{deliver.approvalDate}}</p>
                                                    </div>
                                            </div>
										</div>
										</div>
								
									

								<div class="portlet-body form"  style="border-top:1px solid #dddddd;" >
									<!-- BEGIN FORM-->
									<div class="form-body">
										<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="shipper">发货方<span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                     	<input ng-if="!otherMode" type="text" class="form-control" name="shipper" ng-model="deliver.shipper"    ng-hide="deliverAdd" value="{{deliver.shipper}}" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.shipper}}</p>
                                                    </div>
                                            </div>
										</div>
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="dWarehouseSerial">发货仓库 <!-- <span  ng-hide="deliverAdd"  class="required"> * </span> --></label>
                                                    <div class="">
                                                    <div  ng-hide="deliverAdd" >
                                                    	<select class="form-control"  data-live-search="true"  id="dWarehouseSerialnum"  name="dWarehouseSerial" ng-model="deliver.warehouseSerial"  ng-change="getWarehouseName('deliver')" ng-hide="deliverAdd"  data-size="">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="warehouse in warehouselistf" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                                    </select>
	                                                    </div>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.warehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						
											<!--/span-->
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" > 发货地址</label>
                                                  <!--       	<div class=""   ng-if="showSXf!='1'">
													<div class="input-group"  ng-if="showSXf!='1'">
															<input type="text" name="deliverAddress"
															class="form-control" ng-model="deliver.deliverAddress"
															ng-hide="deliverAdd"   ng-if="showSXf!='1'"/>
															<span ng-hide="deliverAdd" class="input-group-btn" ng-click="showSX('f')"
																style="vertical-align: top;">
																<button class="btn default" type="button" >
																	筛选
																</button>
															</span>
														</div>
														</div>
														<div class="" ng-show="showSXf=='1'">
														<select class="form-control"  id="deliverAddress"  data-live-search="true" data-size=""  
															name="deliverAddress"
															ng-model="deliver.deliverAddress" 
															ng-hide="deliverAdd">
															<option ng-repeat="item in companyAddressesf"
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
													<input type="text" name="deliverAddress"
															class="form-control"
															ng-model="deliver.deliverAddress"    ng-if="showSXf!='1'"   ng-hide="deliverAdd"/>
															<div  ng-show="showSXf=='1'">
																<select class="form-control"   data-live-search="true" data-size=""  ng-hide="deliverAdd"
															name="deliverAddress1"
															ng-model="deliver.deliverAddress"
															 >
															<option ng-repeat="item in companyAddressesf"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														</div>
															<span ng-hide="deliverAdd"  class="input-group-btn" ng-click="showSX('f')"
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
                                                        <div class="form-control-focus"> </div> 
                                                         <!-- <input type="text" class="form-control"  value="{{deliver.warehouseName}}" disabled="disabled"> -->
                                                         <p class="control-label left"   ng-show="deliverView" >{{deliver.deliverAddress}}</p>
                                                    
                                            </div>
										</div>
											<!--/span-->
											
											<!--/span-->
										</div>
											<div class="row">
									<!-- <div class="col-md-4">
									<div class="form-group">
                                                    <label class="control-label bold" for="">发货数量 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverer"  ng-model="totalDeliveryCount" ng-show="inputDeliveryInfo"  readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView" >{{totalDeliveryCount}}</p>
                                                    </div>
                                            </div>
                                            </div> -->
								<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deContact">联系人</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deContact" ng-model="deliver.deContact" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deContact}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="dtContactNum">联系电话</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deContactNum"  name="deContactNum" ng-model="deliver.deContactNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deContactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold"> 备注</label>
                                                    <div class="">
                                                        <input type="text" class="form-control"  ng-model="deliver.deRemark" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deRemark}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
										</div>
									</div>
										
										
										
										<div class="portlet-body form"  style="border-top:1px solid #dddddd;" >
									<!-- BEGIN FORM-->
									<div class="form-body">
											<div class="row">
											<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="receiver">收货方 <span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                     	<input ng-if="!otherMode" type="text" class="form-control" name="receiver" ng-model="deliver.receiver"  value="{{deliver.receiver}}"   ng-hide="deliverAdd" >
                                                        <!-- <input type="text" class="form-control" id="receiver"  name="receiver" ng-model="deliver.receiver" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.receiver}}</p> -->
                                                       	<!-- <select ng-if="otherMode" class="form-control" selectpicker  data-live-search="true"  id="receiver"  name="receiver" ng-model="deliver.receiver" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="customer in customers" value="{{customer.comId}}">{{customer.comName}}</option>
	                                                    </select> -->
                                                        <div class="form-control-focus"  > </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.receiver}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="warehouseSerial">收货仓库 <!-- <span  ng-hide="deliverAdd"  class="required"> * </span> --></label>
                                                    <div class="">
                                                    <div  ng-hide="deliverAdd" >
                                                    	<select class="bs-select2 form-control order"  data-live-search="true"  id="warehouseSerialnum"  name="warehouseSerial" ng-model="takeDeliver.warehouseSerial" ng-change="getWarehouseName()" ng-hide="deliverAdd"  data-size="">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="warehouse in warehouselists" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                                    </select>
	                                                    </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.takeDeliverWarehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" >收货地址<span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <!-- <div class="">
                                                       <input type="text" class="form-control" id="takeDeliverAddress"  name="takeDeliverAddress" ng-model="takeDeliver.takeDeliverAddress" ng-hide="deliverAdd" >
                                                        </div> -->
                                                        	<div class="" >
													<div class="input-group"  >
													<input type="text" name="takeDeliverAddress"  id="takeDeliverAddress"
															class="form-control"
															ng-model="takeDeliver.takeDeliverAddress"   ng-if="showSXs!='1'"   ng-hide="deliverAdd"/>
															<div  ng-show="showSXs=='1'">
																<select class="form-control"  id="takeDeliverAddress1"  data-live-search="true" data-size=""  
															name="takeDeliverAddress1"
															ng-model="takeDeliver.takeDeliverAddress" 
															 >
															<option ng-repeat="item in companyAddressess"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														</div>
															<span ng-hide="deliverAdd"  class="input-group-btn" ng-click="showSX('s')"
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
                                                        <div class="form-control-focus"> </div>
                                                       <!--  <input type="text" class="form-control" value="{{takeDeliver.warehouseName}}" disabled="disabled"> -->
                                                         <p class="control-label left"  ng-show="deliverView">{{takeDeliver.takeDeliverAddress}}</p>
                                                    
                                            </div>
										</div>
										
										</div>
											<div class="row">
								
                                            <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="takeDeliverer">联系人 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="takeDeliverer"  ng-model="deliver.takeDeliverer" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView" >{{deliver.takeDeliverer}}</p>
                                                    </div>
                                            </div>
										</div>
									<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" class="form-control" name="takeContactNum"
															ng-model="deliver.takeContactNum" ng-hide="deliverAdd" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView" >
															{{deliver.takeContactNum}}</p>
													</div>
												</div>
											</div>
										<!--/span-->
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" class="form-control" name="remark"
															ng-hide="deliverAdd"  ng-model="deliver.takeTransportRemark" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView" >
															{{deliver.takeTransportRemark}}</p>
													</div>
												</div>
											</div>
									</div>
										
										</div>
									</div>
									<!-- <div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="shipper">发货方<span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                     	<input ng-if="!otherMode" type="text" class="form-control" name="shipper" ng-model="deliver.shipper"    ng-hide="deliverAdd" value="{{deliver.shipper}}" >
                                                        <input type="text" class="form-control" id="shipper"  name="shipper" ng-model="deliver.shipper" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.shipper}}</p>
                                                        <select ng-if="otherMode" class="form-control" selectpicker data-live-search="true"  id="shipper" ng-change="setSupplyComId(deliver.shipper)"  name="shipper" ng-model="deliver.shipper" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}">{{supplier.comName}}</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.shipper}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="receiver">收货方 <span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                     	<input ng-if="!otherMode" type="text" class="form-control" name="receiver" ng-model="deliver.receiver"  value="{{deliver.receiver}}"   ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"  > </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.receiver}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="handWay">交付方式</label>
                                                    <div class="">包装类型暂时先做为交付方式
                                                        <select class="form-control" id="packageType"   ng-model="deliver.packageType" ng-hide="deliverAdd"   ng-init="deliver.packageType='配送'">
	                                                    	<option value=""></option>
	                                                    	<option value="配送">配送</option>
	                                                    	<option value="自提">自提</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.packageType}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									</row>
												<div class="row">
									<div class="col-md-4">
									<div class="form-group">
                                                    <label class="control-label bold" for="">发货数量 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverer"  ng-model="totalDeliveryCount" ng-hide="deliverAdd" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{totalDeliveryCount}}</p>
                                                    </div>
                                            </div>
                                            </div>
                                            <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverer">联系人 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverer"  ng-model="deliver.deliverer" ng-hide="deliverAdd">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deliverer}}</p>
                                                    </div>
                                            </div>
										</div>
									<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" class="form-control" name="contactNum"
															ng-model="deliver.contactNum" ng-hide="deliverAdd"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView">
															{{deliver.contactNum}}</p>
													</div>
												</div>
											</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" > 备注</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" ng-model="deliver.remark" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="maker">制单人 <span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.maker" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.maker}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="createTime">制单日期 </label>
                                                    <div class="">
                                 <input type="text" class="form-control date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="approvalDate"  ng-model="deliver.approvalDate" ng-hide="deliverAdd" readonly="readonly">
                                                        <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.createTime" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.approvalDate}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									<div  class="row"  >
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="maker">关联销售单号 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="docNum" name="docNum" ng-model="deliver.docNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView" >{{deliver.docNum}}</p>
                                                    </div>
                                                    <div class="">
														<div class="input-group" data-target="#saleOrderInfo"
															data-toggle="modal" ng-click="selectSaleOrderInfo()"
															onclick="return false;">
															<input id="docNum" name="docNum" type="text"   ng-hide="deliverAdd" 
																class="form-control"  ng-model="deliver.docNum"  
																readonly="readonly"> <span
																class="input-group-btn" ng-hide="deliverAdd"  style="vertical-align: top;">
																<button class="btn default" type="button">
																	<i class="fa fa-search"></i>
																</button>
															</span>
															<p class="control-label left" ng-show="deliverView" >{{deliver.docNum}}</p>
														</div>
													</div>
                                            </div>
										</div>
									</div>
									
									<div class="row"  style="border-top:1px solid #dddddd;padding-top: 20px;">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="dWarehouseSerial">发货仓库 <span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                    <div  ng-hide="deliverAdd" >
                                                    	<select class="form-control"  data-live-search="true"  id="dWarehouseSerialnum"  name="dWarehouseSerial" ng-model="deliver.warehouseSerial"  ng-change="getWarehouseName('deliver')" ng-hide="deliverAdd"  data-size="">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="warehouse in warehouselistf" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                                    </select>
	                                                    </div>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.warehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" > 发货地址</label>
                                                        	<div class=""   ng-if="showSXf!='1'">
													<div class="input-group"  ng-if="showSXf!='1'">
															<input type="text" name="deliverAddress"
															class="form-control" ng-model="deliver.deliverAddress"
															ng-hide="deliverAdd"   ng-if="showSXf!='1'"/>
															<span ng-hide="deliverAdd" class="input-group-btn" ng-click="showSX('f')"
																style="vertical-align: top;">
																<button class="btn default" type="button" >
																	筛选
																</button>
															</span>
														</div>
														</div>
														<div class="" ng-show="showSXf=='1'">
														<select class="form-control"  id="deliverAddress"  data-live-search="true" data-size=""  
															name="deliverAddress"
															ng-model="deliver.deliverAddress" 
															ng-hide="deliverAdd">
															<option ng-repeat="item in companyAddressesf"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														<span ng-show="inputDeliveryInfo" class="input-group-btn" ng-click="showSX()"
																style="vertical-align: top;">
																<button class="btn default" type="button"  >
																	<i class="fa fa-search"></i>筛选
																</button>
															</span>
														</div>
														  	<div class="" >
													<div class="input-group"  >
													<input type="text" name="deliverAddress"
															class="form-control"
															ng-model="deliver.deliverAddress"    ng-if="showSXf!='1'"   ng-hide="deliverAdd"/>
															<div  ng-show="showSXf=='1'">
																<select class="form-control"   data-live-search="true" data-size=""  ng-hide="deliverAdd"
															name="deliverAddress1"
															ng-model="deliver.deliverAddress"
															 >
															<option ng-repeat="item in companyAddressesf"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														</div>
															<span ng-hide="deliverAdd"  class="input-group-btn" ng-click="showSX('f')"
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
                                                        <div class="form-control-focus"> </div> 
                                                         <input type="text" class="form-control"  value="{{deliver.warehouseName}}" disabled="disabled">
                                                         <p class="control-label left"   ng-show="deliverView" >{{deliver.deliverAddress}}</p>
                                                    
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverDate"><span ng-if="deliver.packageType=='配送'">发货</span><span ng-if="deliver.packageType=='自提'">提货</span>日期 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control  date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="deliverDate"  name="deliverDate" ng-model="deliver.deliverDate" ng-hide="deliverAdd" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deliverDate}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="warehouseSerial">收货仓库 <span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                    <div  ng-hide="deliverAdd" >
                                                    	<select class="bs-select2 form-control order"  data-live-search="true"  id="warehouseSerialnum"  name="warehouseSerial" ng-model="takeDeliver.warehouseSerial" ng-change="getWarehouseName()" ng-hide="deliverAdd"  data-size="">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="warehouse in warehouselists" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                                    </select>
	                                                    </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.takeDeliverWarehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" >收货地址<span  ng-hide="deliverAdd"  class="required"> * </span></label>
                                                    <div class="">
                                                       <input type="text" class="form-control" id="takeDeliverAddress"  name="takeDeliverAddress" ng-model="takeDeliver.takeDeliverAddress" ng-hide="deliverAdd" >
                                                        </div>
                                                        	<div class="" >
													<div class="input-group"  >
													<input type="text" name="takeDeliverAddress"  id="takeDeliverAddress"
															class="form-control"
															ng-model="takeDeliver.takeDeliverAddress"   ng-if="showSXs!='1'"   ng-hide="deliverAdd"/>
															<div  ng-show="showSXs=='1'">
																<select class="form-control"  id="takeDeliverAddress1"  data-live-search="true" data-size=""  
															name="takeDeliverAddress1"
															ng-model="takeDeliver.takeDeliverAddress" 
															 >
															<option ng-repeat="item in companyAddressess"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														</div>
															<span ng-hide="deliverAdd"  class="input-group-btn" ng-click="showSX('s')"
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
                                                        <div class="form-control-focus"> </div>
                                                        <input type="text" class="form-control" value="{{takeDeliver.warehouseName}}" disabled="disabled">
                                                         <p class="control-label left"  ng-show="deliverView">{{takeDeliver.takeDeliverAddress}}</p>
                                                    
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="takeDeliverDate">预计到货日期 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="takeDeliverDate"  ng-model="takeDeliver.takeDeliverDate" ng-hide="deliverAdd" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.takeDeliverDate}}</p>
                                                    </div>
                                            </div>
										</div>
									</div> -->
						
									<div class="row" style="border-top:1px solid #dddddd;padding-top: 20px;">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="transportType">运输方式</label>
                                                    <div class="">
	                                                    <select class="bs-select form-control order" id="transportType" ng-model="deliverTransport.transportType" ng-hide="deliverAdd">
	                                                    	<option value=""></option>
	                                                    	<option value="水路运输">水路运输</option>
	                                                    	<option value="铁路运输">铁路运输</option>
	                                                    	<option value="公路运输">公路运输</option>
	                                                    	<option value="航空运输">航空运输</option>
	                                                    </select>
                                                      <!--   <input type="text" class="form-control" id="transportType" name="transportType" ng-model="deliverTransport.transportType" ng-hide="deliverAdd" > -->
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.transportType}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="transport">运输方 <!-- <span  ng-hide="deliverAdd"  class="required"> * </span> --></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="transport"  name="transport" ng-model="deliverTransport.transport" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.transport}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="shipNumber">运单号<!-- <span  ng-hide="deliverAdd"  class="required"> * </span> --></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" name="shipNumber" id="shipNumber" ng-model="deliverTransport.shipNumber" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.shipNumber}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="dtContact">联系人</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="dtContact" ng-model="deliverTransport.contact" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.contact}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="dtContactNum">联系电话</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="dtContactNum"  name="dtContactNum" ng-model="deliverTransport.contactNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold"> 备注</label>
                                                    <div class="">
                                                        <input type="text" class="form-control"  ng-model="deliverTransport.remark" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
								
								</div>
         				</div>
         			
         		
         			
         			<!-- 收货信息END -->
         			
         			<!-- 物料信息START -->
         			<!-- <div class=""> -->
						<div class="tab-pane fade active" id="tab_1_3">
							<div class="portlet-body form">
								<div class="form-body">
									<!--  <div class="portlet-title">
                            <div class="caption">物料信息</div>
                            <div class="actions">
                            	 <button   ng-if="otherMode" class="btn blue btn-sm btn-circle" ng-click="addMateriel()" onclick="return false;">
                              <i class="fa fa-plus"></i> 添加物料 </button>
                            </div>
                        </div> -->
									<div class="portlet-body">
										<!-- <div class="table-scrollable"> -->
											<table ng-if="!otherMode"
												class="table table-striped table-bordered table-advance table-hover"
												id="deliveryMaterielTable">
												<thead>
													<tr>
														<th rowspan="2">物料编号</th>
														<th rowspan="2">物料名称</th>
														<th rowspan="2">规格型号</th>
														<th rowspan="2">单位</th>
													<!-- 	<th style="min-width: 120px;" rowspan="2">批次号<span
															class="required2"> * </span></th> -->
													<!-- 	<th style="min-width: 120px;" rowspan="2">生产日期</th> -->
														<th colspan="5"
															style="text-align: center; min-width: 100px;">发货</th>
														<!-- <th colspan="3"  style="text-align: center;min-width: 100px;">收货</th> -->
														<!-- <th colspan="3"  style="text-align: center;">检验</th>
										<th colspan="5"  style="text-align: center;">入库</th>
										<th rowspan="2">状态</th> -->
													</tr>
													<tr>
														<th>订单数量</th>
														<th>未发数量</th>
														<th>发货数量<span class="required2"> * </span></th>
														<th>附件</th>
														<th style="min-width: 120px;">备注</th>
														<!-- <th>实收数量<span class="required2"> * </span></th>
										<th>拒收数量</th>
										<th style="min-width: 120px;">备注</th> -->
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
														ng-repeat="materiel in orderMateriels track by materiel.serialNum"
														>
														<td><span class="help-block"></span>{{materiel.materiel.materielNum}}</td>
														<td><span class="help-block"></span>{{materiel.materiel.materielName}}</td>
														<td><span class="help-block"></span>{{materiel.materiel.specifications}}</td>
														<td><span class="help-block"></span>{{materiel.materiel.unit}}</td>
														<!-- <td class="form-group"><input type="text"
															class="form-control" id="batchNum{{$index}}"
															name="batchNum" ng-model="materiel.batchNum"
															ng-hide="deliverAdd">
															<div class="form-control-focus"></div></td> -->
														<!-- <td class="form-group"><input type="text"
															class="form-control date date-picker"
															data-date-format="yyyy-mm-dd" data-date-viewmode="years"
															readonly="" id="manufactureDate{{$index}}"
															ng-model="materiel.manufactureDate" placeholder="">
															<span class="help-block"></span></td> -->
														<td><span class="help-block"></span>{{materiel.amount}}</td><!-- ng-init="orderMateriels[$index].deliverCount=materiel.amount" -->
														<td><span class="help-block"></span>{{materiel.amount-materiel.deliveredCount}}</td>
														<td class="form-group"><input type="text"
															class="form-control" id="deliverCount{{$index}}"     ng-if="type!='edit'"  ng-init="orderMateriels[$index].deliverCount=materiel.amount-materiel.deliveredCount"
															name="deliverCount" data-ordercount="{{materiel.amount}}"   
															ng-model="orderMateriels[$index].deliverCount"
															ng-hide="deliverAdd"/>
															<input type="text"
															class="form-control" id="deliverCount{{$index}}"     ng-if="type=='edit'"
															name="deliverCount" data-ordercount="{{materiel.amount}}"   
															ng-model="orderMateriels[$index].deliverCount"
															ng-hide="deliverAdd"/>
															<div class="form-control-focus"></div><p class="form-control-static"
																ng-show="deliverAdd">
																{{materiel.deliverCount}}</p></td>
														<td class="form-group">
															 <p id="batchNumReal{{$index}}" ng-hide="true"> </p>
															<p class="form-control-static" id="batchNum{{$index}}">
															<a href="javascript:;" class="btn btn-xs green" id="addBatchNum{{$index}}"
															ng-click="addAttachFile($index)" onclick="return false;"> <i
																class="fa fa-plus"></i>添加
														    </a>
															</p>
														</td>
														<td class="form-group"><input type="text"
															class="form-control" id="deliverRemark{{$index}}"
															name="deliverRemark" ng-model="materiel.deliverRemark"
															ng-hide="deliverAdd">
															<div class="form-control-focus"></div></td>
													</tr>
													<tr ng-if="orderMateriels!=null&&orderMateriels.length>0">
														<td colspan="2" class="bold" align="right">合计：</td>
														<td>{{calcTotalNum()}}{{materielCount}}</td>
														<td></td>
														<td>{{totalAmount}}</td>
														<td>{{totalUnDeliveryCount}}</td>
														<td>{{totalDeliverCount}}</td>
														<td></td>
														<td></td>
													</tr>
													<tr ng-if="orderMateriels==null||orderMateriels.length==0">
														<td colspan="12" align="center">暂无数据</td>
													</tr>
												</tbody>
											</table>

											<table ng-if="otherMode"
												class="table table-striped table-bordered table-advance table-hover"
												id="deliveryMaterielTable">
												<thead>
													<tr>
														<th rowspan="2">物料编号</th>
														<th rowspan="2">物料名称</th>
														<th rowspan="2">规格型号</th>
														<th rowspan="2">单位</th>
														<th style="min-width: 120px;" rowspan="2">批次号<span
															class="required2"> * </span></th>
														<th style="min-width: 120px;" rowspan="2">生产日期<span
															class="required2"> * </span></th>
														<th colspan="2"
															style="text-align: center; min-width: 100px;">发货</th>
														<!-- <th colspan="3"  style="text-align: center;min-width: 100px;">收货</th> -->
														<!-- <th colspan="3"  style="text-align: center;">检验</th>
										<th colspan="5"  style="text-align: center;">入库</th>
										<th rowspan="2">状态</th> -->
													</tr>
													<tr>
														<!-- <th>订单数量</th> -->
														<th>发货数量<span class="required2"> * </span></th>
														<th style="min-width: 120px;">备注</th>
														<!-- <th>实收数量<span class="required2"> * </span></th>
										<th>拒收数量</th>
										<th style="min-width: 120px;">备注</th> -->
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
														ng-repeat="materiel in orderMateriels track by materiel.serialNum"
														repeat-done="repeatDone(this)">
														<td><span class="help-block"></span>{{materiel.materiel.materielNum}}</td>
														<td><span class="help-block"></span>{{materiel.materiel.materielName}}</td>
														<td><span class="help-block"></span>{{materiel.materiel.specifications}}</td>
														<td><span class="help-block"></span>{{materiel.materiel.unit}}</td>
														<td class="form-group"><input type="text"
															class="form-control" id="batchNum{{$index}}"
															name="batchNum" ng-model="materiel.batchNum"
															ng-hide="deliverAdd">
															<div class="form-control-focus"></div></td>
														<td class="form-group"><input type="text"
															class="form-control date date-picker"
															data-date-format="yyyy-mm-dd" data-date-viewmode="years"
															readonly="" id="manufactureDate{{$index}}"
															ng-model="materiel.manufactureDate"
															name="manufactureDate" placeholder=""> <span
															class="help-block"></span></td>
														<!-- <td><span class="help-block"></span>{{materiel.amount}}</td> -->
														<td class="form-group"><input type="text"
															class="form-control" id="deliverCount{{$index}}"
															ng-model="materiel.deliverCount" ng-hide="deliverAdd">
															<div class="form-control-focus"></div></td>
														<td class="form-group"><input type="text"
															class="form-control" id="deliverRemark{{$index}}"
															name="deliverRemark" ng-model="materiel.deliverRemark"
															ng-hide="deliverAdd">
															<div class="form-control-focus"></div></td>
													</tr>
													<tr ng-if="orderMateriels!=null&&orderMateriels.length>0">
														<td colspan="2" class="bold" align="right">合计：</td>
														<td>{{calcTotalNum()}}{{materielCount}}</td>
														<td></td>
														<td></td>
														<td></td>
														<td>{{totalDeliverCount}}</td>
														<td></td>
													</tr>
													<tr ng-if="orderMateriels==null||orderMateriels.length==0">
														<td colspan="11" align="center">暂无数据</td>
													</tr>
												</tbody>
											</table>
										<!-- </div> -->
										<!-- </div> -->
									</div>
									
								</div>
							</div>
						</div>
						<!-- 物料信息END -->
						</div>	
						</div>
					</div>
    		</div>
		</div>
		 </form>
	</div>
</div>
<!-- END MAIN CONTENT -->
<jsp:include page="selectBuyOrder.jsp"></jsp:include>
<jsp:include page="selectSaleOrder.jsp"></jsp:include><!-- 单号 日期 制单人 销售数量 -->
<jsp:include page="../demandPlan/selectMateriel.jsp"></jsp:include>
<jsp:include page="../upload/uploadPage.jsp"></jsp:include>