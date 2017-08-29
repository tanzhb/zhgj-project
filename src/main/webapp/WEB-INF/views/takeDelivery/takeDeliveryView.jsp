<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.left{
	float: left;
}
</style>
<h3 class="page-title"> 查看收货详情
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
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="takeDelivery">查看收货详情</a>
        </li>
    </ul>

</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<form  id="takeDeliveryyForm" class="form-horizontal" >
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
				 	
					<!-- 基本信息START -->
                        <div class="portlet-title">
                            <div class="caption">基本信息</div>
                            <div class="actions">
                            </div>
                        </div>
                        <div class="portlet-body form">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="takeDeliveryNum">收货单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.takeDeliverNum}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="deliverNum"> 发货单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliverNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="orderSerial"> 采购订单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.orderNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId"> 供应商：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.supplyName}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="shipper"> 发货方：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.shipper}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="receiver"> 收货方：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.receiver}}</p>
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
                                                         <p class="control-label left" >{{deliver.maker}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="makerDate"> 制单日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.makeDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="approval"> 审批人：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.approval}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="approvalDate"> 审批日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.approvalDate}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" > 备注：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label"> 状态：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.status}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
         				<!-- </div> -->
         			<!-- 基本信息END -->
					<!-- 发货信息START -->
                        <div class="portlet-title">
                            <div class="caption">发货信息</div>
                            <div class="actions">
                            </div>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="dWarehouseSerial"> 发货仓库：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.warehouse.warehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" > 仓库地址：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.warehouse.address}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="deliverDate"> 发货日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliverDate}}</p>
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
                                                         <p class="control-label left" >{{deliver.materielCount}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="packageCount"> 包装件数 ：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.packageCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="packageType"> 包装类型：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.packageType}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="packageSpecifications"> 包装规格：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.packageSpecifications}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="materielWeight"> 物料重量：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.materielWeight}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="serviceMoney"> 服务费：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.serviceMoney}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="deliverer"> 发货人：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliverer}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="dContactNum"> 联系电话：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" >备注：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliverRemark}}</p>
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
                            </div>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="transportType"> 运输方式：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliveryTransport.transportType}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="transport"> 运输方：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliveryTransport.transport}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="port"> 港口：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliveryTransport.port}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="shipNumber"> 船号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliveryTransport.shipNumber}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="playArrivalDate"> 预计到港日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliveryTransport.playArrivalDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="playWarehouseDate"> 预计到库日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliveryTransport.playWarehouseDate}}</p>
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
                                                         <p class="control-label left" >{{deliver.deliveryTransport.contact}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="dtContactNum">联系电话：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliveryTransport.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label"> 备注：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.deliveryTransport.remark}}</p>
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
                            </div>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="warehouseSerial"> 收货仓库：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.warehouse.warehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" >仓库地址：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left">{{deliver.takeDelivery.warehouse.address}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="takeDeliveryDate"> 收货日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.takeDeliverDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tdReceiver"> 收货人：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.receiver}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tdContactNum"> 联系电话：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" >备注：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.remark}}</p>
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
								<tbody > 
									<tr ng-repeat="materiel in deliver.deliveryMateriels track by $index" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>
                                            {{materiel.batchNum}}
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
										<td>
                                            {{materiel.acceptCount}}
										</td>
										<td>
											{{materiel.refuseCount}}
										</td>
										<td>
                                            {{materiel.takeRemark}}
										</td>
										<td>{{materiel.qualifiedCount}}</td>
										<td>{{materiel.unqualifiedCount}}</td>
										<td>{{materiel.checkRemark}}</td>
										<td>{{materiel.stockCount}}</td>
										<td>{{materiel.unstockCount}}</td>
										<td>{{materiel.warehouse.warehouseName}}</td>
										<td>{{materiel.positionSerial}}</td>
										<td>{{materiel.stockRemark}}</td>
										<td></td>
									</tr>
									<tr ng-if="deliver.deliveryMateriels==undefined||deliver.deliveryMateriels.length==0">
											<td colspan="22" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
						</div>
					  </div> 
         			<!-- 物料信息END -->
         			
       			</div> 
    		</div>
		</div>
		</form>
	</div>
</div>
<!-- END MAIN CONTENT -->
