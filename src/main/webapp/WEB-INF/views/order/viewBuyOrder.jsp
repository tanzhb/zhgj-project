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
					<!-- <div class="portlet-title">
			               <div class="tools">
			               		<button type="button" ng-click="submitPage()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">提交审核</button>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div> -->
           		 <div class="portlet-body">
					<jsp:include  page="viewBuyOrderBase.jsp"/>
					<jsp:include  page="showUnderMateriel.jsp"/> <!-- 显示下级物料 -->
					
		            <div class="portlet-title">
			               <div class="tools"  ng-hide="hideAllBtn">
			               		<button type="button" ng-if = "buyOrder.status==0" ng-click="pingTaiSubmit()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">提交供方确认</button>
			               		<button type="button" ng-if = "buyOrder.status==77" ng-click="pingTaiSubmit()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">重新提交确认</button>
			               		<button type="button" ng-if = "buyOrder.status==77" ng-click="pingTaiConfirmed()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">确认</button>
			               		<!-- <button type="button" ng-if = "buyOrder.status==0||buyOrder.status==77" ng-click="submitBuyApply('view')" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">申请采购</button> -->
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div>
      			</div>
			</div>
	</div>
</div>

