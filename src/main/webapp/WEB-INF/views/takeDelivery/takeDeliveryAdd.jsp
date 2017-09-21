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
                       <button   ng-hide="companyAdd" class="btn green  btn-sm btn-circle" ng-click="saveTakeDelivery()">
                              <i class="fa fa-check"></i> 确认发货 </button>
                       <button   ng-hide="companyAdd" class="btn defualt  btn-sm btn-circle" ng-click="cancelTakeDelivery()" onclick="return false;">
                              <i class="fa fa-mail-reply"></i> 取消 </button>
                                
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
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverNum">发货单号 <span class="required"> * </span></label>
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
                                                    <label class="control-label bold" for="deliverType">发货类型<span class="required"> * </span></label>
                                                    <div class="">
                                                        <select class="form-control"  id="deliverType" ng-init="deliver.deliverType='贸易发货'"  name="deliverType" ng-model="deliver.deliverType" ng-hide="deliverAdd"  data-size="8">
	                                                        <option  value="贸易发货" selected="selected" >贸易发货</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.takeDeliverNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="orderSerial">采购订单号<span class="required"> * </span></label>
                                                    <div class="">
                                                    	 <div class="input-group" data-toggle="modal" data-target="#buyOrderInfo" onclick="return false;">
	                                                        <input id="orderSerial"  name="orderSerial" type="text" class="form-control" ng-model="deliver.orderNum" readonly="readonly" >
	                                                        <span class="input-group-btn" style="vertical-align: top;">
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
	                                                    </div>
	                                                    	<!-- <input id="orderSerial" data-toggle="modal" data-target="#buyOrderInfo"  name="orderSerial" type="text" class="form-control" ng-model="deliver.orderNum" readonly="readonly" >
	                                                    	 --><div class="form-control-focus"> </div>
                                                    </div>
                                                    <!-- <div class="col-md-2">
                                                    <button class="btn btn-icon-only default" data-toggle="modal" data-target="#buyOrderInfo" onclick="return false;">
                                            			<i class="fa fa-search" ></i></button>
                                                    </div> -->
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="supplyComId">供应商 <span class="required"> * </span></label>
                                                    <div class="">
                                                     	<input type="text" class="form-control"  value="{{deliver.supplyName}}" disabled="disabled">
                                                    	<!-- <select class="form-control" selectpicker  id="supplyComId"  name="supplyComId" ng-model="deliver.supplyComId" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}">{{supplier.comName}}</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.supplyName}}</p> -->
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="shipper">发货方 <span class="required"> * </span></label>
                                                    <div class="">
                                                     	<input type="text" class="form-control"  value="{{deliver.shipperName}}" disabled="disabled">
                                                       <!--  <input type="text" class="form-control" id="shipper"  name="shipper" ng-model="deliver.shipper" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.shipper}}</p> -->
                                                         <!-- <select class="form-control" selectpicker  id="shipper"  name="shipper" ng-model="deliver.shipper" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}">{{supplier.comName}}</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.shipperName}}</p> -->
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="receiver">收货方 <span class="required"> * </span></label>
                                                    <div class="">
                                                     	<input type="text" class="form-control"  value="{{deliver.receiverName}}" disabled="disabled">
                                                        <!-- <input type="text" class="form-control" id="receiver"  name="receiver" ng-model="deliver.receiver" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.receiver}}</p> -->
                                                      <!--    <select class="form-control" selectpicker  id="receiver"  name="receiver" ng-model="deliver.receiver" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="customer in customers" value="{{customers.comId}}">{{customer.comName}}</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.receiverName}}</p> -->
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="maker">制单人 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.maker" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.maker}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="makerDate">制单日期 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control  date-picker" size="16"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" 
                                                         id="makeDate"  name="makeDate" ng-model="deliver.makeDate" ng-hide="deliverAdd" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.makeDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									<!-- 	<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="approval">审批人 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="approval"  name="approval" ng-model="deliver.approval" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.approval}}</p>
                                                    </div>
                                            </div>
										</div>
										/span
									</div>
									/row
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="approvalDate">审批日期 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="approvalDate" name="approvalDate" ng-model="deliver.approvalDate" ng-hide="deliverAdd" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.approvalDate}}</p>
                                                    </div>
                                            </div>
										</div>
						
										/span -->
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
										<!--/span-->
										<!-- <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold"> 状态</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="comName"  name="comName" ng-model="deliver.status" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.status}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
									</div>
									<!--/row-->
								</div>
         				</div>
         		</div>
				
         			<!-- 基本信息END -->
					<!-- 发货信息START -->
                       <!--  <div class="portlet-title">
                            <div class="caption">发货信息</div>
                            <div class="actions">
                                <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div> -->
                   <div class="tab-pane fade" id="tab_1_2">
                        <div class="portlet-body form">
								<div class="form-body">
								<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="dWarehouseSerial">发货仓库 <span class="required"> * </span></label>
                                                    <div class="">
                                                    	<select class="bs-select form-control order" data-live-search="true"  id="dWarehouseSerial"  name="dWarehouseSerial" ng-model="deliver.warehouseSerial"  ng-change="getWarehouseName('deliver')" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option value="无">无</option>
	                                                        <option  ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.warehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" > 发货地址</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverAddress"  name="deliverAddress" ng-model="deliver.deliverAddress" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div> 
                                                         <!-- <input type="text" class="form-control"  value="{{deliver.warehouseName}}" disabled="disabled"> -->
                                                         <!-- <p class="control-label left" >{{deliver.warehouseName}}</p> -->
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverDate">发货日期 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control  date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="deliverDate"  name="deliverDate" ng-model="deliver.deliverDate" ng-hide="deliverAdd" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deliverDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="materielCount">物料数</label>
                                                    <div class="">
                                                        <!-- <input type="text" class="form-control" id="materielCount" name="materielCount" ng-model="deliver.materielCount" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div> -->
                                                        <input type="text" class="form-control"  value="{{deliver.materielCount}}" disabled="disabled">
                                                        <!--  <p class="control-label left" >{{deliver.materielCount}}</p> -->
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="packageCount">包装件数 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="packageCount"   ng-model="deliver.packageCount" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.packageCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="packageType">包装类型 </label>
                                                    <div class="">
                                                     	<select class="form-control" id="packageType"   ng-model="deliver.packageType" ng-hide="deliverAdd" >
	                                                    	<option value=""></option>
	                                                    	<option value="原厂包装">原厂包装</option>
	                                                    	<option value="供应商包装">供应商包装</option>
	                                                    	<option value="其他类型">其他类型</option>
	                                                    </select>
                                                        <!-- <input type="text" class="form-control" id="packageType"  name="packageType" ng-model="deliver.packageType" ng-hide="deliverAdd" > -->
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.packageType}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="packageSpecifications">包装规格 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="packageSpecifications"  ng-model="deliver.packageSpecifications" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.packageSpecifications}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="materielWeight">物料重量 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="materielWeight"   ng-model="deliver.materielWeight" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.materielWeight}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="serviceMoney">服务费</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="serviceMoney"  ng-model="deliver.serviceMoney" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.serviceMoney}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverer">联系人 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="deliverer"  ng-model="deliver.deliverer" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deliverer}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="dContactNum">联系电话 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="dContactNum"  name="dContactNum" ng-model="deliver.contactNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" >备注</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" ng-model="deliver.deliverRemark" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deliverRemark}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
         				</div>
         			</div>	
         			<!-- 发货信息END -->
					<!-- 运输START -->
                       <!--  <div class="portlet-title">
                            <div class="caption">运输信息</div>
                            <div class="actions">
                                <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div> -->
                    <div class="tab-pane fade" id="tab_1_3">
                        <div class="portlet-body form">
								<div class="form-body">
								 <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
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
                                                    <label class="control-label bold" for="transport">运输方 <span class="required"> * </span></label>
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
                                                    <label class="control-label bold" for="shipNumber">运单号<span class="required"> * </span></label>
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
         			</div>	
         			<!-- 运输信息END -->
					<!-- 收货信息START -->
                       <!--  <div class="portlet-title">
                            <div class="caption">收货信息</div>
                            <div class="actions">
                                <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div> -->
                        <div class="tab-pane fade" id="tab_1_4">
                        <div class="portlet-body form">
								<div class="form-body">
								<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="warehouseSerial">收货仓库 <span class="required"> * </span></label>
                                                    <div class="">
                                                    	<select class="bs-select form-control order" data-live-search="true"  id="warehouseSerial"  name="warehouseSerial" ng-model="takeDeliver.warehouseSerial" ng-change="getWarehouseName()" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option value="无">无</option>
	                                                        <option  ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.warehouseSerial}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" >收货地址</label>
                                                    <div class="">
                                                       <input type="text" class="form-control" id="takeDeliverAddress"  name="takeDeliverAddress" ng-model="takeDeliver.takeDeliverAddress" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                       <!--  <input type="text" class="form-control" value="{{takeDeliver.warehouseName}}" disabled="disabled"> -->
                                                        <!--  <p class="control-label left">{{takeDeliver.warehouseName}}</p> -->
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
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
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="tdReceiver">联系人 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="tdReceiver"  ng-model="takeDeliver.receiver" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.receiver}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="tdContactNum">联系电话 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="tdContactNum"  name="tdContactNum" ng-model="takeDeliver.contactNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" >备注</label>
                                                    <div class="">
                                                        <input type="text" class="form-control"  ng-model="takeDeliver.remark" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
         				</div>
         			</div>
         			</div>
         			<!-- 收货信息END -->
         			<!-- 物料信息START -->
         			<!-- <div class=""> -->
                        <div class="portlet-title">
                            <div class="caption">物料信息</div>
                            <div class="actions">
                            </div>
                        </div>
                        <div class="portlet-body">
						<div class="table-scrollable">
							<table
								class="table table-striped table-bordered table-advance table-hover" id="deliveryMaterielTable">
								<thead>
									<tr>
										<th  rowspan="2">物料编号</th>
										<th  rowspan="2">物料名称</th>
										<th  rowspan="2">规格型号</th>
										<th  rowspan="2">单位</th>
										<th style="min-width: 120px;" rowspan="2">批次号<span class="required2"> * </span></th>
										<th style="min-width: 120px;" rowspan="2">生产日期<span class="required2"> * </span></th>
										<th colspan="3" style="text-align: center;min-width: 100px;">发货</th>
										<!-- <th colspan="3"  style="text-align: center;min-width: 100px;">收货</th> -->
										<!-- <th colspan="3"  style="text-align: center;">检验</th>
										<th colspan="5"  style="text-align: center;">入库</th>
										<th rowspan="2">状态</th> -->
									</tr>
									<tr>
										<th>订单数量</th>
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
										<th>库位</th>
										<th>备注</th> -->
									</tr>
								</thead>
								<tbody > 
									<tr ng-repeat="materiel in orderMateriels track by materiel.serialNum" repeat-done="repeatDone(this)">
										<td><span class="help-block"></span>{{materiel.materiel.materielNum}}</td>
										<td><span class="help-block"></span>{{materiel.materiel.materielName}}</td>
										<td><span class="help-block"></span>{{materiel.materiel.specifications}}</td>
										<td><span class="help-block"></span>{{materiel.materiel.unit}}</td>
										<td class="form-group">
                                                 <input type="text" class="form-control" id="batchNum{{$index}}" name="batchNum"  ng-model="materiel.batchNum" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
                                        </td>
										<td class="form-group">
												  <!-- <div  class="input-group date date-picker"
														 data-date-format="yyyy-mm-dd"
														data-date-viewmode="years" size="8">
														<input type="text" class="form-control" size="8" readonly="" id="manufactureDate{{$index}}" ng-model="materiel.manufactureDate" name="manufactureDate"
															> <span class="input-group-btn">
															<button class="btn default " type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
														<span class="help-block"></span>
													</div> -->
													<input type="text" class="form-control date date-picker" data-date-format="yyyy-mm-dd"
													data-date-viewmode="years" readonly="" id="manufactureDate{{$index}}" ng-model="materiel.manufactureDate" name="manufactureDate"
														placeholder="" > 
													<span class="help-block"></span>
										</td>
										<td><span class="help-block"></span>{{materiel.amount}}</td>
										<td class="form-group">
                                                 <input type="text" class="form-control" id="deliverCount{{$index}}" name="deliverCount" data-ordercount="{{materiel.amount}}"   ng-model="materiel.deliverCount" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
										</td>
										<td class="form-group">
                                                 <input type="text" class="form-control" id="deliverRemark{{$index}}" name="deliverRemark"   ng-model="materiel.deliverRemark" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
										</td>
										<!-- <td class="form-group">
                                                 <input type="text" class="form-control" id="acceptCount{{$index}}" name="acceptCount" data-delivercount="{{materiel.deliverCount}}"  ng-model="materiel.acceptCount" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
										</td>
										<td><span class="help-block"></span>
											<span ng-if="materiel.deliverCount!=undefined && materiel.acceptCount!=undedined">{{materiel.deliverCount-materiel.acceptCount}}</span>
										</td>
										<td>
                                                 <input type="text" class="form-control" id="takeRemark{{$index}}" name="takeRemark"  ng-model="materiel.takeRemark" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
										</td> -->
										<!-- <td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td> -->
									</tr>
									<tr>
										<td colspan="2" class="bold" align="right">合计：</td>
										<td>{{calcTotalNum()}}{{materielCount}}</td>
										<td></td>
										<td></td>
										<td></td>
										<td>{{totalAmount}}</td>
										<td>{{totalDeliverCount}}</td>
										<td></td>
									</tr>
									<tr ng-if="orderMateriels==undefined||orderMateriels.length==0">
											<td colspan="12" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
						</div>
					 <!-- </div> -->
					 </div>
         			<!-- 物料信息END -->
         			<!-- <div class="row">
         				<div class="col-md-5"></div>
         				<div class="col-md-1">
         					 <button   ng-hide="deliverAdd" class="btn blue" ng-click="saveTakeDelivery()">
                                            <i class="fa fa-check"></i> 确认收货 </button>
         				</div>
         				<div class="col-md-1">
         					 <button   ng-hide="deliverAdd" class="btn red btn-outline" ng-click="cancelTakeDelivery()" onclick="return false;">
                                            <i class="fa fa-undo"></i> 取消 </button>
         				</div>
         				<div class="col-md-5"></div>
         			</div> -->
       			
       				</div>
    		</div>
		</div>
		 </form>
	</div>
</div>
<!-- END MAIN CONTENT -->
<jsp:include page="selectBuyOrder.jsp"></jsp:include>
