<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- <div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="purchaseForecast">采购订单</a><i class="fa fa-angle-right"></i></li>
		<li><a ui-sref="purchaseForecast">采购预测</a></li>
	</ul>
</div> -->

<div class="row" >
	<div class="col-md-12">

		<script type="text/javascript">

		function MyCtrl($scope, $location) {
			  $scope.jumpToUrl = function(path) {
			 
				  $location.path(path);
			  };
			}
		
		</script>
		
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
			<div class="caption">
					<i class="fa fa-globe"></i>采购预测列表
				</div>
				<div class="actions" ng-controller='MyCtrl'>
					<%-- <shiro:hasPermission name="purchaseForecast:export">
						<label
							class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
							ng-click="exportPurchaseForecast()"> <i
							class="fa fa-file-excel-o"></i> 导出
						</label>
					</shiro:hasPermission> --%>
					
				</div>
			</div>

      
			<div class="portlet-body" >
				<table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_saleForecast">
					<thead>
						<tr>
							<th>
                                                 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                                     <input type="checkbox" class="group-checkable" id="example-select-all"/>
                                                     <span></span>
                                                 </label>
                                             </th>

							<th style="white-space: nowrap;">销售预测编号</th>
							<th style="white-space: nowrap;">物料名称</th>
							<th style="white-space: nowrap;">规格型号</th>
							<th style="white-space: nowrap;">数量</th>
							<th style="white-space: nowrap;">交付日期</th>
							<th style="white-space: nowrap;">交付地址</th>
							<th style="white-space: nowrap;">距交付</th>
							<th style="white-space: nowrap;">备注</th>
							<th style="white-space: nowrap;">预测生成日期</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- <script>
/* TableDatatablesFixedHeader.init();//页面元素载入结束后，装载datatables */
	$(document).ready(function(){
　　		
　　		
　　		
	});  
 
 

</script> -->