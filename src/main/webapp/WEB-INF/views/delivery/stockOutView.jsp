<%-- <%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
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
<!-- <h3 class="page-title"> 新建出库
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
            <a>查看出库记录</a>
        </li>
    </ul>

</div> -->
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
                            <div class="caption">出库信息</div>
                            <div class="actions">
                              <!--   <button    class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
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
                                                    <label class="col-md-4 control-label" for="inOutNum"> 出库单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.inOutNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="inOutType">出库类型 ：</label>
	                                                 <div class="col-md-8">
			                                              <p class="control-label left" >{{record.inOutType}}</p>
                                                     </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="stockDate"> 出库日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.stockDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										
										<!-- <div class="col-md-4" >
											<div class="form-group form-md-line-input"  ng-if="record.inOutType=='贸易'">
                                                    <label class="col-md-4 control-label" for="takeDeliverSerial"> 发货单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.delivery.deliverNum}}</p>
                                                    </div>
                                            </div>
                                            <div class="form-group form-md-line-input" ng-if="record.inOutType!='贸易'">
                                                    <label class="col-md-4 control-label" for="docNum"> 关联单据号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.docNum}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<!-- <div class="col-md-4"  ng-if="record.inOutType=='贸易'">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="orderSerial"> 采购订单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.delivery.orderNum}}</p>
                                                    </div>
                                            </div>
                                            
										</div> -->
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId"> 出库仓库：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{warehouseCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId">出库数量：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.outCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId">包装类型：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.packageType}}</p>
                                                    </div>
                                            </div>
											</div>
										<!--/span-->
										<!-- <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="shipper"> 出库库区：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.positionCount}}</p>
                                                    </div>
                                            </div>
										</div>
										/span -->
										
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<!-- <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="operator">收货方：</label>
                                                    <div class="col-md-8">
                                                       <p class="control-label left" >{{record.shipperOrReceiver}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId">包装规格：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.packageSpecifications}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId">包装件数：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.packageCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId">操作员：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{deliver.operator}}</p>
                                                    </div>
                                            </div>
											
										</div>
									<!-- 	<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="operator"> 操作员：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.operator}}</p>
                                                    </div>
                                            </div>
										</div>
						
										/span
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="contactNum"> 联系方式：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.contactNum}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
									</div>
									<!--/row-->
									
									<div class="row">
									<div class="col-md-4">
									<div class="form-group form-md-line-input">
												<label class="col-md-4 control-label" for="approval">
													联系方式 ：</label>
												<div class="col-md-8">
													<p class="control-label left">
														{{record.contactNum}}
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
												<label class="col-md-4 control-label" for="approval">
													备注：</label>
												<div class="col-md-8">
													<p class="control-label left">
														{{record.remark}}
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
												<label class="col-md-4 control-label" for="approval">
													状态：</label>
												<div class="col-md-8">
													<p class="control-label left">
														<span class="label label-sm label-success">已出库</span>
													</p>
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
							<div class="row">
								<div class="col-md-6 col-sm-6">
									<div class="dataTables_length" id="sample_5_length">
										<label>每页显示 <select name="sample_5_length"
											aria-controls="sample_5" ng-model="pageSize"
											ng-change="createDispalyList()"
											class="form-control input-sm input-xsmall input-inline">
												<option value="5">5</option>
												<option value="10">10</option>
												<option value="15">15</option>
												<option value="30">30</option>
												<option value="99999">All</option>
										</select> 条数据
										</label>
									</div>
								</div>
								<div class="col-md-6 col-sm-6">
									<div id="sample_5_filter" style="text-align: right;">
										<label>查询:<input type="search" ng-model="queryStr"
											ng-change="queryForPage()"
											class="form-control input-sm input-small input-inline"
											placeholder="" aria-controls="sample_5"></label>
									</div>
								</div>
							</div>
							<div class="table-scrollable">
							<table id="deliveryMaterielTable" ng-if="record.inOutType=='贸易'"
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
										<th>出库数量</th>
										<th>未出数量</th>
										<!-- <th>仓库</th>
										<th>库区</th> -->
										<th>出库批次</th> 
										<th>备注</th> 
									</tr>
								</thead>
								<tbody data-repeater-list="group-a"> 
									<tr data-repeater-item ng-repeat="materiel in dispalyDeliveryMateriel track by $index" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>{{materiel.batchNum}}</td>
										<td>{{materiel.manufactureDate}}</td>
										<td>{{materiel.orderMateriel.amount}}</td>
										<td>{{materiel.deliverCount}}</td>
										<td>{{materiel.deliverRemark}}</td>
										<td>
											{{materiel.stockCount}}
										</td>
										<td>
											{{materiel.unstockCount}}
										</td>
										<!-- <td>
											{{materiel.warehouse.warehouseName}}
										</td>
										<td>
											{{materiel.position.positionName}}
										</td> -->
										<td >{{materiel.inOutNums}}</td>
										<td>
											{{materiel.stockRemark}}
										</td> 
									</tr>
									<tr ng-if="record.delivery.deliveryMateriels==undefined||record.delivery.deliveryMateriels.length==0">
											<td colspan="15" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
							<table id="deliveryMaterielTable" ng-if="record.inOutType!='贸易'"
								class="table table-striped table-bordered table-advance table-hover">
								<thead>
									<tr>
										<th>物料编号</th>
										<th>物料名称</th>
										<th>规格型号</th>
										<th>单位</th>
										<th>出库数量</th>
										<!-- <th>仓库</th>
										<th>库区</th> -->
										<th>出库批次</th> 
										<th>备注</th> 
										<th>状态</th>
								</thead>
								<tbody data-repeater-list="group-a"> 
									<tr data-repeater-item ng-repeat="materiel in takeDeliveryMateriels track by $index" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>
											{{materiel.stockCount}}
										</td>
										<!-- <td>
											{{materiel.warehouse.warehouseName}}
										</td>
										<td>
											{{materiel.position.positionName}}
										</td> -->
										<td >{{materiel.inOutNums}}</td>
										<td>
											{{materiel.stockRemark}}
										</td> 
										<td>
											<span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待出库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已出库</span>
										</td>
									</tr>
									<tr ng-if="takeDeliveryMateriels==undefined||takeDeliveryMateriels.length==0">
											<td colspan="9" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="row">
								<div class="col-md-5 col-sm-5">
									<div class="dataTables_info" id="sample_5_info" role="status"
										aria-live="polite">从
										{{(pageIndex-1)*pageSize+1>filterDeliveryMateriel.length?filterDeliveryMateriel.length:(pageIndex-1)*pageSize+1}}
										到
										{{pageIndex*pageSize>filterDeliveryMateriel.length?filterDeliveryMateriel.length:pageIndex*pageSize}}
										/共 {{filterDeliveryMateriel.length}}
										条数据（从{{record.delivery.deliveryMateriels.length}}条数据中筛选）</div>
								</div>
								<div class="col-md-7 col-sm-7">
									<div style="text-align: right;" id="sample_5_paginate">
										<ul class="pagination" style="visibility: visible;">
											<li class="prev" ng-if="pageIndex>1"><a href="#"
												ng-click="link2PreviousPage()" title="前一页"><i
													class="fa fa-angle-left"></i></a></li>
											<li class="prev disabled" ng-if="1>=pageIndex"><a
												href="#" title="前一页"><i class="fa fa-angle-left"></i></a></li>
											<li ng-if="pageIndex-2>0"><a href="#"
												ng-click="link2ThisPage(pageIndex-2)">{{pageIndex-2}}</a></li>
											<li ng-if="pageIndex-1>0"><a href="#"
												ng-click="link2ThisPage(pageIndex-1)">{{pageIndex-1}}</a></li>
											<li class="active"><a href="#">{{pageIndex}}</a></li>
											<li ng-if="totalPage>pageIndex"><a href="#"
												ng-click="link2ThisPage(pageIndex+1)">{{pageIndex+1}}</a></li>
											<li ng-if="totalPage>pageIndex+1"><a href="#"
												ng-click="link2ThisPage(pageIndex+2)">{{pageIndex+2}}</a></li>
											<li class="next disabled" ng-if="pageIndex>=totalPage"><a
												href="#"><i class="fa fa-angle-right"></i></a></li>
											<li class="next" ng-if="totalPage>pageIndex"><a href="#"
												ng-click="link2NextPage()" title="后一页"><i
													class="fa fa-angle-right"></i></a></li>
										</ul>
									</div>
								</div>
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
 --%>
 
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
<!-- <h3 class="page-title s_tip"> 新建出库记录
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
            <a ui-sref="takeDelivery">出库记录</a>
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
							<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
						</li>												
					</ul>
					<!-- 基本信息START -->
					<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
                        <div class="portlet-title">
                            <div class="caption"></div>
                        </div>
                       
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输出正确数据！</div>
                                              <%--    <jsp:include  page="../takeDelivery/stockInOutDeliveryInfo.jsp" /> --%>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="inOutNum">出库单号</label>
                                                    <div class="">
                                                    <div class="form-control-focus"> </div>
                                                         <p class="control-label left" >{{record.inOutNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="orderNum">销售订单号 </label>
	                                                   <div class="">
	                                                   <div class="form-control-focus"> </div>
                                                         <p class="control-label left" >{{record.order.orderNum}}</p>
                                                    </div>
                                            </div>
										</div>
											<div class="col-md-4"  ng-show="record.order.orderType=='委托销售'">
										<div class="form-group">
													<label class="control-label bold">关联项目单号</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-hide="span">
															{{record.order.projectNum}}</p>
													</div>

												</div>
                                            
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="inOutType">出库类型 </label>
	                                                   <div class="">
	                                                   <div class="form-control-focus"> </div>
                                                         <p class="control-label left" >{{record.inOutType}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										
									</div>
									<!--/row-->
									<div class="row">
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="stockDate">出库日期 </label>
                                                    <div class="">
                                                    <div class="form-control-focus"> </div>
                                                         <p class="control-label left" >{{record.stockDate|date:'yyyy-MM-dd'}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="supplyComId"> 出库仓库</label>
                                                    <div class="">
                                                    <div class="form-control-focus"> </div>
                                                         <p class="control-label left"   >{{deliver.deliveryWarehouseName==null?deliver.deliveryAddress:deliver.deliveryWarehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="supplyComId"> 出库数量</label>
                                                    <div class="">
                                                    <div class="form-control-focus"> </div>
                                                       <p class="control-label left"  >{{record.materielCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">包装类型</label>
													<div class="">
													<div class="form-control-focus"> </div>
														<p class="control-label left" >
															{{record.packageType}}</p>
													</div>
												</div>
											</div>
										
									<!-- </div> -->
									<!--/row-->
									<!-- <div class="row"> -->
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="packageSpecifications">包装规格</label>
                                                    <div class="">
                                                    <div class="form-control-focus"> </div>
                                                         <p class="control-label left" >{{record.packageSpecifications}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="remark">包装件数</label>
                                                    <div class="">
                                                    <div class="form-control-focus"> </div>
                                                         <p class="control-label left" >{{record.packageCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="operator">操作员 <!-- <span class="required"> * </span> --></label>
                                                    <div class="">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" >{{record.operator}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<!--/span-->
									<!-- </div> -->
									<!--/row-->
									<!-- <div class="row"> -->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="contactNum">联系方式 </label>
                                                    <div class="">
                                                    <div class="form-control-focus"> </div>
                                                         <p class="control-label left" >{{record.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="remark">备注</label>
                                                    <div class="">
                                                    <div class="form-control-focus"> </div>
                                                         <p class="control-label left" >{{record.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="remark">状态</label>
                                                    <div class="">
                                                    <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ><span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待出库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已出库</span></p>
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
										<div class="row"     style="border-top:1px solid #dddddd;padding-top: 20px;"  >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">运输方式</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" >
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
														<div class="form-control-focus"></div>
														<p class="form-control-static" >
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
														<div class="form-control-focus"></div>
														<p class="form-control-static" >
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
														<div class="form-control-focus"></div>
														<p class="form-control-static" >
															{{record.transportContact}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" >
															{{record.transportContactNum}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<div class="form-control-focus"></div>
														<p class="form-control-static" >
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
         					<!-- <!--  <div class="portlet-body">
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
										<th colspan="4"  style="text-align: center;">出库</th>
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
										<th>出库数量</th>
										<th>未出数量</th>
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
											<span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待出库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已出库</span>
										</td> 
									</tr>
									<tr ng-if="takeDeliveryMateriels==undefined||takeDeliveryMateriels.length==0">
											<td colspan="15" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
						</div>
					 </div> --> 
					 <div class="portlet-body">
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="dataTables_length" id="sample_5_length">
						<label>每页显示 <select name="sample_5_length"
							aria-controls="sample_5" ng-model="pageSize" ng-change="createDispalyList()"
							class="form-control input-sm input-xsmall input-inline">
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="99999">All</option>
							</select> 条数据
						</label>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div id="sample_5_filter" style="text-align: right;">
						<label>查询:<input type="search" ng-model="queryStr"  ng-change="queryForPage()"
							class="form-control input-sm input-small input-inline"
							placeholder="" aria-controls="sample_5"></label>
					</div>
				</div>
			</div>
			<div class="table-scrollable">
				<table id="deliveryMaterielTable"
					class="table table-striped table-bordered table-advance table-hover">
					<thead>
						<tr>
							<th rowspan="2">物料编号</th>
							<th rowspan="2">物料名称</th>
							<th rowspan="2">规格型号</th>
							<th rowspan="2">单位</th>
							<th rowspan="2">订单数量</th>
							<th rowspan="2">发货数量</th>
							<th rowspan="2">合格数量</th>
							<!-- <th rowspan="2">生产批次</th> -->
							<th rowspan="2">出库数量</th>
							<th rowspan="2">未出数量</th>
							<th rowspan="2">备注</th>
							<th rowspan="2">附件</th>
							<th rowspan="2">状态</th>
						</tr>
					</thead>
					<tbody>
						<tr
							ng-repeat="materiel in dispalyDeliveryMateriel  track by $index">
							<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
							<td>{{materiel.orderMateriel.materiel.materielName}}</td>
							<td>{{materiel.orderMateriel.materiel.specifications}}</td>
							<td>{{materiel.orderMateriel.materiel.unit}}</td>
							<td>{{materiel.orderMateriel.amount}}</td>
							<td>{{materiel.deliverCount}}</td>
							<td>{{materiel.qualifiedCount==null?"无出库检验":materiel.qualifiedCount}}</td>
							<!-- <td>{{materiel.inOutNums}}<span ng-repeat="stockInBatch in materiel.stockInBatchs track by $index">
												<span ng-if="!$first">;</span> {{stockInBatch.batchNum}}({{stockInBatch.stockInCount}})
												</span></td> -->
							<td>{{materiel.stockCount==null?"0":materiel.stockCount}}</td>
							<td>{{materiel.deliverCount-materiel.stockCount}}</td>
							<td>{{materiel.stockRemark}}</td>
							<td>
								<a href="javascript:;" ng-click="downloadFile1(item.file)" ng-repeat="item in materiel.deliveryFiles">{{item.file|limitTo:30:item.file.indexOf('_')+1}}&nbsp;</a>
							</td>
							<td><span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待出库</span>
														<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已出库</span></td>
						<!-- 	<td>{{materiel.acceptCount}}</td>
							<td>{{materiel.refuseCount}}</td>
							<td>
								<a href="javascript:;" ng-click="downloadFile1(item.file)" ng-repeat="item in materiel.files">{{item.file|limitTo:30:item.file.indexOf('_')+1}}&nbsp;</a>
							</td>
							<td>{{materiel.takeRemark}}</td> -->
							<!-- <td>{{materiel.stockInQualifiedCount}}</td>
							<td>{{materiel.stockInUnqualifiedCount}}</td>
							<td>{{materiel.stockInCheckRemark}}</td>
							<td>{{materiel.stockInCount}}</td>
							<td>{{materiel.unstockInCount}}</td>
							<td>{{materiel.stockInWarehouse.warehouseName}}</td>
							<td>{{materiel.stockInPosition.positionName}}</td>
							<td>{{materiel.stockInRemark}}</td> -->
						</tr>
						<tr
							ng-if="dispalyDeliveryMateriel==0">
							<td colspan="13" align="center">没有符合条件的物料信息</td>
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
														<!-- <td></td> -->
														<td>{{totalStockOutCount}}</td>
														<td>{{totalUnstockOutCount}}</td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
												</tfoot>
				</table>
			</div>
			
			<div class="row">
				<div class="col-md-5 col-sm-5">
					<div class="dataTables_info" id="sample_5_info" role="status"
						aria-live="polite">从 {{(pageIndex-1)*pageSize+1>filterDeliveryMateriel.length?filterDeliveryMateriel.length:(pageIndex-1)*pageSize+1}}
						到 {{pageIndex*pageSize>filterDeliveryMateriel.length?filterDeliveryMateriel.length:pageIndex*pageSize}} /共 {{filterDeliveryMateriel.length}} 条数据（从{{deliver.deliveryMateriels.length}}条数据中筛选）</div>
				</div>
				<div class="col-md-7 col-sm-7">
					<div  style="text-align: right;" id="sample_5_paginate">
						<ul class="pagination" style="visibility: visible;">
							<li class="prev" ng-if="pageIndex>1"><a href="#" ng-click="link2PreviousPage()" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li class="prev disabled" ng-if="1>=pageIndex"><a href="#" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li ng-if="pageIndex-2>0"><a href="#" ng-click="link2ThisPage(pageIndex-2)">{{pageIndex-2}}</a></li>
							<li ng-if="pageIndex-1>0"><a href="#" ng-click="link2ThisPage(pageIndex-1)">{{pageIndex-1}}</a></li>
							<li class="active"><a href="#">{{pageIndex}}</a></li>
							<li ng-if="totalPage>pageIndex"><a href="#" ng-click="link2ThisPage(pageIndex+1)">{{pageIndex+1}}</a></li>
							<li ng-if="totalPage>pageIndex+1"><a href="#" ng-click="link2ThisPage(pageIndex+2)">{{pageIndex+2}}</a></li>
							<li class="next disabled" ng-if="pageIndex>=totalPage"><a href="#" ><i
									class="fa fa-angle-right"></i></a></li>
							<li class="next" ng-if="totalPage>pageIndex"><a href="#" ng-click="link2NextPage()" title="后一页"><i
									class="fa fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
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
										<th colspan="4"  style="text-align: center;">出库</th>
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
										<th>出库数量</th>
										<th>未出数量</th>
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
											<span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待出库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已出库</span>
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

 