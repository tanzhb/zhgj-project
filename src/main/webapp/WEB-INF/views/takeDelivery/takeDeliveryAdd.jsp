<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.left{
	float: left;
}
</style>
<h3 class="page-title"> 新建收货
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">物流管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="takeDelivery">收货计划</a>
        </li>
    </ul>

</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
				 	<form  id="takeDeliveryForm" class="form-horizontal" >
					<!-- 基本信息START -->
                        <div class="portlet-title">
                            <div class="caption">基本信息</div>
                            <div class="actions">
                              <!--   <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button> -->
                            </div>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="takeDeliverNum"> <span class="required"> * </span>收货单号：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="takeDeliverNum" name="takeDeliverNum" ng-model="takeDeliver.takeDeliverNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.takeDeliverNum}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="deliverNum"> <span class="required"> * </span>发货单号：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="deliverNum"  name="deliverNum" ng-model="deliver.deliverNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deliverNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="orderSerial"> <span class="required"> * </span>采购订单号：</label>
                                                    <div class="col-md-8">
                                                       <select class="bs-select form-control order" data-live-search="true"  id="orderSerial"  name="orderSerial" ng-change="getOrderMateriel()" ng-model="deliver.orderSerial" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="order in orders" value="{{order.serialNum}}">{{order.orderNum}}</option>
	                                                   </select>
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
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId"> <span class="required"> * </span>供应商：</label>
                                                    <div class="col-md-8">
                                                    	<select class="bs-select form-control order" data-live-search="true"  id="supplyComId"  name="supplyComId" ng-model="deliver.supplyComId" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}">{{supplier.comName}}</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.supplyName}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="shipper"> <span class="required"> * </span>发货方：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="shipper"  name="shipper" ng-model="deliver.shipper" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.shipper}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="receiver"> <span class="required"> * </span>收货方：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="receiver"  name="receiver" ng-model="deliver.receiver" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.receiver}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="maker"> <span class="required"> * </span>制单人：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="maker" name="maker" ng-model="deliver.maker" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.maker}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="makerDate"> <span class="required"> * </span>制单日期：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control  form-control-inline input-medium date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years"
                                                         id="makeDate"  name="makeDate" ng-model="deliver.makeDate" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.makeDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="approval"> <span class="required"> * </span>审批人：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="approval"  name="approval" ng-model="deliver.approval" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.approval}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="approvalDate"> <span class="required"> * </span>审批日期：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control  form-control-inline input-medium date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="approvalDate" name="approvalDate" ng-model="deliver.approvalDate" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.approvalDate}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" > 备注：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" ng-model="deliver.remark" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label"> 状态：</label>
                                                    <div class="col-md-8">
                                                        <!-- <input type="text" class="form-control" id="comName"  name="comName" ng-model="deliver.status" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div> -->
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.status}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
         				</div>
         			<!-- 基本信息END -->
					<!-- 发货信息START -->
                        <div class="portlet-title">
                            <div class="caption">发货信息</div>
                            <div class="actions">
                               <!--  <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button> -->
                            </div>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="dWarehouseSerial"> <span class="required"> * </span>发货仓库：</label>
                                                    <div class="col-md-8">
                                                    	<select class="bs-select form-control order" data-live-search="true"  id="dWarehouseSerial"  name="dWarehouseSerial" ng-model="deliver.warehouseSerial"  ng-change="getWarehouseName('deliver')" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.warehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" > 仓库地址：</label>
                                                    <div class="col-md-8">
                                                        <!-- <input type="text" class="form-control" id="comName"  name="comName" ng-model="deliver.comName" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div> -->
                                                         <p class="control-label left" >{{deliver.warehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="deliverDate"> <span class="required"> * </span>发货日期：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control  form-control-inline input-medium date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="deliverDate"  name="deliverDate" ng-model="deliver.deliverDate" ng-hide="deliverAdd" >
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
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="materielCount">物料数：</label>
                                                    <div class="col-md-8">
                                                        <!-- <input type="text" class="form-control" id="materielCount" name="materielCount" ng-model="deliver.materielCount" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div> -->
                                                         <p class="control-label left" >{{deliver.materielCount}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="packageCount"> <span class="required"> * </span>包装件数 ：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="packageCount"  name="packageCount" ng-model="deliver.packageCount" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.packageCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="packageType"> <span class="required"> * </span>包装类型：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="packageType"  name="packageType" ng-model="deliver.packageType" ng-hide="deliverAdd" >
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
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="packageSpecifications"> <span class="required"> * </span>包装规格：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="packageSpecifications" name="packageSpecifications" ng-model="deliver.packageSpecifications" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.packageSpecifications}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="materielWeight"> <span class="required"> * </span>物料重量：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="materielWeight"  name="materielWeight" ng-model="deliver.materielWeight" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.materielWeight}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="serviceMoney"> <span class="required"> * </span>服务费：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="serviceMoney"  name="serviceMoney" ng-model="deliver.serviceMoney" ng-hide="deliverAdd" >
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
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="deliverer"> <span class="required"> * </span>发货人：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="deliverer" name="deliverer" ng-model="deliver.deliverer" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.deliverer}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="dContactNum"> <span class="required"> * </span>联系电话：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="dContactNum"  name="dContactNum" ng-model="deliver.contactNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" >备注：</label>
                                                    <div class="col-md-8">
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
         			<!-- 发货信息END -->
					<!-- 运输START -->
                        <div class="portlet-title">
                            <div class="caption">运输信息</div>
                            <div class="actions">
                               <!--  <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button> -->
                            </div>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="transportType"> <span class="required"> * </span>运输方式：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="transportType" name="transportType" ng-model="deliverTransport.transportType" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.transportType}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="transport"> <span class="required"> * </span>运输方：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="transport"  name="transport" ng-model="deliverTransport.transport" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.transport}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="port"> <span class="required"> * </span>港口：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="port"  name="port" ng-model="deliverTransport.port" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.port}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="shipNumber"> <span class="required"> * </span>船号：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="shipNumber" name="shipNumber" ng-model="deliverTransport.shipNumber" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.shipNumber}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="playArrivalDate"> <span class="required"> * </span>预计到港日期：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control  form-control-inline input-medium date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="playArrivalDate"  name="playArrivalDate" ng-model="deliverTransport.playArrivalDate" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.playArrivalDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="playWarehouseDate"> <span class="required"> * </span>预计到库日期：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control  form-control-inline input-medium date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="playWarehouseDate"  name="playWarehouseDate" ng-model="deliverTransport.playWarehouseDate" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.playWarehouseDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="dtContact"><span class="required"> * </span>联系人：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="dtContact" name="dtContact" ng-model="deliverTransport.contact" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.contact}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="dtContactNum"><span class="required"> * </span>联系电话：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="dtContactNum"  name="dtContactNum" ng-model="deliverTransport.contactNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliverTransport.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label"> 备注：</label>
                                                    <div class="col-md-8">
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
         			<!-- 运输信息END -->
					<!-- 收货信息START -->
                        <div class="portlet-title">
                            <div class="caption">收货信息</div>
                            <div class="actions">
                             <!--    <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button> -->
                            </div>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="warehouseSerial"> <span class="required"> * </span>收货仓库：</label>
                                                    <div class="col-md-8">
                                                    	<select class="bs-select form-control order" data-live-search="true"  id="warehouseSerial"  name="warehouseSerial" ng-model="takeDeliver.warehouseSerial" ng-change="getWarehouseName()" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                                    </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.warehouseSerial}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" >仓库地址：</label>
                                                    <div class="col-md-8">
                                                       <!--  <input type="text" class="form-control" id="comName"  name="comName" ng-model="deliver.comName" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div> -->
                                                         <p class="control-label left">{{takeDeliver.warehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="takeDeliverDate"> <span class="required"> * </span>收货日期：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control  form-control-inline input-medium date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="takeDeliverDate"  name="takeDeliverDate" ng-model="takeDeliver.takeDeliverDate" ng-hide="deliverAdd" >
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
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tdReceiver"> <span class="required"> * </span>收货人：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="tdReceiver" name="tdReceiver" ng-model="takeDeliver.receiver" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.receiver}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tdContactNum"> <span class="required"> * </span>联系电话：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="tdContactNum"  name="tdContactNum" ng-model="takeDeliver.contactNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" >备注：</label>
                                                    <div class="col-md-8">
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
         			<!-- 收货信息END -->
         			<!-- 物料信息START -->
                        <div class="portlet-title">
                            <div class="caption">物料信息</div>
                            <div class="actions">
                            </div>
                        </div>
                        <div class="portlet-body">
						<div class="table-scrollable">
							<table
								class="table table-striped table-bordered table-advance table-hover">
								<thead>
									<tr>
										<th  rowspan="2">物料编号</th>
										<th  rowspan="2">物料名称</th>
										<th  rowspan="2">规格型号</th>
										<th  rowspan="2">单位</th>
										<th  rowspan="2">批次号</th>
										<th  rowspan="2">生产日期</th>
										<th colspan="3" style="text-align: center;">发货</th>
										<th colspan="3"  style="text-align: center;">收货</th>
										<th colspan="3"  style="text-align: center;">检验</th>
										<th colspan="5"  style="text-align: center;">入库</th>
										<th rowspan="2">状态</th>
									</tr>
									<tr>
										<th>订单数量</th>
										<th>发货数量</th>
										<th>备注</th>
										<th>实收数量</th>
										<th>拒收数量</th>
										<th>备注</th>
										<th>合格数量</th>
										<th>不合格数量</th>
										<th>备注</th>
										<th>入库数量</th>
										<th>未入数量</th>
										<th>仓库</th>
										<th>库位</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody data-repeater-list="group-a"> 
									<tr data-repeater-item ng-repeat="materiel in orderMateriels track by $index" repeat-done="repeatDone()">
										<td>{{materiel.materiel.materielNum}}</td>
										<td>{{materiel.materiel.materielName}}</td>
										<td>{{materiel.materiel.specifications}}</td>
										<td>{{materiel.materiel.unit}}</td>
										<td>
											<div class="col-md-12 form-group">
                                                 <input type="text" class="form-control input-small" id="batchNum{{$index}}" name="batchNum"  ng-model="materiel.batchNum" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
                                            </div>
                                        </td>
										<td>
											<div class="col-md-12 form-group">
													<input type="text" class="form-control  input-small date date-picker" data-date-format="yyyy-mm-dd"
													data-date-viewmode="years" readonly="" id="manufactureDate{{$index}}" ng-model="materiel.manufactureDate" name="manufactureDate"
														placeholder=""> 
													<span class="help-block"></span>
											</div>
										</td>
										<td>{{materiel.amount}}</td>
										<td>
											<div class="col-md-12 form-group">
                                                 <input type="text" class="form-control input-small" id="deliverCount{{$index}}" name="deliverCount" data-ordercount="{{materiel.amount}}"  ng-model="materiel.deliverCount" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
                                            </div>
										</td>
										<td>
											<div class="col-md-12 form-group">
                                                 <input type="text" class="form-control input-small" id="deliverRemark{{$index}}" name="deliverRemark"   ng-model="materiel.deliverRemark" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
                                            </div>
										</td>
										<td>
											<div class="col-md-12 form-group">
                                                 <input type="text" class="form-control input-small" id="acceptCount{{$index}}" name="acceptCount" data-delivercount="{{materiel.deliverCount}}"  ng-model="materiel.acceptCount" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
                                            </div>
										</td>
										<td>
											<span ng-if="materiel.deliverCount!=undefined && materiel.acceptCount!=undedined">{{materiel.deliverCount-materiel.acceptCount}}</span>
										</td>
										<td>
											<div class="col-md-12">
                                                 <input type="text" class="form-control input-small" id="takeRemark{{$index}}" name="takeRemark"  ng-model="materiel.takeRemark" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
                                            </div>
										</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr ng-if="orderMateriels==undefined||orderMateriels.length==0">
											<td colspan="22" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
						</div>
					 </div>
         			<!-- 物料信息END -->
         			<div class="row">
         				<div class="col-md-5"></div>
         				<div class="col-md-1">
         					 <button   ng-hide="deliverAdd" class="btn blue" ng-click="saveTakeDelivery()">
                                            <i class="fa fa-save"></i> 确认收货 </button>
         				</div>
         				<div class="col-md-1">
         					 <button   ng-hide="deliverAdd" class="btn red outline" ng-click="cancelTakeDelivery()">
                                            <i class="fa fa-undo"></i> 取消 </button>
         				</div>
         				<div class="col-md-5"></div>
         			</div>
       			 </div>
       			 </form>
    		</div>
		</div>
	</div>
</div>
<!-- END MAIN CONTENT -->
