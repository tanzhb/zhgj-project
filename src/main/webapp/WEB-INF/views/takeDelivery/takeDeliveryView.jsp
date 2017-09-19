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
<!-- <h3 class="page-title"> 查看收货详情
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
            <a ui-sref="takeDelivery">收货计划</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="takeDelivery">查看收货详情</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
<%-- 	<div class="col-md-12">
		<jsp:include page="step.jsp"></jsp:include>
	</div> --%>
    <div class="col-md-12">
    	<form  id="takeDeliveryyForm" class="form-horizontal" >
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
				 	
				 	   <jsp:include page="commonTakeDeliveryView.jsp"></jsp:include>
					   <div class="portlet-title">
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="takeDeliverNum"> 收货单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.takeDeliverNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tdReceiver"> 实际收货日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.actualDate}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tdContactNum"> 收货人：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.taker}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" >备注：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.takeDelivery.takeDate}}</p>
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
										<td>{{materiel.stockInQualifiedCount}}</td>
										<td>{{materiel.stockInUnqualifiedCount}}</td>
										<td>{{materiel.stockInCheckRemark}}</td>
										<td>{{materiel.stockInCount}}</td>
										<td>{{materiel.unstockInCount}}</td>
										<td>{{materiel.stockInWarehouse.warehouseName}}</td>
										<td>{{materiel.stockInPosition.positionName}}</td>
										<td>{{materiel.stockInRemark}}</td>
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
