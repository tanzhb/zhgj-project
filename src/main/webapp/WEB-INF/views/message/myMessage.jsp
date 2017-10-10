<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<style>
 .todo-comment-p{
 	position: absolute;
    top: 1px;
    display: block;
    right: 20px;
    font-size: 12px;
    color: #566e7c;
    border-color: #a2aeb5;
 }
</style>
<div class="row" >
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light ">
			<div class="portlet-title tabbable-line">
				<div class="caption">
					<i class="icon-user font-green"></i> <span
						class="caption-subject font-green bold uppercase">消息</span>
				</div>
				<ul class="nav nav-tabs" id="message_tab">
					<li class="active" ><a data-target="#portlet_tab2_1"  ng-click="businessMessageList()"
						data-toggle="tab">业务提醒</a></li>
					<li ><a data-target="#portlet_tab2_2" ng-click="systemMessageList()"
						data-toggle="tab">系统消息</a></li>
				</ul>
			</div>
			<div class="portlet-body" id="businessMessage">
				<div class="tab-content">
					<div class="tab-pane active" id="portlet_tab2_1">
						<!-- ng-repeat="message in messageList track by message.serialNum" -->
						<div class="row todo-container">
							<div class="todo-tasks-container" style="padding: 0px 20px;border:0px solid #ebf0f5;">
								<ul class="todo-tasks-content">
									<li class="todo-tasks-item" style="padding: 20px 0;" ng-repeat="message in messageList track by message.serialNum">
										<h4 class="todo-inline">
											<a data-toggle="modal" href="#todo-task-modal">{{delHtmlTag(message.context)}}</a>
										</h4>
										<p class="todo-inline todo-float-r">
											
										</p>
									</li>
								</ul>
							</div>
						</div>
						<div class="portlet-body dataTables_wrapper" id="businessMessage">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-5 col-sm-5">
										<div class="dataTables_info" id="businessMessage_info" role="status"></div>
									</div>
									<div class="col-md-7 col-sm-7">
										<div class="dataTables_paginate paging_bootstrap_full_number">
											<ul id="businessMessage_paginator"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="portlet_tab2_2">
						<div class="row todo-container">
							<div class="todo-tasks-container" style="padding: 0px 20px;border:0px solid #ebf0f5;">
								<ul class="todo-tasks-content">
									<li class="todo-tasks-item" style="padding: 20px 0;" ng-repeat="message in messageList track by message.serialNum">
										<h4 class="todo-inline">
											<a data-toggle="modal" href="#todo-task-modal">{{delHtmlTag(message.context)}}</a>
										</h4>
										<p class="todo-inline todo-float-r">
											
										</p>
									</li>
								</ul>
							</div>
						</div>
						<div class="portlet-body dataTables_wrapper" id="systemMessage">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-5 col-sm-5">
										<div class="dataTables_info" id="systemMessage_info" role="status"></div>
									</div>
									<div class="col-md-7 col-sm-7">
										<div class="dataTables_paginate paging_bootstrap_full_number">
											<ul id="systemMessage_paginator"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>

<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<script>
	// TableDatatablesManaged.init();
	//$('.date-picker').datepicker();
</script>
<!-- END MAIN JS -->
