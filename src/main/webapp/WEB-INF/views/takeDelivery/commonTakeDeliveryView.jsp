<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="step.jsp"></jsp:include>
<ul class="nav nav-tabs">
	<li class="active bold"><a data-target="#tab_1_1"
		data-toggle="tab">基本信息</a></li>
	<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">发货信息</a>
	</li>
	<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">运输信息</a></li>
	<li class="bold"><a data-target="#tab_1_4" data-toggle="tab">收货信息</a></li>
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
							<p class="control-label left">{{deliver.orderNum}}</p>
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
							<p class="control-label left">{{deliver.shipperName}}</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-4 control-label" for="receiver"> 收货方：</label>
						<div class="col-md-8">
							<p class="control-label left">{{deliver.receiverName}}</p>
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
		</div>
		<!-- </div> -->
		<!-- 基本信息END -->
	</div>
	<div class="tab-pane fade" id="tab_1_2">
		<!-- 发货信息START -->
		<!-- <div class="portlet-title">
                            <div class="caption">发货信息</div>
                            <div class="actions">
                            </div>
                        </div> -->
		<div class="portlet-body form">
			<div class="form-body">
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
							<label class="col-md-4 control-label"> 仓库地址：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.warehouse.address}}</p>
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
		</div>
		<!-- 发货信息END -->
	</div>
	<div class="tab-pane fade" id="tab_1_3">
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
			</div>
		</div>
		<!-- 运输信息END -->
	</div>
	<div class="tab-pane fade" id="tab_1_4">
		<!-- 收货信息START -->
		<!--  <div class="portlet-title">
                            <div class="caption">收货信息</div>
                            <div class="actions">
                            </div>
                        </div> -->
		<div class="portlet-body form">
			<div class="form-body">
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
							<label class="col-md-4 control-label">仓库地址：</label>
							<div class="col-md-8">
								<p class="control-label left">{{deliver.takeDelivery.warehouse.address}}</p>
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
			</div>
		</div>
	</div>
</div>