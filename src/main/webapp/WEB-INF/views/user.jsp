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
					<a href="javascript:;" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> Add
					</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-print"></i> Print
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-header-fixed"
					id="sample_2">
					<thead>
						<tr>
							<th>id</th>
							<th>用户名</th>
							<th>密码</th>
							<th>状态</th>
							<th>更新时间</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>id</th>
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
TableDatatablesFixedHeader.init();//页面元素载入结束后，装载datatables
</script>