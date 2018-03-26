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
				<label class="btn btn-transparent blue btn-circle btn-sm"
							ng-click="newProcurementPlan()">  生成采购计划
						</label>
					<shiro:hasPermission name="purchaseForecast:delete">
						<label class="btn btn-transparent red btn-circle btn-sm"
							ng-click="del()"> <i class="fa fa-minus"></i> 删除
						</label>
					</shiro:hasPermission>
					<shiro:hasPermission name="purchaseForecast:export">
						<label
							class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
							ng-click="exportPurchaseForecast()"> <i
							class="fa fa-file-excel-o"></i> 导出
						</label>
					</shiro:hasPermission>
					<!-- <a href="javascript:;" ng-click="del()"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</a> -->
					
					<!-- <div class="btn-group">
						<a class="btn btn-default btn-outline btn-circle"
							href="javascript:;" data-toggle="dropdown"> <i
							class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
							class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right" id="sample_3_tools">
							<li><a href="javascript:;" data-action="1"
								class="tool-action" ng-click="exportPurchaseForecast()"> <i class="fa fa-file-excel-o"></i> 导出
							</a></li>
						</ul>
					</div> -->
				</div>
			</div>

            <div id="delUsersModal" class="modal fade" tabindex="-1"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选条目?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDel()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
<div id="confirmModal" class="modal fade" tabindex="-1"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否确认生成采购计划?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmNewProcurementPlan()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<div class="portlet-body" >
				<table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_2">
					<thead>
						<tr>
							<th>
                                                 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                                     <input type="checkbox" class="group-checkable" id="example-select-all"/>
                                                     <span></span>
                                                 </label>
                                             </th>

							<th style="white-space: nowrap;">采购预测编号</th>
							<th style="white-space: nowrap;">物料名称</th>
							<th style="white-space: nowrap;">规格型号</th>
							<th style="white-space: nowrap;">数量</th>
							<th style="white-space: nowrap;">使用客户</th>
							<th style="white-space: nowrap;">交付日期</th>
							<th style="white-space: nowrap;">交付地点</th>
							<th style="white-space: nowrap;">距交付</th>
							<th style="white-space: nowrap;">发布人</th>
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