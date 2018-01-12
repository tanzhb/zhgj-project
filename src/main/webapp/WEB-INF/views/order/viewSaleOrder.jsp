<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<!-- <h3 class="page-title"> 订单信息
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="buyOrder">采购订单</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a>查看</a>
        </li>
    </ul>
    <div class="page-toolbar">
          <div class="btn-group pull-right">
              <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" onclick="printdiv('buyOrderPrint')"> 
              	<i class="fa fa-print"></i>
                  		打印
              </button>
              
          </div>
      </div>
</div> -->
<div class="row" id="buyOrderPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light ">
           		 <div class="portlet-body">
					<jsp:include  page="viewSaleOrderBase.jsp"/>
					
		            <div class="portlet-title">
			               <div class="tools"   ng-hide="hideAllBtn">
			               		<button ng-if="saleOrder.status==55" type="button" ng-click="submitPage()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">接收</button>
			               		<button ng-if="saleOrder.orderType =='委托销售'&&(saleOrder.orderSerial ==''||saleOrder.orderSerial ==null)" type="button" ng-click="saleGenerateBuy()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">分解采购</button>
			               		<button type="button" ng-if = "saleOrder.status==0||saleOrder.status==77" ng-click="submitSaleApply('view')" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">申请销售</button>
			               		<button ng-if="saleOrder.orderType =='自主销售'&&(saleOrder.orderSerial ==''||saleOrder.orderSerial ==null)" type="button" ng-click="saleGenerateProcurementPlan()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">分解采购</button> 
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div>
      			</div>
			</div>
	</div>
</div>

