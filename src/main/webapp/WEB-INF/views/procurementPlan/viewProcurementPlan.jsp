<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- BEGIN PAGE HEADER-->


<div class="row" id="procurementPlanPrint">
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
					<jsp:include  page="viewProcurementPlanBase.jsp"/>
					
		            <div class="portlet-title">
			               <div class="tools">
			               <shiro:hasPermission name="procurementPlan:edit">
			               		<button ng-if="procurementPlan.status == 0" type="button" ng-click="procurementPlanGenerateBuy()"  class="btn blue btn-circle  btn-sm">发布采购</button>
			               	</shiro:hasPermission>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div>
      			</div>
			</div>
	</div>
</div>

