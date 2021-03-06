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
<!-- <h3 class="page-title"> 新建入库
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
            <a>查看入库记录</a>
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
                            <div class="caption">入库信息</div>
                            <div class="actions" >
                                <button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
                            </div>
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
                                                    <label class="col-md-4 control-label" for="inOutNum"> 入库单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.inOutNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="inOutType">入库类型 ：</label>
	                                                 <div class="col-md-8">
			                                              <p class="control-label left" >{{record.inOutType}}</p>
                                                     </div>
                                            </div>
										</div>
										<!--/span-->
										<!-- 
										<div class="col-md-4">
											<div class="form-group form-md-line-input" ng-if="record.inOutType=='贸易'">
                                                    <label class="col-md-4 control-label" for="takeDeliverSerial"> 收货单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.delivery.takeDelivery.takeDeliverNum}}</p>
                                                    </div>
                                            </div>
											<div class="form-group form-md-line-input" ng-if="record.inOutType!='贸易'">
                                                    <label class="col-md-4 control-label" for="docNum"> 关联单据号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.docNum}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="inOutType">入库日期  ：</label>
	                                                 <div class="col-md-8">
			                                              <p class="control-label left" >{{record.stockDate}}</p>
                                                     </div>
                                            </div>
											
										</div>
										<!--/span-->
										
									</div>
									<!--/row-->
									<div class="row">
									<!-- 	<div class="col-md-4" ng-if="record.inOutType=='贸易'">
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
                                                    <label class="col-md-4 control-label" for="supplyComId"> 入库仓库：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.inWarehouseName==null?deliver.takeDelivery.takeDeliverAddress:record.inWarehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
							<div class="col-md-4">
							<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId"> 入库数量：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.inCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId"> 包装类型：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.packageType}}</p>
                                                    </div>
                                            </div>
											</div>
										<!--/span-->
										<!-- <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="shipper"> 入库库区：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.positionCount}}</p>
                                                    </div>
                                            </div>
										</div>
										/span -->
										<!-- <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="stockDate"> 入库日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.stockDate}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<!-- <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="operator">发货方：</label>
                                                    <div class="col-md-8">
                                                       <p class="control-label left" >{{record.shipperOrReceiver}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<!--/span-->
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId"> 包装规格：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.packageSpecifications}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
										<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId"> 包装件数：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.packageCount}}</p>
                                                    </div>
                                            </div>
											
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="operator"> 操作员：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.operator}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
									
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="contactNum"> 联系方式：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{record.contactNum}}</p>
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
														<span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待入库</span>
														<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已入库</span>
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
										<th  rowspan="2">发货数量</th>
										<!-- <th colspan="3"  style="text-align: center;">收货</th>
										<th colspan="3"  style="text-align: center;">检验</th> -->
										<th colspan="3"  style="text-align: center;">入库</th>
										<th rowspan="2">状态</th>
									</tr>
									<tr>
										<!-- <th>实收数量</th>
										<th>拒收数量</th>
										<th>备注</th>
										<th>合格数量</th>
										<th>不合格数量</th>
										<th>备注</th> -->
										<th>入库数量</th>
										<th>未入数量</th>
										<!-- <th>仓库</th>
										<th>库区</th> -->
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
										 <td>{{materiel.acceptCount}}</td>
										<!--<td>{{materiel.refuseCount}}</td>
										<td>{{materiel.takeRemark}}</td>
										<td>{{materiel.stockInQualifiedCount}}</td>
										<td>{{materiel.stockInUnqualifiedCount}}</td>
										<td>{{materiel.stockInCheckRemark}}</td> -->
										<td>
											{{materiel.stockInCount}}
										</td>
										<td>
											{{materiel.unstockInCount}}
										</td>
										<!-- <td>
											{{materiel.stockInWarehouse.warehouseName}}
										</td>
										<td>
											{{materiel.stockInPosition.positionName}}
										</td> -->
										<td>
											{{materiel.stockInRemark}}
										</td> 
										<td>
											<span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待入库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已入库</span>
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
										<th>入库数量</th>
										<th>仓库</th>
										<th>库区</th>
										<th>备注</th>
										<th>状态</th>
									</tr>
								</thead>
								<tbody data-repeater-list="group-a"> 
									<tr data-repeater-item ng-repeat="materiel in takeDeliveryMateriels track by $index" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>
											{{materiel.stockInCount}}
										</td>
										<td>
											{{materiel.stockInWarehouse.warehouseName}}
										</td>
										<td>
											{{materiel.stockInPosition.positionName}}
										</td>
										<td>
											{{materiel.stockInRemark}}
										</td> 
										<td>
											<span ng-if="record.status==0"  class="label label-sm label-warning ng-scope">待入库</span>
											<span ng-if="record.status==1" class="label label-sm label-success ng-scope">已入库</span>
										</td> 
									</tr>
									<tr ng-if="takeDeliveryMateriels==null||takeDeliveryMateriels.length==0">
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
