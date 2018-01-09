<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.btn-default.active, .btn-default:active{
color: #32c5d2;
background-color: #fff;
border-color: #32c5d2;
}
.btn-default.active.focus, .btn-default.active:hover{
color: #32c5d2;
background-color: #fff;
border-color: #32c5d2;
}
.btn-default-margin{
margin-right: 20px;
}



</style>
<!-- 采购订单基本信息 START -->
<ul class="nav nav-tabs">
	<li class="active bold">
              		<a data-target="#tab_1_1" data-toggle="tab">基本信息</a>
          		</li>

	<li class="bold" ng-hide="tab_1_1Hide"><a data-target="#tab_1_4" data-toggle="tab">物料信息</a></li>
	
	<li class="dropdown pull-right tabdrop">
		<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
	</li>	
	
</ul>
<div class="tab-content" style="min-height: 300px;">
	<div class="tab-pane fade active in" id="tab_1_1">
		<div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right" id="noprintdiv">
                        	<button type="submit" ng-click="save()" ng-hide="procurementPlanInput" class="btn green  btn-circle  btn-sm">
                           		<i class="fa fa-save"></i> 保存 </button>
                           <button ng-click="cancel()" type="button" ng-hide="procurementPlanInput" class="btn defualt  btn-circle  btn-sm">
                           		<i class="fa fa-undo"></i> 取消 </button>
                           <button ng-click="edit()" type="button" ng-show="procurementPlanShow&&noShow" class="btn purple  btn-circle  btn-sm">
                           		<i class="fa fa-edit"></i> 编辑 </button>
                           <!--  <a href="javascript：;" class="collapse"> </a> -->
                         </div>
                   	</div>
         <div class="portlet-body form">
             <!-- BEGIN FORM-->
             <form action="#" id="form_sample_1"  >
                 <div class="form-body">
                     <div class="alert alert-danger display-hide">
                         <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                     <div class="row">
                     		<!--/span-->
                         <!--/span-->
                         
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>采购计划单号：</label>
                                 <div class="">
	                                 <div ng-hide="procurementPlanInput">
		                               	
	                                 </div>
	                                 <p class="form-control-static" > {{procurementPlan.procurementPlanNum}} </p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>销售订单号：</label>
                                 <div class="">
	                                 <div ng-hide="procurementPlanInput">
	                                 </div>
	                                 <p class="form-control-static" > {{procurementPlan.saleOrder.orderNum}} </p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold">销售下单日期：</label>
                                 <div class="">
	                                 <div ng-hide="procurementPlanInput">
	                                 </div>
	                                 <p class="form-control-static" > {{procurementPlan.saleOrder.orderDate}} </p>
                                 </div>
                             </div>
                         </div>
                     </div>
                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">销售数量：</label>
                                 <div class="">
                                 	<div  ng-hide="procurementPlanInput">
                                 		
                                               </div>
                                     <p class="form-control-static" > {{procurementPlan.saleOrder.materielCount}} </p>
                                 </div>
                                 
                                 
                             </div>
                         </div>
                         <div class="col-md-4" >
                             <div class="form-group ">
                                 <label class="control-label bold">客户：</label>
                                 <div class="">
                                     <p class="form-control-static" > {{procurementPlan.saleOrder.buyName}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold">采购下单日期：</label>
                                 <div class="">
                                     <p class="form-control-static" >{{procurementPlan.buyDate}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                     </div>
                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">采购数量：</label>
                                 <div class="">
                                 
                                     <p class="form-control-static" ng-show="procurementPlanShow"> {{procurementPlan.buyCount}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">备注：</label>
                                 <div class="">
                                 <input type="text" name="remark" class="form-control" ng-hide="procurementPlanInput" ng-model="procurementPlan.remark"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="procurementPlanInput">请输入备注</span>
                                     <p class="form-control-static" ng-show="procurementPlanShow"> {{procurementPlan.remark}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">状态：</label>
                                 <div class="">
                                 	<p ng-if="procurementPlan.status==1" style="color:#fcb95b"> 已完成 </p>
                                 	<p ng-if="procurementPlan.status!=1" style="color:green"> 待采购 </p>
                                 </div>
                                 
                             </div>
                         </div>
                         
                         
                     </div>
                     <!--/row-->
                 </div>
			</form>
            </div>   
	</div>
	<div class="tab-pane fade " id="tab_1_4">
		<!-- 订单物料 start-->
         <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
                 <!-- <button ng-click="addProcurementPlanMateriel()" type="button"  ng-hide="procurementPlanMaterielInput" class="btn blue  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i> 添加物料 </button> -->
                 <button type="submit" ng-click="saveAllProcurementPlanMateriel()" ng-hide="procurementPlanMaterielInput"  class="btn green  btn-circle  btn-sm">
                 		<i class="fa fa-save"></i> 保存 </button>
                 <button ng-click="cancelAllProcurementPlanMateriel()" type="button" ng-hide="procurementPlanMaterielInput" class="btn defualt  btn-circle  btn-sm">
                 		<i class="fa fa-undo"></i> 取消 </button>
                 <button ng-click="editAllProcurementPlanMateriel()" type="button" ng-show="procurementPlanMaterielShow" class="btn purple  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i> 编辑 </button>
               </div>
           </div>

          <div class="portlet-body form">
		     <form id="form_sample_5"   >
		         <div class="table-scrollable">
                         <table class="table table-bprocurementPlaned table-hover">
                             <thead>
                                 <tr>
								<th>商品编号</th>
								<th>物料名称</th>
								<th>规格型号</th>
								<th>单位</th>
								<th><span style="display:inline-block;width:100px;">需求数量</span></th>
								<th><span style="display:inline-block;width:100px;">采购数量</span></th>
								<th>供应商</th>
								<th>交付日期</th>
								<th>最晚交付日期</th>
								<th>交付地址</th>
								<th><span style="display:inline-block;width:80px;">操作</span></th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr ng-repeat="_procurementPlanMateriel in procurementPlanMateriel track by $index" ng-mouseover="showOperation('procurementPlanMateriel',$index)" ng-mouseleave="hideOperation('procurementPlanMateriel',$index)"  repeat-done="repeatDone(this)">
		                          <td>
                                           <!-- <span ng-hide="procurementPlanMaterielInput{{$index}}"><a href="javascript：;" ng-click="addMateriel('single',$index)">{{_procurementPlanMateriel.materiel.materielNum}}</a></span> -->
		                                <p class="form-control-static" > {{_procurementPlanMateriel.materiel.materielNum}} </p>
		                          </td>
		                          <td>
	                                 	<p class="form-control-static" > {{_procurementPlanMateriel.materiel.materielName}} </p>
		                          </td>
		                           <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.materiel.specifications}} </p>
		                          </td>
		                          <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.materiel.unit}} </p>
		                          </td>
		                          <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.planCount}} </p>
		                          </td>
		                          <td>  
		                          		<input style="padding:6px 3px" type="text"  name="buyCount{{$index}}" class="form-control" ng-hide="procurementPlanMaterielInput{{$index}}" ng-model="procurementPlanMateriel[$index].buyCount" ng-keyup="clearNoNum(procurementPlanMateriel[$index],'buyCount')" >
                                     		<p class="form-control-static" ng-show="procurementPlanMaterielShow{{$index}}"> {{_procurementPlanMateriel.buyCount}} </p>
                                     		<p class="form-control-static" ng-hide="procurementPlanMaterielInput{{$index}}"> 当前库存：{{_procurementPlanMateriel.materiel.stockCount}} </p>
		                          </td>
		                          <td>  
		                          		<select name="supplier{{$index}}" ng-hide="procurementPlanMaterielInput{{$index}}" ng-model="procurementPlanMateriel[$index].supplyMaterielSerial" class="bs-select form-control procurementPlan" data-live-search="true"   data-size="8"
		                          		ng-change="changeSupplyName(this,procurementPlanMateriel[$index],'supplyName')">
                                               <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}" >{{supplier.comName}}</option>
                                         </select>
                                     	<p class="form-control-static" ng-show="procurementPlanMaterielShow{{$index}}"> {{_procurementPlanMateriel.supplyName}} </p>
		                          </td>
		                          <td>  
		                          		<input type="text"  style="width: 100px!important" name="deliveryDate{{$index}}" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="procurementPlanMaterielInput{{$index}}" ng-model="procurementPlanMateriel[$index].deliveryDate"  ng-change="setAllDeliveryDate(_procurementPlanMateriel,$index)">
                                     		<p class="form-control-static" ng-show="procurementPlanMaterielShow{{$index}}"> {{_procurementPlanMateriel.deliveryDate}} </p>
		                          </td>
		                          <td>  
		                          		<input type="text"  style="width: 100px!important" name="lastDeliveryDate{{$index}}" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="procurementPlanMaterielInput{{$index}}" ng-model="procurementPlanMateriel[$index].lastDeliveryDate"  ng-change="setAllLastDeliveryDate(_procurementPlanMateriel,$index)" >
                                     		<p class="form-control-static" ng-show="procurementPlanMaterielShow{{$index}}"> {{_procurementPlanMateriel.lastDeliveryDate}} </p>
		                          </td>
		                          <td>  
		                          		<select  ng-if="$first" name="deliveryAddress{{$index}}" ng-hide="procurementPlanMaterielInput{{$index}}" ng-model="procurementPlanMateriel[$index].deliveryAddress" class="bs-select form-control procurementPlan" data-live-search="true"  ng-init="warehouses[0].serialNum" ng-change="setAllDeliveryAddress(_procurementPlanMateriel)"  data-size="8">
                                              <!--  <option value=""></option> -->
                                               <option  ng-repeat="warehouse in warehouses" value="{{warehouse.warehouseName}}">{{warehouse.warehouseName}}</option>
                                         </select>
                                         <select  ng-if="!$first" name="deliveryAddress{{$index}}" ng-hide="procurementPlanMaterielInput{{$index}}" ng-model="procurementPlanMateriel[$index].deliveryAddress" class="bs-select form-control procurementPlan" data-live-search="true"  ng-init="warehouses[0].serialNum" data-size="8">
                                              <!--  <option value=""></option> -->
                                               <option  ng-repeat="warehouse in warehouses" value="{{warehouse.warehouseName}}">{{warehouse.warehouseName}}</option>
                                         </select>
                                     		<p class="form-control-static" ng-show="procurementPlanMaterielShow{{$index}}"> {{_procurementPlanMateriel.deliveryAddress}} </p>
		                          </td>
                                     
                                     <td>
                                     	<div style="width:100px">
                                     	<span ng-hide="procurementPlanMaterielInput{{$index}}">
                                      		<!-- &nbsp;&nbsp;&nbsp;&nbsp;
                                       	<a  ng-click="saveProcurementPlanMateriel(_procurementPlanMateriel,$index)" title="保存"><i class="fa fa-save"></i></a> -->
                                       	&nbsp;&nbsp;&nbsp;
                                       	<a  ng-click="cancelProcurementPlanMateriel(_procurementPlanMateriel,$index)" title="取消"><i class="fa fa-undo"></i></a>
                                       </span>
                                       <span  ng-show="operation_o{{$index}}&&noShow">
                                       	&nbsp;&nbsp;&nbsp;&nbsp;
                                       	<a ng-show="procurementPlanMaterielShow{{$index}}"   title="编辑" ng-click="editProcurementPlanMateriel(_procurementPlanMateriel)"><i class="fa fa-edit"></i></a>
                                       	&nbsp;&nbsp;&nbsp;
                                       	<!-- <a ng-show="procurementPlanMaterielShow{{$index}}"  title="删除" ng-click="deleteProcurementPlanMateriel(_procurementPlanMateriel)"><i class="fa fa-minus"></i></a> -->
                                      	</span>
                                      	</div>
                                     </td>
                                 </tr>
                                 <tr>
								<th></th>
								<th>合计</th>
								<th>{{totalCount()}}</th>
								<th></th>
								<th>{{totalMaterielPlanCount()}}</th>
								<th>{{totalMaterielCount()}}</th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
                                 </tr>
                             </tbody>
                         </table>
                     </div>
                 </form>
         </div>
         <!-- 订单物料 end-->
	</div>
     </div>
<!-- 采购订单基本信息 end -->