<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<h3 class="page-title"> 物料信息
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="saleOrder">销售订单</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a>{{opration}}</a>
        </li>
    </ul>
    <div class="page-toolbar">
          <div class="btn-group pull-right">
              <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" onclick="printdiv('saleOrderPrint')"> 
              	<i class="fa fa-print"></i>
                  		打印
              </button>
              
          </div>
      </div>
</div>
<div class="row" id="saleOrderPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption">订单信息</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="save()" ng-hide="saleOrderInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancel()" type="button" ng-hide="saleOrderInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="edit()" type="button" ng-show="saleOrderShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                                 <!-- <a href="javascript:;" class="collapse"> </a> -->
                             </div>
                        </div>
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form action="#" id="form_sample_1"  class="form-horizontal">
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>销售订单号:</label>
                                  <div class="col-md-9">
                                  <input type="text" name="orderNum" class="form-control" ng-hide="saleOrderInput" ng-model="saleOrder.orderNum"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入销售订单号</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.orderNum}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">销售方:</label>
                                  <div class="col-md-9">
                                      <p class="form-control-static"> 中航能科 </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>销售类型:</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="orderType"  ng-hide="saleOrderInput" name="orderType"  ng-model="saleOrder.orderType" >
                                            <option value=""></option>
                                           	<option value="标准销售" >标准销售</option>
                                             <option value="委托销售" >委托销售</option>
                                             <option value="委托采购" >委托采购</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择销售类型</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.orderType}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">采购方:<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                  <input type="text" name="buyComId" class="form-control" ng-hide="saleOrderInput" ng-model="saleOrder.buyComId"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择采购商</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.buyComId}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">关联需求计划单号:</label>
                                  <div class="col-md-9">
                                  <input type="text"  class="form-control" ng-hide="saleOrderInput"   >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择需求计划单号</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">关联采购单号:</label>
                                  <div class="col-md-9">
                                  <input type="text"  class="form-control" ng-hide="saleOrderInput"   >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择采购单号</span>
                                      <p class="form-control-static" ng-show="saleOrderShow">  </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>服务模式:</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="serviceModel"  ng-hide="saleOrderInput" name="serviceModel"  ng-model="saleOrder.serviceModel" >
                                            <option value="无">无</option>
                                           	<option value="仓储服务" >仓储服务</option>
                                             <option value="仓储+垫资服务" >仓储+垫资服务</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择服务模式</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.serviceModel}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>结算条款:</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="settlementClause"  ng-hide="saleOrderInput" name="settlementClause"  ng-model="saleOrder.settlementClause" >
                                            <option value=""></option>
                                           	<option value="进销差" >进销差</option>
                                             <option value="服务费" >服务费</option>
                                             <option value="折扣折让" >折扣折让</option>
                                             <option value="红票" >红票</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择结算条款</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.settlementClause}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>提货方式:</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="deliveryMode"  ng-hide="saleOrderInput" name="deliveryMode"  ng-model="saleOrder.deliveryMode" >
                                            <option value=""></option>
                                           	<option value="仓库自提" >仓库自提</option>
                                             <option value="物料配送" >物料配送</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择提货方式</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.deliveryMode}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">下单日期:</label>
                                  <div class="col-md-9">
                                  <input type="text" name="orderDate" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="saleOrderInput" ng-model="saleOrder.orderDate"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择下单日期</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.orderDate}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">制单人:</label>
                                  <div class="col-md-9">
                                  <input type="text" name="maker" class="form-control" ng-hide="saleOrderInput" ng-model="saleOrder.maker"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入制单人</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.maker}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">审批人:</label>
                                  <div class="col-md-9">
                                  <input type="text"  class="form-control" ng-hide="saleOrderInput"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入审批人</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                  </div>
				</form>
             </div>   
             
             <!-- 供应商 start-->
          <div class="portlet-title">
               <div class="caption">物料信息</div>
               <div class="tools">
                  <button ng-click="addOrderMateriel()" type="button"  class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-edit"></i> 添加物料 </button>
                </div>
            </div>
           <div class="portlet-body form">
			     <form id="form_sample_5"   class="form-horizontal">
			         <div class="table-scrollable">
                          <table class="table table-bordered table-hover">
                              <thead>
                                  <tr>
									<th>物料编号</th>
									<th>物料名称</th>
									<th>规格型号</th>
									<th>单位</th>
									<th>供应商</th>
									<th>库存数量</th>
									<th>销售数量</th>
									<th>单价</th>
									<th>销售单价</th>
									<th>含税销售单价</th>
									<th>金额</th>
									<th>税额</th>
									<th>价税合计</th>
									<th>交付日期</th>
									<th>最晚交付日期</th>
									<th>交付/提货地点</th>
									<th style="width:100px;">操作</th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr ng-repeat="_orderMateriel in orderMateriel track by $index" ng-mouseover="showOperation('orderMateriel',$index)" ng-mouseleave="hideOperation('orderMateriel',$index)"  repeat-done="repeatDone()">
			                          <td>
                                            <span ng-hide="orderMaterielInput{{$index}}"><a href="javascript:;" ng-click="addMateriel('single',$index)">{{_orderMateriel.materiel.materielNum}}</a></span>
			                                <p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.materiel.materielNum}} </p>
			                          </td>
			                          <td>
		                                 	<p class="form-control-static" > {{_orderMateriel.materiel.materielName}} </p>
			                          </td>
			                           <td>
                                      		<p class="form-control-static" > {{_orderMateriel.materiel.specifications}} </p>
			                          </td>
			                          <td>
                                      		<p class="form-control-static" > {{_orderMateriel.materiel.unit}} </p>
			                          </td>
			                          <td>
                                      		<p class="form-control-static" > {{_orderMateriel.supplyMateriel.supply.comName}} </p>
			                          </td>
			                          <td>
                                      		<p class="form-control-static" > 库存数量... </p>
			                          </td>
			                          <td>  
			                          		<input type="text"  name="amount{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].amount"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.amount}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static" > {{_orderMateriel.materiel.unitPrice}} </p>
			                          </td>
			                          <td>  
			                          		<input type="text"  name="orderUnitPrice{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].orderUnitPrice"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.orderUnitPrice}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static"> {{_orderMateriel.orderUnitPrice?_orderMateriel.orderUnitPrice*17/100:0}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static" > {{_orderMateriel.orderUnitPrice&&_orderMateriel.amount?_orderMateriel.orderUnitPrice*_orderMateriel.amount:0}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static"> {{_orderMateriel.orderUnitPrice&&_orderMateriel.amount?_orderMateriel.orderUnitPrice*_orderMateriel.amount*17/100:0}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static"> {{_orderMateriel.orderUnitPrice&&_orderMateriel.amount?(_orderMateriel.orderUnitPrice*_orderMateriel.amount+_orderMateriel.orderUnitPrice*_orderMateriel.amount*17/100):0}} </p>
			                          </td>
			                          <td>  
			                          		<input type="text"  name="deliveryDate{{$index}}" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].deliveryDate"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.deliveryDate}} </p>
			                          </td>
			                          <td>  
			                          		<input type="text"  name="lastDeliveryDate{{$index}}" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].lastDeliveryDate"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.lastDeliveryDate}} </p>
			                          </td>
			                          <td>  
			                          		<input type="text"  name="deliveryAddress{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].deliveryAddress"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.deliveryAddress}} </p>
			                          </td>
                                      
                                      <td>
                                      	<span ng-hide="orderMaterielInput{{$index}}">
                                       		&nbsp;&nbsp;&nbsp;&nbsp;
                                        	<a  ng-click="saveOrderMateriel(_orderMateriel,$index)"><i class="fa fa-save"></i></a>
                                        	&nbsp;&nbsp;&nbsp;
                                        	<a  ng-click="cancelOrderMateriel(_orderMateriel,$index)"><i class="fa fa-undo"></i></a>
                                        </span>
                                        <span  ng-show="operation_o{{$index}}">
                                        	&nbsp;&nbsp;&nbsp;&nbsp;
                                        	<a ng-show="orderMaterielShow{{$index}}"   ng-click="editOrderMateriel(_orderMateriel)"><i class="fa fa-edit"></i></a>
                                        	&nbsp;&nbsp;&nbsp;
                                        	<a ng-show="orderMaterielShow{{$index}}"  ng-click="deleteOrderMateriel(_orderMateriel)"><i class="fa fa-minus"></i></a>
                                       	</span>
                                      </td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
                  </form>
          </div>
          <!-- 供应商 end-->
          </div>
      </div>
</div>
</div>
</div> 

<jsp:include  page="../demandPlan/selectMateriel.jsp"/>
