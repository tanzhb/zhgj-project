<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="row" ng-controller="UserController as uTrl">
	<div class="col-md-12">

		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box red">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Header & Footer Fixed
				</div>
				<div class="actions">
					<a href="javascript:;" data-target="#addUserModal"
						data-toggle="modal" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</a> <a href="javascript:;" data-target="#delUsersModal"
						data-toggle="modal" 
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


			<!-- 添加用户modal 开始 -->
			<div id="addUserModal" class="modal fade" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">添加用户</h4>
						</div>
						<div class="modal-body form">
							<form action="#" class="form-horizontal form-row-seperated">
								<div class="form-group">
									<label class="col-sm-4 control-label">用户名</label>
									<div class="col-sm-6">
										<div class="input-group">
											<span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> <input type="text" id="typeahead_example_modal_1"
												ng-model="user.username" name="typeahead_example_modal_1"
												class="form-control" />
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">密码</label>
									<div class="col-sm-6">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-search"></i>
											</span> <input type="text" id="typeahead_example_modal_2"
												ng-model="user.password" name="typeahead_example_modal_2"
												class="form-control" />
										</div>
										
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" ng-click="saveUser()"
								class="btn btn-primary">
								<i class="fa fa-check"></i> 保存
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 添加用户modal 结束 -->

			<!-- 删除用户modal 开始 -->
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
							<p>是否删除已选用户?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="del()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除用户modal 结束 -->


			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-header-fixed"
					id="sample_2">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>

							<th>用户名</th>
							<th>密码</th>
							<th>状态</th>
							<th>更新时间</th>
						</tr>
					</thead>
					<tfoot>
						<tr>

							<th></th>
							<th>用户名</th>
							<th>密码</th>
							<th>状态</th>
							<th>更新时间</th>
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
	//TableDatatablesFixedHeader.init();//页面元素载入结束后，装载datatables
</script>