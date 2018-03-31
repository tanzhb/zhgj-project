<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<div class="row" id="ProcurementPlanPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light ">
					<!-- <div class="portlet-title">
			               <div class="tools">
			               		<button type="button" ng-click="submitPage()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">提交审核</button>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div> -->
           		<%--  <div class="portlet-body">
					<jsp:include  page="editProcurementPlanBase.jsp"/>
      			</div> --%>
      			<ul class="nav nav-tabs">
	<li class="active bold">
              		<a data-target="#tab_1_1" data-toggle="tab">基本信息</a>
          		</li>
<li class="bold" ><a data-target="#tab_1_3" data-toggle="tab">需求物料</a></li>
	<li class="bold" ><a data-target="#tab_1_4" data-toggle="tab">采购清单</a></li>
	
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
                     		
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>采购计划单号：</label>
                                 <div class="">
	                                <input type="text" name="procurementPlanNum" class="form-control" ng-hide="procurementPlanInput" ng-model="procurementPlan.procurementPlanNum"  >
	                                 <p class="form-control-static"  ng-show="procurementPlanShow"> {{procurementPlan.procurementPlanNum}} </p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold">计划生成日期：</label>
                                 <div class="">
                                  <input type="text" readonly class="form-control" ng-hide="procurementPlanInput" ng-model="procurementPlan.buyDate" >
	                                 <p class="form-control-static"  ng-show="procurementPlanShow"> {{procurementPlan.buyDate}} </p>
                                 </div>
                             </div>
                         </div> 
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold">物料条目：</label>
                                 <div class="">
                                  <input type="text" readonly class="form-control" ng-hide="procurementPlanInput" ng-model="materielCount" >
	                                 <p class="form-control-static"  ng-show="procurementPlanShow"> {{materielCount}} </p>
                                 </div>
                             </div>
                         </div> 
                         <!-- <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">关联销售订单号：</label>
                                 <div class="">
                                 <input type="text" readonly   data-target="#saleOrderInfo"
															data-toggle="modal"  class="form-control" ng-hide="procurementPlanInput" ng-model="saleOrder.orderNum" 
                                 ng-click="selectOrder()" >
                                     <div class="form-control-focus"> </div>
                                     <p class="form-control-static"  ng-show="procurementPlanShow"> {{saleOrder.orderNum}} </p>
                                 </div>
                             </div>
                         </div> -->
                        
                         <!-- <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold">销售下单日期：</label>
                                 <div class="">
                                  <input type="text" readonly class="form-control" ng-hide="procurementPlanInput" ng-model="saleOrder.orderDate" >
	                                 <p class="form-control-static"  ng-show="procurementPlanShow"> {{saleOrder.orderDate}} </p>
                                 </div>
                             </div>
                         </div> -->
                     </div>
                     <div class="row">
                         
                         <!-- <div class="col-md-4" >
                             <div class="form-group ">
                                 <label class="control-label bold">客户：</label>
                                 <div class="">
                                  <input type="text" readonly class="form-control" ng-hide="procurementPlanInput" ng-model="saleOrder.buyName" >
                                     <p class="form-control-static"   ng-show="procurementPlanShow"> {{saleOrder.buyName}} </p>
                                 </div>
                                 
                             </div>
                         </div> -->
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold">计划采购数量：</label>
                                 <div class="">
                                 <input type="text" readonly class="form-control" ng-hide="procurementPlanInput" ng-model="procurementPlan.buyCount"   >
                                     <p class="form-control-static"  ng-show="procurementPlanShow">{{procurementPlan.buyCount}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold">齐套数量：</label>
                                 <div class="">
                                 <input type="text" readonly  class="form-control" ng-hide="procurementPlanInput" ng-model="procurementPlan.endCount"  > 
                                     <p class="form-control-static"  ng-show="procurementPlanShow" >0 </p>
                                 </div>
                                 
                             </div>
                         </div>
                         
                          <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold">关联采购订单：</label>
                                 <div class="">
                                 <input type="text" readonly class="form-control" ng-hide="procurementPlanInput"  ng-model="procurementPlan.buyOrderCount"  > 
                                     <p class="form-control-static"  ng-show="procurementPlanShow" >0 </p>
                                 </div>
                                 
                             </div>
                         </div>
                         
                     </div>
                     <div class="row">
                     <div class="col-md-4">
                         <div class="form-group ">
                                 <label class="control-label bold">备注：</label>
                                 <div class="">
                                 <input type="text" name="remark" class="form-control" ng-hide="procurementPlanInput" ng-model="procurementPlan.remark"  >
                                     <div class="form-control-focus"> </div>
                                     <p class="form-control-static" ng-show="procurementPlanShow"> {{procurementPlan.remark}} </p>
                                 </div>
                                 
                             </div>
                             </div>
                         <!-- <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">计划发布日期：</label>
                                  <div class="">
                                 <input type="text" name="buyDate" class="form-control form-control-inline date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="procurementPlanInput" ng-model="procurementPlan.buyDate" >
                                     <div class="form-control-focus"> </div>
                                      <p class="form-control-static" ng-show="procurementPlanShow"> {{procurementPlan.buyDate}} </p>
                                 </div>
                                 
                             </div>
                         </div> -->
                         <div class="col-md-4">
                              <div class="form-group ">
                              <label class="control-label bold"> 制单人：</label>
                                 <div class="">
                                 <input type="text" class="form-control" ng-hide="procurementPlanInput" ng-model="procurementPlan.maker" >
                                     <p class="form-control-static" ng-show="procurementPlanShow"> {{procurementPlan.maker}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                              <div class="form-group ">
                              <label class="control-label bold"> 更新日期：</label>
                                 <div class="">
                                 <input type="text"  readonly class="form-control" ng-hide="procurementPlanInput" ng-model="procurementPlan.updateTime" >
                                     <p class="form-control-static" ng-show="procurementPlanShow"> {{procurementPlan.updateTime}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <!-- <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">采购订单：</label>
                                 <div class="">
                                 <input type="text"  readonly class="form-control" ng-hide="procurementPlanInput" ng-model="procurementPlan.buyOrderCount" >
                                  <p class="form-control-static"  > {{procurementPlan.buyOrderCount}} </p>ng-show="procurementPlanShow"
                                 </div>
                                 
                             </div>
                         </div> -->
                         
                         
                     </div>
                     <!--/row-->
                      <div class="row">
                         <!-- <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">
                                  <label class="control-label bold">采购齐套：</label>
                                 <div class="">
                                     <p class="form-control-static" > {{procurementPlan.endCount}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                              <div class="form-group ">齐套日期：</label>
                                 <div class="">
                                  <p class="form-control-static"    > --</p>
                                  <p class="form-control-static" ng-if="procurementPlan.endCount!=undefined" > {{procurementPlan.endCount}} </p>
                                 </div>
                                 
                             </div>
                         </div> -->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">状态：</label>
                                 <div class="">
                                 	<p class="form-control-static" ng-if="procurementPlan.status==undefined||procurementPlan.status==0"    > 未完成</p>
                                 	<!-- <p class="form-control-static" ng-if="procurementPlan.endCount!=undefined&&procurementPlan.endCount!=procurementPlan.buyCount"    > 部分完成</p> -->
                                 	<p class="form-control-static" ng-if="procurementPlan.status==1"    > 已完成</p>
                                 </div>
                                 
                             </div>
                         </div>
                     </div>
                 </div>
			</form>
            </div>
			</div>
			<!-- <div class="tab-pane fade " id="tab_1_3"  ng-if="saleOrder.orderNum==undefined">无需求物料</div> -->
			<div class="tab-pane fade " id="tab_1_3"  ><!-- ng-if="saleOrder.orderNum!=undefined" -->
		<!-- 销售订单物料 start-->
         <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
              <button ng-click="addDemandMateriel()" type="button"     ng-if="saleOrder.orderNum==undefined" ng-hide="demandMaterielInput" class="btn blue  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i> 添加物料 </button><!--未选择销售订单时,可新增物料  -->
                 <button ng-click="chooseDemandMateriel()" type="button"  ng-show="demandMaterielShow" class="btn blue  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i>分解采购 </button>
                 <button type="submit" ng-click="saveAllDemandMateriel()" ng-hide="demandMaterielInput"  class="btn green  btn-circle  btn-sm">
                 		<i class="fa fa-save"></i> 保存 </button>
                 <button ng-click="cancelAllDemandMateriel()" type="button" ng-hide="demandMaterielInput" class="btn defualt  btn-circle  btn-sm">
                 		<i class="fa fa-undo"></i> 取消 </button>
                 <button ng-click="editAllDemandMateriel()" type="button" ng-show="demandMaterielShow" class="btn purple  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i> 编辑 </button>
               </div>
           </div>

          <div class="portlet-body form">
		     <form id="form_sample_6"   >
		         <div class="table-scrollable">
                         <table class="table table-bprocurementPlaned table-hover" >
                             <thead>
                                 <tr>
                                 <th style="display:inline-block;">
                                 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                        <input type="checkbox" class="group-checkable"  id="group-checkable1"  ng-click="selectAllMateriel('group-checkable1')" />
                                          <span></span>
                                    </label>
                                 </th>
								<th>物料编号</th>
								<th>物料名称</th>
								<th>规格型号</th>
								<th>单位</th>
								<th>单套用量</th>
								<th>需求数量</th>
								<th>齐套数量</th>
								<th>交付日期</th>
								<th>交付地址</th>
								<th  style="display:inline-block;">状态</th>
								<th><span style="display:inline-block;width:80px;">操作</span></th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr ng-repeat="_procurementPlanMateriel in demandMateriel track by $index" ng-mouseover="showOperation('procurementPlanMateriel',$index)" ng-mouseleave="hideOperation('procurementPlanMateriel',$index)"  repeat-done="repeatDone(this)">
		                          <td>
		                          <div  ng-hide="showCheckBoxForDm{{$index}}"   ng-if="_procurementPlanMateriel.status==0||_procurementPlanMateriel.status==undefined">
		                                <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"  >
                                        <input type="checkbox" class="group-checkable1"      name="{{$index}}"  id="{{_procurementPlanMateriel.materielSerial}}"  value="{{_procurementPlanMateriel.isBOM}}" />
                                        <span></span>
                                    </label>
                                    </div>
		                          </td>
		                          <td>
                                           <!-- <span ng-hide="procurementPlanMaterielInput{{$index}}"><a href="javascript：;" ng-click="addMateriel('single',$index)">{{_procurementPlanMateriel.materiel.materielNum}}</a></span> -->
		                                <p class="form-control-static"   ng-if="saleOrder.orderNum!=undefined" > {{_procurementPlanMateriel.materielNum}} </p>
		                                 <p class="form-control-static"   ng-if="saleOrder.orderNum==undefined" > {{_procurementPlanMateriel.materiel.materielNum}} </p>
		                          </td>
		                          <td>
	                                 	<p class="form-control-static"   ng-if="saleOrder.orderNum!=undefined" > {{_procurementPlanMateriel.materielName}} </p>
	                                 	<p class="form-control-static"   ng-if="saleOrder.orderNum==undefined" > {{_procurementPlanMateriel.materiel.materielName}} </p>
		                          </td>
		                           <td>
                                     		<p class="form-control-static"   ng-if="saleOrder.orderNum!=undefined" > {{_procurementPlanMateriel.specifications}} </p>
                                     		<p class="form-control-static"   ng-if="saleOrder.orderNum==undefined" > {{_procurementPlanMateriel.materiel.specifications}} </p>
		                          </td>
		                          <td>
                                     		<p class="form-control-static"   ng-if="saleOrder.orderNum!=undefined" > {{_procurementPlanMateriel.unit}} </p>
                                     		<p class="form-control-static"   ng-if="saleOrder.orderNum==undefined" > {{_procurementPlanMateriel.materiel.unit}}</p>
		                          </td>
		                          <td>
                                     		<p class="form-control-static" > 1 </p>
		                          </td>
		                             <td>  
		                          		<input style="padding:6px 3px" type="text"  name="buyCount{{$index}}" class="form-control" ng-hide="demandMaterielInput{{$index}}" ng-model="demandMateriel[$index].planCount"   ng-if="demandMateriel[$index].planCount==null"  ng-init="demandMateriel[$index].planCount=demandMateriel[$index].amount" ng-keyup="clearNoNum(demandMateriel[$index],'planCount' ,'amount')"   ><!--已注释内有验证采购数量不得大于需求数量  -->
                                     	<input style="padding:6px 3px" type="text"  name="buyCount{{$index}}" class="form-control" ng-hide="demandMaterielInput{{$index}}" ng-model="demandMateriel[$index].planCount"   ng-if="demandMateriel[$index].planCount!=null"   ng-keyup="clearNoNum(demandMateriel[$index],'planCount' ,'amount')"   >	
                                     		<p class="form-control-static" ng-show="demandMaterielShow{{$index}}"> {{_procurementPlanMateriel.planCount}} </p>
                                     		<!-- <p class="form-control-static" ng-hide="procurementPlanMaterielInput{{$index}}"> 当前库存：{{_procurementPlanMateriel.materiel.stockCount}} </p> -->
		                          </td>
		                          <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.completeCount==undefined?0:_procurementPlanMateriel.completeCount}} </p>
		                          </td>
		                       
		                          <td>  
		                          <div ng-hide="demandMaterielInput{{$index}}"  input-medium class="input-group date date-picker"
															 data-date-format="yyyy-mm-dd"
															data-date-viewmode="years">
															<input type="text" class="form-control" style="min-width: 110px;" readonly="" id="deliveryDate{{$index}}" ng-model="demandMateriel[$index].deliveryDate"    name="deliveryDate"   
																ng-change="setAllDeliveryDate(demandMateriel[$index] ,$index)" > <span class="input-group-btn">
																<button class="btn default " type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
                                     
                                     		<p class="form-control-static" ng-show="demandMaterielShow{{$index}}"> {{_procurementPlanMateriel.deliveryDate}} </p>
		                          </td>
		                        
		                          <td>  
		                          <input style="padding:6px 3px" type="text"  name="buyCount{{$index}}" class="form-control" ng-hide="demandMaterielInput{{$index}}" ng-model="demandMateriel[$index].deliveryAddress"  
		                           ng-if="$first"    ng-change="setAllDeliveryAddress(demandMateriel[$index] ,$index)" />
		                           <input style="padding:6px 3px" type="text"  name="buyCount{{$index}}" class="form-control" ng-hide="demandMaterielInput{{$index}}" ng-model="demandMateriel[$index].deliveryAddress"  
		                           ng-if="!$first"     />
                                     		<p class="form-control-static" ng-show="demandMaterielShow{{$index}}"> {{_procurementPlanMateriel.deliveryAddress}} </p>
		                          </td>
                                      <td>  
                                     		<p class="form-control-static"  style="color:#fcb95b" ng-if="_procurementPlanMateriel.status==undefined||_procurementPlanMateriel.status==0"> 待采购 </p>
                                     		<p class="form-control-static"  style="color:#fcb95b"  ng-if="_procurementPlanMateriel.status==1"> 已分解 </p>
                                     		<p class="form-control-static"  style="color:#fcb95b"  ng-if="_procurementPlanMateriel.status==2"> 部分入库 </p>
                                     		<p class="form-control-static"  style="color:green" ng-if="_procurementPlanMateriel.status==2"> 已入库</p>
		                          </td>
                                     <td>
                                     	<div style="width:100px">
                                     	<!-- <span ng-hide="demandMaterielInput{{$index}}">
                                      		&nbsp;&nbsp;&nbsp;&nbsp;
                                       	<a  ng-click="saveDemandMateriel(_procurementPlanMateriel,$index)" title="保存"><i class="fa fa-save"></i></a>
                                       	&nbsp;&nbsp;&nbsp;
                                       	<a  ng-click="canceDemandMateriel(_procurementPlanMateriel,$index)" title="取消"><i class="fa fa-undo"></i></a>
                                       </span> -->
                                       <span  ng-show="operation_o{{$index}}">
                                       	&nbsp;&nbsp;&nbsp;&nbsp;
                                       	<a    ng-hide="demandMaterielInput{{$index}}" ng-click="saveDemandMateriel(_procurementPlanMateriel,$index)" title="保存"><i class="fa fa-save"></i></a>
                                       	<a  ng-show="demandMaterielShow{{$index}}"  title="编辑" ng-click="editDemandMateriel(_procurementPlanMateriel)"><i class="fa fa-edit"></i></a>
                                       	&nbsp;&nbsp;&nbsp;
                                       	<a   title="删除" ng-click="deleteDemandMateriel(_procurementPlanMateriel)"><i class="fa fa-minus"></i></a>
                                      	</span>
                                      	</div>
                                      	<!-- <p class="form-control-static"> 分解采购 </p> -->
                                     </td>
                                 </tr>
                                 <tr>
								<th></th>
								<th>合计</th>
								<th>{{totalDemandCount()}}</th>
								<th></th>
								<th><!-- {{totalMaterielPlanCount()}} --></th>
								<th><!-- {{totalMaterielCount()}} --></th>
								<th>{{totalDemandMaterielCount()}}</th>
								<th>{{totalEndCount()}}</th>
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
			<div class="tab-pane fade " id="tab_1_4">
		<!-- 订单物料 start-->
         <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
              <button ng-click="releaseProcurementPlanMateriel()" type="button"  ng-show="procurementPlanMaterielShow" class="btn blue  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i>发布采购 </button>
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
                                 
                                 <th>
                                 
                                 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                        <input type="checkbox" class="group-checkable"  name="{{$index}}"   id="group-checkable2"  ng-click="selectAllMateriel('group-checkable2')" />
                                          <span></span>
                                    </label>
                                    </th>
								<th>物料编号</th>
								<th>物料名称</th>
								<th>规格型号</th>
								<th>单位</th>
								<th>台套</th>
								<th>单套用量</th>
								<th>需求数量</th>
								<th>采购数量</th>
								<th>选择供应商</th>
								<th>交付日期</th>
								<th>交付地址</th>
								<th  style="display:inline-block;">状态</th>
								<th><span style="display:inline-block;width:80px;">操作</span></th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr ng-repeat="_procurementPlanMateriel in procurementPlanMateriel track by $index" ng-mouseover="showOperation('procurementPlanMateriel',$index)" ng-mouseleave="hideOperation('procurementPlanMateriel',$index)"  repeat-done="repeatDone(this)">
		                          <td>
		                           <div   ng-hide="showCheckBoxForPpm{{$index}}"     ng-if="_procurementPlanMateriel.status==0||_procurementPlanMateriel.status==undefined" ><!--  (_procurementPlanMateriel.status==0||_procurementPlanMateriel.status==null)-->
		                                <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"   >
                                        <input type="checkbox"  class="group-checkable2"   id="{{$index}}"/>
                                        <span></span>
                                    </label>
                                    </div>
		                          </td>
		                          <td>
                                           <!-- <span ng-hide="procurementPlanMaterielInput{{$index}}"><a href="javascript：;" ng-click="addMateriel('single',$index)">{{_procurementPlanMateriel.materiel.materielNum}}</a></span> -->
		                                <p class="form-control-static" > {{_procurementPlanMateriel.materielNum==null?_procurementPlanMateriel.materiel.materielNum:_procurementPlanMateriel.materielNum}} </p>
		                          </td>
		                          <td>
	                                 	<p class="form-control-static" > {{_procurementPlanMateriel.materielName==null?_procurementPlanMateriel.materiel.materielName:_procurementPlanMateriel.materielName}} </p>
		                          </td>
		                           <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.specifications==null?_procurementPlanMateriel.materiel.specifications:_procurementPlanMateriel.specifications}}   </p>
		                          </td>
		                          <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.unit==null?_procurementPlanMateriel.materiel.unit:_procurementPlanMateriel.unit}} </p>
		                          </td>
		                          <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.sets==null?_procurementPlanMateriel.planCount:_procurementPlanMateriel.sets}} </p>
		                          </td>
		                            <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.singleDose}}</p>
		                          </td>
		                            <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.singleDose*(_procurementPlanMateriel.sets==null?_procurementPlanMateriel.planCount:_procurementPlanMateriel.sets)}} </p>
		                          </td>
		                          <td>  
		                          		<input style="padding:6px 3px"   type="text"  ng-if="_procurementPlanMateriel.status==0||_procurementPlanMateriel.status==undefined"  name="buyCount{{$index}}" class="form-control" ng-hide="procurementPlanMaterielInput{{$index}}" ng-model="_procurementPlanMateriel.buyCount"    ng-keyup="clearNoNum(procurementPlanMateriel[$index],'buyCount','planCount')"   /> 
                                     		<p class="form-control-static" ng-show="procurementPlanMaterielShow{{$index}}"> {{_procurementPlanMateriel.buyCount}} </p>
                                     		<!-- <p class="form-control-static" ng-hide="procurementPlanMaterielInput{{$index}}"> 当前库存：{{_procurementPlanMateriel.materiel.stockCount}} </p> -->
		                          </td>
		                          <td>  
		                          <div   ng-hide="procurementPlanMaterielInput{{$index}}"   ng-if="_procurementPlanMateriel.status==0||_procurementPlanMateriel.status==undefined">
		                          		<select name="supplier{{$index}}"     ng-if="$first"  ng-hide="procurementPlanMaterielInput{{$index}}"  ng-model="_procurementPlanMateriel.supplyComId" class="bs-select form-control procurementPlan" data-live-search="true"   data-size="8"
		                          		ng-change="changeSupplyName(_procurementPlanMateriel,'supplyComId')">
                                               <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}" >{{supplier.comName}}</option>
                                         </select>
                                         <select name="supplier{{$index}}"  ng-if="!$first"      ng-hide="procurementPlanMaterielInput{{$index}}"   ng-model="_procurementPlanMateriel.supplyComId" class="bs-select form-control procurementPlan" data-live-search="true"   data-size="8"
		                          		ng-change="changeSupplyName(_procurementPlanMateriel,'supplyComId')">
                                               <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}" >{{supplier.comName}}</option>
                                         </select>
                                         </div>
                                     	<p class="form-control-static" ng-show="procurementPlanMaterielShow{{$index}}"> {{_procurementPlanMateriel.supplyName}} </p>
		                          </td>
		                          <td>  
		                          		<input type="text"   name="deliveryDate{{$index}}"   ng-if="_procurementPlanMateriel.status==0||_procurementPlanMateriel.status==undefined"  class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="procurementPlanMaterielInput{{$index}}" ng-model="_procurementPlanMateriel.deliveryDate"   disabled="disabled">
                                     		<p class="form-control-static" ng-show="procurementPlanMaterielShow{{$index}}"> {{_procurementPlanMateriel.deliveryDate}} </p>
		                          </td>
		                       
		                          <td>  
		                          		<input style="padding:6px 3px" type="text"   ng-if="_procurementPlanMateriel.status==0||_procurementPlanMateriel.status==undefined"  name="deliveryAddress{{$index}}" class="form-control"   disabled="disabled" ng-hide="procurementPlanMaterielInput{{$index}}" ng-model="_procurementPlanMateriel.deliveryAddress"    >
                                     		<p class="form-control-static" ng-show="procurementPlanMaterielShow{{$index}}"> {{_procurementPlanMateriel.deliveryAddress}} </p>
		                          </td>
                                     <td >
                                     <p class="form-control-static"  style="color:#fcb95b" ng-if="_procurementPlanMateriel.status==undefined||_procurementPlanMateriel.status==0"> 待采购 </p>
                                     		<p class="form-control-static"  style="color:#fcb95b"  ng-if="_procurementPlanMateriel.status==1"> 已采购 </p>
                                     		</td>
                                     <td>
                                     	<div style="width:100px">
                                     	<!-- <span ng-hide="procurementPlanMaterielInput{{$index}}">
                                      		&nbsp;&nbsp;&nbsp;&nbsp;
                                       	<a  ng-click="saveProcurementPlanMateriel(_procurementPlanMateriel,$index)" title="保存"><i class="fa fa-save"></i></a>
                                       	&nbsp;&nbsp;&nbsp;
                                       	<a  ng-click="cancelProcurementPlanMateriel(_procurementPlanMateriel,$index)" title="取消"><i class="fa fa-undo"></i></a>
                                       </span> -->
                                       <span  ng-show="operation_o{{$index}}">
                                       	&nbsp;&nbsp;&nbsp;&nbsp;
                                       	<a    ng-hide="procurementPlanMaterielInput{{$index}}" ng-click="saveProcurementPlanMateriel(_procurementPlanMateriel,$index)" title="保存"><i class="fa fa-save"></i></a>
                                       	<a ng-show="procurementPlanMaterielShow{{$index}}"   title="编辑" ng-click="editProcurementPlanMateriel(_procurementPlanMateriel)"><i class="fa fa-edit"></i></a>
                                       	&nbsp;&nbsp;&nbsp;
                                       	<a  title="删除" ng-click="deleteProcurementPlanMateriel(_procurementPlanMateriel)"><i class="fa fa-minus"></i></a>
                                      	</span>
                                      	</div>
                                      	<!-- <a href="javascript:void(0);"    ng-click="viewProcurementPlan()"   ng-if="">发布采购</a> -->
                                      	
                                     </td>
                                 </tr>
                                 <tr>
								<th></th>
								<th>合计</th>
								<th>{{totalCount()}}</th>
								<th></th>
								<th></th>
								<th><!-- {{totalMaterielPlanCount()}} --></th>
								<th><!-- {{totalMaterielCount()}} --></th>
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
</div>
<jsp:include  page="selectMateriel.jsp"/> <!-- 选择基本物料 -->

<div id="saleOrderInfo" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">选择销售订单</h4>
			</div>
			<div class="modal-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_21">
					<thead>
						<tr>
							<th style="text-align: center"></th>
							<th> 销售订单号 </th>
                            <th> 采购商 </th>
                            <th> 销售数量 </th>
                            <th> 金额 </th>
                            <!-- <th> 配送 </th> -->
                            <th> 销售类型 </th>
                            <!-- <th> 关联销售合同 </th> -->
                            <th> 关联采购单 </th>
                            <th> 下单日期 </th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"
					class="btn dark btn-outline">取消</button>
				<button type="button" ng-click="confirmSelectOrder()" class="btn green">确定
				</button>
			</div>
		</div>
	</div>
</div>




