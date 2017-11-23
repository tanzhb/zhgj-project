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
				 	
					<!-- 基本信息START -->
                        <div class="portlet-title">
                            <div class="caption">出库信息</div>
                            <div class="actions">
                              <!--   <button   ng-show="deliverView" class="btn blue  btn-outline  btn-sm " ng-click="editdeliver()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="deliverEdit" class="btn red  btn-outline  btn-sm " ng-click="canceldeliver('deliver')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="deliverAdd" class="btn blue  btn-outline  btn-sm " ng-click="savedeliver()">
                                            <i class="fa fa-save"></i> 保存 </button> -->
                                 <button   class="btn green  btn-sm btn-circle" ng-click="saveStockIn()">
                              		<i class="fa fa-check"></i> 确认出库 </button>
                      			 <button    class="btn defualt  btn-sm btn-circle" ng-click="cancelStockIn()" onclick="return false;">
                              		<i class="fa fa-mail-reply"></i> 取消 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输出正确数据！</div>
                                                <jsp:include  page="../takeDelivery/stockInOutDeliveryInfo.jsp" />
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
	                                                 	<select class="form-control" id="inOutType" name="inOutType" ng-init="record.inOutType='贸易'"  ng-model="record.inOutType"   data-size="8">
			                                                   <option value="贸易" selected="selected" >贸易</option>
			                                             </select> 
                                                     </div>
                                                     <div class="form-control-focus"> </div>
                                            </div>
										</div>
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="deliverSerial">发货单号 <span class="required"> * </span></label>
                                                     <input id="deliverSerial"   name="deliverSerial" type="text" class="form-control" ng-model="record.deliverNum" disabled="disabled">
                                                     <div class="form-control-focus"> </div>
                                            </div>
										</div>
										<!--/span-->
										
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="orderSerial"> 采购订单号</label>
                                                    <div class="">
                                                    		<input type="text" class="form-control" value="{{record.orderNum}}" disabled="disabled">
                                                         <!-- <p class="control-label left" >{{record.orderNum}}</p> -->
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="supplyComId"> 出库仓库</label>
                                                    <div class="">
                                                    	<input type="text" class="form-control" value="{{warehouseCount}}" disabled="disabled">
                                                      <!--    <p class="control-label left" >{{warehouseCount}}</p> -->
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
                                                    <label class="control-label bold" for="stockDate">出库日期 <span class="required"> * </span></label>
                                                    <div class="">
                                                       <input type="text" class="form-control  date-picker" size="16"  data-date-format="yyyy-mm-dd" data-date-viewmode="years"
                                                         id="stockDate"  name="stockDate" ng-model="record.stockDate" ng-hide="deliverAdd" readonly="readonly">
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
											<div class="form-group">
                                                    <label class="control-label bold" for="operator">收货方 <span class="required"> * </span></label>
                                                    <div class="">
                                                       <input type="text" class="form-control" value="{{record.shipperOrReceiver}}" disabled="disabled">
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="operator">操作员 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="operator" name="operator" ng-model="record.operator" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{deliver.maker}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
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
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
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
										<!--/span-->
									</div>
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
										<th><!-- <span class="required" style="color: red"> * </span> -->出库数量</th>
										<th>当前库存</th>
										<th>未出数量</th>
										<!-- <th>仓库</th>
										<th>库区</th> -->
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
										<!-- <td class="form-group">
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
										</td> -->
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
					 </div>
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