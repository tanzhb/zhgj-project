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
						class="caption-subject font-green bold uppercase">公告</span>
				</div>
				<ul class="nav nav-tabs" id="notice_tab">
					<li class="active" ><a data-target="#portlet_tab2_1"  ng-click="myNoticeList()"
						data-toggle="tab">最新公告</a></li>
					<li ><a data-target="#portlet_tab2_2" ng-click="notices()"
						data-toggle="tab">公告列表</a></li>
				</ul>
			</div>
			<div class="portlet-body" id="myNotice">
				<div class="tab-content">
					<div class="tab-pane active" id="portlet_tab2_1">
						<div class="row"
							ng-if="noticeList == null || noticeList.length == 0"
							align="center">暂无公告</div>
						<div class="row"
							ng-repeat="notice in noticeList track by notice.serialNum">
							<div class="col-md-12">
								<ul class="media-list">
									<li class="media" style="border-bottom: 1px #F2F2F2 solid;">
										<a class="pull-left" href="javascript:;"> <img
											class="todo-userpic" src="assets/pages/img/avatars/team1.jpg"
											width="27px" height="27px">
									</a>
										<div class="media-body todo-comment">
											<p class="todo-comment-p">
												<font ng-if="notice.readFlg==null">未读</font> <font
													ng-if="notice.readFlg==1">已读</font>
											</p>
											<p class="todo-comment-head">
												<span class="todo-comment-username">{{notice.updater}}</span>
												&nbsp; <span class="todo-comment-date">{{notice.relaseDate}}</span>
											</p>
											<p class="todo-text-color">
												<font style="font-weight: bolder;">{{notice.noticeTitle}}:
												</font> {{delHtmlTag(notice.context)}} &nbsp;
												<a href="javascript:;"
													ng-click="myNoticeView(notice.serialNum)">查看详情</a>&nbsp;&nbsp;
												<a
													href="javascript:;"
													ng-click="deleteMyNotice(notice.serialNum)">点击删除</a> <br>
											</p>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div class="portlet-body dataTables_wrapper">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-5 col-sm-5">
										<div class="dataTables_info" id="myNotice_info" role="status"></div>
									</div>
									<div class="col-md-7 col-sm-7">
										<div class="dataTables_paginate paging_bootstrap_full_number">
											<ul id="myNotice_paginator"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="portlet_tab2_2">




						<div class="portlet-body">
							<!-- <div class="table-responsive"> -->
							<div class="tabbable-custom">
								<ul class="nav nav-tabs" id="notice_tab">
									<li class="active"><a data-target="#tab_25_1"
										data-toggle="tab">公告管理</a></li>
									<li><a data-target="#tab_25_2" data-toggle="tab"
										ng-click="toDaiban()">待办<dbQuantity/></a></li>
									<li><a data-target="#tab_25_3" data-toggle="tab"
										ng-click="toYiban()">已办 <ybQuantity/></a></li>
								</ul>
								<div class="tab-content">
									<!-- tab1 START-->
									<div class="tab-pane active" id="tab_25_1">
										<!-- 公告管理列表START -->
										<div class="row">
											<div class="col-md-12">
												<div class="portlet light">
													<div class="portlet-title">
														<div class="caption"></div>
														<div class="actions"
															style="float: right; padding: 0px 10px 10px 0px;">
															<div class="btn-group btn-group-devided"
																data-toggle="buttons">
																<label
																	class="btn btn-transparent yellow btn-circle btn-sm"
																	ng-click="toApplyNotice()"> <i class="glyphicon glyphicon-play"></i>
																	申请
																</label> <label
																	class="btn btn-transparent green btn-circle btn-sm"
																	ng-click="toAddNotice()"> <i class="fa fa-plus"></i>
																	添加
																</label> <label
																	class="btn btn-transparent purple btn-circle btn-sm"
																	ng-click="toEditNotice()"> <i
																	class="fa fa-edit"></i> 修改
																</label> <label
																	class="btn btn-transparent red btn-circle btn-sm"
																	ng-click="deleteNoticeBatch()"> <i
																	class="fa fa-minus"></i> 删除
																</label>
															</div>
														</div>
													</div>
													<div class="portlet-body">
														<!-- <div class="table-responsive"> -->
														<table
															class="table table-striped table-bordered table-hover table-checkable order-column"
															id="noticeTable">
															<thead>
																<tr>
																	<th><label
																		class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
																			<input type="checkbox" class="group-checkable"
																			data-set="#noticeTable .checkboxes" /> <span></span>
																	</label></th>
																	<th>公告编号</th>
																	<th>提交日期</th>
																	<th>发布人</th>
																	<th>标题</th>
																	<th>接收对象</th>
																	<th>发布日期</th>
																	<th>状态</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
										<!-- 公告管理列表END -->
									</div>
									<!-- tab1 END -->

									<!-- tab2 START -->
									<div class="tab-pane" id="tab_25_2">
										<!-- 待办列表 -->
										<div class="row">
											<div class="col-md-12">
												<!-- BEGIN EXAMPLE TABLE PORTLET -->
												<div class="portlet light"
													style="padding: 0px 15px; box-shadow: 0px 0px; margin-bottom: 0px;">
													<div class="portlet-title">
														<div class="actions">
															<div class="btn-group btn-group-devided"
																data-toggle="buttons">
																<label class="btn default btn-sm"
																	ng-click="noticeAudit()"> 办理 </label>
															</div>
															<div class="btn-group btn-group-devided"
																data-toggle="buttons">
																<label class="btn default btn-sm"
																	ng-click="receiveNotice()"> 签收 </label>
															</div>
														</div>
														<div class="tools"></div>
													</div>
													<div class="portlet-body">
														<table
															class="table table-striped table-bordered table-hover table-checkable order-column"
															id="sample_n2">
															<thead>
																<tr>
																	<th style="text-align: center"><label
																		class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>
																			<input type='checkbox' class='checkboxes' value='1' />
																			<span></span>
																	</label></th>
																	<th>任务状态</th>
																	<th>单据类型</th>
																	<th>申请人</th>
																	<th>标题</th>
																	<th>当前节点</th>
																	<th>负责人</th>
																	<th>任务创建时间</th>
																	<th>流程状态</th>
																</tr>
															</thead>
															<tbody>

															</tbody>
														</table>
													</div>
												</div>
												<!-- END EXAMPLE TABLE PORTLET -->
											</div>
										</div>
										<!-- 待办列表 -->
									</div>
									<!-- tab2 END -->

									<!-- tab3 START -->
									<div class="tab-pane" id="tab_25_3">
										<div class="row">
											<div class="col-md-12">
												<!-- BEGIN EXAMPLE TABLE PORTLET -->
												<div class="portlet light"
													style="padding: 0px 15px; box-shadow: 0px 0px; margin-bottom: 0px;">
													<!-- <div class="portlet-title">
																	<div class="actions">
																	</div>
																</div> -->
													<div class="portlet-body">
														<table
															class="table table-striped table-bordered table-hover"
															id="ybnTable">
															<thead>
																<tr>
																	<th>单据类型</th>
																	<th>申请人</th>
																	<th>标题</th>
																	<th>任务开始时间</th>
																	<th>任务签收时间</th>
																	<th>任务结束时间</th>
																	<th>流程结束原因</th>
																	<th>流程版本号</th>
																	<th>操作</th>
																</tr>
															</thead>
															<tbody>

															</tbody>
														</table>
													</div>
												</div>
												<!-- END EXAMPLE TABLE PORTLET -->
											</div>
										</div>
									</div>
									<!-- tab3 END -->

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
