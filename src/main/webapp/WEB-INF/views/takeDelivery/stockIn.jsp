<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.left{
	float: left;
}
.form-control {
    outline: 40px!important;
}
/* #deliveryMaterielTable thead tr th{
	text-align: center;
	vertical-align:middle;
} */
</style>
<!-- <h3 class="page-title s_tip"> 新建入库记录
</h3> -->
<!-- <div class="page-bar">
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

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<form  id="stockInForm" class=""  >
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
				 	<ul class="nav nav-tabs">
					
						<li class="active bold"><a data-target="#tab_1_1"
							data-toggle="tab">入库信息</a></li>
						<!-- <li class="bold"><a data-target="#tab_1_3" data-toggle="tab">收货信息</a>
						</li> -->
						<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">发货信息</a></li>
						<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">物料信息</a></li>
						<li class="dropdown pull-right tabdrop">
						<button   class="btn green  btn-sm btn-circle" ng-click="saveStockIn()" ng-if="record.wmsDeliveryId==null">
                              		<i class="fa fa-check"></i> 确认入库 </button>
                        <button   class="btn green  btn-sm btn-circle" ng-click="getWmsStockIn()" ng-if="record.wmsDeliveryId!=null">
                              		<i class="fa fa-check"></i> 同步WMS入库 </button>
							<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
						</li>						
					</ul>
					<!-- 基本信息START -->
					<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
                        <div class="portlet-title">
                            <div class="caption"></div>
                            <div class="actions" style="float:right;height: 18px;">
                              <!--   <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button> -->
                                 <!-- <button   class="btn green  btn-sm btn-circle" ng-click="saveStockIn()">
                              		<i class="fa fa-check"></i> 确认入库 </button> -->
                              		 <button   class="btn green  btn-sm btn-circle"   ng-hide="deliverAdd"  ng-click="saveStockIn('save')">
                              		<i class="fa fa-save"></i>保存 </button>
                      			 <button    class="btn defualt  btn-sm btn-circle" ng-click="cancelStockIn()" onclick="return false;">
                              		<i class="fa fa-mail-reply"></i> 取消 </button>
                            </div>
                        </div>
                       
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
                                                <%--  <jsp:include  page="stockInOutDeliveryInfo.jsp" /> --%>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="inOutNum">入库单号 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="inOutNum" name="inOutNum" ng-model="record.inOutNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.inOutNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="inOutType">入库类型<span class="required"> * </span> </label>
	                                                 <div class="">
	                                                 	<select class="form-control"  name="inOutType" ng-model="record.inOutType"  data-size="8" ng-hide="deliverAdd">
	                                                 	<option value="">无</option>
			                                                   <option value="采购">采购</option>
			                                                    <option value="备品备件">备品备件</option>
			                                                     <option value="退货">退货</option>
			                                                      <option value="生产">生产</option>
			                                             </select>
                                                     </div>
                                                     <div class="form-control-focus"> </div>
                                                     <p class="control-label left" ng-show="deliverView">{{record.inOutType}}</p>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="stockDate">入库日期 <span class="required"> * </span></label>
                                                    <div class="">
                                                      <!--  <input type="text" class="form-control  date-picker" size="16"  data-date-format="yyyy-mm-dd hh:mm:ss" 
                                                         id="stockDate"  name="stockDate" ng-model="record.stockDate" ng-hide="deliverAdd" readonly="readonly"> -->
                                                         <input type="text" class="form-control  date-picker" size="16"  
                                                         id="stockDate"  name="stockDate" ng-model="record.stockDate" ng-hide="deliverAdd" disabled>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.stockDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!-- <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="takeDeliverSerial">收货单号 </label>
	                                                 <div class="">
	                                                        <input id="takeDeliverSerial"   name="takeDeliverSerial" type="text" class="form-control" ng-model="record.takeDeliverNum" disabled="disabled">
	                                                        <span class="input-group-btn" style="vertical-align: top;">
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
                                                     </div>
                                            </div>
										</div> -->
										<!--/span-->
										
									</div>
									<!--/row-->
									<div class="row">
										<!-- <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="orderSerial"> 采购订单号</label>
                                                    <div class="">
                                                    		<input type="text" class="form-control" value="{{record.delivery.orderNum}}" disabled="disabled">
                                                         <p class="control-label left" >{{record.orderNum}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="supplyComId"> 入库仓库</label>
                                                    <div class="">
                                                    	<input type="hidden" class="form-control" value="{{warehouseCount}}" disabled="disabled">
                                                    	<input type="hidden" class="form-control" value="{{WarehouseName}}" disabled="disabled">
                                                    	<input type="text" class="form-control"      value="{{deliver.takeDelivery.warehouse.warehouseName}}" disabled="disabled"  ng-hide="deliverAdd">
                                                         <p class="control-label left"   ng-show="deliverView">{{deliver.takeDelivery.takeDeliverAddress}}</p>
                                                    </div>
                                            </div>
										</div>
						<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="supplyComId"> 入库数量</label>
                                                    <div class="">
                                                    	<input type="text" class="form-control"   ng-model="record.materielCount"  disabled="disabled"  ng-hide="deliverAdd"/>
                                                       <p class="control-label left"   ng-show="deliverView">{{record.materielCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">包装类型</label>
													<div class="">
														<select class="form-control" id="packageType"
															name="packageType" ng-model="record.packageType"
															ng-hide="deliverAdd"><!--ng-init="delivery.deliverType='贸易发货'"  -->
															<option   value=""></option>
															<option   value="原厂包装">原厂包装</option>
															<option value="供应商包装">供应商包装</option>
															<option value="其他类型">其他类型</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView">
															{{record.packageType}}</p>
													</div>
												</div>
											</div>
										<!--/span-->
										<!-- <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="shipper"> 入库库区</label>
                                                    <div class="">
                                                    		<input type="text" class="form-control" value="{{positionCount}}" disabled="disabled">
                                                         <p class="control-label left" >{{positionCount}}</p>
                                                    </div>
                                            </div>
										</div>
										/span -->
										
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="packageSpecifications">包装规格</label>
                                                    <div class="">
                                                         <input type="text" class="form-control" id="packageSpecifications"  name="packageSpecifications" ng-model="record.packageSpecifications" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.packageSpecifications}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="remark">包装件数</label>
                                                    <div class="">
                                                         <input type="text" class="form-control" id="packageCount"  name="packageCount" ng-model="record.packageCount"   ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.packageCount}}</p>
                                                    </div>
                                            </div>
										</div>
									<!-- 	<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="operator">发货方 <span class="required"> * </span></label>
                                                    <div class="">
                                                       <input type="text" class="form-control" value="{{record.shipperOrReceiver}}" disabled="disabled">
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="operator">操作员 <!-- <span class="required"> * </span> --></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="operator" name="operator" ng-model="record.operator" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.operator}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="contactNum">联系方式 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="contactNum" name="contactNum"   ng-model="record.contactNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="remark">备注</label>
                                                    <div class="">
                                                         <input type="text" class="form-control" id="remark"  name="remark" ng-model="record.remark" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="remark">状态</label>
                                                    <div class="">
                                                         <p class="control-label left" ng-show="deliverView"><span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待入库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已入库</span></p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<!-- <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="approval">状态</label>
                                                    <div class="">
                                                         <p class="control-label left" ng-show="deliverView">待检验</p>
                                                    </div>
                                            </div>
										</div>
										/span -->
									</div>
									<!--/row-->
								</div>
         				</div>
         					</div>
         					<div class="tab-pane fade" id="tab_1_2"  >
         					<jsp:include  page="stockInOutDeliveryInfo.jsp" />
         					</div>
         					<div class="tab-pane fade" id="tab_1_3"  >
         					 <div class="portlet-body">
						<div class="table-scrollable">
							<table id="deliveryMaterielTable"
								class="table table-striped table-bordered table-advance table-hover">
								<thead>
									<tr>
										<th  rowspan="2">物料编号</th>
										<th  rowspan="2">物料名称</th>
										<th  rowspan="2">规格型号</th>
										<th  rowspan="2">单位</th>
										<th rowspan="2">订单数量</th>
										<th  rowspan="2">发货数量</th>
										<th  rowspan="2">合格数量</th>
										<!-- <th  rowspan="2">生产批次</th> -->
										<th  rowspan="2">入库数量</th>
										<th  rowspan="2">未入数量</th>
										<th  rowspan="2">备注</th>
										<th  rowspan="2">附件</th>
										<th rowspan="2">状态</th>
									</tr>
								
								</thead>
								<tbody data-repeater-list="group-a"> 
									<tr data-repeater-item ng-repeat="materiel in takeDeliveryMateriels track by materiel.serialNum" repeat-done="setDefualtNum(this)" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>{{materiel.orderMateriel.amount}}</td>
										<td>{{materiel.deliverCount}}</td>
									<td>{{materiel.stockInQualifiedCount==null?"无入库检验":materiel.stockInQualifiedCount}}</td>
										<!-- <td>{{materiel.refuseCount}}</td>
										<td>{{materiel.takeRemark}}</td>
										<td>{{materiel.stockInQualifiedCount}}</td>
										<td>{{materiel.stockInUnqualifiedCount}}</td>
										<td>{{materiel.stockInCheckRemark}}</td> -->
										<td >
												<!-- <span ng-repeat="stockInBatch in materiel.stockInBatchs track by $index">
												<span ng-if="!$first">;</span> {{stockInBatch.batchNum}}({{stockInBatch.stockInCount}})
												</span>
                                                <button ng-if="materiel.stockInBatchs.length==0" class="btn blue btn-sm btn-circle"
													ng-click="showStockBatch(materiel,$index)" onclick="return false;"  data-toggle="modal" >
													<i class="fa fa-plus"></i>添加
												</button>
												<button ng-if="materiel.stockInBatchs.length!=0" class="btn blue btn-sm btn-circle"
													ng-click="showStockBatch(materiel,$index)" onclick="return false;"  data-toggle="modal" >
													<i class="fa fa-edit"></i>修改
												</button> -->
												<div class="col-md-12 form-group">
                                                 <input type="text" class="form-control input-small" id="stockCount{{$index}}" name="stockCount" data-acceptcount="{{materiel.acceptCount}}"  ng-model="materiel.stockInCount"   ng-if="materiel.stockInCount==null"   ng-init="materiel.stockInCount=materiel.deliverCount"  ng-hide="deliverAdd" >
                                                 <input type="text" class="form-control input-small" id="stockCount{{$index}}" name="stockCount" data-acceptcount="{{materiel.acceptCount}}"  ng-model="materiel.stockInCount"   ng-if="materiel.stockInCount!=null"  ng-hide="deliverAdd" >
                                                 <p class="form-control-static" ng-show="deliverView">
															{{materiel.stockInCount}}</p>
                                            </div>
										</td>
										<!-- <td>
										{{materiel.stockInCount==null?0:materiel.stockInCount}}
										</td> -->
										<td>
											<span ng-if="materiel.acceptCount!=undefined && materiel.stockInCount!=undefined">{{materiel.acceptCount-materiel.stockInCount}}</span>
											<span ng-if="materiel.acceptCount!=undefined && materiel.stockInCount==undefined">{{materiel.acceptCount}}</span>
										</td>
										<!-- <td>
											<div class="col-md-12 form-group">
                                                <select ng-if="$first" class="form-control input-small"  data-live-search="true"  id="warehouseSerial" ng-init="warehouses[0].serialNum" ng-change="getPositionsAndSelectedAll(materiel)"  name="warehouseSerial" ng-model="materiel.warehouseSerial"  data-size="8">
	                                                 <option value=""></option>
	                                                  <option   class="{{materiel.serialNum}}" ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                            </select>
                                                <select ng-if="!$first" class="form-control input-small" data-live-search="true"  id="warehouseSerial" ng-init="warehouses[0].serialNum" ng-change="getPositions(materiel)"  name="warehouseSerial" ng-model="materiel.warehouseSerial"  data-size="8">
	                                                 <option value=""></option>
	                                                  <option  class="{{materiel.serialNum}}" ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                            </select>
	                                            <div class="form-control-focus"></div>
                                            </div>
										</td>
										<td>
											<div class="col-md-12">
                                                <select class="form-control input-small"  data-live-search="true"  id="positionSerial" ng-change="countPosition()" ng-init="materiel.warehousePositons[0].serialNum"   name="positionSerial" ng-model="materiel.positionSerial"    data-size="8">
	                                                   <option value=""></option>
	                                                   <option  ng-repeat="warehousePositon in materiel.warehousePositons" value="{{warehousePositon.serialNum}}">{{warehousePositon.positionName}}</option>
	                                             </select>
	                                             <div class="form-control-focus"> </div>
                                            </div>
										</td> -->
										<td>
											<div class="col-md-6 form-group">
                                                 <input type="text" class="form-control input-small" id="stockRemark{{$index}}" name="stockRemark"  ng-model="materiel.stockRemark"   ng-init="materiel.stockRemark=materiel.stockInRemark"  ng-hide="deliverAdd">
                                                 <div class="form-control-focus"> </div>
                                                 <p class="form-control-static" ng-show="deliverView">
															{{materiel.stockRemark}}</p>
                                            </div>
										</td> 
										<td >
										<div class="col-md-6 form-group">
                                                
                                            </div>
										</td>
										<td >
											<span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待入库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已入库</span>
										</td> 
									</tr>
									<tr ng-if="takeDeliveryMateriels==undefined||takeDeliveryMateriels.length==0">
											<td colspan="12" align="center">暂无数据</td>
									</tr>
								</tbody>
								<tfoot>
													<tr>
														<td>合计</td>
														<td></td>
														<td></td>
														<td></td>
														<td>{{totalOrderCount}}</td>
														<td>{{totalDeliveryCount}}</td>
														<td>{{totalQualifiedCount}}</td>
														<td>{{totalStockCount()}}</td><!--{{totalStockInCount}}  -->
														<td>{{totalUnStockInCount()}}</td><!--{{totalUnstockInCount}}  -->
														<td></td>
														<td></td>
														<!-- <td></td> -->
													</tr>
												</tfoot>
							</table>
						</div>
					 </div>
         					</div>
         				</div>
         			<!-- 基本信息END -->
         			<!-- 物料信息START -->
                        <!-- <div class="portlet-title">
                            <div class="caption">物料信息</div>
                            <div class="actions">
                            </div>
                        </div> -->
                      <!--   <div class="portlet-body">
						<div class="table-scrollable">
							<table id="deliveryMaterielTable"
								class="table table-striped table-bordered table-advance table-hover">
								<thead>
									<tr>
										<th  rowspan="2">物料编号</th>
										<th  rowspan="2">物料名称</th>
										<th  rowspan="2">规格型号</th>
										<th  rowspan="2">单位</th>
										<th  rowspan="2">批次号</th>
										<th  rowspan="2">生产日期</th>
										<th  rowspan="2">发货数量</th>
										<th colspan="3"  style="text-align: center;">收货</th>
										<th colspan="3"  style="text-align: center;">检验</th>
										<th colspan="4"  style="text-align: center;">入库</th>
										<th rowspan="2">状态</th>
									</tr>
									<tr>
										<th>实收数量</th>
										<th>拒收数量</th>
										<th>备注</th>
										<th>合格数量</th>
										<th>不合格数量</th>
										<th>备注</th>
										<th>生产批次</th>
										<th>入库数量</th>
										<th>未入数量</th>
										<th>仓库</th>
										<th>库区</th>
										<th>备注</th> 
									</tr>
								</thead>
								<tbody data-repeater-list="group-a"> 
									<tr data-repeater-item ng-repeat="materiel in takeDeliveryMateriels track by materiel.serialNum" repeat-done="setDefualtNum(this)" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>{{materiel.batchNum}}</td>
										<td>{{materiel.manufactureDate}}</td>
										<td>{{materiel.acceptCount}}</td>
										<td>{{materiel.refuseCount}}</td>
										<td>{{materiel.takeRemark}}</td>
										<td>{{materiel.stockInQualifiedCount}}</td>
										<td>{{materiel.stockInUnqualifiedCount}}</td>
										<td>{{materiel.stockInCheckRemark}}</td>
										<td >
												<span ng-repeat="stockInBatch in materiel.stockInBatchs track by $index">
												<span ng-if="!$first">;</span> {{stockInBatch.batchNum}}({{stockInBatch.stockInCount}})
												</span>
                                                <button ng-if="materiel.stockInBatchs.length==0" class="btn blue btn-sm btn-circle"
													ng-click="showStockBatch(materiel,$index)" onclick="return false;"  data-toggle="modal" >
													<i class="fa fa-plus"></i>添加
												</button>
												<button ng-if="materiel.stockInBatchs.length!=0" class="btn blue btn-sm btn-circle"
													ng-click="showStockBatch(materiel,$index)" onclick="return false;"  data-toggle="modal" >
													<i class="fa fa-edit"></i>修改
												</button>
										</td>
										<td>
										{{materiel.stockInCount}}
										</td>
										<td>
											<span ng-if="materiel.acceptCount!=undefined && materiel.stockCount!=undedined">{{materiel.acceptCount-materiel.stockInCount}}</span>
										</td>
										<td>
											<div class="col-md-12 form-group">
                                                <select ng-if="$first" class="form-control input-small"  data-live-search="true"  id="warehouseSerial" ng-init="warehouses[0].serialNum" ng-change="getPositionsAndSelectedAll(materiel)"  name="warehouseSerial" ng-model="materiel.warehouseSerial"  data-size="8">
	                                                 <option value=""></option>
	                                                  <option   class="{{materiel.serialNum}}" ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                            </select>
                                                <select ng-if="!$first" class="form-control input-small" data-live-search="true"  id="warehouseSerial" ng-init="warehouses[0].serialNum" ng-change="getPositions(materiel)"  name="warehouseSerial" ng-model="materiel.warehouseSerial"  data-size="8">
	                                                 <option value=""></option>
	                                                  <option  class="{{materiel.serialNum}}" ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                            </select>
	                                            <div class="form-control-focus"></div>
                                            </div>
										</td>
										<td>
											<div class="col-md-12">
                                                <select class="form-control input-small"  data-live-search="true"  id="positionSerial" ng-change="countPosition()" ng-init="materiel.warehousePositons[0].serialNum"   name="positionSerial" ng-model="materiel.positionSerial"    data-size="8">
	                                                   <option value=""></option>
	                                                   <option  ng-repeat="warehousePositon in materiel.warehousePositons" value="{{warehousePositon.serialNum}}">{{warehousePositon.positionName}}</option>
	                                             </select>
	                                             <div class="form-control-focus"> </div>
                                            </div>
										</td>
										<td>
											<div class="col-md-12 form-group">
                                                 <input type="text" class="form-control input-small" id="stockRemark{{$index}}" name="stockRemark"  ng-model="materiel.stockRemark"   ng-init="materiel.stockRemark=materiel.stockInRemark" >
                                                 <div class="form-control-focus"> </div>
                                            </div>
										</td> 
										<td>
											<span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待入库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已入库</span>
										</td> 
									</tr>
									<tr ng-if="takeDeliveryMateriels==undefined||takeDeliveryMateriels.length==0">
											<td colspan="15" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
						</div>
					 </div> -->
         			<!-- 物料信息END -->
         			<!-- <div class="row">
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
         			</div> -->
       			 </div>
       			
    		</div>
		</div>
		 </form>
	</div>
</div>

<script type="text/javascript">
	/* $('#stockDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd hh:mm:ss",
		language : "zh-CN"
	}); */
</script>
<!-- END MAIN CONTENT -->
<jsp:include page="selectTakeDelivery.jsp"></jsp:include>
<jsp:include page="addStockBatch.jsp"></jsp:include>
