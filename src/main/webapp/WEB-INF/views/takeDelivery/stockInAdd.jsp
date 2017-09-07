<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.left{
	float: left;
}
</style>
<h3 class="page-title s_tip"> 新建入库记录
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
            <a ui-sref="takeDelivery">入库记录</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a class="s_tip">新建入库记录</a>
        </li>
    </ul>

</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<form  id="stockInForm" class="form-horizontal"  >
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
				 	
					<!-- 基本信息START -->
                        <div class="portlet-title">
                            <div class="caption">入库信息</div>
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
                                                    <label class="col-md-4 control-label" for="inOutNum"> <span class="required"> * </span>入库单号：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="inOutNum" name="inOutNum" ng-model="record.inOutNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.inOutNum}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="takeDeliverSerial"> <span class="required"> * </span>收货单号：</label>
                                                    <div class="col-md-8">
                                                    	<select class="bs-select form-control order" data-live-search="true"  id="takeDeliverSerial"  name="takeDeliverSerial" ng-change="getTakeDeliverMateriel()" ng-model="record.takeDeliverSerial" ng-hide="deliverAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="takeDeliver in takeDeliverys" value="{{takeDeliver.takeDelivery.serialNum}}" data-deliveryserial="{{takeDeliver.serialNum}}" >{{takeDeliver.takeDelivery.takeDeliverNum}}</option>
	                                                   	</select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.takeDeliverSerial}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="orderSerial"> 采购订单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.orderNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId"> 入库仓库：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{warehouseCount}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="shipper"> 入库库位：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{positionCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="stockDate"> <span class="required"> * </span>入库日期：</label>
                                                    <div class="col-md-8">
                                                       <input type="text" class="form-control  date-picker" size="16"  data-date-format="yyyy-mm-dd" data-date-viewmode="years"
                                                         id="stockDate"  name="stockDate" ng-model="record.stockDate" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.stockDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="operator"> <span class="required"> * </span>操作员：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="operator" name="operator" ng-model="record.operator" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.maker}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="contactNum"> <span class="required"> * </span>联系方式：</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="contactNum"  name="contactNum" ng-model="record.contactNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="approval"> <span class="required"> * </span>状态：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" ng-show="deliverView">待检验</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
         				</div>
         			<!-- 基本信息END -->
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
										<th colspan="3"  style="text-align: center;">收货</th>
										<th colspan="5"  style="text-align: center;">入库</th>
										<th rowspan="2">状态</th>
									</tr>
									<tr>
										<th>实收数量</th>
										<th>拒收数量</th>
										<th>备注</th>
										<th>入库数量</th>
										<th>未入数量</th>
										<th>仓库</th>
										<th>库位</th>
										<th>备注</th> 
									</tr>
								</thead>
								<tbody data-repeater-list="group-a"> 
									<tr data-repeater-item ng-repeat="materiel in takeDeliveryMateriels track by $index" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>{{materiel.batchNum}}</td>
										<td>{{materiel.manufactureDate}}</td>
										<td>{{materiel.acceptCount}}</td>
										<td>{{materiel.refuseCount}}</td>
										<td>{{materiel.takeRemark}}</td>
										<td>
											<div class="col-md-12 form-group">
                                                 <input type="text" class="form-control input-small" id="stockCount{{$index}}" name="stockCount" data-acceptcount="{{materiel.acceptCount}}"  ng-model="materiel.stockCount" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
                                            </div>
										</td>
										<td>
											<span ng-if="materiel.acceptCount!=undefined && materiel.stockCount!=undedined">{{materiel.acceptCount-materiel.stockCount}}</span>
										</td>
										<td>
											<div class="col-md-12 form-group">
                                                <select class="bs-select form-control order" data-live-search="true"  id="warehouseSerial" ng-init="warehouses[0].serialNum" ng-change="getPositions(materiel)"  name="warehouseSerial" ng-model="materiel.warehouseSerial"  data-size="8">
	                                                 <!--  <option value=""></option> -->
	                                                  <option  ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                            </select>
	                                            <div class="form-control-focus"></div>
                                            </div>
										</td>
										<td>
											<div class="col-md-12">
                                                <select class="bs-select form-control order" data-live-search="true"  id="positionSerial" ng-change="countPosition()" ng-init="materiel.warehousePositons[0].serialNum"   name="positionSerial" ng-model="materiel.positionSerial"    data-size="8">
	                                                   <!-- <option value=""></option> -->
	                                                   <option  ng-repeat="warehousePositon in materiel.warehousePositons" value="{{warehousePositon.serialNum}}">{{warehousePositon.positionName}}</option>
	                                             </select>
	                                             <div class="form-control-focus"> </div>
                                            </div>
										</td>
										<td>
											<div class="col-md-12 form-group">
                                                 <input type="text" class="form-control input-small" id="stockRemark{{$index}}" name="stockRemark" data-delivercount="{{materiel.stockRemark}}"  ng-model="materiel.stockRemark"  >
                                                 <div class="form-control-focus"> </div>
                                            </div>
										</td> 
									</tr>
									<tr ng-if="orderMateriels==undefined||orderMateriels.length==0">
											<td colspan="15" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
						</div>
					 </div>
         			<!-- 物料信息END -->
         			<div class="row">
         				<div class="col-md-5"></div>
         				<div class="col-md-1">
         					 <button   ng-hide="deliverAdd" class="btn blue" ng-click="saveStockIn()">
                                            <i class="fa fa-check"></i> 确认入库 </button>
         				</div>
         				<div class="col-md-1">
         					 <button   ng-hide="deliverAdd" class="btn red btn-outline" ng-click="cancelStockIn()">
                                            <i class="fa fa-undo"></i> 取消 </button>
         				</div>
         				<div class="col-md-5"></div>
         			</div>
       			 </div>
       			
    		</div>
		</div>
		 </form>
	</div>
</div>
<!-- END MAIN CONTENT -->