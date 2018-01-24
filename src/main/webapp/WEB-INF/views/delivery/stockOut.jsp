<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.left{
	float: left;
}

/* #deliveryMaterielTable thead tr th{
	text-align: center;
	vertical-align:middle;
} */
</style>
<!-- <h3 class="page-title s_tip"> 新建出库记录
</h3>
<div class="page-bar">
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
            <a ui-sref="delivery">出库记录</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a class="s_tip">新建出库记录</a>
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
							data-toggle="tab">出库信息</a></li>
						<!-- <li class="bold"><a data-target="#tab_1_3" data-toggle="tab">收货信息</a>
						</li> -->
						<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">发货信息</a></li>
						<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">物料信息</a></li>
						<li class="dropdown pull-right tabdrop">
						<button   class="btn green  btn-sm btn-circle" ng-click="saveStockOut()">
                              		<i class="fa fa-check"></i> 确认出库 </button>
							<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
						</li>												
					</ul>
					<!-- 基本信息START -->
					<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
                        <div class="portlet-title">
                            <div class="caption"></div>
                            <div class="actions" style="float:right">
                              <!--   <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button> -->
                                 <!-- <button   class="btn green  btn-sm btn-circle" ng-click="saveStockOut()">
                              		<i class="fa fa-check"></i> 确认出库 </button> -->
                              		 <button   class="btn green  btn-sm btn-circle"   ng-hide="deliverAdd"  ng-click="saveStockOut('save')">
                              		<i class="fa fa-save"></i>保存 </button>
                              		<!-- <button   class="btn green  btn-sm btn-circle"  ng-show="deliverView"   ng-click="editStockOut(record.serialNum)">
                              		<i class="fa fa-save"></i>编辑 </button> -->
                      			 <button    class="btn defualt  btn-sm btn-circle" ng-click="cancelStockOut()" onclick="return false;">
                              		<i class="fa fa-mail-reply"></i> 取消 </button>
                            </div><br/><br/>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输出正确数据！</div>
                                                <%-- <jsp:include  page="../takeDelivery/stockInOutDeliveryInfo.jsp" /> --%>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="inOutNum">出库单号 <span class="required"> * </span></label>
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
                                                    <label class="control-label bold" for="inOutType">出库类型 <span class="required"> * </span></label>
	                                                 <div class="">
	                                                 	<select class="form-control" id="inOutType" name="inOutType"  ng-model="record.inOutType"   ng-hide="deliverAdd">
	                                                 	<option value="">无</option>
			                                                  <option value="销售">销售</option>
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
                                                    <label class="control-label bold" for="stockDate">出库日期 <span class="required"> * </span></label>
                                                    <div class="">
                                                       <input type="text" class="form-control  date-picker" size="16"  data-date-format="yyyy-mm-dd hh:ii" data-date-viewmode="years"
                                                         id="stockDate"  name="stockDate" ng-model="record.stockDate" ng-hide="deliverAdd" disabled>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.stockDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!-- <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverSerial">发货单号 <span class="required"> * </span></label>
                                                     <input id="deliverSerial"   name="deliverSerial" type="text" class="form-control" ng-model="record.deliverNum" disabled="disabled">
                                                     <div class="form-control-focus"> </div>
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
                                                    		<input type="text" class="form-control" value="{{record.orderNum}}" disabled="disabled">
                                                         <p class="control-label left" >{{record.orderNum}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="supplyComId"> 出库仓库</label>
                                                    <div class="">
                                                    		<input type="text" class="form-control"   ng-hide="deliverAdd"   value="{{deliver.deliveryAddress}}" disabled="disabled">
                                                      <p class="control-label left"   ng-show="deliverView">{{deliver.deliveryAddress}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<!-- <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="shipper"> 出库库区</label>
                                                    <div class="">
                                                    		<input type="text" class="form-control" value="{{positionCount}}" disabled="disabled">
                                                         <p class="control-label left" >{{positionCount}}</p>
                                                    </div>
                                            </div>
										</div>
										/span -->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="stockDate">出库数量 </label>
                                                    <div class="">
                                                       <input type="text" class="form-control"  ng-hide="deliverAdd"  ng-model="record.materielCount"  disabled="disabled"  ng-if="record.materielCount!=undefined">
                                                       <input type="text" class="form-control"   ng-hide="deliverAdd" ng-model="record.materielCount"    ng-init="record.materielCount=0"   disabled="disabled"  ng-if="record.materielCount==undefined">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{record.materielCount}}</p>
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
									</div>
									<!--/row-->
									<div class="row">
										
										<!-- <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="operator">收货方 <span class="required"> * </span></label>
                                                    <div class="">
                                                       <input type="text" class="form-control" value="{{record.shipperOrReceiver}}" disabled="disabled">
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
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
										<div class="col-md-4">
										
											<div class="form-group">
                                                    <label class="control-label bold" for="operator">操作员<!--  <span class="required"> * </span> --></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="operator" ng-model="record.operator" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.maker}}</p>
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
                                                        <input type="text" class="form-control" id="contactNum" ng-model="record.contactNum" ng-hide="deliverAdd" >
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
                                                         <p class="control-label left" ><span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待出库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已出库</span></p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
											<div class="row"     style="border-top:1px solid #dddddd;padding-top: 20px;"  >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运输方式</label>
													<div class="">
														<!-- <input type="text" class="form-control" name="transportType"
													ng-model="deliveryTransport.transportType" ng-show="input" /> -->
														<select class="form-control" id="transportType"
															name="transportType" ng-model="record.transportType"
															ng-hide="deliverAdd">
															<option value="水路运输">水路运输</option>
															<option value="铁路运输">铁路运输</option>
															<option value="公路运输">公路运输</option>
															<option value="铁路运输">铁路运输</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView">
															{{record.transportType}}</p>
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
															ng-model="record.transport" ng-hide="deliverAdd" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView">
															{{record.transport}}</p>
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
															ng-model="record.shipNumber" ng-hide="deliverAdd" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView">
															{{record.shipNumber}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<div class="row"  >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系人</label>
													<div class="">
														<input type="text" class="form-control"
															name="transportContact" ng-hide="deliverAdd"
															ng-model="record.transportContact" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView">
															{{record.transportContact}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<input type="text" class="form-control"
															name="transportContactNum" ng-hide="deliverAdd"
															ng-model="record.transportContactNum" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView">
															{{record.transportContactNum}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" class="form-control" name="transportRemark"
															ng-hide="deliverAdd"  ng-model="record.transportRemark" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="deliverView">
															{{record.transportRemark}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
								</div>
         				</div>
         					</div>
         					<div class="tab-pane fade" id="tab_1_2"  >
         					<jsp:include  page="../takeDelivery/stockInOutDeliveryInfo.jsp" />
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
										<th  rowspan="2">订单数量</th>
										<th rowspan="2">发货数量</th>
										<th  rowspan="2">生产批次</th>
										<th  rowspan="2">出库数量</th>
										<th rowspan="2">未出数量</th>
										<th rowspan="2">当前库存</th>
										<th rowspan="2">备注</th>
										<th rowspan="2">附件</th>
										<th rowspan="2">状态</th>
									</tr>
								</thead>
								<tbody data-repeater-list="group-a"> 
									<tr data-repeater-item ng-repeat="materiel in takeDeliveryMateriels track by $index" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>{{materiel.orderMateriel.amount}}</td>
										<td>{{materiel.deliverCount}}</td>
										<!-- <td>{{materiel.orderMateriel.amount}}</td>
										<td>{{materiel.deliverRemark}}</td> -->
										<td >
										<!-- <span id="{{materiel.serialNum}}"  ng-repeat="stockOutBatch in materiel.stockOutMateriels track by $index"  ng-if="materiel.stockOutMateriels.length!=0">
										<span ng-if="!$first">;</span> {{stockOutBatch.batchNum}}({{stockOutBatch.outCount}})
										</span> -->
										<!-- <span id="{{materiel.serialNum}}"   ng-if="materiel.stockOutMateriels.length==0"  ></span> -->
											<span id="{{materiel.serialNum}}"    >{{materiel.inOutNums}}</span>
                                                <button class="btn blue btn-sm btn-circle"      ng-hide="deliverAdd"  ng-if="materiel.stockOutMateriels .length==0&&materiel.currentStockAmount!=0" 
								ng-click="showStockBatch($index,materiel,'add')" onclick="return false;"  data-toggle="modal" >
								<i class="fa fa-plus"></i>选择批次
							</button>
							<button class="btn blue btn-sm btn-circle"    ng-hide="deliverAdd"  ng-if="materiel.stockOutMateriels.length!=0&&materiel.currentStockAmount!=0" 
								ng-click="showStockBatch($index,materiel,'edit')" onclick="return false;"  data-toggle="modal" >
								<i class="fa fa-plus"></i>修改批次
							</button>
										</td>
										<td  ng-if="materiel.currentStockAmount!=0">{{materiel.stockCount}}</td>
										<td  ng-if="materiel.currentStockAmount==0">0</td>
										<td>
											<span ng-if="materiel.deliverCount!=undefined && materiel.stockCount!=undedined&&materiel.stockCount!=null">{{materiel.deliverCount-materiel.stockCount}}</span>
											<span ng-if="materiel.stockCount==undefined||materiel.stockCount==null">{{materiel.deliverCount}}</span>
										</td>
										<!-- <td class="form-group">
                                                 <input type="text"    style="border:none;"  readonly="readonly"       class="form-control input-small" id="stockCountinline{{materiel.serialNum}}" name="stockCount" data-delivercount="{{materiel.deliverCount}}"   data-currentstock="{{materiel.currentStockAmount}}"    ng-change="deleteOrdinaryData(materiel.serialNum)"    ng-model="materiel.stockCount" ng-hide="deliverAdd" >
                                                 
                                                 <div class="form-control-focus"> </div>
										</td> -->
										<td>
											{{materiel.currentStockAmount}}
										</td>
										
										<td class="form-group">
                                                 <input type="text" class="form-control input-small"  ng-hide="deliverAdd"  ng-model="materiel.stockRemark"  >
                                                 <div class="form-control-focus"   ng-show="deliverView">{{materiel.stockRemark}} </div>
										</td> 
										<td class="form-group">
                                                 <div class="form-control-focus"> </div>
										</td> 
										<td >
                                               <span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待出库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已出库</span>
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
														<td></td>
														<td>{{totalStockOutCount}}</td>
														<td>{{totalUnstockOutCount}}</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
												</tfoot>
							</table>
						</div>
					 </div>
         						</div>
         					
         				</div>
         			<!-- 基本信息END -->
         			<!-- 物料信息START -->
                      <!--   <div class="portlet-title">
                            <div class="caption">物料信息</div>
                            <div class="actions">
                            </div>
                        </div>
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
										<th  rowspan="2">批次号</th>
										<th  rowspan="2">生产日期</th>
										<th colspan="3"  style="text-align: center;">发货</th>
										<th colspan="5"  style="text-align: center;">出库</th>
										<th rowspan="2">状态</th>
									</tr>
									<tr>
										<th>订单数量</th>
										<th>发货数量</th>
										<th>备注</th>
										<th><span class="required" style="color: red"> * </span>出库批次</th>
										<th><span class="required" style="color: red"> * </span>出库数量</th>
										<th>当前库存</th>
										<th>未出数量</th>
										<th>仓库</th>
										<th>库区</th>
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
										<td>{{materiel.orderMateriel.amount}}</td>
										<td>{{materiel.deliverCount}}</td>
										<td>{{materiel.deliverRemark}}</td>
										<td >
										<span id="{{materiel.serialNum}}"></span>
                                                <button class="btn blue btn-sm btn-circle"
								ng-click="showStockBatch($index,materiel.orderMateriel.materielSerial,materiel.orderMateriel.orderSerial,materiel.serialNum,materiel.deliverCount)" onclick="return false;"  data-toggle="modal" >
								<i class="fa fa-plus"></i>选择批次
							</button>
										</td>
										<td class="form-group">
                                                 <input type="text"    style="border:none;"  readonly="readonly"       class="form-control input-small" id="stockCountinline{{materiel.serialNum}}" name="stockCount" data-delivercount="{{materiel.deliverCount}}"   data-currentstock="{{materiel.currentStockAmount}}"    ng-change="deleteOrdinaryData(materiel.serialNum)"    ng-model="materiel.stockCount" ng-hide="deliverAdd" >
                                                 
                                                 <div class="form-control-focus"> </div>
										</td>
										<td>
											{{materiel.currentStockAmount}}
										</td>
										<td>
											<span ng-if="materiel.deliverCount!=undefined && materiel.stockCount!=undedined">{{materiel.deliverCount-materiel.stockCount}}</span>
										</td>
										<td class="form-group">
                                                <select class="bs-select form-control order" data-live-search="true"  id="warehouseSerial" ng-init="warehouses[0].serialNum" ng-change="getPositions(materiel)"  name="warehouseSerial" ng-model="materiel.warehouseSerial"  data-size="8">
	                                                  <option value=""></option>
	                                                  <option  ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
	                                            </select>
	                                            <div class="form-control-focus"></div>
										</td>
										<td class="form-group">
                                                <select class="bs-select form-control order" data-live-search="true"  id="positionSerial" ng-change="countPosition()" ng-init="materiel.warehousePositons[0].serialNum"   name="positionSerial" ng-model="materiel.positionSerial"    data-size="8">
	                                                   <option value=""></option>
	                                                   <option  ng-repeat="warehousePositon in materiel.warehousePositons" value="{{warehousePositon.serialNum}}">{{warehousePositon.positionName}}</option>
	                                             </select>
	                                             <div class="form-control-focus"> </div>
										</td>
										<td class="form-group">
                                                 <input type="text" class="form-control input-small" id="stockRemark{{$index}}" name="stockRemark" data-delivercount="{{materiel.stockRemark}}"  ng-model="materiel.stockRemark"  >
                                                 <div class="form-control-focus"> </div>
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
                                            <i class="fa fa-check"></i> 确认出库 </button>
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
<!-- END MAIN CONTENT -->
<jsp:include page="selectDelivery.jsp"></jsp:include>
<jsp:include page="selectStockBatch.jsp"></jsp:include>
 <script>
 function judgeNumber(stockCount,stockOutCount,serialNum){

	 var value=$("#stockOutCount"+serialNum).val();
	 debugger;
	 if(isNaN(value)&&value%1!=0){
		 toastr.warning("必须为正整数！");
		 return;
	 }
	 if(Number(value)>Number(stockOutCount)){
		toastr.warning("当前出库数量不得大于总出库数量！");
		/*  $("#stockOutCount"+serialNum).empty();
		 $("#stockOutCount"+serialNum).focus(); */
		 return;
	 }else if(Number(value)>Number(stockCount)){
		 toastr.warning("当前出库数量不得大于结存数量！");
		/*  $("#stockOutCount"+serialNum).empty();
		 $("#stockOutCount"+serialNum).focus(); */
		 return;
	 }
	 $scope.totalCount;
 }
</script> 