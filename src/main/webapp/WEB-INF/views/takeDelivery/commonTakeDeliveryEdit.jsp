<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="step.jsp"></jsp:include>
<ul class="nav nav-tabs">
	<li class="active bold"><a data-target="#tab_1_1"
		data-toggle="tab">发货信息</a></li>
	<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">收货信息</a>
	</li>
	<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">物料信息</a></li>
</ul>
<div class="tab-content">
	<div class="tab-pane fade active in" id="tab_1_1">
		<!-- 基本信息START -->
		<!-- <div class="portlet-title">
                            <div class="caption">基本信息</div>
                            <div class="actions">
                            </div>
                        </div> -->
		<div class="portlet-body form">
			<div class="row">

				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="deliverNum">
							发货单号：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.deliverNum}}</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="deliverType">发货类型：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.deliverType}}</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="orderSerial">
							采购订单号：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.docNum==null?deliver.orderNum:deliver.docNum}}</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row">
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="supplyComId">
							供应商：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.supplyName}}</p>
						</div>
					</div>
				</div>

				<!--/span-->
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="shipper"> 发货方：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.shipper}}</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="receiver"> 收货方：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.receiver}}</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row">
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="maker"> 制单人：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.maker}}</p>
						</div>
					</div>
				</div>

				<!--/span-->
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="makerDate">
							制单日期：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.makeDate}}</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="approval"> 审批人：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.approval}}</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row">
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="approvalDate">
							审批日期：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.approvalDate}}</p>
						</div>
					</div>
				</div>

				<!--/span-->
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label"> 备注：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.remark}}</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<!-- <div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label"> 状态：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.status}}</p>
						</div>
					</div>
				</div> -->
				<!--/span-->
			</div>
			<!--/row-->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="dWarehouseSerial">
								发货仓库：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.warehouse.warehouseName}}</p>
							</div>
						</div>
					</div>

					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label"> 发货地址：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliverAddress}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="deliverDate">
								发货日期：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliverDate}}</p>
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
								<p class="control-label left">{{deliver.materielCount}}</p>
							</div>
						</div>
					</div>

					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="packageCount">
								包装件数 ：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.packageCount}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="packageType">
								包装类型：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.packageType}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="packageSpecifications">
								包装规格：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.packageSpecifications}}</p>
							</div>
						</div>
					</div>

					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="materielWeight">
								物料重量：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.materielWeight}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="serviceMoney">
								服务费：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.serviceMoney}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="deliverer">
								发货人：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliverer}}</p>
							</div>
						</div>
					</div>

					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="dContactNum">
								联系电话：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.contactNum}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label">备注：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliverRemark}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
		</div>
		<!-- 发货信息END -->
	</div>
	<div class="tab-pane fade" id="tab_1_2">
		<!-- 运输START -->
		<!-- <div class="portlet-title">
                            <div class="caption">运输信息</div>
                            <div class="actions">
                            </div>
                        </div> -->
		<div class="portlet-body form">
			<div class="form-body">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="transportType">
								运输方式：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliveryTransport.transportType}}</p>
							</div>
						</div>
					</div>

					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="transport">
								运输方：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliveryTransport.transport}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
					<!-- <div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="port"> 港口：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliveryTransport.port}}</p>
							</div>
						</div>
					</div>
					/span -->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="shipNumber">
								运单号：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliveryTransport.shipNumber}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="dtContact">联系人：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliveryTransport.contact}}</p>
							</div>
						</div>
					</div>

					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="dtContactNum">联系电话：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliveryTransport.contactNum}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label"> 备注：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.deliveryTransport.remark}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="warehouseSerial">
								收货仓库：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.takeDelivery.warehouse.warehouseName}}</p>
							</div>
						</div>
					</div>

					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label">收货地址：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.takeDelivery.takeDeliverAddress}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="takeDeliveryDate">
								预计到货日期：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.takeDelivery.takeDeliverDate}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="tdReceiver">
								收货人：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.takeDelivery.receiver}}</p>
							</div>
						</div>
					</div>

					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label" for="tdContactNum">
								联系电话：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.takeDelivery.contactNum}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-4 control-label">备注：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.takeDelivery.remark}}</p>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-4 control-label" for="takeDeliverNum">
								<span class="required"> * </span>收货单号:
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="takeDeliverNum"
									name="takeDeliverNum" ng-model="takeDeliver.takeDeliverNum"
									ng-hide="deliverAdd">
								<div class="form-control-focus"></div>
								<p class="control-label left" ng-show="deliverView">{{takeDeliver.takeDeliverNum}}</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-4 control-label" for="actualDate"><span
								class="required"> * </span>实际收货日期 :</label>
							<div class="col-md-8">
								<input type="text" class="form-control date-picker"
									data-date-format="yyyy-mm-dd" data-date-viewmode="years"
									id="actualDate" name="actualDate"
									ng-model="takeDeliver.actualDate" readonly="readonly">
								<div class="form-control-focus"></div>
								<p class="control-label left" ng-show="deliverView">{{takeDeliver.actualDate}}</p>
							</div>
						</div>
					</div>

					<!--/span-->
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-4 control-label"><span
								class="required"> * </span>收货人:</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="taker" name="taker"
									ng-model="takeDeliver.taker">
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-4 control-label" for="takeDeliverDate">备注:</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="takeRemark"
									name="takeRemark" ng-model="takeDeliver.takeRemark">
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="tab_1_3">
		<div class="portlet-body form">
			<div class="form-body">
						<!-- <div class="table-scrollable"> -->
							<table id="deliveryMaterielTable"
								class="table table-striped table-bordered table-advance table-hover">
								<thead>
									<tr>
										<th  rowspan="2">物料编号</th>
										<th  rowspan="2">物料名称</th>
										<th  rowspan="2">规格型号</th>
										<th  rowspan="2">单位</th>
										<th  rowspan="2">附件</th>
										<th  rowspan="2">生产日期</th>
										<th colspan="3" style="text-align: center;">发货</th>
										<th colspan="3"  style="text-align: center;">收货</th>
										<th rowspan="2">状态</th>
									</tr>
									<tr>
										<th>订单数量</th>
										<th>发货数量</th>
										<th>备注</th>
										<th>实收数量</th>
										<th>拒收数量</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody > 
									<tr ng-repeat="materiel in deliver.deliveryMateriels track by $index" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>
                                           <p id="batchNumReal{{$index}}" ng-hide="true"> </p>
												
											<p class="form-control-static" id="batchNum{{$index}}">
											<a href="javascript:;" class="btn btn-xs green" id="addBatchNum{{$index}}"
											ng-click="addAttachFile($index)" onclick="return false;"> <i
												class="fa fa-plus"></i>添加
										    </a>
											</p>
                                        </td>
										<td>
											{{materiel.manufactureDate}}
										</td>
										<td>{{materiel.orderMateriel.amount}}</td>
										<td>
                                            {{materiel.deliverCount}}
										</td>
										<td>
                                            {{materiel.deliverRemark}}
										</td>
										<td class="form-group">
                                                 <input type="text" class="form-control" id="acceptCount{{$index}}" name="acceptCount" data-delivercount="{{materiel.deliverCount}}"  ng-model="materiel.acceptCount" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
										</td>
										<td><span class="help-block"></span>
											<span ng-if="materiel.deliverCount!=undefined && materiel.acceptCount!=undedined">{{materiel.deliverCount-materiel.acceptCount}}</span>
										</td>
										<td>
                                                 <input type="text" class="form-control" id="takeRemark{{$index}}" name="takeRemark"  ng-model="materiel.takeRemark" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
										</td>
									</tr>
									<tr ng-if="deliver.deliveryMateriels==undefined||deliver.deliveryMateriels.length==0">
											<td colspan="22" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
						<!-- </div> -->
			</div>
		</div>
	</div>
</div>