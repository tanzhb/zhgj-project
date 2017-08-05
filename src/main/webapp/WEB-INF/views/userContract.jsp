<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="row" ng-controller="ContractController as uTrl">
	<div class="col-md-12">

		<script type="text/javascript">

		function MyCtrl($scope, $location) {
			  $scope.jumpToUrl = function(path) {
			 
				  $location.path(path);
			  };
			}
		
		</script>
		
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box red">
			<div class="portlet-title">
				<!-- <div class="caption">
					<i class="fa fa-globe"></i>Header & Footer Fixed
				</div>
				<div class="actions" ng-controller='MyCtrl'>
					<a href="javascript:;" ng-click="jumpToUrl('addUserContract')"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-plus"></i> 新增合同
					</a> <a href="javascript:;" ng-click="del()"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</a>
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
				</div> -->
				<div class="actions" ng-controller='MyCtrl'>
					<a href="javascript:;" ng-click="jumpToUrl('addUserContract')"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-plus"></i> 新增合同
					</a> <a href="javascript:;" ng-click="del()"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</a>
					<div class="btn-group">
						<a class="btn btn-default btn-outline btn-circle"
							href="javascript:;" data-toggle="dropdown"> <i
							class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
							class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right" id="sample_3_tools">
							<li><a href="javascript:;" data-action="0"
								class="tool-action"> <i class="icon-printer"></i> Print
							</a></li>
							<li><a href="javascript:;" data-action="1"
								class="tool-action"> <i class="icon-check"></i> Copy
							</a></li>
							<li><a href="javascript:;" data-action="2"
								class="tool-action"> <i class="icon-doc"></i> PDF
							</a></li>
							<li><a href="javascript:;" data-action="3"
								class="tool-action"> <i class="icon-paper-clip"></i> Excel
							</a></li>
							<li><a href="javascript:;" data-action="4"
								class="tool-action"> <i class="icon-cloud-upload"></i> CSV
							</a></li>
							<li class="divider"></li>
							<li><a href="javascript:;" data-action="5"
								class="tool-action"> <i class="icon-refresh"></i> Reload
							</a></li>
							</li>
						</ul>
					</div>
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

			<div class="portlet-body" >
				<table
					class="table table-striped table-bordered table-hover table-header-fixed"
					id="sample_2">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>

							<th style="white-space: nowrap;">合同编号</th>
							<th style="white-space: nowrap;">合同类型</th>
							<th style="white-space: nowrap;">服务模式</th>
							<th style="white-space: nowrap;">结算条款</th>
							<th style="white-space: nowrap;">开始日期</th>
							<th style="white-space: nowrap;"></th>
						</tr>
					</thead>
					<tfoot>
						<tr >
							<th style="white-space: nowrap;"></th>
							<th style="white-space: nowrap;">合同编号</th>
							<th style="white-space: nowrap;">合同类型</th>
							<th style="white-space: nowrap;">服务模式</th>
							<th style="white-space: nowrap;">结算条款</th>
							<th style="white-space: nowrap;">开始日期</th>
							<th style="white-space: nowrap;"></th>
						</tr>
					</tfoot>
					<tbody>

						<!-- <tr ng-repeat="user in uTrl.users">
			                <td>{{ user.id }}</td>
			                <td>{{ user.username }}</td>
			                <td>{{ user.password }}</td>
			                <td>{{ user.state }}</td>
			                <td>{{ user.createTime }}</td>			               
			            </tr>
						 -->
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>

<script>
/* TableDatatablesFixedHeader.init();//页面元素载入结束后，装载datatables */
	$(document).ready(function(){
　　		
　　		
　　		
	});  
 
 

</script>